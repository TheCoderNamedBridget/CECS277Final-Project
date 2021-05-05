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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilePanel extends JInternalFrame {
    protected static JTable table;
    private static FileSystemView fileSystemView;
    private static ListSelectionListener listSelectionListener;
    private static boolean cellSizesSet;

    private static JLabel fileName;
    private static JTextField path;
    private static JLabel date;
    private static JLabel size;
    static JList list;
    static DefaultListModel model;
    static FilePanelModel fpm;



    public FilePanel() {
        table = new JTable();
        fileSystemView = FileSystemView.getFileSystemView();
        list = new JList();
        model = new DefaultListModel();
        cellSizesSet = false;
        fileName = new JLabel("");
        path = new JTextField("");
        date = new JLabel("");
        size = new JLabel("");
        fpm = new FilePanelModel();

        App.buildStatusBar();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        table.setDragEnabled(true);
        add(scrollPane);
        this.setResizable(true);
        table.setModel(fpm);

        setVisible(true);
        listSelectionListener = lse -> {
            int row = table.getSelectionModel().getLeadSelectionIndex();
            setFileDetails( ((FilePanelModel)table.getModel()).getFile(row) );
        };


        //DRAG AND DROP FEATURE
        list.setPreferredSize(new Dimension(500,500));
        this.setDropTarget(new MyDropTarget());
        list.setDragEnabled(true);
        list.setModel(model);
        //add(list);

    }

    public static void setTableData(File[] files) {
        FilePanelModel filePanelModel = new FilePanelModel();
        table.setModel(filePanelModel);

        table.getSelectionModel().removeListSelectionListener(listSelectionListener);
        filePanelModel.setFiles(files);
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

    static void setFileDetails(File file) {

    	System.out.println("setFileDetails " + file.getName());

        App.currentFile = file;
        Icon icon = fileSystemView.getSystemIcon(file);

        fileName.setIcon(icon);
        fileName.setText(fileSystemView.getSystemDisplayName(file));
        path.setText(file.getPath());
        date.setText(new Date(file.lastModified()).toString());
        size.setText(file.length() + " bytes");

    }

    class MyDropTarget extends DropTarget{
    public void drop(DropTargetDropEvent evt){
        try {
            //types of events accepted
            evt.acceptDrop(DnDConstants.ACTION_COPY);
            //storage to hold the drop data for processing
            List result = new ArrayList();
            //what is being dropped? First, Strings are processed
            if(evt.getTransferable().isDataFlavorSupported(DataFlavor.stringFlavor)){
                String temp = (String)evt.getTransferable().getTransferData(DataFlavor.stringFlavor);
                //String events are concatenated if more than one list item
                //selected in the source. The strings are separated by
                //newline characters. Use split to break the string into
                //individual file names and store in String[]
                String[] next = temp.split("\\n");
                //add the strings to the listmodel
                for(int i=0; i<next.length;i++)
                    model.addElement(next[i]);
            }
            else{ //then if not String, Files are assumed
                result =(List)evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                //process the input
                for(Object o : result){
                    System.out.println(o.toString());
                    model.addElement(o.toString());
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
}
