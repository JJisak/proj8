import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Assignment8 assignment8 = new Assignment8();
        List<CompletableFuture<List<Integer>>> futures = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            futures.add(CompletableFuture.supplyAsync(() -> assignment8.getNumbers()));
        }

        CompletableFuture<List<Integer>> allFutures = CompletableFuture.allOf(
                        futures.toArray(new CompletableFuture[futures.size()]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .flatMap(List::stream)
                        .collect(Collectors.toList()));

        Map<Integer, Long> counts = allFutures.get().stream()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        System.out.println(counts);
    }
}
