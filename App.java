import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class App extends JFrame
{
	JPanel panel;
	JButton ok, cancel;
	
	public App()
	{
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		ok = new JButton("Okay");
		cancel = new JButton("Cancel");
		ok.addActionListener(new okActionListener());
		cancel.addActionListener(new okActionListener());
	}
	
	public void go()
	{
		panel.add(ok, BorderLayout.NORTH);
		panel.add(cancel, BorderLayout.SOUTH);

		this.add(panel);
		
		this.setSize(300,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
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
