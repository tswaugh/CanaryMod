public class PlayerInventory extends ItemArray<OInventoryPlayer> implements Inventory {
    private final OEntityPlayerMP user;

    public PlayerInventory(Player player) {
        super(player.getUser().bJ);
        user = player.getUser();
    }

    public void giveItem(int itemId, int amount) {
        Item toGive = new Item(itemId, amount);
        if (!this.insertItem(toGive)) {
            // Not all was given, drop rest (insertItem updates amount).
            this.getPlayer().giveItemDrop(toGive);
        }
    }

    @Override
    public void update() {
        user.j_();
    }

    /**
     * Returns a String value representing this PlayerInventory
     *
     * @return String representation of this PlayerInventory
     */
    @Override
    public String toString() {
        return String.format("PlayerInventory[user=%s]", user.getPlayer());
    }

    /**
     * Returns the owner of this PlayerInventory
     *
     * @return Player
     */
    public Player getPlayer() {
        return user.getPlayer();
    }

    @Override
    public String getName() {
        return container.getName();
    }

    @Override
    public void setName(String value) {
        container.setName(value);
    }
}
