import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.io.File;

/**
 * A simple Rock-Paper-Scissors game with a Swing GUI.
 *
 * Uses sticker icons from an absolute Windows path (now including resources subfolder).
 */
public class RockPaperScissors extends JFrame {
    // مسیر جدید فولدر تصاویر شامل زیرپوشه resources
    private static final String RESOURCE_PATH = "E:\\Java\\Rockpaper\\resources\\";

    private JLabel statusLabel, scoreLabel;
    private int playerScore = 0, computerScore = 0;
    private final String[] choices = {"Rock", "Paper", "Scissors"};
    private final String[] imgFiles = {"rock.png", "paper.png", "scissors.png"};
    private Random rand = new Random();

    public RockPaperScissors() {
        setTitle("Rock Paper Scissors");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Status label
        statusLabel = new JLabel("Make your choice!", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(statusLabel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        int iconSize = 80;  // scaled icon size
        for (int i = 0; i < choices.length; i++) {
            String choice = choices[i];
            String imgPath = RESOURCE_PATH + imgFiles[i];
            File f = new File(imgPath);
            if (!f.exists()) {
                System.err.println("Icon not found: " + imgPath);
            }
            ImageIcon originalIcon = new ImageIcon(imgPath);
            Image scaled = originalIcon.getImage().getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
            JButton btn = new JButton(new ImageIcon(scaled));
            btn.setActionCommand(choice);
            btn.setPreferredSize(new Dimension(iconSize + 20, iconSize + 20));
            btn.setToolTipText(choice);
            btn.addActionListener(e -> playRound(e.getActionCommand()));
            buttonPanel.add(btn);
        }
        add(buttonPanel, BorderLayout.CENTER);

        // Score label
        scoreLabel = new JLabel(getScoreText(), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(scoreLabel, BorderLayout.SOUTH);
    }

    private String getScoreText() {
        return String.format("Player: %d    Computer: %d", playerScore, computerScore);
    }

    private void playRound(String playerChoice) {
        String computerChoice = choices[rand.nextInt(choices.length)];
        String result;
        if (playerChoice.equals(computerChoice)) {
            result = "Tie! Both chose " + playerChoice + ".";
        } else if (
            (playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
            (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
            (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))
        ) {
            result = "You win! " + playerChoice + " beats " + computerChoice + ".";
            playerScore++;
        } else {
            result = "You lose! " + computerChoice + " beats " + playerChoice + ".";
            computerScore++;
        }
        statusLabel.setText(result);
        scoreLabel.setText(getScoreText());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RockPaperScissors().setVisible(true));
    }
}