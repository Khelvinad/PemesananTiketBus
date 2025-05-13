



public class TarifBus extends Trayek {
    private String lokasiNaik;
    private String lokasiTurun;
    private Seat kursi;

    public  TarifBus(String lokasiNaik, String lokasiTurun) {
        this.lokasiNaik = lokasiNaik;
        this.lokasiTurun = lokasiTurun;
        inisialisasiTarif();
    }

    public void setSeat(Seat kursi) {
        this.kursi = kursi;
    }

    public String getLokasiNaik() {
        return lokasiNaik;
    }
    public String getLokasiTurun() {
        return lokasiTurun;
    }
    public int getHarga() {
        return getTarif(lokasiNaik, lokasiTurun);
    }
    public Seat getSeat() {
        return kursi;
    }


    private void inisialisasiTarif() {
        tambahTarif("Wilangan", "Ngawi", 35000);
        tambahTarif("Wilangan", "Gendingan", 45000);
        tambahTarif("Wilangan", "Solo", 55000);
        tambahTarif("Wilangan", "Kartosuro", 60000);
        tambahTarif("Wilangan", "Jogja", 70000);
        tambahTarif("Wilangan", "Magelang", 85000);

        tambahTarif("Ngawi", "Wilangan", 35000);
        tambahTarif("Ngawi", "Gendingan", 25000);
        tambahTarif("Ngawi", "Solo", 30000);
        tambahTarif("Ngawi", "Kartosuro", 40000);
        tambahTarif("Ngawi", "Jogja", 45000);
        tambahTarif("Ngawi", "Magelang", 60000);

        tambahTarif("Gendingan", "Wilangan", 45000);
        tambahTarif("Gendingan", "Ngawi", 25000);
        tambahTarif("Gendingan", "Solo", 25000);
        tambahTarif("Gendingan", "Kartosuro", 35000);
        tambahTarif("Gendingan", "Jogja", 40000);
        tambahTarif("Gendingan", "Magelang", 55000);

        tambahTarif("Solo", "Wilangan", 55000);
        tambahTarif("Solo", "Ngawi", 30000);
        tambahTarif("Solo", "Gendingan", 25000);
        tambahTarif("Solo", "Kartosuro", 15000);
        tambahTarif("Solo", "Jogja", 15000);
        tambahTarif("Solo", "Magelang", 30000);

        tambahTarif("Kartosuro", "Wilangan", 60000);
        tambahTarif("Kartosuro", "Ngawi", 40000);
        tambahTarif("Kartosuro", "Gendingan", 35000);
        tambahTarif("Kartosuro", "Solo", 15000);
        tambahTarif("Kartosuro", "Jogja", 15000);
        tambahTarif("Kartosuro", "Magelang", 30000);

        tambahTarif("Jogja", "Wilangan", 70000);
        tambahTarif("Jogja", "Ngawi", 45000);
        tambahTarif("Jogja", "Gendingan", 40000);
        tambahTarif("Jogja", "Solo", 15000);
        tambahTarif("Jogja", "Kartosuro", 15000);
        tambahTarif("Jogja", "Magelang", 15000);

        tambahTarif("Magelang", "Wilangan", 85000);
        tambahTarif("Magelang", "Ngawi", 60000);
        tambahTarif("Magelang", "Gendingan", 55000);
        tambahTarif("Magelang", "Solo", 30000);
        tambahTarif("Magelang", "Kartosuro", 30000);
        tambahTarif("Magelang", "Jogja", 15000);
    }
}
