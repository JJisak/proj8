
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Assignment8 assignment8 = new Assignment8();
        List<CompletableFuture<List<Integer>>> futures = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            CompletableFuture<List<Integer>> future = CompletableFuture.supplyAsync(() -> assignment8.getNumbers(finalI), executorService);
            futures.add(future);
        }

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0]));

        CompletableFuture<List<Integer>> combinedFuture = allFutures.thenApply(v ->
                futures.stream()
                        .flatMap(future -> future.join().stream())
                        .collect(Collectors.toList()));

        Map<Integer, Long> counts = combinedFuture.get().stream()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        System.out.println(counts);

        executorService.shutdown();
    }
}

