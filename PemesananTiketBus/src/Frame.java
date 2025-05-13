import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class Frame extends JFrame {

    private JButton[] seatA = new JButton[18];
    private JButton[] seatB = new JButton[22];
    private Seat seat;
    private Map<String, JButton> tombolKursi = new HashMap<>();
    private JButton driver, conductor;
    private ImageIcon seatIcon, chosenIcon, takenIcon;
    private Penumpang penumpang;
    private TarifBus tarifBus;
    private Tiket tiket;
    private JComboBox<String> asal;
    private JComboBox<String> tujuan;


    public Frame() {
        this.setTitle("Pemesanan Tiket Bus codEXpress");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 650);
        this.setLayout(new BorderLayout());
        this.seat = new Seat();

        loadIcon();
        UIuser();
        UIbus();
        this.setVisible(true);
    }

    private void UIuser(){
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(400, 650));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel userPanel = new JPanel(new GridBagLayout());
        Dimension maxSize = new Dimension(300, 400);
        userPanel.setPreferredSize(maxSize);
        userPanel.setMaximumSize(maxSize);
        userPanel.setMinimumSize(maxSize);
        userPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        userPanel.setBackground(Color.decode("#4E71FF"));

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        String[] labels = {"Nama", "NIK", "No.Telp"};
        ArrayList<JTextField> textFields = new ArrayList<>();

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            JLabel label = new JLabel(labels[i]);
            label.setForeground(Color.WHITE);
            userPanel.add(label, gbc);

            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            JTextField field = new JTextField(15);

            if (i == 1 || i == 2) {
                field.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!Character.isDigit(c)) {
                            e.consume();
                    }
                }
            });
        }
            textFields.add(field);
            userPanel.add(field, gbc);
        }

        String[] Trayek = {"Wilangan", "Ngawi", "Gendingan", "Solo", "Kartosuro", "Jogja", "Magelang"};
        gbc.gridx = 0;
        gbc.gridy = labels.length;
        JLabel asalLabel = new JLabel("Asal");
        asalLabel.setForeground(Color.WHITE);
        userPanel.add(asalLabel, gbc);
        gbc.gridx = 1;
        asal = new JComboBox<>(Trayek);
        userPanel.add(asal, gbc);
        gbc.gridx = 0;
        gbc.gridy = labels.length + 1;
        JLabel tujuanLabel = new JLabel("Tujuan");
        tujuanLabel.setForeground(Color.WHITE);
        userPanel.add(tujuanLabel, gbc);
        gbc.gridx = 1;
        tujuan = new JComboBox<>(Trayek);
        userPanel.add(tujuan, gbc);

        asal.addActionListener(e -> updateSeatIcons());
        tujuan.addActionListener(e -> updateSeatIcons());


        JButton submitButton = new JButton("Submit");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        userPanel.add(submitButton, gbc);

        asal.addActionListener(e -> {
            String selectedAsal = (String) asal.getSelectedItem();
            tujuan.removeAllItems();
            for (String t : Trayek) {
                if (!t.equals(selectedAsal)) {
                    tujuan.addItem(t);
                }
            }
            updateSeatIcons();
        });

        tujuan.addActionListener(e -> updateSeatIcons());

        submitButton.addActionListener(e -> {
            String asalDipilih = (String) asal.getSelectedItem();
            String tujuanDipilih = (String) tujuan.getSelectedItem();

            if (asalDipilih.equals(tujuanDipilih)) {
                JOptionPane.showMessageDialog(null, "Asal dan tujuan tidak boleh sama");
                return;
            }

            tarifBus = new TarifBus(asalDipilih, tujuanDipilih);
            tarifBus.setSeat(seat);
            penumpang = new Penumpang(textFields.get(0).getText(), 
                                    textFields.get(1).getText(), 
                                    textFields.get(2).getText());

            String noKursi = seat.getKursiTerpilih().stream().iterator().next();
            tiket = new Tiket(penumpang, noKursi, tarifBus);
            try (FileWriter fw = new FileWriter("data_tiket.txt", true)) {
                fw.write(tiket.toDataString() + "\n");
                JOptionPane.showMessageDialog(null, "Tiket berhasil dipesan");
                for (JTextField field : textFields) {
                    field.setText("");
                }
                
                asal.setSelectedIndex(0);
                tujuan.setSelectedIndex(0);
                
                seat.resetKursiTerpilih();
                updateSeatIcons();
                
                penumpang = null;
                tarifBus = null;
                tiket = null;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            updateSeatIcons();
            seat.resetKursiTerpilih();
        });

        this.add(mainPanel, BorderLayout.WEST);
        mainPanel.add(userPanel);
    }

    private void loadIcon(){
        ImageIcon rawIcon = new ImageIcon(getClass().getResource("Resource/Seat.png"));
        Image scaledImage = rawIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        seatIcon = new ImageIcon(scaledImage);

        ImageIcon rawChosen = new ImageIcon(getClass().getResource("Resource/ChosenSeat.png"));
        Image scaledChosen = rawChosen.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        chosenIcon = new ImageIcon(scaledChosen);

        ImageIcon rawTaken = new ImageIcon(getClass().getResource("Resource/TakenSeat.png"));
        Image scaledTaken = rawTaken.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        takenIcon = new ImageIcon(scaledTaken);
    }

    private void UIbus() {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(350, 650));
        mainPanel.setLayout(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel Upanel = new JPanel(new BorderLayout());
        Upanel.setPreferredSize(new Dimension(230, 70));
        JPanel topLeftPanel = new JPanel(new BorderLayout());
        topLeftPanel.setPreferredSize(new Dimension(150, 60));
        topLeftPanel.setOpaque(false);

        JLabel pintuDepan = new JLabel("PINTU DEPAN", SwingConstants.CENTER);
        pintuDepan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pintuDepan.setPreferredSize(new Dimension(150, 30));
        topLeftPanel.add(pintuDepan, BorderLayout.NORTH);
        
        conductor = new JButton("KONDEKTUR");
        conductor.setPreferredSize(new Dimension(150, 30));
        topLeftPanel.add(conductor, BorderLayout.SOUTH);
        conductor.setContentAreaFilled(false);
        conductor.setFocusPainted(false);
        
        JPanel topRightPanel = new JPanel(new BorderLayout());
        driver = new JButton("DRIVER");
        driver.setPreferredSize(new Dimension(80, 80));
        topRightPanel.add(driver, BorderLayout.EAST);
        driver.setContentAreaFilled(false);
        driver.setFocusPainted(false);
        
        Upanel.add(topLeftPanel, BorderLayout.WEST);
        Upanel.add(topRightPanel, BorderLayout.EAST);

        JPanel Lpanel = new JPanel(new GridLayout(9, 2,5 , 5));
        Lpanel.setPreferredSize(new Dimension(115, 500));
        for (int i = 1; i <= 18; i++) {
            seatA[i-1] = SeatButton("A" + i);
            tombolKursi.put("A" + i, seatA[i-1]);
            Lpanel.add(seatA[i-1]);
        }
        JPanel Rpanel = new JPanel(new GridLayout(9, 2, 5, 5));
        Rpanel.setPreferredSize(new Dimension(115, 500));
        for (int i = 1; i <= 18; i++) {
            seatB[i-1] = SeatButton("B" + i);
            tombolKursi.put("B" + i, seatB[i-1]);
            Rpanel.add(seatB[i-1]);
        }

        JPanel Fpanel = new JPanel(new BorderLayout());
        Fpanel.setPreferredSize(new Dimension(220, 85));
        JPanel bottomLeftPanel = new JPanel(new BorderLayout());
        bottomLeftPanel.setOpaque(false);
        JLabel pintuBelakang = new JLabel("PINTU BELAKANG", SwingConstants.CENTER);
        pintuBelakang.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pintuBelakang.setPreferredSize(new Dimension(120, 20));
        bottomLeftPanel.add(pintuBelakang, BorderLayout.NORTH);
        JLabel Toilet = new JLabel("TOILET", SwingConstants.CENTER);
        Toilet.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Toilet.setPreferredSize(new Dimension(100, 50));
        bottomLeftPanel.add(Toilet, BorderLayout.SOUTH);

        JPanel bottomRoghtPanel = new JPanel(new GridLayout(2, 2,5,5));
        bottomRoghtPanel.setPreferredSize(new Dimension(115, 80));
        for (int i = 19; i <= seatB.length; i++) {
            seatB[i-1] = SeatButton("B" + i);
            tombolKursi.put("B" + i, seatB[i-1]);
            bottomRoghtPanel.add(seatB[i-1]);
        }

        Fpanel.add(bottomLeftPanel, BorderLayout.WEST);
        Fpanel.add(bottomRoghtPanel, BorderLayout.EAST);

        mainPanel.setBackground(Color.LIGHT_GRAY);
        Upanel.setBackground(Color.LIGHT_GRAY);
        Fpanel.setBackground(Color.LIGHT_GRAY);

        mainPanel.add(Upanel, BorderLayout.NORTH);
        mainPanel.add(Lpanel, BorderLayout.WEST);
        mainPanel.add(Rpanel, BorderLayout.EAST);
        mainPanel.add(Fpanel, BorderLayout.SOUTH);

        this.add(mainPanel, BorderLayout.EAST);
    }

    private JButton SeatButton(String seatNum) {
        JButton seatButton = new JButton(seatNum);
        seatButton.setFont(new Font("Arial", Font.BOLD, 11));
        seatButton.setHorizontalTextPosition(SwingConstants.CENTER);
        seatButton.setVerticalTextPosition(SwingConstants.CENTER);
        seatButton.setContentAreaFilled(false);
        seatButton.setBorderPainted(false);
        seatButton.setFocusPainted(false);

        updateButtonIcon(seatButton, seatNum);

        seatButton.addActionListener(e -> {
            if (seatButton.getIcon() == seatIcon) {
                seat.pesanKursi(seatNum);
                seatButton.setIcon(chosenIcon);
            } else if (seatButton.getIcon() == chosenIcon) {
                seat.kosongkanKursi(seatNum);
                seatButton.setIcon(seatIcon);
            }
        });
        return seatButton;
    }

    private void updateButtonIcon(JButton button, String seatNum) {
        if (asal.getSelectedItem() == null || tujuan.getSelectedItem() == null) {
            button.setIcon(seatIcon);
            return;
        }

        String asalStr = asal.getSelectedItem().toString();
        String tujuanStr = tujuan.getSelectedItem().toString();

        if (Tiket.isKursiTerpesan(seatNum, asalStr, tujuanStr)) {
            button.setIcon(takenIcon);
            button.setDisabledIcon(takenIcon);
            for (ActionListener al : button.getActionListeners()) {
                button.removeActionListener(al);
            }
        } else if (seat.getKursiTerpilih().contains(seatNum)) {
            button.setIcon(chosenIcon);
        } else {
            button.setIcon(seatIcon);
        }
    }

    private void updateSeatIcons() {
    if (asal.getSelectedItem() == null || tujuan.getSelectedItem() == null) {
        return;
    }

    String asalStr = asal.getSelectedItem().toString();
    String tujuanStr = tujuan.getSelectedItem().toString();

    if (asalStr.equals(tujuanStr)) {
        return;
    }
    for (Map.Entry<String, JButton> entry : tombolKursi.entrySet()) {
        updateButtonIcon(entry.getValue(), entry.getKey());
    }
}
}