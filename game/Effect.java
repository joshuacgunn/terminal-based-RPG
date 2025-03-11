public interface Effect {
    void applyEffect(Entity target); // Apply the effect (e.g., damage)
    void decrementDuration();        // Reduce duration by 1 turn
    boolean isExpired();             // Check if the effect has ended
}