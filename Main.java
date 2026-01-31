import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Message Encryption");
        frame.setSize(400, 350); // Set frame dimensions

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel messageLabel = new JLabel("Enter a message:");
        panel.add(messageLabel);

        JTextField messageField = new JTextField(20); // Set text field width
        panel.add(messageField);

        JButton encryptButton = new JButton("Encrypt");
        panel.add(encryptButton);

        JLabel passwordLabel = new JLabel("Enter the password:");
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField(20); // Set password field width
        panel.add(passwordField);

        JLabel decryptLabel = new JLabel("Enter encrypted message:");
        panel.add(decryptLabel);

        JTextField decryptField = new JTextField(20); // Set decrypt field width
        panel.add(decryptField);

        JButton decryptButton = new JButton("Decrypt");
        panel.add(decryptButton);

        JTextArea resultArea = new JTextArea(5, 20); // Set text area dimensions
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea); // Add scroll pane for better readability
        panel.add(scrollPane);

        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                MessageEncryption encryption = new MessageEncryption("39546"); 
                String encryptedMessage = encryption.encryptMessage(message);
                resultArea.setText("Encrypted message: " + encryptedMessage);
                decryptField.setText(encryptedMessage); 
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            private int attempts = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredPassword = new String(passwordField.getPassword());
                MessageEncryption encryption = new MessageEncryption("39546");

                if (enteredPassword.equals(encryption.password)) {
                    String encryptedMessage = decryptField.getText(); 
                    String decryptedMessage = encryption.decryptMessage(encryptedMessage);
                    resultArea.setText("Decrypted message: " + decryptedMessage);
                    decryptButton.setEnabled(false); 
                } else {
                    attempts++;
                    if (attempts == 2) {
                        JOptionPane.showMessageDialog(frame, "You have entered an incorrect password again. Cannot decrypt the message.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        Toolkit.getDefaultToolkit().beep();
                        decryptButton.setEnabled(false); 
                    } else {
                        JOptionPane.showMessageDialog(frame, "Incorrect password. Please try again, you have one last chance.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

   
}