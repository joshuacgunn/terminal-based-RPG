public class ClassRogue extends Player {
    private double dodgeChance;
    private double agility;

    public ClassRogue(String name) {
        super(name, PlayerClass.ROGUE);
        this.hp = 100.0f;
        this.agility = 1;
    }
    public double getAgility() {
        return agility;
    }
}
