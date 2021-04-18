import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;

public class FileFrame extends JInternalFrame
{
	
	JSplitPane splitPane;
	public  FileFrame()
	{
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new DirPanel(), new FilePanel());
		this.setTitle("C:");
		
		this.getContentPane().add(splitPane);
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setClosable(true);
		this.setSize(200, 200);
		this.setVisible(true);
	}
}
