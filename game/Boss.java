/**
 * TODO Write a one-sentence summary of your class here.
 * TODO Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author  @joshuacgunn on GitHub
 * @version Feb 27, 2025
 */
public class Boss extends Entity {
    private int hp;
    private String name;
    private Weapon weapon;
    protected EnemyType enemyType;

    public Boss(int hp, String name, Weapon weapon) {
        super(name);
        this.hp = hp;
        this.weapon = weapon;
    }

    public void attack(Player player) {
        player.takeDamage(weapon.getDam());
    }
}
