public class EffectSlow implements Effect{
    private int duration;
    private float slowMagn;
    public EffectSlow(int duration, float slowMagn) {
        this.duration = duration;
        this.slowMagn = slowMagn;
    }
    @Override
    public void applyEffect(Entity target) {
        target.slowAttackSpeed(slowMagn);
    }
    
    @Override
    public void decrementDuration(int ticks) {
        duration -= ticks;
    }

    @Override
    public boolean isExpired() {
        return duration <= 0;
    }
    public int getDuration() { return duration; }
}
