import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class CECS277GUIIntro {
	
	
	
	
	public static void main( String[] args )
	{
		App app = new App();
		app.go();
		

		
		//how to get stuff out of directories and files
		//this part works
//		File file = new File("C:\\Program Files");
//		File[] files;
//		files = file.listFiles();
//		for ( int i = 0; i < files.length; i++)
//		{
//			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//			DecimalFormat dformatter = new DecimalFormat("#,###");
//			
//			if ( files[i].isDirectory() )
//			{
//				System.out.println("Directory " + files[i].getAbsolutePath() 
//						+ " Date: " + formatter.format(files[i].lastModified())
//						+ " Size: " + dformatter.format(files[i].length()));
//			}
//			else
//			{
//				System.out.println("File " + files[i].getAbsolutePath() 
//						+ " Date: " + formatter.format(files[i].lastModified())
//						+ " Size: " + dformatter.format(files[i].length()));
//			}
//			
//		}
		
		
		
		//supposed to be able to get a file from desktop
//		Desktop desktop = Desktop.getDesktop();
//		try
//		{
//			System.out.print("it worked: ");
//			desktop.open(new File("C:\\Users\\bridg\\Desktop\\fakedoc.txt"));
//			System.out.print("it worked: ");
//		}
//		catch ( IOException ex)
//		{
//			System.out.println( ex.toString() );
//		}
	}

}
