public class XPCalculator {
    private final int baseXP;
    private final double power;

    public XPCalculator() {
        this.baseXP = 100;
        this.power = 1.5;
    }

    public int calculateXPRequired(int currentLevel) {
        return (int) (baseXP * Math.pow(currentLevel, power));
    }
}