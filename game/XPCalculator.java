public class XPCalculator {
    private final int baseXP;
    private final float power;

    public XPCalculator() {
        this.baseXP = 100;
        this.power = 1.5f;
    }

    public int calculateXPRequired(int currentLevel) {
        return (int) (baseXP * Math.pow(currentLevel, power));
    }
}