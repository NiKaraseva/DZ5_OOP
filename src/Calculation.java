import java.util.Objects;

public class Calculation {

    double num1;
    double num2;


    public String sum(double num1, double num2) {
        double total = num1 + num2;
        return "Результат выражения " + num1 + " + " + num2 + " = " + total;
    }

    public String sub(double num1, double num2) {
        double total = num1 - num2;
        return "Результат выражения " + num1 + " - " + num2 + " = " + total;
    }

    public String mult(double num1, double num2) {
        double total = num1 * num2;
        return "Результат выражения " + num1 + " * " + num2 + " = " + total;
    }

    public String div(double num1, double num2) {
        if (num2 != 0){
            double total = num1 / num2;
            return "Результат выражения " + num1 + " / " + num2 + " = " + total;
        }
        else {
            return "Ошибка: на 0 делить нельзя!";
        }
    }
}


