import java.util.*;

public class Main {
    public static void main(String[] args) {
        Assignment8 assignment8 = new Assignment8();
        List<Integer> numbers = assignment8.getNumbers();

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int number : numbers) {
            countMap.put(number, countMap.getOrDefault(number, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue() );
        }
    }
}