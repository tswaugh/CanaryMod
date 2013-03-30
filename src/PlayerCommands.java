import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@SuppressWarnings("LoggerStringConcat")
public class PlayerCommands extends CommandHandler {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static PlayerCommands instance;

    public PlayerCommands() {
        this.addAll(this.getClass());
    }

    /**
     * Performs a lookup for a command of the given name and executes it if
     * found. Returns false if command not found.
     *
     * @param command The command to run
     * @param caller The {@link MessageReceiver} to send messages back to
     * (assumed to be the caller)
     * @param args The arguments to {@code command}
     * @return true if {@code command} was found, false otherwise
     */
    public static boolean parsePlayerCommand(MessageReceiver caller, String command, String[] args) {
        BaseCommand cmd = instance.getCommand(command);

        if (cmd != null) {
            cmd.parseCommand(caller, args);
            // Inform caller a matching command was found.
            return true;
        }
        return false;
    }

    /**
     * Returns the <tt>PlayerCommands</tt> instance.
     * @return the <tt>PlayerCommands</tt> as used by the server.
     */
    public static PlayerCommands getInstance() {
        return instance;
    }

    @Command
    public static final BaseCommand help = new BaseCommand("<Page|Pattern> - Shows a list of commands. 7 per page.") {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            // Meh, not the greatest way, but not the worst either.
            List<String> availableCommands = new ArrayList<String>();

            for (Entry<String, String> entry : etc.getInstance().getCommands().entrySet()) {
                if (!(caller instanceof Player) || ((Player) caller).canUseCommand(entry.getKey())) {
                    if (entry.getKey().equals("/kit") && !etc.getDataSource().hasKits()) {
                        continue;
                    }
                    if (entry.getKey().equals("/listwarps") && !etc.getDataSource().hasWarps()) {
                        continue;
                    }

                    availableCommands.add(entry.getKey() + " " + entry.getValue());
                }
            }

            caller.notify(Colors.Blue + "Available commands (" + (args.length > 1 ? (args[1].matches("\\d+") ? "Page " + args[1] + " of " + (availableCommands.size() - 1) / 7 : "Matching " + etc.combineSplit(1, args, " ")) : "Page 1 of " + ((availableCommands.size() - 1) / 7)) + ") <> = required [] = optional:");
            if (args.length > 1) {
                if (args[1].matches("\\d+")) {
                    try {
                        int amount = Integer.parseInt(args[1]);

                        if (amount > 0) {
                            amount = (amount - 1) * 7;
                        } else {
                            amount = 0;
                        }

                        for (int i = amount; i < amount + 7; i++) {
                            if (availableCommands.size() > i) {
                                caller.notify(availableCommands.get(i));
                            }
                        }
                    } catch (NumberFormatException ex) {
                        caller.notify("Not a valid page number.");
                    }
                } else {
                    try {
                        int count = 0;
                        Pattern p = Pattern.compile(etc.combineSplit(1, args, " "));

                        for (String command : availableCommands) {
                            if (p.matcher(command).find()) {
                                caller.notify(command);
                                count += 1;
                            }
                            if (count > 6) {
                                break;
                            }
                        }
                        if (count == 0) {
                            caller.notify("No matches found");
                        }
                    } catch (java.util.regex.PatternSyntaxException e) {
                        caller.notify("Invalid pattern.");
                    }
                }
            } else {
                for (int i = 0; i < 7; i++) {
                    if (availableCommands.size() > i) {
                        caller.notify(availableCommands.get(i));
                    }
                }
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            return null;
        }
    };
    @Command
    public static final BaseCommand mute = new BaseCommand("<Player> - Mutes the player", "Correct usage is: /mute <player>", 2, 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            Player player = etc.getServer().matchPlayer(args[1]);

            if (player != null) {
                if (player.toggleMute()) {
                    //df: adding to mutelist
                    etc.getDataSource().setPlayerToMuteList(player.getName());
                    caller.notify("Player "+Colors.Yellow+player.getName()+Colors.Rose+" was muted");
                    player.notify("You have been temporarily muted");
                } else {
                    //df: removing from mute list
                    etc.getDataSource().removePlayerFromMuteList(player.getName());
                    caller.notify("Player "+Colors.Yellow+player.getName()+Colors.Rose+" was unmuted");
                    player.notify("You have been unmuted");
                }
            } else {
                caller.notify("Can't find player "+Colors.Yellow+args[1]);
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            String[] split = currentText.split(" ", -1);
            if (split.length != 2) {
                return null;
            }

            return etc.autoCompleteNames(split[1]);
        }
    };
    @Command({"tell", "msg", "m"})
    public static final BaseCommand tell = new BaseCommand("<Player> <Message> - Sends a message to player", "Correct usage is: /msg <player> <message>", 3) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            if ((caller instanceof Player) && ((Player) caller).isMuted()) {
                caller.notify("You are currently muted.");
                return;
            }

            Player player = etc.getServer().matchPlayer(args[1]);

