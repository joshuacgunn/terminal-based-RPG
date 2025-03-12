/**
 * TODO Write a one-sentence summary of your class here.
 * TODO Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author  @joshuacgunn on GitHub
 * @version Mar 10, 2025
 */
public class Armor extends Item {
    public enum ArmorSlots {
        HELMET {
            @Override
            public String toString(){
                return "Helmet";
            }
        },
        CHESTPLATE {
            @Override
            public String toString(){
                return "Chestplate";
            }
        },
        LEGGINGS {
            @Override
            public String toString(){
                return "Leggings";
            }
        },
        BOOTS {
            @Override
            public String toString(){
                return "Boots";
            }
        }
    }
    private int armorRating;
    private ArmorSlots slot;
    private int durability;

    public Armor(int durability, int armorRating, String name, float value, ArmorSlots slot) {
        super(name, value);
        this.armorRating = armorRating;
        this.slot = slot;
        this.durability = durability;
    }
    public ArmorSlots getSlot() {
        return this.slot;
    }
    public int getArmorRating() {
        return this.armorRating;
    }

}
