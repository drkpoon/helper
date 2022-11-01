public class ContinuedFraction {
    /**
     * @param integral integral parts, length should be one longer than fraction array
     * @param fraction fraction parts
     * @return repeated fraction
     */
    static float getRepeatedFraction(int[] integral, int[] fraction) {
        if (integral.length - fraction.length != 1) throw new IllegalArgumentException("input arrays sizes not match");
        float sum = 0;
        for (int i = fraction.length - 1; i >= 0; i--) {
            sum = fraction[i] / (sum + integral[i + 1]);
        }
        return sum + integral[0];
    }
}
