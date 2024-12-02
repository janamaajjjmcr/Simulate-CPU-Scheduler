import java.util.ArrayList;
import java.util.List;

public class Utility {
    // Method to create a deep copy of the list of Row objects
    public static List<Row> deepCopy(List<Row> oldList) {
        List<Row> newList = new ArrayList<>();
        
        // Iterate over the old list and create new Row objects
        for (Row row : oldList) {
            newList.add(new Row(row.getProcessName(), row.getArrivalTime(), row.getBurstTime(), row.getPriorityLevel()));
        }
        
        return newList; // Return the new list
    }
}
