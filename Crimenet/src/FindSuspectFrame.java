import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FindSuspectFrame extends JFrame {
    private Registry registry;  // Reference to the registry

    private JPanel panel = new JPanel();
    private JTextField nameField = new JTextField("Please enter suspect's name");
    private JButton findButton = new JButton("Find");

    // Constructor accepts a Registry object
    public FindSuspectFrame(Registry registry) {
        this.registry = registry;  // Store the reference to the registry

        // Adding components to the panel
        panel.add(nameField);
        panel.add(findButton);

        this.setContentPane(panel);

        // Adding action listeners to buttons
        FindButtonListener findListener = new FindButtonListener();
        findButton.addActionListener(findListener);

        // JFrame settings
        this.setVisible(true);
        this.setSize(400, 200);
        this.setTitle("Find Suspect");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Listener for the "Find Suspect" button
    class FindButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String enteredName = nameField.getText();  // Get text from the input field
            boolean found = false;

            // Searching for the suspect in the registry's suspects list
            for (Suspect suspect : registry.getSuspects()) {
                if (enteredName.equalsIgnoreCase(suspect.getName())) {//ignoring lowercase characters
                    new SuspectDetailsFrame(suspect, registry); // Open the suspect's details frame
                    found = true;
                    break;
                }
            }

            // If not found, notify the user
            if (!found) {
                new WrongNameWindow(enteredName);  // Show a window for wrong name
            }
        }
    }
}
