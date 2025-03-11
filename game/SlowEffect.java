public class SlowEffect implements Effect{
    private int duration;
    private int slowMagn;
    public SlowEffect(int duration, int slowMagn) {
        this.duration = duration;
        this.slowMagn = slowMagn;
    }
    @Override
    public void applyEffect(Entity target) {
        target.slowAttackSpeed(slowMagn);
    }
    
    @Override
    public void decrementDuration() {
        duration--;
    }

    @Override
    public boolean isExpired() {
        return duration <= 0;
    }
    public int getDuration() { return duration; }
}
