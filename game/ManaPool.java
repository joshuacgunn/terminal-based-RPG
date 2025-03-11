public class ManaPool {
    private int maxMana;
    private int currentMana;
    
    public ManaPool(int maxMana) {
        this.currentMana = maxMana;
        this.maxMana = maxMana;
    }
    // Methods to increase your max mana, as well as your current mana
    public void increaseMaxMana(int amount) {
        this.maxMana += amount;
    }
    public void increaseMana(int amount) {
        if ((currentMana + amount) > maxMana) {
            currentMana = maxMana;
        }
        else {
            currentMana += amount;
        }
    }
}
