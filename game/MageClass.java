import java.util.ArrayList;
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
                ArrayList<Entity> m = this.dun.getMonOnFloor();
                for (int n = 20; n > 6; n -= 7) {
                    Entity r = m.get(rand.nextInt(m.size()-1));
                    r.takeDamage(n+this.currentItem.getDam());
                    m.remove(r);
                }
                break;
            case ARCANE:
                if (this.currentItem instanceof Weapon) {
                    this.currentItem.setArPen(1.5);
                    target.takeDamage(this.currentItem.getDam());
                }
        }
    }
}
