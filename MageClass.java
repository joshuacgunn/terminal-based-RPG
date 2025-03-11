import java.util.Random;

public class MageClass extends Player {
    private final ManaPool manaPool;
    private Random rand = new Random();

    public MageClass(String name, String playerClass) {
        super(name, playerClass);
        this.manaPool = new ManaPool(100);
    }
    private void applyElementEffect(Element element, Entity target) {
        switch (element) {
            case FIRE:
                target.addStatusEffect(new BurnEffect(3, 2)); // Burn for 3 turns, 2 damage/turn
                break;
            case ICE:
                target.addStatusEffect(new SlowEffect(2, 3)); // Slow movement for 2 turns
                break;
            case LIGHTNING:
                
                break;
        }
    }
}