            if (player != null) {
                if (player.getName().equals(caller.getName())) {
                    caller.notify("You can't message yourself!");
                    return;
                }

                player.sendMessage("(MSG) " + (caller instanceof Player ? ((Player) caller).getColor() : "") + "<" + caller.getName() + "> " + Colors.White + etc.combineSplit(2, args, " "));
                caller.notify(Colors.White + "(MSG) " + "<" + (caller instanceof Player ? ((Player) caller).getColor() : "") + caller.getName() + Colors.White + "> " + Colors.White + etc.combineSplit(2, args, " "));
            } else {
                caller.notify("Couldn't find player " + args[1]);
            }
        }
    };
    @Command
    public static final BaseCommand kit = new BaseCommand("<Kit> - Gives a kit. To get a list of kits type /kit", "Available kits (overridden)", 2, 3) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            if (!(caller instanceof Player)) {
                return;
            }
            Player toGive = (Player) caller;
            Player player = toGive;

            if (args.length > 2) {
                if (!player.canIgnoreRestrictions()) {
                    onBadSyntax(caller, args);
                } else {
                    toGive = etc.getServer().matchPlayer(args[2]);
                }
            }

            Kit kit = etc.getDataSource().getKit(args[1]);

            if (toGive != null) {
                if (kit != null) {
                    if (!player.isInGroup(kit.Group) && !kit.Group.equals("")) {
                        caller.notify("That kit does not exist.");
                    } else if (player.getOnlyOneUseKits().contains(kit.Name)) {
                        caller.notify("You can only get this kit once per login.");
                    } else if (!player.canUseCooldownKit(kit)) {
                        caller.notify("You can't get this kit again for a while.");
                    } else {
                        if (!((Player) caller).canIgnoreRestrictions()) {
                            if (kit.Delay >= 0) {
                                player.addCooldownKit(kit, kit.Delay);
                            } else {
                                player.getOnlyOneUseKits().add(kit.Name);
                            }
                        }

                        log.info(caller.getName() + " got a kit!");
                        toGive.notify("Enjoy this kit!");
                        for (Entry<String, Integer> entry : kit.IDs.entrySet()) {
                            try {
                                int itemId;

                                try {
                                    itemId = Integer.parseInt(entry.getKey());
                                } catch (NumberFormatException n) {
                                    itemId = etc.getDataSource().getItem(entry.getKey());
                                }

                                toGive.giveItem(itemId, entry.getValue());
                            } catch (Exception e1) {
                                log.info("Got an exception while giving out a kit (Kit name \"" + kit.Name + "\"). Are you sure all the Ids are numbers?");
                                caller.notify("The server encountered a problem while giving the kit :(");
                            }
                        }
                    }
                } else {
                    caller.notify("That kit does not exist.");
                }
            } else {
                caller.notify("That user does not exist.");
            }
        }

        @Override
        public void onBadSyntax(MessageReceiver caller, String[] args) {
            if (caller instanceof Player) {
                caller.notify("Available kits" + Colors.White + ": " + etc.getDataSource().getKitNames((Player) caller));
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            if (!(caller instanceof Player)) {
                return null;
            }
            if (currentText.split(" ", -1).length == 2) {
                return etc.autoComplete(currentText, etc.getDataSource().getKitNames((Player) caller));
            }
            return super.autoComplete(caller, currentText);
        }
    };
    @Command
    public static final BaseCommand tp = new BaseCommand("<Player> - Teleports to player.", "Correct usage is: /tp <player>", 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            if (!(caller instanceof Player)) {
                return;
            }

            Player player = etc.getServer().matchPlayer(args[1]);

            if (player == null) {
                caller.notify("Can't find user " + args[1] + ".");
                return;
            }
            if (!player.getWorld().equals(((Player) caller).getWorld()) && !((Player) caller).canIgnoreRestrictions()) {
                caller.notify("That player is in another world.");
                return;
            }
            if (caller.equals(player)) {
                caller.notify("You're already here!");
                return;
            }

            log.info(caller.getName() + " teleported to " + player.getName());
            ((Player) caller).teleportTo(player);
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            String[] split = currentText.split(" ", -1);
            if (split.length != 2) {
                return null;
            }

            return etc.autoCompleteNames(split[1]);
        }
    };
    @Command({"tphere", "s"})
    public static final BaseCommand tphere = new BaseCommand("<Player> - Teleports the player to you", "Correct usage is: /tphere <player>", 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            if (!(caller instanceof Player)) {
                return;
            }

            Player player = etc.getServer().matchPlayer(args[1]);

            if (player == null) {
                caller.notify("Can't find user " + args[1] + ".");
                return;
            }

            if (!player.getWorld().equals(((Player) caller).getWorld()) && !((Player) caller).canIgnoreRestrictions()) {
                caller.notify("That player is in another world.");
                return;
            }
            if (caller.equals(player)) {
                caller.notify("Wow look at that! You teleported yourself to yourself!");
                return;
            }
            log.info(caller.getName() + " teleported " + player.getName() + " to their self.");
            player.teleportTo((Player) caller);
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            String[] split = currentText.split(" ", -1);
            if (split.length != 2) {
                return null;
            }

            return etc.autoCompleteNames(split[1]);
        }
    };
    @Command({"playerlist", "who"})
    public static final BaseCommand playerlist = new BaseCommand("- Shows a list of players") {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            caller.notify("Player list (" + etc.getServer().getPlayerList().size() + "/" + etc.getInstance().getPlayerLimit() + "): " + Colors.White + etc.getServer().getPlayerNames());
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            return null;
        }
    };
    @Command({"item", "i", "give"})
    public static final BaseCommand item = new BaseCommand("<ID> [Amount] [Damage] [Player] - Gives items", "Correct usage is: /item <itemid> [amount] [damage] [player]", 2, 5) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            if (!(caller instanceof Player)) {
                return;
            }
            Player player = (Player) caller;
            Player toGive = (Player) caller;
            int itemId = 0, amount = 1, damage = 0;

            try {
                if (args.length > 1) {
                    if (args[1].matches("\\d+")) {
                        itemId = Integer.parseInt(args[1]);
                    } else {
                        itemId = etc.getDataSource().getItem(args[1]);
                    }
                }
                if (args.length > 2) {
                    amount = Integer.parseInt(args[2]);
                    if (amount <= 0 && !player.isAdmin()) {
                        amount = 1;
                    }

                    if (amount > 64 && !player.canIgnoreRestrictions()) {
                        amount = 64;
                    }
                    if (amount > 1024) {
                        amount = 1024;
                    }
                }
                if (args.length == 4) {
                    if (args[3].matches("\\d+")) {
                        damage = Integer.parseInt(args[3]);
                    } else if (player.canIgnoreRestrictions()) {
                        toGive = etc.getServer().matchPlayer(args[3]);
                    }
                } else if (args.length == 5) {
                    damage = Integer.parseInt(args[3]);
                    if (player.canIgnoreRestrictions()) {
                        toGive = etc.getServer().matchPlayer(args[4]);
                    }
                }

            } catch (NumberFormatException localNumberFormatException) {
                caller.notify("Improper ID and/or amount.");
                return;
            }

            if (toGive != null) {

                boolean allowedItem = etc.getInstance().getAllowedItems().isEmpty() || etc.getInstance().getAllowedItems().contains(itemId);

                if (!etc.getInstance().getDisallowedItems().isEmpty() && etc.getInstance().getDisallowedItems().contains(itemId)) {
                    allowedItem = false;
                }

                if (Item.isValidItem(itemId)) {
                    if (allowedItem || player.canIgnoreRestrictions()) {
                        // This is a bit of a hack, this whole function should
                        // probably be rewritten to use .giveItem()
                        if (!toGive.isAdmin() &&
                            !etc.getInstance().allowEnchantableItemStacking &&
                            ((itemId >= 256 && itemId <= 258) ||
                             (itemId >= 267 && itemId <= 279) ||
                             (itemId >= 283 && itemId <= 286) ||
                             (itemId >= 298 && itemId <= 317) ||
                             (itemId == 261))) {
                            toGive.giveItem(itemId, amount);
                        } else {
                            Item i = new Item(itemId, amount, -1, damage);

                            log.info("Giving " + toGive.getName() + " some " + i.toString());
                            // toGive.giveItem(itemId, amount);
                            Inventory inv = toGive.getInventory();
                            inv.insertItem(i);
//                            ArrayList<Item> list = new ArrayList<Item>();

//                            for (Item it : inv.getContents()) {
//                                if (it != null && it.getItemId() == i.getItemId() && it.getDamage() == i.getDamage()) {
//                                    list.add(it);
//                                }
//                            }
//
//                            for (Item it : list) {
//                                if (it.getAmount() < 64) {
//                                    if (amount >= 64 - it.getAmount()) {
//                                        amount -= 64 - it.getAmount();
//                                        it.setAmount(64);
//                                        toGive.giveItem(it);
//                                    } else {
//                                        it.setAmount(it.getAmount() + amount);
//                                        amount = 0;
//                                        toGive.giveItem(it);
//                                    }
//                                }
//                            }
//                            if (amount != 0) {
//                                i.setAmount(64);
//                                while (amount > 64) {
//                                    amount -= 64;
//                                    toGive.giveItem(i);
//                                    i.setSlot(-1);
//                                }
//                                i.setAmount(amount);
//                                toGive.giveItem(i);
//                            }
                            if (toGive.getName().equalsIgnoreCase(caller.getName())) {
                                caller.notify("There you go " + caller.getName() + ".");
                            } else {
                                caller.notify("Gift given! :D");
                                toGive.notify("Enjoy your gift! :D");
                            }
                        }
                    } else if (!allowedItem && !player.canIgnoreRestrictions()) {
                        caller.notify("You are not allowed to spawn that item.");
                    }
                } else {
                    caller.notify("No item with ID " + args[1]);
                }

            } else {
                caller.notify("Can't find user " + args[3]);
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            String[] split = currentText.split(" ", -1);

            if (split.length == 2) {
                return etc.autoComplete(split[1], etc.getDataSource().getItems().keySet().toArray(new String[0]));
            }
            
            if (split.length == 4 && !(caller instanceof Player) || ((Player) caller).canIgnoreRestrictions()) {
                return etc.autoCompleteNames(split[3]);
            }
            
            if (split.length == 5 && !(caller instanceof Player) || ((Player) caller).canIgnoreRestrictions()) {
                return etc.autoCompleteNames(split[4]);
            }

            return null;
        }
    };
    @Command({"cloth", "dye"})
    public static final BaseCommand clothdye = new BaseCommand("<Color> [Amount] - Gives you the specified dye/cloth", "Overridden", 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            try {
                String color = args[1];

                int amountIndex = 2;

                if (args.length > 2 && !args[2].matches("\\d+")) {
                    amountIndex = 3;
                    color += " " + args[2];
                }

                Cloth.Color c = Cloth.Color.getColor(color);

                if (c == null) {
                    caller.notify("Invalid color name!");
                    return;
                }
                Item i = c.getItem();

                int amount = 1;

                if (args.length > amountIndex) {
                    amount = Integer.parseInt(args[amountIndex]);
                    if (amount <= 0 && ((caller instanceof Player) && !((Player) caller).isAdmin())) {
                        amount = 1;
                    }

                    if (amount > 64 && ((caller instanceof Player) && !((Player) caller).canIgnoreRestrictions())) {
                        amount = 64;
                    }
                    if (amount > 1024) {
                        amount = 1024;
                    } // 16 stacks worth. More than enough.
                }

                // If caller is not a Player and no player specified, bail (We
                // can't go giving items to non-players, can we?)
                if (!(caller instanceof Player) && args.length < amountIndex + 2) {
                    return;
                }

                Player toGive = (Player) caller;

                if ((!(caller instanceof Player) || ((Player) caller).canIgnoreRestrictions()) && args.length > amountIndex + 2) {
                    toGive = etc.getServer().matchPlayer(args[amountIndex + 1]);
                }

                if (toGive == null) {
                    caller.notify("Could not find player.");
                    return;
                }

                if (args[0].equalsIgnoreCase("/dye")) {
                    i.setType(Item.Type.InkSack);
                    // some1 had fun inverting this i guess .....
                    i.setDamage(15 - i.getDamage());
                }
                i.setAmount(amount);
                log.info("Giving " + caller.getName() + " some " + i.toString());

                Inventory inv = toGive.getInventory();
                ArrayList<Item> list = new ArrayList<Item>();

                for (Item it : inv.getContents()) {
                    if (it != null && it.getItemId() == i.getItemId() && it.getDamage() == i.getDamage()) {
                        list.add(it);
                    }
                }

                for (Item it : list) {
                    if (it.getAmount() < 64) {
                        if (amount >= 64 - it.getAmount()) {
                            amount -= 64 - it.getAmount();
                            it.setAmount(64);
                            toGive.giveItem(it);
                        } else {
                            it.setAmount(it.getAmount() + amount);
                            amount = 0;
                            toGive.giveItem(it);
                        }
                    }
                }
                if (amount != 0) {
                    i.setAmount(64);
                    while (amount > 64) {
                        amount -= 64;
                        toGive.giveItem(i);
                        i.setSlot(-1);
                    }
                    i.setAmount(amount);
                    toGive.giveItem(i);
                }
                if (toGive.getName().equalsIgnoreCase(caller.getName())) {
                    caller.notify("There you go " + caller.getName() + ".");
                } else {
                    caller.notify("Gift given! :D");
                    toGive.notify("Enjoy your gift! :D");
                }
            } catch (NumberFormatException localNumberFormatException) {
                caller.notify("Improper ID and/or amount.");
            }
        }

        @Override
        public void onBadSyntax(MessageReceiver caller, String[] args) {
            if (caller instanceof Player && !((Player) caller).canIgnoreRestrictions()) {
                caller.notify("Correct usage is: " + args[0] + " <color> [amount]");
            } else {
                caller.notify("Correct usage is: " + args[0] + " <color> [amount] [player]");
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            String[] split = currentText.split(" ", -1);

            if (split.length == 2) {
                Cloth.Color[] colors = Cloth.Color.values();
                String[] colorNames = new String[colors.length];
                for (int i = 0; i < colors.length; i++) {
                    colorNames[i] = colors[i].getName().replaceAll("\\s+", "");
                }

                return etc.autoComplete(split[1], colorNames);
            }

            if (split.length == 3) {
                if (split[1].equalsIgnoreCase("light")) {
                    return etc.autoComplete(split[2], "blue", "green", "gray");
                } else if (split[1].equalsIgnoreCase("dark")) {
                    return Arrays.asList("green");
                }
            }

            if (!(caller instanceof Player) || ((Player) caller).canIgnoreRestrictions()) {
                if ((split[1].equalsIgnoreCase("light") || split[1].equalsIgnoreCase("dark"))
                        && split.length == 5) {
                    return etc.autoCompleteNames(split[4]);
                } else if (split.length == 4) {
                    return etc.autoCompleteNames(split[3]);
                }
            }

            return null;
        }
    };
    @Command({"me", "emote"})
    public static final BaseCommand me = new BaseCommand("<Message> - * hey0 says hi!") {

        @Override
        protected void execute(MessageReceiver caller, String[] split) {
            if (caller instanceof Player && ((Player) caller).isMuted()) {
                caller.notify("You are currently muted.");
                return;
            }
            if (split.length == 1) {
                return;
            }
            String paramString2 = "* " + (caller instanceof Player ? ((Player) caller).getColor() : "") + caller.getName() + Colors.White + " " + etc.combineSplit(1, split, " ");

            log.info("* " + caller.getName() + " " + etc.combineSplit(1, split, " "));
            etc.getServer().messageAll(paramString2);
        }
    };
    @Command
    public static final BaseCommand sethome = new BaseCommand("- Sets your home") {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            boolean callerIsPlayer = caller instanceof Player;
            if (!callerIsPlayer && args.length < 2) {
                return;
            }

            Player player;

            if (args.length == 2 && (!callerIsPlayer || ((Player) caller).isAdmin())) {
                player = etc.getServer().matchPlayer(args[1]);
            } else {
                player = (Player) caller;
            }

            if (player == null) {
                caller.notify("Could not find player.");
                return;
            }
            World world = player.getWorld();
            if (world.getType() != World.Dimension.NORMAL) {
                if (callerIsPlayer && !((Player) caller).canIgnoreRestrictions()) {
                    caller.notify("You cannot set a home in the " + world.getType().name() + ", mortal.");
                    return;
                }
            }

            Warp home = new Warp();



            home.Location = callerIsPlayer ? ((Player) caller).getLocation()
                                           : player.getLocation();
            home.Group = ""; // no group neccessary, lol.
            home.Name = player.getName();
            etc.getInstance().changeHome(home);

            if (player == caller) {
                caller.notify("Your home has been set.");
            } else {
                caller.notify(player.getName() + "'s home has been set.");
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            if (!(caller instanceof Player) || ((Player) caller).isAdmin()) {
                return super.autoComplete(caller, currentText);
            }
            return null;
        }
    };
    @Command
    public static final BaseCommand spawn = new BaseCommand("- Teleports you to spawn") {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            if (!(caller instanceof Player) && args.length < 2) {
                return;
            }

            Player toMove;

            if (args.length == 2 && (!(caller instanceof Player) || ((Player) caller).isAdmin())) {
                toMove = etc.getServer().matchPlayer(args[1]);
            } else {
                toMove = (Player) caller;
            }

            if (toMove == null) {
                caller.notify("Could not find player.");
                return;
            }
            World world = toMove.getWorld();
            World overWorld = etc.getMCServer().getWorld(world.getName(), 0).world;
            if (!world.equals(overWorld)) {
                if (toMove != caller || toMove.canIgnoreRestrictions()) {
                    toMove.switchWorlds(overWorld);
                } else {
                    toMove.sendMessage(Colors.Red + "The veil between the worlds keeps you bound to the " + world.getType().name() + "...");
                    return;
                }
            }

            toMove.teleportTo(toMove.getWorld().getSpawnLocation());

        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            if (!(caller instanceof Player) || ((Player) caller).isAdmin()) {
                return super.autoComplete(caller, currentText);
            }
            return null;
        }
    };
    @Command
    public static final BaseCommand setspawn = new BaseCommand("- Sets the spawn point to your position.") {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            if (!(caller instanceof Player) && args.length < 2) {
                return;
            }

            Player player;

            if (args.length == 2 && (!(caller instanceof Player) || ((Player) caller).canIgnoreRestrictions())) {
                player = etc.getServer().matchPlayer(args[1]);
            } else {
                player = (Player) caller;
            }

            if (player == null) {
                caller.notify("Could not find player.");
                return;
            }

            if ((player.getWorld().getType().getId()) != 0) {
                caller.notify("You cannot set the spawn point outside of the Overworld, mortal.");
                return;
            }

            for (World world : etc.getServer().getWorld(player.getWorld().getName())) {
                world.setSpawnLocation(player.getLocation());
            }

            log.info("Spawn position changed.");
            if (player == caller) {
                caller.notify("You have set the spawn to your current position.");
            } else {
                caller.notify("You have set the spawn to" + player.getName() + "'s current position.");
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            if (!(caller instanceof Player) || ((Player) caller).canIgnoreRestrictions()) {
                return super.autoComplete(caller, currentText);
            }
            return null;
        }
    };
    @Command
    public static final BaseCommand home = new BaseCommand("- Teleports you home") {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            if (!(caller instanceof Player)) {
                return;
            }
            Player player = (Player) caller;

            Warp home;

            if (args.length > 1 && ((Player) caller).isAdmin()) {
                home = etc.getDataSource().getHome(args[1]);
            } else {
                home = etc.getDataSource().getHome(caller.getName());
            }


            if (home != null) {
                if (!player.getWorld().equals(home.Location.getWorld())) {
                    if (player != caller || player.canIgnoreRestrictions()) {
                        player.switchWorlds(home.Location.getWorld());
                    } else {
                        player.sendMessage(Colors.Red + "The veil between the worlds keeps you bound to the " + player.getWorld().getType().name() + "...");
                        return;
                    }
                }

                player.teleportTo(home.Location);
            } else if (args.length > 1 && player.isAdmin()) {
                caller.notify("That player home does not exist");
            } else {
                player.teleportTo(player.getWorld().getSpawnLocation());
            }

        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            if (!(caller instanceof Player) || ((Player) caller).isAdmin()) {
                List<Warp> homes = etc.getDataSource().homes;
                String[] names = new String[homes.size()];
                for (int i = 0; i < names.length; i++) {
                    names[i] = homes.get(i).Name;
                }

                return etc.autoComplete(currentText.substring(currentText.lastIndexOf(' ') + 1), names);
            }
            return null;
        }
    };
    @Command
    public static final BaseCommand warp = new BaseCommand("<Warp> - Warps to the specified warp.", "Correct usage is: /warp <warpname>", 2, 3) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            Player toWarp;
            Warp warp = etc.getDataSource().getWarp(args[1]);

            if (args.length == 3) {
                if (!(caller instanceof Player) || ((Player) caller).canIgnoreRestrictions()) {
                    toWarp = etc.getServer().matchPlayer(args[2]);
                } else {
                    {
                        onBadSyntax(caller, args);
                        return;
                    }
                }
            } else if (!(caller instanceof Player)) {
                return;
            } else {
                toWarp = (Player) caller;
            }

            if (toWarp != null) {
                if (warp != null) {
                    if ((caller instanceof Player) && !((Player) caller).isInGroup(warp.Group) && !warp.Group.equals("")) {
                        caller.notify("Warp not found.");
                    } else {
//                        World.Dimension worldType = toWarp.getWorld().getType();
//                        if (worldType != World.Dimension.NORMAL) {
//                            if (toWarp != caller || toWarp.canIgnoreRestrictions()) {
//                                toWarp.switchWorlds(World.Dimension.NORMAL.getId());
//                            } else {
//                                toWarp.sendMessage(Colors.Rose + "The veil between the worlds keeps you in the " + worldType + "...");
//                                return;
//                            }
//                        }
                        World world = toWarp.getWorld();
                        if (world.getType() != World.Dimension.NORMAL) {
                            if (toWarp != caller || toWarp.canIgnoreRestrictions()) {
                                toWarp.switchWorlds(world);
                            } else {
                                toWarp.sendMessage(Colors.Red + "The veil between the worlds keeps you bound to the " + world.getType().name() + "...");
                                return;
                            }
                        }


                        toWarp.teleportTo(warp.Location);
                        toWarp.sendMessage(Colors.Rose + "Woosh!");
                    }
                } else {
                    caller.notify("Warp not found");
                }
            } else {
                caller.notify("Player not found.");
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            String[] split = currentText.split(" ", -1);
            boolean callerIsPlayer = caller instanceof Player;

            if (split.length == 2) {
                if (callerIsPlayer && ((Player) caller).canUseCommand("/listwarps")) {
                    return etc.autoComplete(split[1],
                        etc.getDataSource().getWarpNames((Player) caller).split(" "));
                } else if (!callerIsPlayer) {
                    List<Warp> warps = etc.getDataSource().warps;
                    String[] names = new String[warps.size()];
                    for (int i = 0; i < names.length; i++) {
                        names[i] = warps.get(i).Name;
                    }

                    return etc.autoComplete(split[1], names);
                }
            }

            if (split.length == 3 && (!callerIsPlayer || ((Player) caller).canIgnoreRestrictions())) {
                return super.autoComplete(caller, currentText);
            }

            return null;
        }
    };
    @Command
    public static final BaseCommand listwarps = new BaseCommand("- Gives a list of available warps") {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            if (!(caller instanceof Player)) {
                return;
            }
            Player player = (Player) caller;

            DataSource ds = etc.getDataSource();

            if (!ds.hasWarps() || ds.getWarpNames(player).equals("")) {
                caller.notify("No warps available.");
            } else {
                caller.notify("Available warps: " + Colors.White + ds.getWarpNames(player));
            }

        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            return null;
        }
    };
    @Command
    public static final BaseCommand setwarp = new BaseCommand("<Warp> - Sets the warp to your current position.", "Overridden", 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            if (!(caller instanceof Player)) {
                return;
            }

            Warp warp = new Warp();

            warp.Name = args[1];
            warp.Location = ((Player) caller).getLocation();
            if (args.length == 3) {
                warp.Group = args[2];
            } else {
                warp.Group = "";
            }
            etc.getInstance().setWarp(warp);
            caller.notify("Created warp point " + args[1] + ".");
        }

        @Override
        public void onBadSyntax(MessageReceiver caller, String[] args) {
            if (!(caller instanceof Player) || ((Player) caller).canIgnoreRestrictions()) {
                caller.notify("Correct usage is: /setwarp <warpname> [group]");
            } else {
                caller.notify("Correct usage is: /setwarp <warpname>");
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            if (!(caller instanceof Player) || ((Player) caller).canIgnoreRestrictions()) {
                String[] split = currentText.split(" ", -1);
                if (split.length == 3) {
                    List<Group> groupList = etc.getDataSource().getGroupList();
                    String[] groups = new String[groupList.size()];

                    for (int i = 0; i < groups.length; i++) {
                        groups[i] = groupList.get(i).Name;
                    }

                    return etc.autoComplete(split[2], groups);
                }
            }

            return null;
        }
    };
    @Command
    public static final BaseCommand removewarp = new BaseCommand("<Warp> - Removes the specified warp.", "Correct usage is: /removewarp <warpname>", 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            Warp warp = etc.getDataSource().getWarp(args[1]);

            if (warp != null) {
                etc.getDataSource().removeWarp(warp);
                caller.notify(Colors.Blue + "Warp removed.");
            } else {
                caller.notify("That warp does not exist");
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            boolean callerIsPlayer = caller instanceof Player;
            String[] split = currentText.split("", -1);

            if (split.length == 2) {
                if (callerIsPlayer && ((Player) caller).canUseCommand("/listwarps")) {
                    return etc.autoComplete(split[1],
                            etc.getDataSource().getWarpNames((Player) caller).split(" "));
                } else if (!callerIsPlayer) {
                    List<Warp> warpList = etc.getDataSource().warps;
                    String[] warps = new String[warpList.size()];

                    for (int i = 0; i < warps.length; i++) {
                        warps[i] = warpList.get(i).Name;
                    }

                    return etc.autoComplete(split[1], warps);
                }
            }

            return null;
        }
    };
    @Command
    public static final BaseCommand mode = new BaseCommand("- Changes your gamemode") {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            if (args.length == 3 && (!(caller instanceof Player) || ((Player) caller).isAdmin())) {
                Player player = etc.getServer().matchPlayer(args[2]);

                if (player == null) {
                    caller.notify("Can't find user " + args[2] + ".");
                } else {
                    try {
                        int mode = Integer.parseInt(args[1]);

                        mode = OEnumGameType.a(mode).e;
                        if (player.getCreativeMode() != mode) {
                            caller.notify(Colors.Yellow + "Setting " + player.getName() + " to game mode " + mode);
                            player.setCreativeMode(mode);
                        } else {
                            caller.notify(player.getName() + " already has game mode " + mode);
                        }
                    } catch (NumberFormatException var11) {
                        caller.notify("There\'s no game mode with id " + args[1]);
                    }
                }
            } else if (args.length == 2) {
                if ((caller instanceof Player) && args[1].matches("-?\\d+"))
                    try {
                        Player player = ((Player) caller);
                        int mode = Integer.parseInt(args[1]);

                        mode = OEnumGameType.a(mode).e;
                        if (player.getCreativeMode() != mode) {
                            player.notify(Colors.Yellow + "Setting your game mode to " + mode);
                            player.setCreativeMode(mode);
                        } else {
                            caller.notify("Your game mode is already " + mode);
                        }
                    } catch (NumberFormatException var11) {
                        caller.notify("There\'s no game mode with id " + args[1]);
                    }
                else if (!(caller instanceof Player) || ((Player) caller).isAdmin()) {
                    Player player = etc.getServer().matchPlayer(args[1]);
                    if (player != null)
                        caller.notify(String.format("%s's current gamemode is: %d", player.getName(), player.getCreativeMode()));
                    else
                        caller.notify("Can't find user " + args[1] + ".");
                }
            } else if (caller instanceof Player) {
                Player player = ((Player) caller);

                caller.notify(String.format("Your current gamemode is: %d", player.getCreativeMode()));
            } else {
                caller.notify("Usage: mode [newmode] <player>");
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            String[] split = currentText.split(" ", -1);
            if (split.length == 3 && (!(caller instanceof Player) || ((Player) caller).isAdmin())) {
                return super.autoComplete(caller, currentText);
            }
            return null;
        }
    };
    @Command
    public static final BaseCommand getpos = new BaseCommand("- Displays your current position.") {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            if (!(caller instanceof Player)) {
                return;
            }
            Player p = (Player) caller;

            p.sendMessage("Pos X: " + p.getX() + " Y: " + p.getY() + " Z: " + p.getZ());
            p.sendMessage("Rotation: " + p.getRotation() + " Pitch: " + p.getPitch());

            double degreeRotation = ((p.getRotation() - 90) % 360);

            if (degreeRotation < 0) {
                degreeRotation += 360.0;
            }
            p.sendMessage("Compass: " + etc.getCompassPointForDirection(degreeRotation) + " (" + (Math.round(degreeRotation * 10) / 10.0) + ")");
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            return null;
        }
    };
    @Command
    public static final BaseCommand compass = new BaseCommand("- Gives you a compass reading.") {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            if (!(caller instanceof Player)) {
                return;
            }

            double degreeRotation = ((((Player) caller).getRotation() - 90) % 360);

            if (degreeRotation < 0) {
                degreeRotation += 360.0;
            }

            caller.notify("Compass: " + etc.getCompassPointForDirection(degreeRotation));
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            return null;
        }
    };
    @Command
    public static final BaseCommand motd = new BaseCommand("- Displays the MOTD") {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            etc.getInstance().getMotd(caller);
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            return null;
        }
    };
    @Command
    public static final BaseCommand spawnmob = new BaseCommand("<Name> [Amount] - Spawns a mob at the looked-at position", "Correct usage is: /spawnmob <name> [amount]", 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            if (!(caller instanceof Player)) {
                return;
            }
            Player p = (Player) caller;

            if (!Mob.isValid(args[1])) {
                caller.notify("Invalid mob. Name has to start with a capital like so: Pig");
                return;
            }

            Location loc;
            Block t = new HitBlox(p).getTargetBlock();

            if (t == null) {
                loc = p.getLocation();
            } else {
                loc = new Location(t.getX() + .5D, t.getY() + 1.5D, t.getZ() + .5D);
                loc.dimension = p.getWorld().getType().getId();
                loc.world = p.getWorld().getName();
            }

            if (args.length == 2) {
                Mob mob = new Mob(args[1], loc);

                mob.spawn();
            } else if (args.length == 3) {
                if (args[2].matches("\\d+")) {
                    int mobnumber = Integer.parseInt(args[2]);

                    for (int i = 0; i < mobnumber; i++) {
                        Mob mob = new Mob(args[1], loc);

                        mob.spawn();
                    }
                } else if (!Mob.isValid(args[2])) {
                    caller.notify("Invalid mob name or number of mobs.");
                    caller.notify("Mob names have to start with a capital like so: Pig");
                } else {
                    {
                        Mob mob = new Mob(args[1], loc);

                        mob.spawn(new Mob(args[2]));
                    }
                }
            } else if (args.length == 4) {
                try {
                    int mobnumber = Integer.parseInt(args[3]);

                    if (!Mob.isValid(args[2])) {
                        caller.notify("Invalid rider. Name has to start with a capital like so: Pig");
                    } else {
                        for (int i = 0; i < mobnumber; i++) {
                            Mob mob = new Mob(args[1], loc);

                            mob.spawn(new Mob(args[2], mob.getWorld()));
                        }
                    }
                } catch (NumberFormatException nfe) {
                    caller.notify("Invalid number of mobs.");
                }
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            String[] split = currentText.split(" ");
            if (split.length <= 3) {
                List<String> mobNames = new ArrayList<String>();
                // Ugly hack, but it works;
                // Monsters start at 50, Animals are in the range 90-99
                for (int i = 50; i < 100; i++) {
                    Class mobClass = OEntityList.a(i);
                    if (OIMob.class.isAssignableFrom(mobClass) || OIAnimals.class.isAssignableFrom(mobClass)) {
                        mobNames.add(OEntityList.b(i));
                    }
                }

                return etc.autoComplete(split[split.length - 1], mobNames.toArray(new String[mobNames.size()]));
            }
            return null;
        }
    };
    @Command
    public static final BaseCommand clearinventory = new BaseCommand("- Clears your inventory") {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {

            Player target;

            if (args.length >= 2) {
                if (!(caller instanceof Player) || ((Player) caller).isAdmin()) {
                    target = etc.getServer().matchPlayer(args[1]);
                } else {
                    return;
                }
            } else {
                target = (Player) caller;
            }
            if (target != null) {
                Inventory inv = target.getInventory();

                inv.clearContents();
                inv.update();
                if (!target.getName().equals(caller.getName())) {
                    caller.notify("Cleared " + target.getName() + "'s inventory.");
                }
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            if (currentText.indexOf(' ') != currentText.lastIndexOf(' ')
                    || caller instanceof Player && !((Player) caller).isAdmin()) {
                return null;
            }
            return super.autoComplete(caller, currentText);
        }
    };
    @Command
    public static final BaseCommand mspawn = new BaseCommand("<Mob> - Change the looked at mob spawner's mob", "Correct usage is: /mspawn <name>.", 1, 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            if (!(caller instanceof Player)) {
                return;
            }

            HitBlox hb = new HitBlox((Player) caller);
            Block block = hb.getTargetBlock();

            if (block != null && block.getType() == 52) { // mob spawner
                MobSpawnerLogic ms = (MobSpawnerLogic) ((Player) caller).getWorld().getComplexBlock(block.getX(), block.getY(), block.getZ());

                if (ms != null) {
                    if (args.length == 1) {
                        caller.notify(String.format("You are targeting a mob spawner of: %s", ms.getSpawn()));
                    } else {
                        if (!Mob.isValid(args[1])) {
                            caller.notify(String.format("%s is not a valid mob name.", args[1]));
                        } else {
                            ms.setSpawn(args[1]);
                            caller.notify("Mob spawner set to " + args[1]);
                        }
                    }
                }

            } else {
                caller.notify("You are not targeting a mob spawner.");
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            if (currentText.indexOf(' ') == currentText.lastIndexOf(' ')) {
                return spawnmob.autoComplete(caller, currentText);
            }
            return null;
        }
    };
    @Command
    public static final BaseCommand xp = new BaseCommand("<level|total|add|remove> [Player] [value] - XP status", "Usage: /xp <level|total|add|remove> [Player] <value>", 1, 4) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            if (!(caller instanceof Player)) {
                return;
            }

            Player player = (Player) caller;

            if (args.length == 3) {
                if (!args[1].toLowerCase().matches("(?i)add|remove")) {
                    Player p = etc.getServer().matchPlayer(args[2]);

                    if (p == null) {
                        player.notify(args[2] + " does not exist!");
                    } else {
                        if (args[1].equalsIgnoreCase("level")) {
                            player.sendMessage(p.getName() + " is level " + Colors.Yellow + p.getLevel());
                        }
                        if (args[1].equalsIgnoreCase("total")) {
                            player.sendMessage(p.getName() + " has " + Colors.Yellow + p.getXP() + Colors.White + " Total EXP");
                        }
                    }
                } else {
                    try {
                        int xp = Integer.parseInt(args[2]);
                        if (args[1].equalsIgnoreCase("add")) {
                            player.addXP(xp);
                            player.notify("XP added!");
                        } else if (args[1].equalsIgnoreCase("remove")) {
                            player.removeXP(xp);
                            player.notify("XP removed!");
                        }
                    } catch (NumberFormatException nfe) {
                        caller.notify("Please enter numbers, not letters.");
                    }
                }
            } else if (args.length == 4) {
                Player p = etc.getServer().matchPlayer(args[2]);

                if (p == null) {
                    player.notify(args[2] + " does not exist!");
                } else {
                    try {
                        int xp = Integer.parseInt(args[3]);
                        if (args[1].equalsIgnoreCase("add")) {
                            p.addXP(xp);
                            player.notify("XP added to " + p.getName());
                        } else if (args[1].equalsIgnoreCase("remove")) {
                            p.removeXP(xp);
                            player.notify("XP removed from " + p.getName());
                        }
                    } catch (NumberFormatException nfe) {
                        caller.notify("Please enter numbers, not letters.");
                    }
                }
            } else if (args.length == 2) {
                if (args[1].equalsIgnoreCase("level")) {
                    player.sendMessage("You are level " + Colors.Yellow + player.getLevel());
                } else if (args[1].equalsIgnoreCase("total")) {
                    player.sendMessage("You have " + Colors.Yellow + player.getXP() + Colors.White + " Total EXP");
                }
            } else {
                OEntityPlayerMP ent = player.getEntity();

                player.sendMessage("User: " + Colors.Yellow + player.getName() + Colors.White);
                player.sendMessage("Lvl: " + Colors.Yellow + player.getLevel() + Colors.White);
                player.sendMessage(String.format("Exp:%s %.0f %s/%s %d %s(%s%.2f%%%s)", // Exp: xx / yy (zz.zz%)
                        Colors.Yellow, ent.ch * ent.ck(), Colors.White,
                        Colors.Yellow, ent.ck(), Colors.White,
                        Colors.Yellow, ent.ch, Colors.White));

                if(player.isAdmin()) {
                    player.sendMessage(Colors.Yellow + (etc.getInstance().isOldExperience() ? "Pre-":"Post ") + "1.3.2 Experience System" + Colors.White);
                }
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            String[] split = currentText.split(" ", -1);
            if (split.length == 2) {
                return etc.autoComplete(split[1], "level", "total", "add", "remove");
            } else if (split.length == 3) {
                return super.autoComplete(caller, currentText);
            }

            return null;
        }
    };
    @Command
    public static final BaseCommand foodlevel = new BaseCommand("<add|remove|set> [Player] [value] - Sets player food level", "Correct usage is: /foodlevel <add|remove|set> [player] <value>", 2, 4) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            Player subject = (Player) caller;
            String command = "add";
            int foodLevel = 20;

            try {
                if (args.length == 4) {
                    command = args[1];
                    subject = etc.getServer().matchPlayer(args[2]);
                    foodLevel = Integer.parseInt(args[3]);
                } else if (args.length == 3) {
                    command = args[1];
                    foodLevel = Integer.parseInt(args[2]);
                }
            } catch (NumberFormatException e) {
                caller.notify((args.length == 4 ? args[3] : args[2]) + " is not a valid number.");
            } catch (Exception e) {
                caller.notify("Error on /foodlevel command");
                return;
            }

            if (command.equalsIgnoreCase("add")) {
                foodLevel = Math.min(20, subject.getFoodLevel() + foodLevel);
            } else if (command.equalsIgnoreCase("remove")) {
                foodLevel = Math.max(0, subject.getFoodLevel() - foodLevel);
            } else if (command.endsWith("set")) {
                foodLevel = Math.min(20, Math.max(foodLevel, 0));
            }

            if (subject != null) {
                subject.setFoodLevel(foodLevel);
                subject.setFoodExhaustionLevel(1);
            } else {
                caller.notify("Can't find player " + args[1]);
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            String[] split = currentText.split(" ", -1);
            if (split.length == 2) {
                return etc.autoComplete(tooltip, "add", "remove", "set");
            } else if (split.length == 3) {
                return super.autoComplete(caller, currentText);
            }
            return null;
        }
    };
    @Command
    public static final BaseCommand god = new BaseCommand("<Player> - Makes player invulnerable", "Correct usage is: /god <player>", 1, 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            Player subject = (Player) caller;
            String info = Colors.Yellow + "You are";

            if (args.length == 2) {
                subject = etc.getServer().matchPlayer(args[1]);
                info = String.format("%s%s is", Colors.Yellow, subject.getName());
            }
            if (subject != null) {
                if (subject.isCreativeMode()) {
                    caller.notify("Can't apply /god to players in creative mode");
                    return;
                }
                subject.setDamageDisabled(!subject.isDamageDisabled());
                if (subject.isDamageDisabled()) {
                    caller.notify(info + " now invincible!");
                } else {
                    caller.notify(info + " no longer invincible.");
                }
            } else {
                caller.notify("Can't find player " + args[1]);
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            if (currentText.indexOf(' ') == currentText.lastIndexOf(' ')) {
                return super.autoComplete(caller, currentText);
            }
            return null;
        }
    };
    @Command
    public static final BaseCommand kill = new BaseCommand("<Player> - Kill the specified player", "Correct usage is: /kill <player>", 1, 2) {

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            Player killer = (Player) caller;
            if (args.length == 2) {
                Player victim = etc.getServer().matchPlayer(args[1]);
                if (victim == null) {
                    killer.notify(args[1] + " could not be found (and therefore he has not been killed)");
                    return;
                }
                if (killer.hasControlOver(victim)) {
                    victim.dropInventory();
                    victim.setHealth(0);
                    killer.notify(victim.getName() + " has been killed!");
                } else {
                    killer.notify("You cannot kill " + victim.getName());
                }

            } else {
                killer.dropInventory();
                killer.setHealth(0);
                killer.notify("You suicided.");
            }

        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            return god.autoComplete(caller, currentText);
        }
    };
    @Command
    public static final BaseCommand playerinfo = new BaseCommand("<Player> Shows player data", "Correct usage is: /playerinfo <player>", 1, 2) {

        private void sendData(MessageReceiver caller, String caption, Object[] data) {
            caller.notify(Colors.LightGreen + caption + Colors.Gold + Arrays.toString(data));
        }

        private void sendData(MessageReceiver caller, String caption, Object data) {
            caller.notify(Colors.LightGreen + caption + Colors.Gold + String.valueOf(data));
        }

        @Override
        protected void execute(MessageReceiver caller, String[] args) {
            Player subject = null;
            if (args.length == 2) {
                subject = etc.getServer().matchPlayer(args[1]);
            } else if (caller instanceof Player) {
                subject = (Player) caller;
            }
            if (subject != null) {

                caller.notify(Colors.Green + subject.getName() + "'s info:");
                sendData(caller, "Prefix: ", subject.getColor());
                sendData(caller, "IP address : ", subject.getIP());
                sendData(caller, "Groups: ", subject.getGroups());
                sendData(caller, "Commands: ", subject.getCommands());
                sendData(caller, "Health: ", subject.getHealth());
                sendData(caller, "Muted: ", subject.isMuted());
                sendData(caller, "Food Level: ", subject.getFoodLevel());
                sendData(caller, "Food Exhaustion: ", String.format("%.2f", subject.getFoodExhaustionLevel()));
                sendData(caller, "Food Saturation: ", String.format("%.2f", subject.getFoodSaturationLevel()));
                sendData(caller, "Experience: ", subject.getXP());
                sendData(caller, "Level: ", subject.getLevel());
                sendData(caller, "Mode: ", OEnumGameType.a(subject.getCreativeMode()).b());
                Location l = subject.getLocation();

                sendData(caller, "Position: ", String.format("X: %.2f Y: %.2f Z: %.2f Pitch: %.2f Yawn: %.2f", l.x, l.y, l.z, l.rotX, l.rotY));
                sendData(caller, "World: ", String.format("%s (%d)", subject.getWorld().getType().name(), subject.getWorld().getType().getId()));
                Warp home = etc.getDataSource().getHome(subject.getName());

                if (home != null) {
                    l = home.Location;
                    sendData(caller, "Home: ", String.format("X: %.2f Y: %.2f Z: %.2f", l.x, l.y, l.z));
                } else {
                    sendData(caller, "Home: ", "Not set");
                }
            } else if (args.length == 2) {
                caller.notify(Colors.Yellow + "Can't find player " + args[1]);
            }
        }

        @Override
        public List<String> autoComplete(MessageReceiver caller, String currentText) {
            return god.autoComplete(caller, currentText);
        }
    };

    static {
        // CanaryMod: Initialize *after* all the commands
        instance = new PlayerCommands();
    }
}
