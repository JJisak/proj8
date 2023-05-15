import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        // Get the numbers from the Assignment8 class asynchronously
        Assignment8 assignment8 = new Assignment8();
        CompletableFuture<List<List<Integer>>> future = CompletableFuture.supplyAsync(assignment8::getAllNumbers);

        // Combine all batches of numbers and count the occurrences of each number
        Map<Integer, Long> countMap = future.thenApplyAsync(batches -> batches.stream()
                        .flatMap(List::stream)
                        .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting())))
                .join();

        // Print the number of times each unique number appears
        for (Map.Entry<Integer, Long> entry : countMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue() );
        }
    }

}
