import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.filechooser.FileSystemView;

public class FileFrame extends JInternalFrame
{
	
	JSplitPane splitPane;
	public  FileFrame()
	{
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new DirPanel(), new FilePanel());
		
		//This line may change based on Windows/macOS/Linux OS's
        	//this.setTitle(FileSystemView.getFileSystemView().getHomeDirectory().getParentFile().getParentFile().getPath());
		
		this.getContentPane().add(splitPane);
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setClosable(true);
		this.setSize(200, 200);
		this.setVisible(true);
	}
}
