import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {

    private JButton[] kursi = new JButton[36];
    private ImageIcon seatIcon;
    private ImageIcon takenIcon;

    public Frame() {
        this.setTitle("Pemesanan Tiket Bus codEXpress");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 650);
        this.setLayout(new BorderLayout());

        loadIcon();
        UIbus();

        this.setVisible(true);
    }

    private void loadIcon(){
        ImageIcon rawIcon = new ImageIcon(getClass().getResource("Resource/Seat.png"));
        Image scaledImage = rawIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        seatIcon = new ImageIcon(scaledImage);

        ImageIcon rawTaken = new ImageIcon(getClass().getResource("Resource/TakenSeat.png"));
        Image scaledTaken = rawTaken.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        takenIcon = new ImageIcon(scaledTaken);
    }

    private void UIbus() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel Upanel = new JPanel();
        JPanel Fpanel = new JPanel();
        JPanel Rpanel = new JPanel();
        JPanel Lpanel = new JPanel();

        mainPanel.setPreferredSize(new Dimension(250, 650));

        mainPanel.setBackground(Color.gray);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        // Upanel.setBackground(Color.RED);
        Fpanel.setBackground(Color.RED);
        // Rpanel.setBackground(Color.RED);
        Lpanel.setBackground(Color.GREEN);

        Upanel.setPreferredSize(new Dimension(230,70));
        Fpanel.setPreferredSize(new Dimension(10, 70));

        mainPanel.add(Upanel, BorderLayout.NORTH);
        mainPanel.add(Fpanel, BorderLayout.SOUTH);
        mainPanel.add(Lpanel, BorderLayout.WEST);
        mainPanel.add(Rpanel, BorderLayout.EAST);

        this.add(mainPanel, BorderLayout.EAST);

        Lpanel.setLayout(new GridLayout(9, 2, 5, 5));
        Rpanel.setLayout(new GridLayout(9, 2, 5, 5));

        for (int i = 0; i < kursi.length; i++) {
            JButton btn = new JButton();
            btn.setIcon(seatIcon);
            btn.setHorizontalTextPosition(SwingConstants.CENTER);
            btn.setPreferredSize(new Dimension(45, 45));
            btn.setBorder(BorderFactory.createEmptyBorder());
            btn.setFocusPainted(false);
            btn.setContentAreaFilled(false);
            btn.setBorderPainted(true);
            btn.setFont(new Font("Roboto", Font.BOLD, 9));

            kursi[i] = btn;
            btn.addActionListener(e -> {
                if (btn.getIcon().equals(seatIcon)) {
                    btn.setIcon(takenIcon);
                } else {
                    btn.setIcon(seatIcon);
                }
            });

            if (i < 18) {
                btn.setText((i + 1) + "A");
                Lpanel.add(btn);
            } else {
                btn.setText((i + 1) + "B");
                Rpanel.add(btn);
            }
        }
    }
}