import java.util.HashMap;
import java.util.Map;

public class Seat {
    private Map<String, Boolean> statusKursi = new HashMap<>();

    public Seat() {
        for (int i = 1; i <= 18; i++) statusKursi.put("A" + i, false);
        for (int i = 1; i <= 22; i++) statusKursi.put("B" + i, false);
    }

    public boolean isKosong(String kursi) {
        return statusKursi.getOrDefault(kursi, true);
    }

    public boolean pesanKursi(String kursi) {
        if (isKosong(kursi)) {
            statusKursi.put(kursi, false);
            return true;
        }
        return false;
    }

    public void kosongkanKursi(String kursi) {
        statusKursi.put(kursi, false);
    }

    public Map<String, Boolean> getStatusKursi() {
        return statusKursi;
    }
}
