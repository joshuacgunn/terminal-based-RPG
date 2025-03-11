/**
 * TODO Write a one-sentence summary of your class here.
 * TODO Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author  @joshuacgunn on GitHub
 * @version Mar 10, 2025
 */
public class Armor extends Item {
    private int durability;
    private int armorRating;
    // 1: Helmet, 2: Chest, 3: Legs, 4: Full Outfit
    private int piece;

    public Armor(int durability, int armorRating, String name, double value, int piece) {
        super(name, value);
        this.durability = durability;
        this.armorRating = armorRating;
        this.piece = piece;
    }
}
