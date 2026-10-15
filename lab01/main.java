import java.util.Scanner;

public class main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            Matrix a = readMatrix("A");
            System.out.println("Матрица A:");
            System.out.println(a);


            System.out.println("1. Сложение");
            System.out.println("2. Вычитание");
            System.out.println("3. Умножение");
            System.out.println("4. Деление");
            System.out.println("5. Транспонирование");
            System.out.println("6. Определитель");
            System.out.print("Выбор: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Введите число");
                continue;
            }

            try {
                if (choice == 5) {
                    System.out.println("Результат:");
                    System.out.println(a.transposition());
                } else if (choice == 6) {
                    System.out.println("det = " + a.determinant());
                } else if (choice >= 1 && choice <= 4) {
                    Matrix b = readMatrix("B");
                    Matrix r = null;
                    if (choice == 1) r = a.addition(b);
                    if (choice == 2) r = a.subtraction(b);
                    if (choice == 3) r = a.multiplication(b);
                    if (choice == 4) r = a.division(b);
                    System.out.println("Результат:");
                    System.out.println(r);
                } else {
                    System.out.println("Неверный выбор");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    static Matrix readMatrix(String name) {
        System.out.println("Ввод матрицы ");
        System.out.print("Строк: ");
        int r = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Столбцов: ");
        int c = Integer.parseInt(sc.nextLine().trim());

        Matrix m = new Matrix(r, c);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(name + "[" + i + "][" + j + "] (re im): ");
                String[] p = sc.nextLine().trim().split("\\s+");
                double re = Double.parseDouble(p[0]);
                double im = p.length > 1 ? Double.parseDouble(p[1]) : 0;
                m.setData(i, j, new Complex(re, im));
            }
        }
        return m;
    }
}