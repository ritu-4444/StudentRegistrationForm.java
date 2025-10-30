import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentRegistrationForm extends JFrame implements ActionListener {
    // Components
    private JTextField nameField, ageField, emailField;
    private JComboBox<String> courseBox;
    private JButton submitButton, clearButton;
    private JTextArea outputArea;

    public StudentRegistrationForm() {
        setTitle("Student Registration Form");
        setSize(400, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel for inputs
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Student Details"));

        inputPanel.add(new JLabel("Full Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("Course:"));
        String[] courses = { "Computer Science", "Mathematics", "Physics", "Biology" };
        courseBox = new JComboBox<>(courses);
        inputPanel.add(courseBox);

        // Buttons
        submitButton = new JButton("Submit");
        clearButton = new JButton("Clear");
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);

        inputPanel.add(submitButton);
        inputPanel.add(clearButton);

        add(inputPanel, BorderLayout.NORTH);

        // Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createTitledBorder("Output"));
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();
            String email = emailField.getText().trim();
            String course = (String) courseBox.getSelectedItem();

            // Validation
            if (name.isEmpty() || ageText.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int age = Integer.parseInt(ageText);
                if (age <= 0) {
                    throw new NumberFormatException();
                }

                // Simple email validation
                if (!email.contains("@") || !email.contains(".")) {
                    JOptionPane.showMessageDialog(this, "Enter a valid email address.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Display confirmation
                outputArea.setText("✅ Registration Successful!\n\n");
                outputArea.append("Name: " + name + "\n");
                outputArea.append("Age: " + age + "\n");
                outputArea.append("Email: " + email + "\n");
                outputArea.append("Course: " + course + "\n");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid age (number only).", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == clearButton) {
            nameField.setText("");
            ageField.setText("");
            emailField.setText("");
            outputArea.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentRegistrationForm().setVisible(true));
    }
}
