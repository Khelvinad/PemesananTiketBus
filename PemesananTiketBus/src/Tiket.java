import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tiket {
    private Penumpang penumpang;
    private String noKursi;
    private TarifBus trayek;

    public Tiket(Penumpang penumpang, String noKursi, TarifBus trayek) {
        this.penumpang = penumpang;
        this.noKursi = noKursi;
        this.trayek = trayek;
    }

    public Penumpang getPenumpang() { return penumpang; }
    public String getNoKursi() { return noKursi; }
    public Trayek getTrayek() { return trayek; }

    public String toDataString() {
        return penumpang.getNama() + "," + penumpang.getNIK() + "," + penumpang.getNoHp() + "," + noKursi + "," + trayek.getLokasiNaik() + "," + trayek.getLokasiTurun() + "," + trayek.getHarga();
    }

    public static Tiket fromDataString(String data) {
        String[] parts = data.split(",");
        Penumpang penumpang = new Penumpang(parts[0], parts[1], parts[2]);
        String noKursi = parts[3];
        TarifBus trayek = new TarifBus(parts[4], parts[5]);
        return new Tiket(penumpang, noKursi, trayek);
    }

    public void simpanKeFile(Tiket tiket) {
        try (FileWriter writer = new FileWriter("data_tiket.txt", true)) {
            writer.write(tiket.toDataString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<Tiket> bacaDariFile() {
        List<Tiket> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data_tiket.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Tiket tiket = Tiket.fromDataString(line);
                if (tiket != null) list.add(tiket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }



}
