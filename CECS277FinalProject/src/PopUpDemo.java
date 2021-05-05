import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

class PopUpDemo extends JPopupMenu {
    JMenuItem rename;
    JMenuItem copy;
    JMenuItem paste;
    JMenuItem delete;
    
    public PopUpDemo() {
    	rename = new JMenuItem("Rename");
    	copy = new JMenuItem("Copy");
    	paste = new JMenuItem("Paste");
    	delete = new JMenuItem("Delete");
        
        add(rename);
        add(copy);
        add(paste);
        add(delete);
        
        
        rename.addActionListener(e -> {
        App.renameFile();
    });
        
        delete.addActionListener(e -> {
            App.deleteFile();
        });
        
    }
}
