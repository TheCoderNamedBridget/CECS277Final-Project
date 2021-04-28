import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultTreeModel;


public class App extends JFrame
{
    JPanel panel, topPanel;
    JMenuBar menuBar;
    JToolBar toolBar, statusBar;
    JComboBox<String> comboBox;
    JDesktopPane desktop;
    FileFrame myFrame;
    String currentDrive;
    JList list = new JList();
    DefaultListModel model = new DefaultListModel();

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

        //buildModel();
//        } catch (IOException ex){
//            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

/*    private void buildModel()
    {
        model.addElement("You can drag and drop files on me");
        model.addElement("I am just here to demonstrate functionality");
        model.addElement("there are comments are labeled [TODO] around where I was added");
        list.setPreferredSize(new Dimension(280, 300));
        list.setModel(model);
    }*/

    public void go()
    {

        this.setTitle("File Manager");
        panel.setLayout(new BorderLayout());
        topPanel.setLayout(new BorderLayout());
        buildMenu();

        topPanel.add(menuBar,BorderLayout.NORTH);

        //buildComboBox();
        //topPanel.add(comboBox,BorderLayout.NORTH);

        panel.add(topPanel, BorderLayout.NORTH);
        this.add(panel);
        myFrame.setVisible(true);
        //TODO Change this later this is just here for drag nad drop functionality
        //panel.add(list, BorderLayout.NORTH);

        desktop.add(myFrame);
        panel.add(desktop,BorderLayout.CENTER);

        currentDrive = "CurrentDrive";
        buildStatusBar();
        panel.add(statusBar, BorderLayout.SOUTH);


        this.add(panel);
        this.setSize(1000,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //this.setDropTarget(new MyDropTarget());
    }

/*    private void buildComboBox(){
        File[] drives = File.listRoots();
        ArrayList<String> list = new ArrayList<String>();
        for(File x : drives){
            list.add(x.getName());
        }
        //FileSystemView fsv = FileSystemView.getFileSystemView();
        comboBox= new JComboBox<>(list.toArray(new String[list.size()]));
    }*/


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
        collapseBranch.addActionListener(new RunActionListener());
        run.addActionListener(new RunActionListener());
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

    private void buildStatusBar() {
        JLabel status = new JLabel("Total Space: ");
        statusBar.add(status);
        panel.add(statusBar, BorderLayout.SOUTH);

    }

    private class RunActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Tree"))
            {
                System.out.println("Running the Program");
            } else if(e.getActionCommand().equals("New")){
                desktop.add(new FileFrame());
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

//    class MyDropTarget extends DropTarget {
//
//        public void drop(DropTargetDropEvent evt )
//        {
//            try
//            {
//                evt.acceptDrop(DnDConstants.ACTION_COPY);
//                List result = new ArrayList<Object>();
//                result = (List)evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
//                for ( Object o : result )
//                {
//                    System.out.println(o.toString());
//                    model.addElement(o.toString());
//                }
//            }
//            catch ( Exception ex )
//            {
//
//            }
//        }
//    }
}
