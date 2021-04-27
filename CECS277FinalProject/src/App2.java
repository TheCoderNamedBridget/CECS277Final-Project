import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.tree.DefaultTreeModel;


public class App extends JFrame
{
    JPanel panel, topPanel;
    JMenuBar menuBar;
    JToolBar toolBar, driveBar, statusBar;
    JDesktopPane desktop;
    FileFrame myFrame, myFrame2;
    String currentDrive;
    DefaultTreeModel treeModel;
    JTree tree;

    public App()
    {
        //try {
        panel = new JPanel();
        topPanel = new JPanel();
        menuBar = new JMenuBar();
        toolBar = new JToolBar();
        statusBar = new JToolBar();
        desktop = new JDesktopPane();
        myFrame = new FileFrame();
        myFrame2 = new FileFrame();
//        } catch (IOException ex){
//            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void go()
    {
        this.setTitle("File Manager");
        panel.setLayout(new BorderLayout());
        topPanel.setLayout(new BorderLayout());
        buildMenu();

        topPanel.add(menuBar,BorderLayout.NORTH);

        panel.add(topPanel, BorderLayout.NORTH);
        this.add(panel);
        myFrame.setVisible(true);

        desktop.add(myFrame);
        panel.add(desktop,BorderLayout.CENTER);
        //buildToolBar();
        //topPanel.add(toolBar, BorderLayout.SOUTH);

        currentDrive = "CurrentDrive";
        buildStatusBar();
        panel.add(statusBar, BorderLayout.SOUTH);

        this.add(panel);
        this.setSize(1000,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void buildMenu()
    {
        JMenu fileMenu, helpMenu, treeMenu, windowMenu;
        fileMenu = new JMenu("File");
        treeMenu = new JMenu("Tree");
        windowMenu = new JMenu("Window");
        helpMenu = new JMenu("Help");

        JComboBox comboBox;

        File[] drives = File.listRoots();
        //FileSystemView fsv = FileSystemView.getFileSystemView();
        comboBox= new JComboBox(drives);


        panel.add(comboBox, BorderLayout.NORTH);

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

        menuBar.add(fileMenu);
        menuBar.add(treeMenu);
        menuBar.add(windowMenu);
        menuBar.add(helpMenu);
        panel.add(menuBar, BorderLayout.NORTH);
    }

    private void buildToolBar() {
        JMenu file, help;
        file = new JMenu("File");
        help = new JMenu("Help");

        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem about = new JMenuItem("About");

        exit.addActionListener(new ExitActionListener());
        about.addActionListener(new AboutActionListener());

        file.add(exit);
        help.add(about);

        toolBar.add(file);
        toolBar.add(help);
        panel.add(toolBar, BorderLayout.SOUTH);

    }

    private void buildStatusBar() {
        JLabel status = new JLabel("Total Space: ");
        statusBar.add(status);
        panel.add(statusBar, BorderLayout.SOUTH);

    }

    private static class RunActionListener implements ActionListener {
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
    private static class AboutActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            AboutDlg dlg = new AboutDlg(null,true);

            dlg.setVisible(true);
        }
    }
}
