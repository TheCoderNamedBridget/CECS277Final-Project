import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.File;
import java.util.Date;

public class FilePanel extends JInternalFrame {
    private static JTable table = new JTable();
    private final static FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    private static FileTableModel fileTableModel;
    private static ListSelectionListener listSelectionListener;
    private static boolean cellSizesSet = false;
    private JLabel fileName;
    private JTextField path;
    private JLabel date;
    private JLabel size;
    private JCheckBox readable;
    private JCheckBox writable;
    private JCheckBox executable;
    private JRadioButton isDirectory;
    private JRadioButton isFile;

    public FilePanel() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        add(scrollPane);
        this.setResizable(true);
        setVisible(true);
        listSelectionListener = lse -> {
            int row = table.getSelectionModel().getLeadSelectionIndex();
            setFileDetails( ((FileTableModel)table.getModel()).getFile(row) );
        };
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



}
