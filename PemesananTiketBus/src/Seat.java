import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Seat {
    private Map<String, Boolean> statusKursi = new HashMap<>();
    private Set<String> kursiTerpilih = new HashSet<>();

    public Seat() {
        for (int i = 1; i <= 18; i++) {
            statusKursi.put("A" + i, false);
        }
        for (int i = 1; i <= 22; i++) {
            statusKursi.put("B" + i, false);
        }
    }

    public boolean isKosong(String kursi) {
        return !statusKursi.getOrDefault(kursi, false);
    }

    public boolean pesanKursi(String kursi) {
        if (isKosong(kursi)) {
            statusKursi.put(kursi, true);
            kursiTerpilih.add(kursi);
            return true;
        }
        return false;
    }

    public void kosongkanKursi(String kursi) {
        statusKursi.put(kursi, false);
        kursiTerpilih.remove(kursi);
    }

    public Set<String> getKursiTerpilih() {
        return new HashSet<>(kursiTerpilih);
    }

    public void resetKursiTerpilih() {
        this.kursiTerpilih.clear();
    }
}