import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {
    private JTextField guessField;
    private JButton guessButton;
    private JLabel messageLabel;
    private int randomNumber;
    private int attempts;
    private final int maxAttempts = 5;
    private int score;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1));

        JLabel promptLabel = new JLabel("Enter your guess:");
        topPanel.add(promptLabel);

        guessField = new JTextField();
        guessField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    checkGuess();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        topPanel.add(guessField);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        guessButton = new JButton("Guess");
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
        centerPanel.add(guessButton);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        messageLabel = new JLabel();
        mainPanel.add(messageLabel, BorderLayout.SOUTH);

        add(mainPanel);

        initializeGame();
    }

    private void initializeGame() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
        attempts = 0;
        score = 100;
        messageLabel.setText("Guess a number between 1 and 100.");
    }

    private void checkGuess() {
        if (attempts >= maxAttempts) {
            messageLabel.setText("Out of attempts. The correct number was " + randomNumber);
            guessButton.setEnabled(false);
            return;
        }

        String guessText = guessField.getText();
        try {
            int guess = Integer.parseInt(guessText);
            attempts++;

            if (guess == randomNumber) {
                messageLabel.setText("Congratulations! You guessed the number.");
                guessButton.setEnabled(false);
            } else if (guess < randomNumber) {
                messageLabel.setText("Too low. Try again.");
            } else {
                messageLabel.setText("Too high. Try again.");
            }

            score -= 20;
            if (attempts == maxAttempts) {
                messageLabel.setText("Out of attempts. The correct number was " + randomNumber);
                guessButton.setEnabled(false);
            }

            guessField.setText(""); // Clear the input field for a new guess
        } catch (NumberFormatException ex) {
            messageLabel.setText("Invalid input. Please enter a number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NumberGuessingGameGUI game = new NumberGuessingGameGUI();
                game.setVisible(true);
            }
        });
    }
}
