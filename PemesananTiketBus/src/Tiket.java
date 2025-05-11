
public class Tiket {
    private Penumpang penumpang;
    private String noKursi;
    private Trayek trayek;

    public Tiket(Penumpang penumapang, String noKursi, Trayek trayek) {
        this.penumpang = penumapang;
        this.noKursi = noKursi;
        this.trayek = trayek;
    }

    public Penumpang getPenumpang() { return penumpang; }
    public String getNoKursi() { return noKursi; }
    public Trayek getTrayek() { return trayek; }

}
