package by.it.a_khmelev.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак без повторов

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        число золотых слитков
                    (каждый можно использовать только один раз).
Следующая строка содержит n целых чисел, задающих веса каждого из слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.

Sample Input:
10 3
1 4 8
Sample Output:
9

*/

public class B_Knapsack {

    int getMaxWeight(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int W = scanner.nextInt(); // Вместимость рюкзака
        int n = scanner.nextInt(); // Количество золотых слитков
        int[] gold = new int[n]; // Массив весов слитков

        for (int i = 0; i < n; i++) {
            gold[i] = scanner.nextInt(); // Чтение весов слитков
        }

        // Массив для хранения максимального веса для каждой вместимости
        int[] dp = new int[W + 1];

        // Динамическое программирование
        for (int i = 0; i < n; i++) {
            for (int j = W; j >= gold[i]; j--) { // Обновляем в обратном порядке
                dp[j] = Math.max(dp[j], dp[j - gold[i]] + gold[i]);
            }
        }

        // Максимальный вес, который можно унести в рюкзаке
        return dp[W];
    }


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_Knapsack.class.getResourceAsStream("dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }

}
