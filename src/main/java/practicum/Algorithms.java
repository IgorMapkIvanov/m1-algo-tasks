package practicum;

import java.sql.Array;
import java.util.*;

public class Algorithms {

    /**
     * Отсортируйте список, НЕ используя методы стандартной библиотеки (напр. Collections.sort).
     */
    public static List<Integer> sort(List<Integer> list) {
        // Сортировка "пузырьком"
        List<Integer> sortedList = new ArrayList<>(list);
        boolean sorted = false;
        Integer temp;
        while (!sorted){
            sorted = true;
            for (int i = 0; i < sortedList.size() - 1; i++){
                if (sortedList.get(i) > sortedList.get(i + 1)){
                    temp = sortedList.get(i);
                    sortedList.add(i, sortedList.get(i + 1));
                    sortedList.remove(i + 1);
                    sortedList.add(i + 1, temp);
                    sortedList.remove(i + 2);
                    sorted = false;
                }
            }
        }
        return List.copyOf(sortedList);
    }

    /**
     * Удалите дубликаты из списка.
     *
     * Усложнение: не используйте дополнительные структуры данных
     *  для хранения промежуточных значений.
     *  (списки, массивы, хэш-таблицы, множества и т.п.).
     * К списку-результату это не относится.
     */
    public static List<Integer> removeDuplicates(List<Integer> list) {
        List<Integer> uniqueList = new ArrayList<>();
        for (Integer number : list){
            if (!uniqueList.contains(number)){
                uniqueList.add(number);
            }
        }
        return List.copyOf(uniqueList);
    }

    /**
     * Проверьте, является ли список "палиндромом".
     * Палиндром -- это список, который в обе стороны читается одинаково.
     * Например:
     *  палиндромы: [1 2 1], [3 2 1 2 3], [2 2 2], []
     *  не палиндромы: [1 2 3], [2 2 3], [3 2 1 3 2]
     *
     * Доп. условие: у алгоритма должна быть линейная сложность, O(n)
     */
    public static boolean isPalindrome(List<Integer> list) {
        boolean isPalindrome = true;
        for (int i = 0; i < list.size() / 2; i++){
            if (!(list.get(i).equals(list.get(list.size() - 1 - i)))){
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }

    /**
     * Объедините два отсортированных списка в один отсортированный список.
     * Например:
     * [1 3 5] + [2 4 6] = [1 2 3 4 5 6]
     * [1 2 3] + [1 3 5] = [1 1 2 3 3 5]
     * [] + [1] = [1]
     * [7] + [1 4] = [1 4 7]
     *
     * Доп. условие: у алгоритма должна быть линейная сложность, O(n).
     */
    public static List<Integer> mergeSortedLists(List<Integer> a, List<Integer> b) {
        if (a.isEmpty() && b.isEmpty()) return Collections.emptyList();
        if (a.isEmpty()) return b;
        if (b.isEmpty()) return a;

        List<Integer> ab = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < a.size() && j < b.size()){
            if (a.get(i) < b.get(j)){
                ab.add(a.get(i));
                i++;
            } else if (b.get(j) < a.get(i)){
                ab.add(b.get(j));
                j++;
            } else {
                ab.add(a.get(i));
                i++;
                ab.add(b.get(j));
                j++;
            }
        }
        while (i < a.size()){
            ab.add(a.get(i));
            i++;
        }
        while (j < b.size()){
            ab.add(b.get(j));
            j++;
        }
        return List.copyOf(ab);
    }

    /**
     * Проверьте, что в массиве нет дубликатов.
     * Верните true, если дубликатов нет, иначе false.
     *
     * Усложнение: не используйте дополнительные структуры данных
     *  (списки, массивы, хэш-таблицы, множества и т.п.).
     */
    public static boolean containsEveryElementOnce(int[] array) {
        for (int i = 0; i < array.length; i++){
            for (int j = i + 1; j < array.length; j++){
                if (array[i] == array[j]){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Определите, является ли один массив перестановкой другого.
     * Т.е. в массивах хранится один и тот же набор элементов, но (возможно) в разном порядке.
     *
     * Для решения нжуно использовать одну хэш-таблицу.
     *
     * Например:
     * [1 2 3] и [3 2 1] = true
     * [1 1 2] и [1 2 1] = true
     * [1 2 3] и [1 2 3] = true
     * [] и [] = true
     *
     * [1 2] и [1 1 2] = false, разный набор элементов
     */
    public static boolean isPermutation(int[] a, int[] b) {
        if (a.length != b.length) return false;
        if (a.length == 0 && b.length == 0) return true;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i =0; i < a.length; i++){
            if (map.containsKey(a[i])){
                int count = map.get(a[i]) + 1;
                map.put(a[i], count);
            } else {
                map.put(a[i], 1);
            }
        }
        for (Integer key : map.keySet()){
            int count = 0;
            for (int i = 0; i < b.length; i++){
                if (key == b[i]) count++;
            }
            if (count == 0 || count != map.get(key)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Сложная задача.
     *
     * В памяти компьютера изображения (часто) хранятся в виде двумерного массива.
     * Напишите метод, который повернёт "изображение" на 90 градусов вправо.
     * "Изображение" в данном примере -- двумерный массив целых чисел.
     *
     * Например:
     * на входе:
     * [ [1 2]
     *   [3 4]
     *   [5 6] ]
     *
     * на выходе:
     * [ [5 3 1]
     *   [6 4 2] ]
     */
    public static int[][] rotateRight(int[][] image) {
        return null;
    }
}
