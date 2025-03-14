public class EffectBurn implements Effect {
    private int duration; // Turns remaining
    private final float damagePerTurn;

    public EffectBurn(int duration, float damagePerTurn) {
        this.duration = duration;
        this.damagePerTurn = damagePerTurn;
    }
    @Override
    public void applyEffect(Entity target) {
        target.takeDamage(damagePerTurn, 0);
        System.out.println(target.getName() + " burns for " + damagePerTurn + " damage!");
    }
    @Override
    public void decrementDuration(int ticks) {
        duration -= ticks;
    }
    @Override
    public boolean isExpired() {
        return duration <= 0;
    }
    // Optional: For debugging
    public int getDuration() {
        return duration;
    }
}