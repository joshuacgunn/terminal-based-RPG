public class BurnEffect implements Effect {
    private int duration; // Turns remaining
    private int damagePerTurn;

    public BurnEffect(int duration, int damagePerTurn) {
        this.duration = duration;
        this.damagePerTurn = damagePerTurn;
    }

    @Override
    public void applyEffect(Entity target) {
        target.takeDamage(damagePerTurn);
        System.out.println(target.getName() + " burns for " + damagePerTurn + " damage!");
    }

    @Override
    public void decrementDuration() {
        duration--;
    }

    @Override
    public boolean isExpired() {
        return duration <= 0;
    }

    // Optional: For debugging
    public int getDuration() { return duration; }
}