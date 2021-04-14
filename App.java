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
//		panel.setLayout(new BorderLayout());
		menubar = new JMenuBar();
		
		
		
		
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
		panel.add(ok, BorderLayout.NORTH);
		panel.add(cancel, BorderLayout.SOUTH);

		this.add(panel);
		this.setSize(300,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void buildMenu()
	{
		JMenu file, help;
		file = new JMenu("File");
		help = new JMenu("Help");
		
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem about = new JMenuItem("About");
		
		exit.addActionListener(new ExitActionListener());
		about.addActionListener(new AboutActionListener());
		
		file.add(exit);
		help.add(about);
		
		menubar.add(file);
		menubar.add(help);
		panel.add(menubar);
	}
	
	//watches buttons for clicks
	private class AboutActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
//			AboutDlg dlg = new AboutDlg( null, true);
			System.out.println("Java Swing Menus Minute 17");
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
	
	

}
