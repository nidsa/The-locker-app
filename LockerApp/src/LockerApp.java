import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LockerApp extends JFrame {
    private JPasswordField passwordField;
    private JButton[] numberButtons = new JButton[10];
    private JButton enterButton, clearButton;
    private JLabel statusLabel;

    private String correctPassword = null;  // Initially, no password is set

    public LockerApp() {
        // Set up the JFrame
        setTitle("Locker Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize Swing components
        passwordField = new JPasswordField(20);
        enterButton = new JButton("Enter");
        clearButton = new JButton("Clear");
        statusLabel = new JLabel("Enter Passowrd");

        // Setup number buttons panel
        JPanel numberPanel = new JPanel(new GridLayout(4, 3, 5, 5)); // GridLayout for number buttons
        for (int i = 1; i < 10; i++) {  // Buttons 1 to 9
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this::appendNumber);
            numberPanel.add(numberButtons[i]);
        }
        numberButtons[0] = new JButton("0");
        numberButtons[0].addActionListener(this::appendNumber);
        numberPanel.add(new JLabel("")); // Spacer
        numberPanel.add(numberButtons[0]);
        numberPanel.add(new JLabel("")); // Spacer

        // Setup controls panel with password field and buttons
        JPanel controlsPanel = new JPanel(new FlowLayout());
        controlsPanel.add(clearButton);
        controlsPanel.add(passwordField);
        controlsPanel.add(enterButton);
        controlsPanel.add(statusLabel); // Status label next to Enter button

        // Add panels to main frame
        add(numberPanel, BorderLayout.NORTH);
        add(controlsPanel, BorderLayout.CENTER);

        // Action listeners for buttons
        clearButton.addActionListener(e -> clearPassword());
        enterButton.addActionListener(e -> {
            if (correctPassword == null) {
                setPassword();
            } else {
                verifyPassword();
            }
        });
    }

    // Method to append number to password field
    private void appendNumber(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        passwordField.setText(new String(passwordField.getPassword()) + source.getText());
    }

    // Method to set the password for the first time
    private void setPassword() {
        correctPassword = new String(passwordField.getPassword());
        statusLabel.setText("Password Set");
        passwordField.setText("");
    }

    // Method to clear the password field and reset status
    private void clearPassword() {
        passwordField.setText("");
        if (correctPassword == null) {
            statusLabel.setText("Enter Password");
        } else {
            statusLabel.setText("Password Set");
        }
    }

    // Method to verify the entered password
    private void verifyPassword() {
        String enteredPassword = new String(passwordField.getPassword());
        if (enteredPassword.equals(correctPassword)) {
            statusLabel.setText("Correct Password");
        } else {
            statusLabel.setText("Incorrect Password");
        }
        passwordField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LockerApp().setVisible(true));
    }
}
