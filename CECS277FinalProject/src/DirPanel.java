import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;

public class DirPanel extends JInternalFrame {
    private JScrollPane scrollPane = new JScrollPane();
    private JTree dirTree = new JTree();
    private FileSystemView fileSystemView;
    private File currentFile;
    private DefaultTreeModel treeModel;

    public DirPanel() {
        buildTree();
        scrollPane.setViewportView(dirTree);
        add(scrollPane);
        this.setResizable(true);
        setVisible(true);
    }

    private void buildTree() {
        fileSystemView = FileSystemView.getFileSystemView();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        treeModel = new DefaultTreeModel(root);

        File[] roots = fileSystemView.getRoots();
        for (File fileSystemRoot : roots) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
            root.add(node);
            File[] files = fileSystemView.getFiles(fileSystemRoot, true);
            for (File file : files) {
                if (file.isDirectory()) {
                    node.add(new DefaultMutableTreeNode(file));
                }
            }
        }
        dirTree.setModel(treeModel);
        dirTree.setRootVisible(false);
    }
}
