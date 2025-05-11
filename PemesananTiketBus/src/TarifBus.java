



public class TarifBus extends Trayek {
    private String lokasiNaik;
    private String lokasiTurun;
    private Seat kursi;

    public  TarifBus(String lokasiNaik, String lokasiTurun) {
        this.lokasiNaik = lokasiNaik;
        this.lokasiTurun = lokasiTurun;
        this.kursi = new Seat();
        inisialisasiTarif();
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

        tambahTarif("Ngawi", "Gendingan", 25000);
        tambahTarif("Ngawi", "Solo", 30000);
        tambahTarif("Ngawi", "Kartosuro", 40000);
        tambahTarif("Ngawi", "Jogja", 45000);
        tambahTarif("Ngawi", "Magelang", 60000);

        tambahTarif("Gendingan", "Solo", 25000);
        tambahTarif("Gendingan", "Kartosuro", 35000);
        tambahTarif("Gendingan", "Jogja", 40000);
        tambahTarif("Gendingan", "Magelang", 55000);

        tambahTarif("Solo", "Kartosuro", 15000);
        tambahTarif("Solo", "Jogja", 15000);
        tambahTarif("Solo", "Magelang", 30000);

        tambahTarif("Kartosuro", "Jogja", 15000);
        tambahTarif("Kartosuro", "Magelang", 30000);

        tambahTarif("Jogja", "Magelang", 15000);
    }
}
