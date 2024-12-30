import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Користувацький діапазон чисел
        int lowerBound = 0;
        int upperBound = 100;
        int arraySize = new Random().nextInt(21) + 40; // від 40 до 60

        // Генерація масиву
        int[] numbers = new Random().ints(arraySize, lowerBound, upperBound + 1).toArray();

        // Розбиття масиву на частини
        int numThreads = 4; // Кількість потоків
        int chunkSize = (int) Math.ceil((double) numbers.length / numThreads);

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<int[]>> futures = new ArrayList<>();
        List<int[]> results = new ArrayList<>();

        // Час початку роботи
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numbers.length; i += chunkSize) {
            int start = i;
            int end = Math.min(i + chunkSize, numbers.length);

            // Створення Callable для обробки частини масиву
            Callable<int[]> task = () -> {
                int[] part = new int[end - start];
                for (int j = start; j < end; j += 2) {
                    if (j + 1 < numbers.length) {
                        part[j - start] = numbers[j] * numbers[j + 1];
                    } else {
                        part[j - start] = numbers[j]; // Останній елемент без пари
                    }
                }
                return part;
            };

            // Надсилання завдання в ExecutorService
            futures.add(executor.submit(task));
        }

        // Перевірка isDone() і збір результатів
        for (Future<int[]> future : futures) {
            while (!future.isDone() && !future.isCancelled()) {
                // Очікуємо завершення
            }
            results.add(future.get());
        }

        // Об'єднання результатів
        CopyOnWriteArraySet<Integer> finalResults = new CopyOnWriteArraySet<>();
        for (int[] result : results) {
            for (int value : result) {
                finalResults.add(value);
            }
        }

        // Час завершення роботи
        long endTime = System.currentTimeMillis();

        // Вивід результатів
        System.out.println("Оригінальний масив: ");
        printArray(numbers);

        System.out.println("\nРезультат обчислень: ");
        printSet(finalResults);

        System.out.println("\nЧас виконання програми: " + (endTime - startTime) + " мс");

        executor.shutdown();
    }

    private static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    private static void printSet(CopyOnWriteArraySet<Integer> set) {
        for (int value : set) {
            System.out.print(value + " ");
        }
    }
}
