public class WeaponWand extends Weapon {
    protected float damage;
    protected float manaCost;
    public enum MagicEffect {
        RAISEDEAD, // Bring back a random dead monster, power of monster increases with wand power/player level (or stat not sure yet)
        FIREBALL, // Shoots a fireball that does damage and applies burn

    }
    protected MagicEffect magicEffect;
    public WeaponWand(String name, float damage, float armorPenetration, float manaCost, MagicEffect magicEffect) {
        super(name, damage, 0, armorPenetration);
        this.damage = damage;
        this.manaCost = manaCost;
        this.weaponType = WeaponType.WAND;
        this.magicEffect = magicEffect;
    }
//    public void increaseArmorPenetration(float amt) {
//        super.multiplyArmorPenetration(amt);
//    }
}
