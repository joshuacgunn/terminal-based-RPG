/**
 * TODO Write a one-sentence summary of your class here.
 * TODO Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author  @joshuacgunn on GitHub
 * @version Feb 27, 2025
 */
public class Monster extends Entity {
    // Fields
    private double hp;
    private String name;
    private Item weapon;
    protected EnemyType enemyType;

    // Constructor for the Monster object
    public Monster(String name, EnemyType enemyType) {
        super(name);
        this.enemyType = enemyType;
        // Determines the HP and weapon for the different monsters you can encounter
        switch (enemyType) {
            case GOBLIN:
                this.hp = 15;
                this.weapon = new Weapon("Club", 0, 15, 0, 0);
                break;
            case ORC:
                this.hp = 20;
                this.weapon = new Weapon("Crude Sword", 0, 20, 0, 0.2);
                break;
            case SKELETON:
                this.hp = 5;
                this.weapon = new Weapon("Bow", 0, 25, 0, 0.4);
                break;
            case TROLL:
                this.hp = 35;
                this.weapon = new Weapon("Battleaxe", 0, 25, 0, 0.6);
                break;
            
    }
}
}
