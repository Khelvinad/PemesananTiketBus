import java.util.Scanner;
import javax.swing.JFrame;

public class MainTiket {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        JFrame frame = new JFrame("Pemesanan Tiket Bus codEXpress");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 1000);
        frame.setVisible(true);
    }
}
