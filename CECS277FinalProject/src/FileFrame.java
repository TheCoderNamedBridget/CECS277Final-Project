import javax.swing.JInternalFrame;

public class FileFrame extends JInternalFrame
{
	public  FileFrame()
	{
		this.setTitle("C:");
		
		this.setMaximizable(true);
		this.setIconifiable(true);
		this.setClosable(true);
		this.setSize(200, 200);
		this.setVisible(true);
	}
}
