import java.util.HashMap;
import java.util.Map;

public abstract class Bus {
    private final Map<String, Map<String, Integer>> tarifBus = new HashMap<>();

    public void tambahTarif(String asal, String tujuan, int harga) {
        tarifBus.computeIfAbsent(asal, k -> new HashMap<>()).put(tujuan, harga);
    }

    public int getTarif(String asal, String tujuan) {
        if (tarifBus.containsKey(asal)) {
            return tarifBus.get(asal).getOrDefault(tujuan, -1);
        }
        return -1;
    }
}
