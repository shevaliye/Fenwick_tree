import java.util.InputMismatchException;
import java.util.Scanner;

public class Build_Fenwick {
    private final Scanner scanner = new Scanner(System.in);
    private Fenwick tree;
    private int[] arr;

    public void menu() {
        tree = new Fenwick();
        while (true) {
            System.out.println("""
                    ===Меню Фенвика===
                    0 - построение по массиву
                    1 - обновление элемента
                    2 - сумма от 0 до index
                    3 - сумма на отрезке
                    4 - выход""");
            String input = scanner.nextLine().trim();
            switch (input) {
                case ("0"):
                    build();
                    break;
                case ("1"):
                    update();
                    break;
                case ("2"):
                    summ_from_zero_to_index();
                    break;
                case ("3"):
                    summ_on_a_segment();
                    break;
                case ("4"):
                    System.exit(0);
                    break;
                default:
                    System.out.println("Введите цифру от 0 до 4");
            }
        }
    }

    private void build() {
        boolean success = false;
        while (!success) {
            try {
                System.out.print("Введите размер массива и далее элементы массива: ");
                int n = scanner.nextInt();
                if (n <= 0) {
                    System.out.println("Введите верный размер!");
                    continue;
                }
                arr = new int[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = scanner.nextInt();
                }
                scanner.nextLine();
                tree.build(arr);
                tree.printTree();
                printArr();
                System.out.println("Дерево Фенвика успешно построено!");
                success = true;
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод!");
                scanner.nextLine();
            }
        }
    }

    private void update() {
        if (arr == null) {
            System.out.println("Сначала постройте дерево!");
            return;
        }
        boolean success = false;
        while (!success) {
            try {
                System.out.print("Введите индекс элемента и новый элемент: ");
                int index = scanner.nextInt();
                if (index < 0 || index >= arr.length) {
                    System.out.println("Введите верный индекс!");
                    continue;
                }
                int new_elem = scanner.nextInt();
                scanner.nextLine();
                tree.update(index, new_elem, arr);
                tree.printTree();
                printArr();
                System.out.println("Элемент по индексу " + index + " успешно заменен на " + new_elem);
                success = true;
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод!");
                scanner.nextLine();
            }
        }
    }

    private void summ_from_zero_to_index() {
        if (arr == null) {
            System.out.println("Сначала постройте дерево!");
            return;
        }
        boolean success = false;
        while (!success) {
            try {
                System.out.print("Введите индекс до которого будете суммировать: ");
                int index = scanner.nextInt();
                if (index < 0 || index >= arr.length) {
                    System.out.println("Введите верный индекс!");
                    continue;
                }
                scanner.nextLine();
                System.out.println("Ваша сумма до индекса " + index + " равна " + tree.prefixSum(index));
                success = true;
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод!");
                scanner.nextLine();
            }
        }
    }

    private void summ_on_a_segment() {
        if (arr == null) {
            System.out.println("Сначала постройте дерево!");
            return;
        }
        boolean success = false;
        while (!success) {
            try {
                System.out.print("Введите два индекса для суммирования: ");
                int index1 = scanner.nextInt();
                int index2 = scanner.nextInt();
                if (index1 < 0 || index2 < 0 || index1 >= arr.length || index2 >= arr.length) {
                    System.out.println("Введите верный индекс!");
                    continue;
                }
                if (index1 > index2) {
                    System.out.println("Введите индексы в порядке возрастания!");
                    continue;
                }
                scanner.nextLine();
                System.out.println("Ваша сумма от индекса " + index1 + " до индекса " + index2 + " равна " + tree.rangeSum(index1, index2));
                success = true;
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод!");
                scanner.nextLine();
            }
        }
    }

    private void printArr() {
        System.out.print("Исходный массив: ");
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
