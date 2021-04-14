import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//kills program on exit
public class ExitActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);

	}

}
