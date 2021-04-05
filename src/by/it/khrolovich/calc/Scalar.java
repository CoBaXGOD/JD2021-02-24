package by.it.khrolovich.calc;

public class Scalar extends Var {
    private final double value;

    public Scalar(double value) {
        this.value = value;
    }

    public Scalar(String s) {
        this.value = Double.parseDouble(s);
    }

    public Scalar(Scalar otherScalar) {
        this.value = otherScalar.value;
    }


    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public Var add(Var other) throws CalcException {
        if (other instanceof Scalar) {
            //проверили, значит имеем право кастить - приводить типы
            double otherValue = ((Scalar) other).value;//other.value
            double result = this.value + otherValue;
            return new Scalar(result);//если это скаляр, значит возвращаем скаляр
            //или одной строкой
        }
        var result2 = other.add(this);//other -точно не скаляр, this - точно скаляр
        //other - или вектор,или матрица
        return result2;//его же и вернем
    }

    @Override
    public Var sub(Var other) throws CalcException {
        if (other instanceof Scalar) {
            double result = this.value - ((Scalar) other).value;
            return new Scalar(result);
        }
        return other.sub(this).mul(new Scalar(-1));
    }

    @Override
    public Var mul(Var other) throws CalcException {
        if (other instanceof Scalar) {
            return new Scalar(this.value * ((Scalar) other).value);
        }
        return other.mul(this);
    }

    @Override
    public Var div(Var other) throws CalcException {
        if (other instanceof Scalar) {
            double secondOperand = ((Scalar) other).value;//other.value
            if (secondOperand == 0) {
                //return null;//TODO div be zero
               // return super.div(other);
                throw new CalcException("division by zero");
                // throws CalcException добавляется везде
            }

            return new Scalar(this.value / secondOperand);
        }
        //нельзя делить скаляр на вектор и на матрицу, поэтому вызываем метод предка
        //там выводится: деление невозможно
        return super.div(other);
    }

}






