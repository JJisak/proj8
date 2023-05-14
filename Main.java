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

        List<Integer> numbers = completableFuture.get();

        Map<Integer, Integer> counts = new ConcurrentHashMap<>();
        numbers.parallelStream().forEach(n -> {
            counts.compute(n, (k, v) -> v == null ? 1 : v + 1);
        });
        counts.forEach((k, v) -> System.out.println(k + " = " + v ));
    }
}
