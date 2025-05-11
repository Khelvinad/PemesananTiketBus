import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {

    private JButton[] seatA = new JButton[18];
    private JButton[] seatB = new JButton[22];
    private JButton driver, conductor, toilet;
    private ImageIcon seatIcon;
    private ImageIcon chosenIcon;
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

    private void UIuser(){

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
            Lpanel.add(seatA[i-1]);
        }
        JPanel Rpanel = new JPanel(new GridLayout(9, 2, 5, 5));
        Rpanel.setPreferredSize(new Dimension(115, 500));
        for (int i = 1; i <= 18; i++) {
            seatB[i-1] = SeatButton("B" + i);
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

    private JButton SeatButton(String seatNum){
        JButton seatButton = new JButton(seatNum);
        seatButton.setIcon(seatIcon);
        seatButton.setFont(new Font("Arial", Font.BOLD, 11));
        seatButton.setHorizontalTextPosition(SwingConstants.CENTER);
        seatButton.setVerticalTextPosition(SwingConstants.CENTER);
        seatButton.setContentAreaFilled(false);
        seatButton.setBorderPainted(false);
        seatButton.setFocusPainted(false);
        seatButton.addActionListener(e -> {
            if (seatButton.getIcon() == seatIcon) {
                seatButton.setIcon(chosenIcon);
            } else {
                seatButton.setIcon(seatIcon);
            }
        });
        return seatButton;
    }
}