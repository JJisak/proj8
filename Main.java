import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Assignment8 assignment8 = new Assignment8();

        // Use CompletableFuture to asynchronously fetch the data
        CompletableFuture<List<Integer>> completableFuture = CompletableFuture.supplyAsync(() -> {
            List<Integer> numbers = new ArrayList<>();
            while (numbers.size() < 1000000) {
                numbers.addAll(assignment8.getNumbers());
            }
            return numbers;
        });

        // Wait for the data to be fetched
        List<Integer> numbers = completableFuture.get();

        // Count the number of occurrences of each unique number
        Map<Integer, Integer> counts = new ConcurrentHashMap<>();
        numbers.parallelStream().forEach(n -> {
            counts.compute(n, (k, v) -> v == null ? 1 : v + 1);
        });

        // Print out the count of each unique number
        counts.forEach((k, v) -> System.out.println(k + " = " + v ));
    }
}
