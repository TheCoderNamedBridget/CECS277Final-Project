import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.io.File;

public class FilePanel extends JInternalFrame {
    private static JTable table = new JTable();
    private File[] files;
    private final FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    private static DefaultTableModel tableModel;
    private ListSelectionListener listSelectionListener;
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
        tableModel.setColumnIdentifiers(new String[]{"Files Names"});

        Object[] row = new Object[1];

        for (File file : files) {

            row[0] = file.getName();

            tableModel.addRow(row);

        }
        table.setModel(tableModel);
    }


}
