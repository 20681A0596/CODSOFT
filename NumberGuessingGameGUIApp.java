import javax.swing.*;
import java.awt.*;
import java.util.Random;

interface NumberGuessingGame {
    void playGame();
    int getScore();
}

class NumberGuessingGameImpl implements NumberGuessingGame {
    private int minRange;
    private int maxRange;
    private int rounds;
    private int score;

    public NumberGuessingGameImpl(int minRange, int maxRange, int rounds) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.rounds = rounds;
        this.score = 0;
    }

    @Override
    public void playGame() {
        Random random = new Random();

        for (int round = 1; round <= rounds; round++) {
            int numberToGuess = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;
            boolean guessedCorrectly = false;

            while (!guessedCorrectly) {
                int userGuess = getUserGuess(minRange, maxRange);

                attempts++;

                if (userGuess == numberToGuess) {
                    JOptionPane.showMessageDialog(null, "Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    score++;
                    guessedCorrectly = true;
                } else if (userGuess < numberToGuess) {
                    JOptionPane.showMessageDialog(null, "Too low. Try again.");
                } else {
                    JOptionPane.showMessageDialog(null, "Too high. Try again.");
                }
            }
        }
    }

    private int getUserGuess(int min, int max) {
        String userInput = JOptionPane.showInputDialog("Guess a number between " + min + " and " + max);
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            return getUserGuess(min, max);
        }
    }

    @Override
    public int getScore() {
        return score;
    }
}

public class NumberGuessingGameGUIApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        JButton startButton = new JButton("Start Game");
        JLabel resultLabel = new JLabel("Your score: 0");

        startButton.addActionListener(e -> {
            int minRange = Integer.parseInt(JOptionPane.showInputDialog("Enter the minimum range:"));
            int maxRange = Integer.parseInt(JOptionPane.showInputDialog("Enter the maximum range:"));
            int rounds = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of rounds:"));

            NumberGuessingGame game = new NumberGuessingGameImpl(minRange, maxRange, rounds);
            game.playGame();
            resultLabel.setText("Your score: " + game.getScore());
        });

        frame.add(startButton);
        frame.add(resultLabel);
        frame.setVisible(true);
    }
}
