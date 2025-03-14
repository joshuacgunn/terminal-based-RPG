public class Spell extends Item {
    protected Element element;
    public enum spellTypes {
        SINGLE,
        MULTI,
        SELF
    }
    public WeaponWand currentWeaponWand = (WeaponWand) super.equippedItem;
    public float power;
    public float lightningDamage = (currentWeaponWand.getDamage() + this.power / 1.6f);
    protected spellTypes spellType;
    public Spell(String name, float value, Element element, spellTypes spellType, float power) {
        super(name);
        this.spellType = spellType;
        this.element = element;
        this.power = power;
    }
    public Element getElement() {
        return this.element;
    }
    public spellTypes getSpellType() {
        return spellType;
    }
    public void applyElementEffect(Entity target) {
        Element element = this.element;
        if (equippedItem instanceof WeaponWand) {
            switch (element) {
            case FIRE:
                target.addStatusEffect(new EffectBurn(3, this.power)); // Burn for 3 turns, 2 damage/turn
                break;
            case ICE:
                target.addStatusEffect(new EffectSlow(2, this.power)); // Slow movement for 2 turns
                break;
            case LIGHTNING:
                target.takeDamage(lightningDamage, 0);
                break;
            case ARCANE:
                target.takeDamage(((WeaponWand) equippedItem).damage, ((WeaponWand) equippedItem).armorPenetration);
                }
        }
    }
}
