import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * DataSource.java - Abstract class for implementing new data sources.
 *
 * @author James
 */
public abstract class DataSource {

    protected static final Logger  log = Logger.getLogger("Minecraft");
    protected List<Group>          groups = new ArrayList<Group>();
    protected List<Kit>            kits = new ArrayList<Kit>();
    protected List<Warp>           homes = new ArrayList<Warp>();
    protected List<Warp>           warps = new ArrayList<Warp>();
    protected List<Ban>            bans = new ArrayList<Ban>();
    protected List<String>         mutedPlayers = new ArrayList<String>();
    protected Map<String, Integer> items = new HashMap<String, Integer>();
    protected List<Integer>        enderBlocks = new ArrayList<Integer>();
    protected List<Integer>        antiXRayBlocks = new ArrayList<Integer>();
    protected OMinecraftServer     server;
    protected final Object         groupLock = new Object(), kitLock = new Object(), banLock = new Object(), homeLock = new Object();
    protected final Object         warpLock = new Object(), itemLock = new Object(), enderBlocksLock = new Object(), antiXRayBlocksLock = new Object();

    /**
     * Initializes the data source
     */
    abstract public void initialize();

    /**
     * Loads all groups
     */
    abstract public void loadGroups();

    /**
     * Loads all kits
     */
    abstract public void loadKits();

    /**
     * Loads all homes
     */
    abstract public void loadHomes();

    /**
     * Loads all warps
     */
    abstract public void loadWarps();

    /**
     * Loads all items
     */
    abstract public void loadItems();

    /**
     * Loads the ban list
     */
    abstract public void loadBanList();

    /**
     * Loads the list of muted players
     */
    abstract public void loadMutedPlayers();

    /**
     * Loads the enderman blocks list
     */
    abstract public void loadEnderBlocks();

    /**
     * Loads the anti xray blocks list
     */
    abstract public void loadAntiXRayBlocks();

    /**
     * Adds user to the list
     *
     * @param player
     */
    abstract public void addPlayer(Player player);

    /**
     * Modifies the provided user
     *
     * @param player
     */
    abstract public void modifyPlayer(Player player);

    /**
     * Checks to see if the specified player exists
     *
     * @param player
     * @return true if player exists
     */
    abstract public boolean doesPlayerExist(String player);

    /**
     * Returns specified user
     *
     * @param name
     * @return user
     */
    abstract public Player getPlayer(String name);

