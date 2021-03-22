package by.it.papruga.calc;

public class Scalar extends Var {

    private final double value;

    public double getValue() {
        return value;
    }

    public Scalar(double value) {
        this.value = value;
    }


    @Override
    public Var add(Var other) {

        if (other instanceof Scalar) {

            double sub = this.value + ((Scalar) other).value;

            return new Scalar(sub);
        } else
            return other.add(this);
    }

    @Override
    public Var sub(Var other) {

        if (other instanceof Scalar) {

            double sub = this.value - ((Scalar) other).value;

            return new Scalar(sub);
        } else
            return new Scalar(-1).mul(other).add(this);

    }

    @Override
    public Var mul(Var other) {
        if (other instanceof Scalar) {

            double mul = this.value * ((Scalar) other).value;

            return new Scalar(mul);
        } else
            return other.mul(this);
    }

    @Override
    public Var div(Var other) {
        if (other instanceof Scalar) {

            double div = this.value / ((Scalar) other).value;

            return new Scalar(div);
        } else
            return super.div(this);
    }

    public Scalar(String strValue) {
        this.value = Double.parseDouble(strValue);
    }

    public Scalar(Scalar otherScalar) {
        this.value = otherScalar.value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
