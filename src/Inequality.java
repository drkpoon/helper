class Inequality {
    public float xCoefficient;
    public float yCoefficient;
    public greaterLess sign;
    public float constant;

    public Inequality(float xCoefficient, float yCoefficient, greaterLess sign, float constant) {
        this.xCoefficient = xCoefficient;
        this.yCoefficient = yCoefficient;
        this.constant = constant;
        this.sign = sign;
    }

    enum greaterLess {GREATER, LESS}

    enum optimize {MAXIMISE, MINIMISE}
}
