import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class SuspectDetailsFrame extends JFrame {
    private JTextField numberField = new JTextField("Enter phone number");
    private JButton findSMSButton = new JButton("Find SMS");
    private JTextArea messageArea = new JTextArea(); // For displaying messages
    private JTextArea partnersArea = new JTextArea(); // For displaying partners
    private JLabel partnersLabel = new JLabel("Partners"); // Label for the partners section
    private JTextArea suggestedPartnersArea = new JTextArea(); // For displaying suggested partners
    private JLabel suggestedPartnersLabel = new JLabel("Suggested Partners---->"); // Label for the suggested partners section
    private JButton backToSearchScreen = new JButton("Back to Search Screen");
    
    public SuspectDetailsFrame(Suspect suspect, Registry registry) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Use BorderLayout for better positioning

        // Create a lowered etched border for the whole panel
        Border loweredEtchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

        // Create a panel to group all the fields together
        JPanel groupPanel = new JPanel();
        groupPanel.setBorder(loweredEtchedBorder); // Apply the lowered etched border to the panel

        // Add suspect details (name and code name) using JTextField
        JTextField nameField = new JTextField(suspect.getName());
        nameField.setEditable(false); // Make it read-only
        groupPanel.add(nameField);

        JTextField codeNameField = new JTextField(suspect.getCodeName());
        codeNameField.setEditable(false); // Make it read-only
        groupPanel.add(codeNameField);

        // Get the phone list from the suspect
        List<String> phoneList = suspect.getPhoneList();

        // Convert the phone list to a single string with newlines
        String phoneListString = String.join("\n", phoneList);

        // Add the phone list as a JTextArea (for multi-line support)
        JTextArea phoneListArea = new JTextArea(phoneListString);
        phoneListArea.setEditable(false); // Make it read-only
        groupPanel.add(phoneListArea);

        // Add the groupPanel to the main panel
        panel.add(groupPanel, BorderLayout.NORTH);

        // Add the phone number field and button for searching
        JPanel inputPanel = new JPanel(); // Panel to hold the input field and button
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(numberField);

        // Set up the message display area between the numberField and findSMSButton
        messageArea.setEditable(false); // Make it read-only
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane messageScrollPane = new JScrollPane(messageArea);
        messageScrollPane.setPreferredSize(new Dimension(350, 100)); // Set scrollable area size
        inputPanel.add(messageScrollPane); // Add message display area between numberField and findSMSButton

        inputPanel.add(findSMSButton); // Add the findSMSButton after messageArea
        panel.add(inputPanel, BorderLayout.CENTER);

        // Set up a container for both partners and suggested partners sections
        JPanel partnersContainer = new JPanel();
        partnersContainer.setLayout(new BoxLayout(partnersContainer, BoxLayout.Y_AXIS)); // Stack partners and suggested partners vertically

        // Partners Section
        JPanel partnersPanel = new JPanel();
        partnersPanel.setLayout(new BorderLayout()); // Use BorderLayout for label and text area alignment
        partnersPanel.add(partnersLabel, BorderLayout.WEST); // Place label on the left
        partnersArea.setEditable(false); // Make it read-only
        partnersArea.setLineWrap(true);
        partnersArea.setWrapStyleWord(true);
        JScrollPane partnersScrollPane = new JScrollPane(partnersArea);
        partnersScrollPane.setPreferredSize(new Dimension(350, 100)); // Set size for the partners area
        partnersPanel.add(partnersScrollPane, BorderLayout.CENTER); // Add partners area next to the label
        partnersContainer.add(partnersPanel); // Add partners section to the container

        // Suggested Partners Section
        JPanel suggestedPartnersPanel = new JPanel();
        suggestedPartnersPanel.setLayout(new BorderLayout()); // Use BorderLayout for label and text area alignment
        suggestedPartnersPanel.add(suggestedPartnersLabel, BorderLayout.WEST); // Place label on the left
        suggestedPartnersArea.setEditable(false); // Make it read-only
        suggestedPartnersArea.setLineWrap(true);
        suggestedPartnersArea.setWrapStyleWord(true);
        JScrollPane suggestedPartnersScrollPane = new JScrollPane(suggestedPartnersArea);
        suggestedPartnersScrollPane.setPreferredSize(new Dimension(350, 100)); // Set size for the suggested partners area
        suggestedPartnersPanel.add(suggestedPartnersScrollPane, BorderLayout.CENTER); // Add suggested partners area next to the label
        partnersContainer.add(suggestedPartnersPanel); // Add suggested partners section to the container

        // Add the partnersContainer to the south part of the panel
        panel.add(partnersContainer, BorderLayout.SOUTH);

        // Set up the frame
        this.setContentPane(panel);
        this.setVisible(true);
        this.setSize(700, 600);  // Adjusted size to fit content better
        this.setTitle("Suspect Details");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Populate the partners area with the partner list from the suspect
        StringBuilder partnersText = new StringBuilder();
        List<Suspect> partnersList = suspect.getPartners();

        // Sort the partners by their name alphabetically
        Collections.sort(partnersList, new Comparator<Suspect>() {
            @Override
            public int compare(Suspect partner1, Suspect partner2) {
                return partner1.getName().compareTo(partner2.getName()); // Compare names alphabetically
            }
        });

        // Loop through the sorted partners and add their names and code names with a comma separator
        for (Suspect partner : partnersList) {
            partnersText.append(partner.getName()).append(", ").append(partner.getCodeName()).append("\n");
        }
        partnersArea.setText(partnersText.toString()); // Set the text of the partners area

        // Action listener for the button
        findSMSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredPhoneNumber = numberField.getText();
                StringBuilder smsContent = new StringBuilder();

                // Use Registry to search for SMS between the entered number and suspect's numbers
                for (String suspectNumber : phoneList) {
                    List<SMS> messages = registry.getMessagesBetween(enteredPhoneNumber, suspectNumber);
                    for (SMS message : messages) {
                        smsContent.append(message.getContent()).append("\n");
                    }
                }

                if (smsContent.length() == 0) {
                    smsContent.append("No SMS found.");
                }

                messageArea.setText(smsContent.toString()); // Update the message area with SMS content
            }
        });

        // Populate the suggested partners area with the suggested suspects
        StringBuilder suggestedPartnersText = new StringBuilder();
        List<Suspect> suggestedPartnersList = suspect.getSuggestedSuspects(); // Assuming method exists
        for (Suspect suggestedPartner : suggestedPartnersList) {
            suggestedPartnersText.append(suggestedPartner.getName()).append(", ").append(suggestedPartner.getCodeName()).append("\n");
        }
        suggestedPartnersArea.setText(suggestedPartnersText.toString()); // Set the text of the suggested partners area
        
        inputPanel.add(backToSearchScreen); // Add the findSMSButton after messageArea
        panel.add(inputPanel);
        
        backToSearchScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose of the current SuspectDetailsFrame
                SuspectDetailsFrame.this.dispose();
            }
        });

    }
}

