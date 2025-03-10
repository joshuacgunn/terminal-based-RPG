/**
 * TODO Write a one-sentence summary of your class here.
 * TODO Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author  @joshuacgunn on GitHub
 * @version Feb 27, 2025
 */
public class Boss {
    private int hp;
    private int power;
    private String name;
    private boolean isAlive;

    public Boss(int startingHP, int attack, String bossName) {
        this.hp = startingHP;
        this.power = attack;
        this.name = bossName;
    }

    public void attack(Player player) {
        player.takeDamage(power);
    }
}
