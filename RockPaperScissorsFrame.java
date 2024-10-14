import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;

    private JLabel playerWinsLabel;
    private JLabel computerWinsLabel;
    private JLabel tiesLabel;
    private JTextArea resultsTextArea;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choose your move"));
        buttonPanel.setLayout(new FlowLayout());

//        JButton rockButton = new JButton("Rock");
//        JButton paperButton = new JButton("Paper");
//        JButton scissorsButton = new JButton("Scissors");
//        JButton quitButton = new JButton("Quit");

        // Load icons
        ImageIcon rockIcon = new ImageIcon("src/closedfist.png");
        ImageIcon paperIcon = new ImageIcon("src/openhand.png");
        ImageIcon scissorsIcon = new ImageIcon("src/Scissors.png");

        // Create buttons with text and icons
        JButton rockButton = createButton("Rock", rockIcon);
        JButton paperButton = createButton("Paper", paperIcon);
        JButton scissorsButton = createButton("Scissors", scissorsIcon);
        JButton quitButton = new JButton("Quit");

        // Add action listeners to buttons
        rockButton.addActionListener(new MoveListener("Rock"));
        paperButton.addActionListener(new MoveListener("Paper"));
        scissorsButton.addActionListener(new MoveListener("Scissors"));
        quitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);

        // Create a panel for statistics
        JPanel statsPanel = new JPanel(new GridLayout(3, 2));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Statistics"));

        playerWinsLabel = new JLabel("Player Wins: 0");
        computerWinsLabel = new JLabel("Computer Wins: 0");
        tiesLabel = new JLabel("Ties: 0");

        statsPanel.add(playerWinsLabel);
        statsPanel.add(computerWinsLabel);
        statsPanel.add(tiesLabel);

        // Create a panel for results
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsPanel.setBorder(BorderFactory.createTitledBorder("Results"));

        resultsTextArea = new JTextArea();
        resultsTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        resultsPanel.add(scrollPane);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(statsPanel, BorderLayout.CENTER);
        mainPanel.add(resultsPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JButton createButton(String text, ImageIcon icon) {
        // Create a panel to hold the label and button
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Stack vertically

        JLabel label = new JLabel(text); // Text label
        JButton button = new JButton(icon); // Button with icon
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the text label

        panel.add(label); // Add text label
        panel.add(button); // Add button with image

        // Create a JButton from the panel that can trigger actions
        JButton customButton = new JButton();
        customButton.setLayout(new BorderLayout());
        customButton.add(panel, BorderLayout.CENTER);
        customButton.setPreferredSize(new Dimension(200, 250)); // Set size if needed

        return customButton; // Return the custom button
    }


    private class MoveListener implements ActionListener {
        private String playerMove;

        public MoveListener(String move) {
            this.playerMove = move;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] options = {"Rock", "Paper", "Scissors"};
            String computerMove = options[new Random().nextInt(3)];

            String result = determineWinner(playerMove, computerMove);

            resultsTextArea.append(result + "\n");

            if (result.contains("Player Wins")) {
                playerWins++;
            } else if (result.contains("Computer Wins")) {
                computerWins++;
            } else {
                ties++;
            }

            updateStatistics();
        }
    }

    private String determineWinner(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "It's a tie!";
        } else if (
                (playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
                        (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
                        (playerMove.equals("Scissors") && computerMove.equals("Paper"))
        ) {
            return "Player Wins: " + playerMove + " beats " + computerMove;
        } else {
            return "Computer Wins: " + computerMove + " beats " + playerMove;
        }
    }

    private void updateStatistics() {
        playerWinsLabel.setText("Player Wins: " + playerWins);
        computerWinsLabel.setText("Computer Wins: " + computerWins);
        tiesLabel.setText("Ties: " + ties);
    }


}
