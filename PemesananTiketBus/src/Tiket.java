import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tiket {
    private Penumpang penumpang;
    private String noKursi;
    private TarifBus trayek;

    public Tiket() {
    }

    public Tiket(Penumpang penumpang, String noKursi, TarifBus trayek) {
        this.penumpang = penumpang;
        this.noKursi = noKursi;
        this.trayek = trayek;
    }

    public Penumpang getPenumpang() { return penumpang; }
    public String getNoKursi() { return noKursi; }
    public TarifBus getTrayek() { return trayek; }

    public String toDataString() {
        return penumpang.getNama() + "," + penumpang.getNIK() + "," + penumpang.getNoHp() + ","
                + noKursi + "," + trayek.getLokasiNaik() + "," + trayek.getLokasiTurun() + ","
                + trayek.getHarga();
    }

    public static Tiket fromDataString(String data) {
        String[] parts = data.split(",");
        if (parts.length == 7) {
            Penumpang penumpang = new Penumpang(parts[0], parts[1], parts[2]);
            String noKursi = parts[3];
            TarifBus trayek = new TarifBus(parts[4], parts[5]);
            return new Tiket(penumpang, noKursi, trayek);
        }
        return null;
    }

    public void simpanKeFile(Tiket tiket) {
        try (FileWriter writer = new FileWriter("data_tiket.txt", true)) {
            writer.write(tiket.toDataString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Tiket> bacaDariFile() {
        ArrayList<Tiket> list = new ArrayList<>();
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

    public static Set<String> getKursiTerpesan(String asal, String tujuan) {
        Set<String> kursiTerpesan = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data_tiket.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 6) {
                    String dataAsal = data[4];
                    String dataTujuan = data[5];
                    if (dataAsal.equalsIgnoreCase(asal) && dataTujuan.equalsIgnoreCase(tujuan)) {
                        kursiTerpesan.add(data[3]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return kursiTerpesan;
    }

    public static boolean isKursiTerpesan(String seatNum, String asal, String tujuan) {
        try (BufferedReader reader = new BufferedReader(new FileReader("data_tiket.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 6 && 
                    data[3].equals(seatNum) && 
                    data[4].equalsIgnoreCase(asal) && 
                    data[5].equalsIgnoreCase(tujuan)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
