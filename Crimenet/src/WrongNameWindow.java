import javax.swing.JOptionPane;

class WrongNameWindow {
    public WrongNameWindow(String name) {
        // Create the message to display
        String message = "Suspect " + name + " Not Found";
        
        // Use JOptionPane to display an information dialog
        JOptionPane.showMessageDialog(null,message,"Message",JOptionPane.INFORMATION_MESSAGE);
    }
}
