public class HealingPotion extends Item{
    private final float healingFactor;

    public HealingPotion(String name, float value, float healingFactor) {
        super(name, value);
        this.healingFactor = healingFactor;
    }
    public float getHealingFactor() {
        return healingFactor;
    }
}
