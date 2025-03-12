public class RogueClass extends Player {
    private double dodgeChance;
    private double agility;


    public RogueClass(String name, PlayerClass Class) {
        super(name);
        this.agility = 1;
        this.setPlayerClass(PlayerClass.ROGUE);
    }
}
