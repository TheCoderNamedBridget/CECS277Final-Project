import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class FileFrame extends JInternalFrame
{

    JSplitPane splitPane;
    FileSystemView fileSystemView;
    public  FileFrame()
    {
        FilePanel filePanel = new FilePanel();
        DirPanel dirPanel = new DirPanel();
        fileSystemView = FileSystemView.getFileSystemView();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, dirPanel , filePanel);

        this.setTitle("C:");

        this.getContentPane().add(splitPane);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setClosable(true);
        this.setSize(750, 500);
        this.setVisible(true);
    }

}
