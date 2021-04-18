import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;



public class App extends JFrame
{
	JPanel panel;
	JDesktopPane desktopPane;
	JButton ok, cancel;
	JMenuBar menubar, statusbar;
	DefaultTreeModel treeModel;
	JTree tree;
	public App()
	{
		panel = new JPanel();
		ok = new JButton("OK");
		menubar = new JMenuBar();
		statusbar = new JMenuBar();
		panel.setLayout(new BorderLayout());
		desktopPane = new JDesktopPane();
		
		tree = new JTree();
		tree.setPreferredSize( new Dimension(400,400));
		buildTree();
		
		panel.add(tree);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(420, 420);
		this.setTitle("A Tree Demo");

	}
	
	public void go()
	{

		
		buildMenu();
		//buildtoolbar
		buildStatusBar();
		this.add(panel);
		this.setSize(600,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("CECS 277 Final Project");
		
		
		
		
		add(panel);
		panel.add(menubar, BorderLayout.NORTH);
		panel.add(ok);
		panel.add(desktopPane, BorderLayout.CENTER);
		
		
		
		ok.addActionListener(new OkActionListener());
	}
	
	private void buildTree()
	{
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		treeModel = new DefaultTreeModel(root);
		
		DefaultMutableTreeNode node = new DefaultMutableTreeNode("Node1");
		root.add(node);
		node = new DefaultMutableTreeNode("Node2");
		root.add(node);
		for ( int i = 0;i < 10; i++ )
		{
			DefaultMutableTreeNode subNode = new DefaultMutableTreeNode("subNode" + i);
			node.add(subNode);
		}
		
		tree.setModel(treeModel);
		
		
	}
	
	private void buildMenu()
	{
		JMenu fileMenu, helpMenu, treeMenu, windowMenu;
		fileMenu = new JMenu("File");
		treeMenu = new JMenu("Tree");
		windowMenu = new JMenu("Window");
		helpMenu = new JMenu("Help");
		
		//menu options for file
		JMenuItem rename = new JMenuItem("Rename");
		JMenuItem copy = new JMenuItem("Copy");
		JMenuItem delete = new JMenuItem("Delete");
		JMenuItem run = new JMenuItem("Run");
		JMenuItem exit = new JMenuItem("Exit");
		
		//menu options for tree
		JMenuItem expandBranch = new JMenuItem("ExpandBranch");
		JMenuItem collapseBranch = new JMenuItem("CollapseBranch");
		
		//menu options for window
		JMenuItem newW = new JMenuItem("New");
		JMenuItem cascade = new JMenuItem("Cascade");
		
		//menu options for help
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
		
		menubar.add(fileMenu);
		menubar.add(treeMenu);
		menubar.add(windowMenu);
		menubar.add(helpMenu);
		panel.add(menubar, BorderLayout.NORTH);
		FileFrame ff= new FileFrame();
		FileFrame ff1= new FileFrame();
		desktopPane.add(ff);
		desktopPane.add(ff1);
	}
	
	
	
	//watches buttons for clicks
	private class AboutActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			AboutDlg dlg = new AboutDlg( null, true );
			dlg.setVisible(true);
		}
	}
	
	private class OkActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("okay");
		}
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
	
	private void buildStatusBar()
	{
		JLabel size = new JLabel("Size in GB:" );
		
		statusbar.add(size);
		panel.add(statusbar, BorderLayout.SOUTH);
	}
	

}
