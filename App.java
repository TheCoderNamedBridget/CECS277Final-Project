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

		//buttons
		ok = new JButton("Okay");
		cancel = new JButton("Cancel");
		
		//checks if buttons are clicked
		ok.addActionListener(new okActionListener());
		cancel.addActionListener(new okActionListener());
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
		JMenu fileMenu, helpMenu, runMenu;
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");
		runMenu = new JMenu("Run");
		
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem about = new JMenuItem("About");
		JMenuItem run = new JMenuItem("Run");
		JMenuItem debug = new JMenuItem("Debug");
		
		run.addActionListener(new RunActionListener());
		debug.addActionListener(new RunActionListener());
		exit.addActionListener(new ExitActionListener());
		about.addActionListener(new AboutActionListener());
		
		fileMenu.add(exit);
		helpMenu.add(about);
		runMenu.add(run);
		runMenu.add(debug);
		
		menubar.add(fileMenu);
		menubar.add(runMenu);
		menubar.add(helpMenu);
		panel.add(menubar, BorderLayout.NORTH);
	}
	
	//watches buttons for clicks
	private class AboutActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			AboutDlg dlg = new AboutDlg( null, true);
			dlg.setVisible(true);
		}
	}
	
	//watches buttons for clicks
	private class okActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Okay"))
			{
				System.out.print("OKAAY");
			}
			else if (e.getActionCommand().equals("Cancel"))
			{
				System.out.print("Cancel");
			}
		}
	}
	
	private class RunActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Run"))
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
