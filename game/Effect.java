public interface Effect {
    void applyEffect(Entity target); // Apply the effect (e.g., damage)
    void decrementDuration(int ticks);        // Reduce duration by certain # of ticks
    boolean isExpired();             // Check if the effect has ended
}