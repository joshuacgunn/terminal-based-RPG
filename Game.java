import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
/**
 * TODO Add more features: critical hits, dodging, blocking, difficulty of boss tiers, choosing whether to enter the dungeon or not, towns and shops, higher rewards with higher difficulty of dungeon
 * TODO Crits should be % based, with the % based on which class (Rogue highest), and what weapon. Dodging should be the same, but more dependent on armor weight and class(Rogue highest). Difficulty of boss tiers should be based on HP and attack power, while dungeon difficulty should be based on how many monsters and floors there are, as well as how powerful the monsters are.
 * it represents, and how to use it.
 *
 * @author  @joshuacgunn on GitHub
 * @version Mar 10, 2025
 */
public class Game {
    public static void main(String[] args) {
        ArrayList<Dungeon> duns = new ArrayList<>();
        boolean isGameRunning = true;
        Scanner name = new Scanner(System.in);
        System.out.println("What is your name? ");
        String userName = name.nextLine();
        Scanner cls = new Scanner(System.in);
        System.out.println("What class do you want to be? 1. Mage, 2. Paladin, 3. Rogue");
        String Class = cls.nextLine();
        name.close();
        cls.close();
        Player user = new Player(0, userName, Class);
        user.getAllInfo();
        Random random = new Random();
        int numOfFloors = random.nextInt(3, 6);
        int difficulty;
        String[] dunNames = { "Bleakfall Barrows", "Moskowitz", "Hrothgar", "Broken Tower", "Fellglow Keep", "Stone Tower", "Apocrypha", "Sightless Pit", "Saarthal" };
        while (isGameRunning) {
            Dungeon d = new Dungeon((dunNames[random.nextInt(0,9)]), numOfFloors, random.nextInt(0, 1));
            for (DungeonFloor n : d.getFloorList()) {
                System.out.println(n.getMonList());
            }
            isGameRunning = false;
        }
    }
}
