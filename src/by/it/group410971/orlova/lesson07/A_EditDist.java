package by.it.a_khmelev.lesson07;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {


    // Мап для хранения результатов уже вычисленных подзадач
    private Map<String, Integer> memo = new HashMap<>();

    int getDistanceEdinting(String one, String two) {
        return calculateDistance(one, two, one.length(), two.length());
    }

    private int calculateDistance(String one, String two, int lenOne, int lenTwo) {
        // Если одна из строк пустая, расстояние равно длине другой строки
        if (lenOne == 0) return lenTwo;
        if (lenTwo == 0) return lenOne;

        // Создаем ключ для мемоизации
        String key = lenOne + "-" + lenTwo;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Если последние символы равны, продолжаем с оставшимися символами
        if (one.charAt(lenOne - 1) == two.charAt(lenTwo - 1)) {
            int distance = calculateDistance(one, two, lenOne - 1, lenTwo - 1);
            memo.put(key, distance);
            return distance;
        }

        // Если символы разные, вычисляем минимальное расстояние с учетом операций вставки, удаления и замены
        int insert = calculateDistance(one, two, lenOne, lenTwo - 1);
        int delete = calculateDistance(one, two, lenOne - 1, lenTwo);
        int replace = calculateDistance(one, two, lenOne - 1, lenTwo - 1);

        int distance = 1 + Math.min(insert, Math.min(delete, replace));
        memo.put(key, distance);
        return distance;
    }


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_EditDist.class.getResourceAsStream("dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }
}
