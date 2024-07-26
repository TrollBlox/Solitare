package workers;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    // ChatGPT
    public static <T> List<List<T>> groupList(List<T> list, int groupSize) {
        List<List<T>> groupedLists = new ArrayList<>();

        // Edge case: If groupSize is less than 1, return empty grouped list
        if (groupSize < 1) {
            return groupedLists;
        }

        // Iterate over the list and create groups
        for (int i = 0; i < list.size(); i += groupSize) {
            // Determine the end index of the current group
            int end = Math.min(i + groupSize, list.size());

            // Create a sublist from the original list and add it to the grouped lists
            List<T> group = new ArrayList<>(list.subList(i, end));
            groupedLists.add(group);
        }

        return groupedLists;
    }

}
