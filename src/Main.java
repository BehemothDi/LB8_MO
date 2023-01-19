import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        double[] res_array;
        res_array = Swann.find_uncertainty_range(3, 1);
        System.out.println("Интервал [" + res_array[0] + "; " + res_array[1] + "]");
        System.out.println("Внутренняя точка: " + res_array[2] + "\nЗначение функции во внутренней точке: " + res_array[3]);

    }
}