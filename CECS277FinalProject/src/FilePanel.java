import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.File;

public class FilePanel extends JInternalFrame {
    private static JTable table = new JTable();
    private File[] files;
    private final static FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    private static FileTableModel fileTableModel;
    private static ListSelectionListener listSelectionListener;
    private static boolean cellSizesSet = false;
    private final String[] columns = {
            "Icon",
            "File",
            "Path/name",
            "Size",
            "Last Modified",
            "R",
            "W",
            "E",
            "D",
            "F",
    };

    public FilePanel() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        add(scrollPane);
        this.setResizable(true);
        setVisible(true);
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
        table.setModel(fileTableModel);
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



}

