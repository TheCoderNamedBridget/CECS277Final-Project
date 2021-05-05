import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;


public class App extends JFrame
{
    private final static FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    static JPanel panel;
    static JPanel topPanel;
    JMenuBar menuBar;
    JToolBar toolBar;
    static JToolBar statusBar;
    JComboBox<String> comboBox;
    protected static Desktop desktop;
    static JDesktopPane desktopPane;
    static FileFrame myFrame;
    String currentDrive;
    static File currentFile;
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
        desktopPane = new JDesktopPane();
        myFrame = new FileFrame();
        desktop = Desktop.getDesktop();

        //buildModel();
//        } catch (IOException ex){
//            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

   private void buildModel()
    {
        model.addElement("You can drag and drop files on me");
        model.addElement("I am just here to demonstrate functionality");
        model.addElement("there are comments are labeled [TODO] around where I was added");
        list.setPreferredSize(new Dimension(280, 300));
        list.setModel(model);
    }

    public void go()
    {

        this.setTitle("File Manager");
        panel.setLayout(new BorderLayout());
        topPanel.setLayout(new BorderLayout());
        buildMenu();

        topPanel.add(menuBar,BorderLayout.NORTH);

        //TODO Set up ComboBox for drive selection.
//        buildComboBox();
//        panel.add(comboBox,BorderLayout.NORTH);

        panel.add(topPanel, BorderLayout.NORTH);
        this.add(panel);
        myFrame.setLocation(0,100);
        myFrame.setVisible(true);
        //TODO Change this later this is just here for drag nad drop functionality
        //panel.add(list, BorderLayout.NORTH);

        desktopPane.add(myFrame);
        panel.add(desktopPane,BorderLayout.CENTER);

        currentDrive = "CurrentDrive";

        panel.add(statusBar, BorderLayout.SOUTH);


        this.add(panel);
        this.setSize(1000,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
//
//        this.setDropTarget(new MyDropTarget());
//        this.setEnabled(true);
    }

    private void buildComboBox(){
        File[] drives = fileSystemView.getRoots();
        ArrayList<String> list = new ArrayList<>();
        for(File x : drives){
            list.add(x.getName());
        }
        comboBox = new JComboBox<>(list.toArray(new String[0]));
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

        rename.addActionListener(e -> {
            //renameFile();
        });

        copy.addActionListener(e -> {
            //copyFile();
        });

        delete.addActionListener(e -> {
            //deleteFile();
        });

        expandBranch.addActionListener(e -> {

        });

        collapseBranch.addActionListener(e -> {

        });

        newW.addActionListener(e -> {
            FileFrame newWin = new FileFrame();
            newWin.setLocation(0,100);
            desktopPane.add(newWin);
            panel.repaint();
        });

        cascade.addActionListener(e -> {
            FileFrame newWin = new FileFrame();
            if(myFrame == null){
                newWin.setLocation(0,100);
            } else {
                newWin.setLocation(myFrame.getLocation().x + 25, myFrame.getLocation().y + 25);
            }
            desktopPane.add(newWin);
            panel.repaint();
        });
        run.addActionListener(ae -> {
            try {
                desktop.open(currentFile);
            } catch (Throwable t) {
                showThrowable(t);
            }
            panel.repaint();
        });

        exit.addActionListener(e -> System.exit(0));

        about.addActionListener(e -> {
            AboutDlg dlg = new AboutDlg(null,true);

            dlg.setVisible(true);
        });

        help.addActionListener(e -> {
            HelpDlg helpDlg = new HelpDlg(null,true);

            helpDlg.setVisible(true);
        });

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

    public static void buildStatusBar(){
        File diskPartition = new File(FileSystemView.getFileSystemView().getDefaultDirectory().getPath());
        long totalCapacity = diskPartition.getTotalSpace();

        long freePartitionSpace = diskPartition.getFreeSpace();
        long usablePartitionSpace = diskPartition.getUsableSpace();

        JLabel status = new JLabel("Current Drive"+ (diskPartition.getName())  + "Free Space : "
                + (freePartitionSpace / (1024*1024*1024)) + " GB   Used Space: " + (usablePartitionSpace/ (1024*1024*1024))
                + " GB   Total Space: " + ((totalCapacity)/ (1024*1024*1024)) + " GB");

        statusBar.add(status);
        panel.add(statusBar, BorderLayout.SOUTH);

    }

  class MyDropTarget extends DropTarget {

  	  public void drop(DropTargetDropEvent evt )
  	  {
  	      try
  	      {
  	          evt.acceptDrop(DnDConstants.ACTION_COPY);
  	          ArrayList result = new ArrayList();
//  	          result = (List)evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
  	          if (evt.getTransferable().isDataFlavorSupported(DataFlavor.stringFlavor))
  	          {
  	        	  String temp = (String)evt.getTransferable().getTransferData(DataFlavor.stringFlavor);

  	        	  String[] next = temp.split("\\n");

  	        	  for ( int i = 0; i < next.length; i ++)
  	        	  {
  	        		  model.addElement(next[i]);
  	        	  }
  	          }
  	          else
  	          {
  	        	  result = (ArrayList) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
  	              for ( Object o : result )
  	              {
  	                  System.out.println(o.toString());
  	                  model.addElement(o.toString());
  	              }
  	          }

  	      }
  	      catch ( Exception ex )
  	      {
  	    	  ex.printStackTrace();
  	      }
  	  }
  	}
private void showThrowable(Throwable t) {
    t.printStackTrace();
    JOptionPane.showMessageDialog(
            panel,
            t.toString(),
            t.getMessage(),
            JOptionPane.ERROR_MESSAGE
    );
    panel.repaint();
}
    private void showErrorMessage(String errorMessage, String errorTitle) {
        JOptionPane.showMessageDialog(
                panel,
                errorMessage,
                errorTitle,
                JOptionPane.ERROR_MESSAGE
        );
    }
    private void deleteFile() {
        if (currentFile==null) {
            showErrorMessage("No file selected for deletion.","Select File");
            return;
        }

        int result = JOptionPane.showConfirmDialog(
                panel,
                "Are you sure you want to delete this file?",
                "Delete File",
                JOptionPane.YES_NO_OPTION
        );
        if (result==JOptionPane.OK_OPTION) {
            try {
                System.out.println("currentFile: " + currentFile);
                TreePath parentPath = DirPanel.findTreePath(currentFile.getParentFile());
                System.out.println("parentPath: " + parentPath);
                DefaultMutableTreeNode parentNode =
                        (DefaultMutableTreeNode)parentPath.getLastPathComponent();
                System.out.println("parentNode: " + parentNode);

                boolean directory = currentFile.isDirectory();
                boolean deleted = currentFile.delete();
                if (deleted) {
                    if (directory) {
                        // delete the node..
                        TreePath currentPath = DirPanel.findTreePath(currentFile);
                        System.out.println(currentPath);
                        DefaultMutableTreeNode currentNode =
                                (DefaultMutableTreeNode)currentPath.getLastPathComponent();

                        DirPanel.treeModel.removeNodeFromParent(currentNode);
                    }

                    DirPanel.showChildren(parentNode);
                } else {
                    String msg = "The file '" +
                            currentFile +
                            "' could not be deleted.";
                    showErrorMessage(msg,"Delete Failed");
                }
            } catch(Throwable t) {
                showThrowable(t);
            }
        }
        panel.repaint();
    }

    private void renameFile() {
        if (currentFile==null) {
            showErrorMessage("No file selected to rename.","Select File");
            return;
        }

        String renameTo = JOptionPane.showInputDialog(panel, "New Name");
        if (renameTo!=null) {
            try {
                boolean directory = currentFile.isDirectory();
                TreePath parentPath = DirPanel.findTreePath(currentFile.getParentFile());
                DefaultMutableTreeNode parentNode =
                        (DefaultMutableTreeNode)parentPath.getLastPathComponent();

                boolean renamed = currentFile.renameTo(new File(
                        currentFile.getParentFile(), renameTo));
                if (renamed) {
                    if (directory) {
                        // rename the node..

                        // delete the current node..
                        TreePath currentPath = DirPanel.findTreePath(currentFile);
                        System.out.println(currentPath);
                        DefaultMutableTreeNode currentNode =
                                (DefaultMutableTreeNode)currentPath.getLastPathComponent();

                        DirPanel.treeModel.removeNodeFromParent(currentNode);

                        // add a new node..
                    }

                    DirPanel.showChildren(parentNode);
                } else {
                    String msg = "The file '" +
                            currentFile +
                            "' could not be renamed.";
                    showErrorMessage(msg,"Rename Failed");
                }
            } catch(Throwable t) {
                showThrowable(t);
            }
        }
        panel.repaint();
    }
    public static boolean copyFile(File from, File to) throws IOException {

        boolean created = to.createNewFile();

        if (created) {
            FileChannel fromChannel = null;
            FileChannel toChannel = null;
            try {
                fromChannel = new FileInputStream(from).getChannel();
                toChannel = new FileOutputStream(to).getChannel();

                toChannel.transferFrom(fromChannel, 0, fromChannel.size());

                // set the flags of the to the same as the from
                to.setReadable(from.canRead());
                to.setWritable(from.canWrite());
                to.setExecutable(from.canExecute());
            } finally {
                if (fromChannel != null) {
                    fromChannel.close();
                }
                if (toChannel != null) {
                    toChannel.close();
                }
                return false;
            }
        }
        return created;
    }
}
