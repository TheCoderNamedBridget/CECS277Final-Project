import javax.swing.*;

public class FileFrame extends JInternalFrame
{
    JSplitPane splitPane;
    DirPanel dirPanel;
    FilePanel filePanel;

    public  FileFrame()
    {
        dirPanel = new DirPanel();
        filePanel = new FilePanel();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, dirPanel , filePanel);


        this.getContentPane().add(splitPane);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setClosable(true);
        this.setSize(750, 500);
        this.setVisible(true);
    }

}
