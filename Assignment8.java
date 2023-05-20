import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Assignment8 {
    private List<Integer> numbers = null;

    public Assignment8() {
        try {
            // Make sure you download the output.txt file for Assignment 8
            // and place the file in the root of your Java project
            numbers = Files.lines(Paths.get("output.txt"))
                    .parallel()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will return the numbers that you'll need to process from the list
     * of Integers. However, it can only return a specified number of records at a time.
     *
     * @param index The index of the batch to fetch
     * @return Integers from the parsed txt file, batchSize numbers at a time
     */
    public List<Integer> getNumbers(int index) {
        int batchSize = 1000;
        int start = index * batchSize;
        int end = Math.min(start + batchSize, numbers.size());

        System.out.println("Starting to fetch records " + start + " to " + (end - 1));

        List<Integer> sublist = numbers.subList(start, end);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Done fetching records " + start + " to " + (end - 1));
        return new ArrayList<>(sublist);
    }
}
