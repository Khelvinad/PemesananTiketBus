import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PencarianTiketFrame extends JFrame {
    private JTextField tfCariNama;
    private JTextField tfCariNoKursi;
    private JTextField tfCariAsal;
    private JTextField tfCariTujuan;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Tiket> listTiket;

    public PencarianTiketFrame(ArrayList<Tiket> listTiket) {
        this.listTiket = listTiket;
        setTitle("Pencarian Tiket");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel filter pencarian
        JPanel panelFilter = new JPanel(new GridLayout(2, 4, 10, 10));
        panelFilter.add(new JLabel("Nama Penumpang:"));
        panelFilter.add(new JLabel("No Kursi:"));
        panelFilter.add(new JLabel("Asal:"));
        panelFilter.add(new JLabel("Tujuan:"));

        tfCariNama = new JTextField();
        tfCariNoKursi = new JTextField();
        tfCariAsal = new JTextField();
        tfCariTujuan = new JTextField();

        panelFilter.add(tfCariNama);
        panelFilter.add(tfCariNoKursi);
        panelFilter.add(tfCariAsal);
        panelFilter.add(tfCariTujuan);

        add(panelFilter, BorderLayout.NORTH);

        // Tabel hasil pencarian
        String[] kolom = {"Nama Penumpang", "NIK", "No HP", "No Kursi", "Asal", "Tujuan", "Harga"};
        model = new DefaultTableModel(kolom, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Isi data awal
        tampilkanData(listTiket);

        // Event listener filter realtime
        KeyAdapter filterListener = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                filterData();
            }
        };

        tfCariNama.addKeyListener(filterListener);
        tfCariNoKursi.addKeyListener(filterListener);
        tfCariAsal.addKeyListener(filterListener);
        tfCariTujuan.addKeyListener(filterListener);
    }

    private void tampilkanData(ArrayList<Tiket> data) {
        model.setRowCount(0); // Clear tabel
        for (Tiket t : data) {
            model.addRow(new Object[]{
                t.getPenumpang().getNama(),
                t.getPenumpang().getNIK(),
                t.getPenumpang().getNoHp(),
                t.getNoKursi(),
                t.getTrayek().getLokasiNaik(),
                t.getTrayek().getLokasiTurun(),
                t.getTrayek().getHarga()
            });
        }
    }

    private void filterData() {
        String nama = tfCariNama.getText().toLowerCase();
        String kursi = tfCariNoKursi.getText().toLowerCase();
        String asal = tfCariAsal.getText().toLowerCase();
        String tujuan = tfCariTujuan.getText().toLowerCase();

        ArrayList<Tiket> hasilFilter = new ArrayList<>();
        for (Tiket t : listTiket) {
            boolean cocokNama = t.getPenumpang().getNama().toLowerCase().contains(nama);
            boolean cocokKursi = t.getNoKursi().toLowerCase().contains(kursi);
            boolean cocokAsal = t.getTrayek().getLokasiNaik().toLowerCase().contains(asal);
            boolean cocokTujuan = t.getTrayek().getLokasiTurun().toLowerCase().contains(tujuan);

            if (cocokNama && cocokKursi && cocokAsal && cocokTujuan) {
                hasilFilter.add(t);
            }
        }
        tampilkanData(hasilFilter);
    }
}
