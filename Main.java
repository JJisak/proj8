import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {


        Assignment8 assignment8 = new Assignment8();
        List<Integer> numbers = Files.readAllLines(Paths.get("output.txt"))
                .stream()
                .map(n -> Integer.parseInt(n))
                .collect(Collectors.toList());



        Map<Integer, Integer> countMap = new HashMap<>();
        for (int number : numbers) {
            Integer count = countMap.get(number);
            if (count == null) {
                count = 0;
            }
            countMap.put(number, count + 1);
        }

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue() );
        }
    }
}
