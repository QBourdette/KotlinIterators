import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class Counter {
    public static Map<Integer, Integer> getElementsFrequenciesMap(Collection<Integer> collection) {
        final Map<Integer, Integer> map = new HashMap<>();
        for (Integer element : collection) {
            map.put(element, map.getOrDefault(element, 0) + 1);
        }
        return map;
    }
}
