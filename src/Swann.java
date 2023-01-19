import java.math.BigInteger;

public class Swann {

    public static double f(double x) {
        return x;
    }

    // Значение должно быть h > 0
    public static double[] find_uncertainty_range(double starting_point, double step){
        double x0, x1, x2, f_x0, f_x1, f_x2, h, temp;

        System.out.println("Начальная точка: " + starting_point + " ,шаг: " + step);

        // Находим точку на расстоянии h от x0
        h = step;
        x0 = starting_point;
        x1 = x0 + h;
        f_x0 = f(x0);
        f_x1 = f(x1);

        // Определяем направление поиска
        // Если функция убывает, то ищем вправо
        if (f_x1 < f_x0) h*=2;
        // Если функция возрастает, меняем направление
        else {
            h*=-2;

            // При смене направления, меняем местами стартовую и первую точки
            temp = x1;
            x1 = x0;
            x0 = temp;

            // При смене направления, меняем местами стартовую и первую точки
            temp = f_x1;
            f_x1 = f_x0;
            f_x0 = temp;
        }

        // Находим вторую точку
        x2 = x1 + h;
        f_x2 = f(x2);
        System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f %.5f\n", h, x0, x1, x2, f_x0, f_x1, f_x2);

        // Если функция начинает возрастать, то необходимый интервал получен
        if (f_x0 > f_x1 && f_x2 > f_x1) return new double[] {x0, x2, x1, f_x1};

        // Проверяем точку правее текущей точки х2, пока не определим возрастание функции
        while (f_x2 < f_x1) {
            h *= 2;
            x0 = x1;
            x1 = x2;
            x2 = x1 + h;
            f_x0 = f_x1;
            f_x1 = f_x2;
            f_x2 = f(x2);
            System.out.printf("%.5f %.5f %.5f %.5f %.5f %.5f %.5f\n", h, x0, x1, x2, f_x0, f_x1, f_x2);

            // Останавливаемся, если ищем слишком долго
            if (Math.abs(h) > Long.MAX_VALUE) {
                throw new RuntimeException();
            }
        }

        // Если необходимо, меняем местами границы интервала для сохранения верного порядка
        if (x0 > x2) x2 = x0 + x2 - (x0 = x2);

        return new double[] {x0, x2, x1, f_x1};

    }



}
