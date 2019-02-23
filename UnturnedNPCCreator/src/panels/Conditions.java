package panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

public class Conditions extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8852545862960352227L;
	private int conditionIndex = 0;
	private JTabbedPane tabbedPane;
	private JMenuBar menuBar;
	private JButton buttonNewCondition;
	private final Action actionAddCondition = new SwingActionAddCondition();
	private JButton buttonSave;
	private final Action actionSave = new SwingActionSave();
	private JButton buttonRemoveCurrent;
	private final Action actionRemoveCurrent = new SwingActionRemoveCurrent();
	
	/**
	 * Create the panel.
	 * @param container 
	 */
	public Conditions() {
		new JPanel();
		setVisible(true);
		setEnabled(true);
		setBounds(100, 100, 580, 475);
		setMinimumSize(new Dimension(580,455));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		setLayout(new GridLayout(0, 2, 0, 0));
		setLayout(new BorderLayout(0, 0));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		NewCondition newCondition = new NewCondition();
		tabbedPane.addTab("Con#" + ++conditionIndex  , null, newCondition, null);
		tabbedPane.setEnabledAt(0, true);
		add(tabbedPane);
		
		menuBar = new JMenuBar();
		GridBagConstraints gbc_menuBar = new GridBagConstraints();
		gbc_menuBar.fill = GridBagConstraints.VERTICAL;
		gbc_menuBar.anchor = GridBagConstraints.WEST;
		gbc_menuBar.gridx = 0;
		gbc_menuBar.gridy = 0;
		add(menuBar, BorderLayout.NORTH);
		
		buttonNewCondition = new JButton("Add Condition");
		buttonNewCondition.setAction(actionAddCondition);
		menuBar.add(buttonNewCondition);
		
		buttonRemoveCurrent = new JButton("New button");
		buttonRemoveCurrent.setAction(actionRemoveCurrent);
		menuBar.add(buttonRemoveCurrent);
		
		buttonSave = new JButton("New button");
		buttonSave.setAction(actionSave);
		menuBar.add(buttonSave);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		GridBagConstraints gbc_TimeOfDay = new GridBagConstraints();
		gbc_TimeOfDay.insets = new Insets(0, 0, 0, 5);
		gbc_TimeOfDay.fill = GridBagConstraints.BOTH;
		gbc_TimeOfDay.gridx = 0;
		gbc_TimeOfDay.gridy = 2;
		GridBagConstraints gbc_CompareFlags = new GridBagConstraints();
		gbc_CompareFlags.insets = new Insets(0, 0, 0, 5);
		gbc_CompareFlags.fill = GridBagConstraints.BOTH;
		gbc_CompareFlags.gridx = 0;
		gbc_CompareFlags.gridy = 2;
		
	}
	
	public void Load(String[] conditions)
	{
		for(int i = 0; i < conditions.length; i++)
		{
			NewCondition NC = new NewCondition();
			
			NC.fillFields(conditions[i]);
			
			tabbedPane.addTab("Con#" + ++conditionIndex , null, NC, null);
		}
		
		
	}
	
	public String Save() {
		int i = 0;
		String output = "Conditions " + tabbedPane.getTabCount() + "\n";
		for(Component panel : tabbedPane.getComponents())
		{
			output += ((NewCondition) panel).ReturnConditions(i++);
		}
		return output;
	}
	private class SwingActionAddCondition extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5916764870521404311L;
		public SwingActionAddCondition() {
			putValue(NAME, "Add Condition");
			putValue(SHORT_DESCRIPTION, "Add a new condition");
		}
		public void actionPerformed(ActionEvent e) {
			tabbedPane.addTab("Con#" + ++conditionIndex , null, new NewCondition(), null);
		}
	}
	private class SwingActionSave extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8946515299665761207L;
		public SwingActionSave() {
			putValue(NAME, "Save");
			putValue(SHORT_DESCRIPTION, "Save the conditions.");
		}
		public void actionPerformed(ActionEvent e) {
			Save();
		}
	}
	private class SwingActionRemoveCurrent extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1449903464403442892L;
		public SwingActionRemoveCurrent() {
			putValue(NAME, "Remove Current");
			putValue(SHORT_DESCRIPTION, "Remove the currently selected condition");
		}
		public void actionPerformed(ActionEvent e) {
			tabbedPane.remove(tabbedPane.getSelectedComponent());
			ReorganizeIndex();
		}
	}
	public void ReorganizeIndex() {
		for(int i = 0; i < tabbedPane.getComponentCount(); i++)
		{
			tabbedPane.setTitleAt(i, "Con#" + (i+1));
		}
	}
}
