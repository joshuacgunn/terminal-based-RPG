import java.util.ArrayList;
import java.util.Random;

/**
 * TODO Write a one-sentence summary of your class here.
 * TODO Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author  TODO Your Name
 * @version Feb 27, 2025
 */
public class DungeonFloor {
    private int monsterCount;
    private Dungeon inWhichDungeon;
    private int floor;
    ArrayList<Entity> entityList = new ArrayList<>();
    String[] monName = { "Orc", "Goblin", "Troll", "Skeleton", "Wizard", "Necromancer"};
    Random rand = new Random();
    public DungeonFloor(int floorNumber, int monsters, Dungeon dungeon) {
        this.monsterCount = monsters;
        this.inWhichDungeon = dungeon;
        this.floor = floorNumber;
        for (int n = monsterCount; n > 0; n--) {
            Monster monster = new Monster(monName[rand.nextInt(0, 4)]);
            entityList.add(monster);
        }
    }
    public int getFloorNumber() {
        return this.floor;
    }
    public int getMonsterCount() {
        return this.monsterCount;
    }
    public ArrayList<Entity> getEntities() {
        return entityList;        
    }
    public void killEntity(Entity target) {
        entityList.remove(target);
    }
}