import java.util.*;
import java.util.function.Predicate;

public class ClassMage extends Player {
    private final ManaPool manaPool;
    private ArrayList<Spell> playerSpells = new ArrayList<>();
    public boolean isUsingSpell = false;
    public ClassMage(String name) {
        super(name, PlayerClass.MAGE);
        this.manaPool = new ManaPool(100);
        super.setPlayerClass(PlayerClass.MAGE);
    }
    public void useWand(Spell spell, Entity target) {
        float baseDamage = calculateDamage();
        if (spell != null) {
            isUsingSpell = true;
        }
    }
    // Example use: useSpell(slow, e -> e.getAttackSpeed() > (e.getAttackSpeedMax/2), orc1, orc2, troll1) will slow the attack speed of orc1, orc2, and troll1 only if their attackspeed is greater than their attackspeedmax  over 2
    public void useSpell(Spell spell, Predicate<Entity> filter, Entity... targets) {
        int currentIndex = 0;
        for (Entity t : targets) {
            switch (spell.spellType) {
                case SINGLE:
                    spell.applyElementEffect(targets[currentIndex]);
                case MULTI:
                    spell.applyElementEffect(targets[currentIndex]);
                    currentIndex++;
                    if (spell.element == Element.LIGHTNING) {
                        spell.lightningDamage = (float) Math.round(Math.min(8, spell.lightningDamage/(Math.pow(1.2f, currentIndex+1))));
                    }
                    if (targets.length == currentIndex+1) {
                        spell.lightningDamage = (spell.currentWeaponWand.getDamage() + spell.power / 1.6f);
                    }
                case SELF:
                    super.heal(spell.power);
            }
            }
    }
    public ManaPool getManaPool() {
        return manaPool;
    }
}
