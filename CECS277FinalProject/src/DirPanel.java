import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.io.File;
import java.util.List;

public class DirPanel extends JInternalFrame {
    private static JTree dirTree;
    private static JLabel fileName;
    private static FileSystemView fileSystemView;
    static DefaultTreeModel treeModel;
    private static DefaultMutableTreeNode root;

    public DirPanel() {
        dirTree = new JTree();
        fileName = new JLabel("");
        buildTree();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(dirTree);
        Dimension preferredSize = scrollPane.getPreferredSize();
        Dimension widePreferred = new Dimension(
                200,
                (int)preferredSize.getHeight());
        scrollPane.setPreferredSize( widePreferred );
        add(scrollPane);
        setResizable(true);
        setVisible(true);
    }

    private void buildTree() {
        fileSystemView = FileSystemView.getFileSystemView();
        root = new DefaultMutableTreeNode();
        treeModel = new DefaultTreeModel(root);

        TreeSelectionListener treeSelectionListener = tse -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
            showChildren(node);
            File file = (File) node.getUserObject();
            if (file.isDirectory()) {
                System.out.println(file.getPath());
                App.myFrame.setTitle(file.getAbsolutePath());
            }
        };

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
        dirTree.addTreeSelectionListener(treeSelectionListener);
        dirTree.setCellRenderer(new FileTreeCellRenderer());
        dirTree.setRootVisible(false);
        dirTree.setModel(treeModel);
        dirTree.expandRow(0);
    }

    static void showChildren(final DefaultMutableTreeNode node) {
        dirTree.setEnabled(false);
        SwingWorker<Void, File> worker = new SwingWorker<>() {
            @Override
            public Void doInBackground() {
                File file = (File) node.getUserObject();
                if (file.isDirectory()) {
                    File[] files = fileSystemView.getFiles(file, true);
                    if (node.isLeaf()) {
                        for (File child : files) {
                            if (child.isDirectory()) {
                                publish(child);
                            }
                        }
                    }
                   FilePanel.setTableData(files);
                }
                return null;
            }

            @Override
            protected void process(List<File> chunks) {
                for (File child : chunks) {
                    node.add(new DefaultMutableTreeNode(child));
                }
            }

            @Override
            protected void done() {
                dirTree.setEnabled(true);
            }
        };
        worker.execute();
    }
    protected static TreePath findTreePath(File find) {
        for (int ii=0; ii<dirTree.getRowCount(); ii++) {
            TreePath treePath = dirTree.getPathForRow(ii);
            Object object = treePath.getLastPathComponent();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)object;
            File nodeFile = (File)node.getUserObject();

            if (nodeFile==find) {
                return treePath;
            }
        }
        // not found!
        return null;
    }

}