    /**
     * Checks to see if the specified group exists
     *
     * @param groupName
     * @return true if group exists
     */
    public boolean doesGroupExist(String groupName) {
        for (Group group : groups) {
            if (group.Name.equals(groupName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds specified group to the list of groups
     *
     * @param group
     */
    abstract public void addGroup(Group group);

    /**
     * Modifies group
     *
     * @param group
     */
    abstract public void modifyGroup(Group group);

    /**
     * Returns specified group
     *
     * @param name
     * @return group
     */
    public Group getGroup(String name) {
        synchronized (groupLock) {
            for (Group group : groups) {
                if (group.Name.equalsIgnoreCase(name)) {
                    return group;
                }
            }
        }

        if (!name.equals("")) {
            log.log(Level.INFO, "Unable to find group '" + name + "'. Are you sure you have that group?");
        }

        return null;
    }

    /**
     * Returns the default group
     *
     * @return default group
     */
    public Group getDefaultGroup() {
        synchronized (groupLock) {
            for (Group group : groups) {
                if (group.DefaultGroup) {
                    return group;
                }
            }
        }
        return null;
    }

    /**
     * Adds kit to list of kits
     *
     * @param kit
     */
    abstract public void addKit(Kit kit);

    /**
     * Modifies kit
     *
     * @param kit
     */
    abstract public void modifyKit(Kit kit);

    /**
     * Returns specified kit
     *
     * @param name
     * @return kit
     */
    public Kit getKit(String name) {
        synchronized (kitLock) {
            for (Kit kit : kits) {
                if (kit.Name.equalsIgnoreCase(name)) {
                    return kit;
                }
            }
        }
        return null;
    }

    /**
     * Returns true if there are any kits
     *
     * @return true if there are kits
     */
    public boolean hasKits() {
        synchronized (kitLock) {
            return kits.size() > 0;
        }
    }

    /**
     * Returns a list of all kits names separated by commas
     *
     * @param player
     * @return string list of kits
     */
    public String getKitNames(Player player) {
        StringBuilder builder = new StringBuilder();

        builder.append(""); // incaseofnull

        synchronized (kitLock) {
            for (Kit kit : kits) {
                if (player.isInGroup(kit.Group) || kit.Group.equals("")) {
                    builder.append(kit.Name).append(" ");
                }
            }
        }

        return builder.toString();
    }

    /**
     * Adds home to list of homes
     *
     * @param home
     */
    abstract public void addHome(Warp home);

    /**
     * Modifies home
     *
     * @param home
     */
    abstract public void changeHome(Warp home);

    /**
     * Returns specified home
     *
     * @param name
     * @return home
     */
    public Warp getHome(String name) {
        synchronized (homeLock) {
            for (Warp home : homes) {
                if (home.Name.equalsIgnoreCase(name)) {
                    return home;
                }
            }
        }
        return null;
    }

    /**
     * Adds warp to list of warps
     *
     * @param warp
     */
    abstract public void addWarp(Warp warp);

    /**
     * Modifies warp
     *
     * @param warp
     */
    abstract public void changeWarp(Warp warp);

    /**
     * Removes warp from list of warps
     *
     * @param warp
     */
    abstract public void removeWarp(Warp warp);

    /**
     * Returns specified warp
     *
     * @param name
     * @return warp
     */
    public Warp getWarp(String name) {
        synchronized (warpLock) {
            for (Warp warp : warps) {
                if (warp.Name.equalsIgnoreCase(name)) {
                    return warp;
                }
            }
        }
        return null;
    }

    /**
     * Returns true if there are any warps
     *
     * @return true if there are warps
     */
    public boolean hasWarps() {
        synchronized (warpLock) {
            return warps.size() > 0;
        }
    }

    /**
     * Returns a string containing all warp names the player has access to
     *
     * @param player
     * @return string list of warps
     */
    public String getWarpNames(Player player) {
        StringBuilder builder = new StringBuilder();

        builder.append(""); // incaseofnull

        synchronized (warpLock) {
            for (Warp warp : warps) {
                if (player.isInGroup(warp.Group) || warp.Group.equals("")) {
                    builder.append(warp.Name).append(" ");
                }
            }
        }

        return builder.toString();
    }

    /**
     * Get a List<String> of muted players
     * @return List<String>
     */
    public List<String> getMutedPlayers() {
        return mutedPlayers;
    }

    /**
     * Check if a player is on the mute list and will therefore be
     * automatically muted on login
     * @param name
     * @return true if player is on the mute list, false otherwise
     */
    public boolean isPlayerOnMuteList(String name) {
        if(mutedPlayers.contains(name)) {
            return true;
        }
        return false;
    }

    /**
     * Sets a player on the list of muted people.
     * @param name
     */
    abstract public void setPlayerToMuteList(String name);

    /**
     * Removes a player from the list of muted people
     * @param name
     */
    abstract public void removePlayerFromMuteList(String name);

    /**
     * Returns item id corresponding to item name
     *
     * @param name
     * @return item id
     */
    public int getItem(String name) {
        synchronized (itemLock) {
            if (items.containsKey(name)) {
                return items.get(name);
            }
        }
        return 0;
    }

    /**
     * Returns the name of the item corresponding to the ID
     *
     * @param id
     *            id of item
     * @return name of item
     */
    public String getItem(int id) {
        synchronized (itemLock) {
            for (String name : items.keySet()) {
                if (items.get(name) == id) {
                    return name;
                }
            }
        }
        return String.valueOf(id);
    }

    /**
     * Returns an unmodifiable map of items
     *
     * @return unmodifiable map of items
     */
    public Map<String, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    /**
     * Adds player to whitelist
     *
     * @param name
     */
    abstract public void addToWhitelist(String name);

    /**
     * Removes player from whitelist
     *
     * @param name
     */
    abstract public void removeFromWhitelist(String name);

    /**
     * Returns true if whitelist is enabled
     *
     * @return true if whitelist is enabled
     * @deprecated use etc.getInstance().isWhitelistEnabled() instead
     */
    @Deprecated
    public boolean hasWhitelist() {
        return etc.getInstance().isWhitelistEnabled();
    }

    /**
     * Returns true if the player is on the whitelist
     *
     * @param user
     * @return true if player is on whitelist
     */
    abstract public boolean isUserOnWhitelist(String user);

    /**
     * Adds or modifies specified ban
     *
     * @param ban
     *            Ban to add or modify
     */
    abstract public void addBan(Ban ban);

    /**
     * Checks to see if this player or IP is on the ban list
     *
     * @param player
     *            Player name
     * @param ip
     *            IP Address
     * @return true if either name or IP is on the ban list
     */
    public boolean isOnBanList(String player, String ip) {
        synchronized (banLock) {
            for (Ban ban : bans) {
                if ((ban.getName().equalsIgnoreCase(player)
                        || ban.getIp().equalsIgnoreCase(ip))
                        && (ban.getTimestamp() == -1
                        || ban.getTimestamp()
                                > (int) (System.currentTimeMillis() / 1000))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Retrieves the ban details
     *
     * @param player
     *            Player name
     * @param ip
     *            IP Address
     * @return the ban
     */
    public Ban getBan(String player, String ip) {
        synchronized (banLock) {
            for (Ban ban : bans) {
                if ((ban.getName().equalsIgnoreCase(player)
                        || ban.getIp().equalsIgnoreCase(ip))
                        && (ban.getTimestamp() == -1
                        || ban.getTimestamp()
                                > (int) (System.currentTimeMillis() / 1000))) {
                    return ban;
                }
            }
        }
        return null;
    }

    /**
     * Adds player to reservelist
     *
     * @param name
     */
    abstract public void addToReserveList(String name);

    /**
     * Removes player from reservelist
     *
     * @param name
     */
    abstract public void removeFromReserveList(String name);

    /**
     * Returns true if player is on reservelist
     *
     * @param user
     * @return true if player is on reserve list
     */
    abstract public boolean isUserOnReserveList(String user);

    /**
     * Returns a <tt>List</tt> containing {@link Group}s.
     * Please note: This is a clone of the actual list. To modify groups, use
     * {@link #modifyGroup(Group)}.
     * @return a <tt>List&lt;Group&gt;</tt> containing all the groups.
     */
    public List<Group> getGroupList() {
        return new ArrayList<Group>(this.groups);
    }

    /**
     * Retrieves the list of blocks the anti xray will hide
     *
     * @return the list of anti xray blocks
     */
    public List<Integer> getAntiXRayBlocks() {
        synchronized (antiXRayBlocksLock) {
            return antiXRayBlocks;
        }
    }

    /**
     * Expires the specified ban. This will just set the timestamp to now.
     * @param ban The ban to expire.
     */
    abstract public void expireBan(Ban ban);

    /**
     * Returns a <tt>List</tt> containing {@link Ban}s.
     * Please note: This is a clone of the actual list. To remove/add bans,
     * use the appropriate methods.
     * @return a <tt>List&lt;Ban&gt;</tt> containing all the bans.
     */
    public List<Ban> getBans() {
        return new ArrayList<Ban>(this.bans);
    }

    public Player getDefaultGroupPlayer() {
        Player player = new Player();
        player.setGroups(new String[]{etc.getDataSource().getDefaultGroup().Name});
        return player;
    }
}
