import java.util.ArrayList;
import java.util.Random;

/**
 * TODO Write a one-sentence summary of your class here.
 * TODO Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author  @joshuacgunn on GitHub
 * @version Feb 27, 2025
 */
public class Dungeon {
    public String dungeonName;
    private ArrayList<DungeonFloor> floorsInDungeon = new ArrayList<DungeonFloor>();
    private int bossFloor;
    private Random rand = new Random();
    private DungeonFloor currentFloor;
    private int clearedFloors = 0;
    private ArrayList<Item> droppedItems = new ArrayList<>();
    ArrayList<Entity> playersInDungeon = new ArrayList<>();

    public Dungeon(String name, int floors, int bossFloorLevel) {
        if (bossFloorLevel == 0) {
            this.bossFloor = 0;
        } else {
            this.bossFloor = bossFloorLevel;
        }
        this.dungeonName = name;
        for (int i = floors; i > 0; i--) {
            if ((bossFloorLevel > 0) && i == bossFloorLevel) {
                floorsInDungeon.add(new DungeonFloor(i, 1, this));
            } else {
                floorsInDungeon.add(
                        new DungeonFloor(i,
                                (rand.nextInt(1, 5)) * rand.nextInt(1, 3),
                                this));
            }
        }
        this.currentFloor = floorsInDungeon.get(floorsInDungeon.size() - (floorsInDungeon.size()));
    }

    public void clearFloor() {
        if (clearedFloors == floorsInDungeon.size()) {
            clearDungeon();
        }
        else {
            clearedFloors += 1;
            currentFloor = floorsInDungeon.get((floorsInDungeon.size() - floorsInDungeon.size()) + clearedFloors);
        }
    }

    public ArrayList<DungeonFloor> getFloorList() {
        return floorsInDungeon;
    }

    public int getfloorsInDungeon() {
        return floorsInDungeon.size();
    }

    public int bossFloorLevel() {
        return bossFloor;
    }

    public void clearDungeon() {
        System.out.println("You cleared " + this.dungeonName);
    }

    public int getCurrentFloor() {
        return currentFloor.getFloorNumber();
    }
    
    public String getDungeonName() {
        return this.dungeonName;
    }
    public ArrayList<Entity> getMonOnFloor() {
        return this.getFloorList().get(this.getCurrentFloor()).getEntities(); // Returns a list monsters on the players current floor. 
    }
}