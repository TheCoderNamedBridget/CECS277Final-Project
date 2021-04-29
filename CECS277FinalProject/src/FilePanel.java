import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilePanel extends JInternalFrame {
    private static JTable table = new JTable();
    private final static FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    private static FileTableModel fileTableModel;
    private static ListSelectionListener listSelectionListener;
    private static boolean cellSizesSet = false;
    
    private JLabel fileName = new JLabel("");
    private JTextField path = new JTextField("");
    private JLabel date = new JLabel("");
    private JLabel size = new JLabel("");
    private JCheckBox readable = new JCheckBox("");
    private JCheckBox writable = new JCheckBox("");
    private JCheckBox executable = new JCheckBox("");
    private JRadioButton isDirectory = new JRadioButton("");
    private JRadioButton isFile = new JRadioButton("");
    JList list = new JList();
    DefaultListModel model = new DefaultListModel();
    
    

    public FilePanel() {
    	App.buildStatusBar("");
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        table.setDragEnabled(true);
        add(scrollPane);
        this.setResizable(true);
        
        setVisible(true);
        listSelectionListener = lse -> {
            int row = table.getSelectionModel().getLeadSelectionIndex();
            setFileDetails( ((FileTableModel)table.getModel()).getFile(row) );
            
            
        };
        
        
        //DRAG AND DROP FEATURE
//        list.setPreferredSize(new Dimension(500,500));
//        this.setDropTarget(new MyDropTarget());
//        list.setDragEnabled(true);
//        list.setModel(model);
//        add(list);
        

    }

    public static void setTableData(File[] files) {
        if (fileTableModel == null) {
            fileTableModel = new FileTableModel();
            table.setModel(fileTableModel);
        }
        table.getSelectionModel().removeListSelectionListener(listSelectionListener);
        fileTableModel.setFiles(files);
        table.getSelectionModel().addListSelectionListener(listSelectionListener);
        if (!cellSizesSet) {
            Icon icon = fileSystemView.getSystemIcon(files[0]);

            table.setRowHeight(icon.getIconHeight());

            setColumnWidth(0, -1);
            setColumnWidth(3, 60);
            table.getColumnModel().getColumn(3).setMaxWidth(120);
            setColumnWidth(4, -1);
            setColumnWidth(5, -1);
            setColumnWidth(6, -1);
            setColumnWidth(7, -1);
            setColumnWidth(8, -1);
            setColumnWidth(9, -1);

            cellSizesSet = true;
        }
    }

    private static void setColumnWidth(int column, int width) {
        TableColumn tableColumn = table.getColumnModel().getColumn(column);
        if (width<0) {
            JLabel label = new JLabel( (String)tableColumn.getHeaderValue() );
            Dimension preferred = label.getPreferredSize();
            width = (int)preferred.getWidth();
        }
        tableColumn.setPreferredWidth(width);
        tableColumn.setMaxWidth(width);
        tableColumn.setMinWidth(width);
    }

    private void setFileDetails(File file) {
    	
//    	System.out.println("setFileDetails " + file.getName());
    	
        Icon icon = fileSystemView.getSystemIcon(file);
        
        fileName.setIcon(icon);
        fileName.setText(fileSystemView.getSystemDisplayName(file));
        path.setText(file.getPath());
        date.setText(new Date(file.lastModified()).toString());
        size.setText(file.length() + " bytes");
        readable.setSelected(file.canRead());
        writable.setSelected(file.canWrite());
        executable.setSelected(file.canExecute());
        isDirectory.setSelected(file.isDirectory());

        isFile.setSelected(file.isFile());
    }
//    class MyDropTarget extends DropTarget {
//
//    	  public void drop(DropTargetDropEvent evt )
//    	  {
//    		  System.out.println("here " );
//    	      try
//    	      {
//    	          evt.acceptDrop(DnDConstants.ACTION_COPY);
//    	          List result = new ArrayList();
////    	          result = (List)evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
//    	          System.out.println("here1 data = " + evt.getTransferable().isDataFlavorSupported(DataFlavor.stringFlavor) );
//    	          if (evt.getTransferable().isDataFlavorSupported(DataFlavor.stringFlavor))
//    	          {
//    	        	  String temp = (String)evt.getTransferable().getTransferData(DataFlavor.stringFlavor);
//    	        	  
//    	        	  String[] next = temp.split("\\n");
//    	        	  System.out.println("here2 " );
//    	        	  for ( int i = 0; i < next.length; i ++)
//    	        	  {
//    	        		  model.addElement(next[i]);
//    	        		  System.out.print("here " + next[i]);
////    	        		  int row = table.getSelectionModel().getLeadSelectionIndex();
////    	        		  table.add(desktopIcon);
////    	        		  System.out.println("tried to drag drop somtetin");
////    	                  setFileDetails( ((FileTableModel)table.getModel()).getFile(row) );
//    	        		  
//    	        	  }
//    	          }
//    	          else
//    	          {
//    	        	  result = (List)evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
//    	              for ( Object o : result )
//    	              {
//    	                  System.out.println("im getting here " + o.toString());
//    	                  File newFile = new File("C:\\Users\\bridg\\Desktop\\New Text Document (2).txt");
//    	                  model.addElement(o.toString());
//    	                  int row = table.getSelectionModel().getLeadSelectionIndex();
//    	                  setFileDetails( newFile );
//    	              }
//    	          }
//
//    	      }
//    	      catch ( Exception ex )
//    	      {
//    	    	  ex.printStackTrace();
//    	      }
//    	  }
//    	}


}
