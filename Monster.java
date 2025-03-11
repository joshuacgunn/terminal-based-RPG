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

    // Constructor for the Monster object
    public Monster(String monsterName) {
        super(monsterName);
        this.name = monsterName;
        // Determines the HP for the different monsters you can encounter
        if (monsterName.equals("Goblin")) {
            this.hp = 15;
            this.weapon = new Weapon("Club", 0, 15, 0, 0);
        }
        else if (monsterName.equals("Orc")) {
            this.hp = 20;
            this.weapon = new Weapon("Crude Sword", 0, 20, 0, 0.2);
        }
        else if (monsterName.equals("Skeleton")) {
            this.hp = 5;
            this.weapon = new Weapon("Bow", 0, 25, 0, 0.4);
        }
        else if (monsterName.equals("Troll")) {
            this.hp = 35;
            this.weapon = new Weapon("Battleaxe", 0, 25, 0, 0.6);
        }
    }
    // Method to damage individual monsters
    public void takeDmg(int dmg) {
        if (dmg > hp) {
            hp = 0;
        }
        hp -= dmg;
    }
    public void attack(Player p) {

    }
}
