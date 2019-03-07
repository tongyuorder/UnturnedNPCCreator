package rewards;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class RewardsDialog extends JDialog {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4964775139618654007L;
	static String returnRewards;
	private static RewardsPanel RewardsPanel;

	/**
	 * Create the dialog.
	 */
	public static String Dialog(String rewards) {
		JDialog dialog = new JDialog();
		RewardsPanel = new RewardsPanel(rewards);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		dialog.getContentPane().add(RewardsPanel);
		dialog.requestFocus();
		dialog.toFront();
		dialog.setSize(new Dimension(500,350));
		dialog.setMinimumSize(new Dimension(500,350));
		dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		dialog.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent we)
		    {
		        String ObjButtons[] = {"Save","Cancel","Exit"};
		        int PromptResult = JOptionPane.showOptionDialog(null,"You are about to exit, would you like to save?","Are you sure?",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[0]);
		        if(PromptResult==0)
		        {
		        	returnRewards = RewardsPanel.Save();
		        	dialog.dispose();
		        } else if (PromptResult==2)
		        	dialog.dispose();
		    }
		});
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		dialog.setSize(580, 475);
		dialog.setLocationRelativeTo(null);
		dialog.pack();
		dialog.setVisible(true);
		return returnRewards;
	}
}
