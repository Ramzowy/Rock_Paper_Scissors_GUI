import javax.swing.*;

public class RockPaperScissorsRunnner {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            RockPaperScissorsFrame frame = new RockPaperScissorsFrame();
            frame.setVisible(true);
        });
    }
}