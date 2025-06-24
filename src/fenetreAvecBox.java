import javax.swing.*;
import java.awt.event.ActionListener;

public class fenetreAvecBox implements Runnable{

	@Override
	public void run() {
		JFrame frame = new JFrame("Ma fenetre avec boîte");

		// Create Box
		Box box = Box.createVerticalBox();
		frame.add(box);

		// Create a JLabel message
		JLabel petitMessage = new JLabel();
		petitMessage.setText("Un petit message");
		petitMessage.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		box.add(petitMessage);

		// Create Glue between the message and the button
		box.createGlue();

		// Create button
		JButton monButton = new JButton("Un button");
		monButton.addActionListener(new ButtonHandler());
		monButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		box.add(monButton);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setSize(420, 420);
	}


	public static void main(String[] argv) {
		SwingUtilities.invokeLater(new fenetreAvecBox());
	}

}
