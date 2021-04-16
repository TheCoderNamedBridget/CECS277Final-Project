import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;



public class App extends JFrame
{
	JPanel panel;
	JButton ok, cancel;
	JMenuBar menubar;
	
	public App()
	{
		panel = new JPanel();
		menubar = new JMenuBar();
		panel.setLayout(new BorderLayout());
	
	}
	
	public void go()
	{
		buildMenu();
//		panel.add(ok, BorderLayout.NORTH);
//		panel.add(cancel, BorderLayout.SOUTH);

		this.add(panel);
		this.setSize(300,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void buildMenu()
	{
		JMenu fileMenu, helpMenu, treeMenu, windowMenu;
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");
		treeMenu = new JMenu("Tree");
		windowMenu = new JMenu("Window");
		
		JMenuItem rename = new JMenuItem("Rename");
		JMenuItem copy = new JMenuItem("Copy");
		JMenuItem delete = new JMenuItem("Delete");
		JMenuItem run = new JMenuItem("Run");
		JMenuItem exit = new JMenuItem("Exit");
		
		
		JMenuItem expandBranch = new JMenuItem("ExpandBranch");
		JMenuItem collapseBranch = new JMenuItem("CollapseBranch");
		
		JMenuItem newW = new JMenuItem("New");
		JMenuItem cascade = new JMenuItem("Cascade");
		
		
		
		JMenuItem about = new JMenuItem("About");
		JMenuItem help = new JMenuItem("Help");
		
		expandBranch.addActionListener(new RunActionListener());
		newW.addActionListener(new RunActionListener());
		help.addActionListener(new RunActionListener());
		exit.addActionListener(new ExitActionListener());
		about.addActionListener(new AboutActionListener());
		
		fileMenu.add(rename);
		fileMenu.add(copy);
		fileMenu.add(delete);
		fileMenu.add(run);
		fileMenu.add(exit);
		treeMenu.add(expandBranch);
		treeMenu.add(collapseBranch);
		windowMenu.add(newW);
		windowMenu.add(cascade);
		helpMenu.add(about);
		helpMenu.add(help);
		
//		treeMenu.add(window);
//		treeMenu.add(debug);
		
		menubar.add(fileMenu);
		menubar.add(treeMenu);
		menubar.add(windowMenu);
		menubar.add(helpMenu);
		panel.add(menubar, BorderLayout.NORTH);
	}
	
	//watches buttons for clicks
	private class AboutActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			AboutDlg dlg = new AboutDlg( null, true );
			dlg.setVisible(true);
		}//
	}
	
	private class RunActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Tree"))
			{
				System.out.println("Running the Program");
			}
			else 
			{
				System.out.println("Debugging the program");
			}
		}
	}
	
	

}
