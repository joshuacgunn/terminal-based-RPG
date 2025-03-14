/**
 * Represents a specific piece of armor that can be equipped by an entity.
 * Each ArmorPiece is defined by its slot, durability, piece rating, and overall quality.
 *
 * Armor pieces can be equipped in one of four slots: Helmet, Chestplate, Leggings, or Boots.
 * The quality of the armor determines its defensive capabilities, which is expressed
 * by the `pieceRating` and `maxPieceRating`, and it is also constrained by the type of slot.
 *
 * Durability is calculated based on the piece rating and serves as a measurement of how long
 * the armor will last before it becomes unusable.
 */
import java.util.Random;

public class ArmorPiece extends Item {
    public enum ArmorSlots {
        HELMET {
            @Override
            public String toString() {
                return "Helmet";
            }
        },
        CHESTPLATE {
            @Override
            public String toString() {
                return "Chestplate";
            }
        },
        LEGGINGS {
            @Override
            public String toString() {
                return "Leggings";
            }
        },
        BOOTS {
            @Override
            public String toString() {
                return "Boots";
            }
        }
    }

    private final ArmorSlots slot;
    private float durability;
    private float maxPieceRating; // Max allowed defense of this piece of armor
    private float pieceRating; // Defense rating of this piece of armor

    public enum ArmorQuality {
        FLIMSY(0.5f, 5),
        WORN(1.0f, 5),
        STURDY(1.5f, 5),
        FINE(2.0f, 5),
        SUPERB(2.5f, 5),
        MASTERWORK(3.0f, 5),
        LEGENDARY(3.5f, 5),
        SECRET(4.0F, 10);

        public final float defenseValue;
        public final int durabilityMultiplier;

        ArmorQuality(float quality, int durabilityMultiplier) {
            defenseValue = quality;
            this.durabilityMultiplier = durabilityMultiplier;
        }
    }

    Random rand = new Random();
    private ArmorQuality quality;

    public ArmorPiece(String name, ArmorSlots slot, ArmorQuality quality) {
        super(name);
        final float pieceRatingCalculation = rand.nextFloat(quality.defenseValue - 0.5f, quality.defenseValue);
        final float durabilityCalculation = pieceRatingCalculation * quality.durabilityMultiplier;

        // Round float variables to 1 decimal place where necessary
        this.value = Math.round(pieceRatingCalculation * 100);
        this.slot = slot;

        if (quality != ArmorQuality.SECRET) {
            switch (slot) {
                case HELMET:
                    this.maxPieceRating = Math.round(2.0f * 10) / 10.0f;
                    break;
                case CHESTPLATE:
                    this.maxPieceRating = Math.round(3.5f * 10) / 10.0f;
                    break;
                case LEGGINGS:
                    this.maxPieceRating = Math.round(3.0f * 10) / 10.0f;
                    break;
                case BOOTS:
                    this.maxPieceRating = Math.round(1.5f * 10) / 10.0f;
                    break;
            }

            this.pieceRating = Math.round(pieceRatingCalculation * 10) / 10.0f;
            this.durability = Math.round(durabilityCalculation * 10) / 10.0f;
        }
    }

    public ArmorSlots getSlot() {
        return this.slot;
    }

    public float getPieceRating() {
        return Math.round(this.pieceRating * 10) / 10.0f;
    }
}