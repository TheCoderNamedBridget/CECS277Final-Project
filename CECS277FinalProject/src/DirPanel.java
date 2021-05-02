import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DirPanel extends JInternalFrame {
    private final JTree dirTree = new JTree();
    private FileSystemView fileSystemView;

    public DirPanel() {
        buildTree();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(dirTree);
        Dimension preferredSize = scrollPane.getPreferredSize();
        Dimension widePreferred = new Dimension(
                200,
                (int)preferredSize.getHeight());
        scrollPane.setPreferredSize( widePreferred );
        add(scrollPane);
        this.setResizable(true);
        setVisible(true);
    }

    private void buildTree() {
        fileSystemView = FileSystemView.getFileSystemView();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        System.out.println(Arrays.toString(root.getPath()));
        DefaultTreeModel treeModel = new DefaultTreeModel(root);

        TreeSelectionListener treeSelectionListener = tse -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
            showChildren(node);
        };

        File[] roots = fileSystemView.getRoots();
        for (File fileSystemRoot : roots) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
            root.add(node);
            File[] files = fileSystemView.getFiles(fileSystemRoot.getAbsoluteFile(), true);
            for (File file : files) {
                if (file.isDirectory()) {
                    node.add(new DefaultMutableTreeNode(file));
                }
            }
        }
        dirTree.addTreeSelectionListener(treeSelectionListener);
        dirTree.setRootVisible(false);
        dirTree.setModel(treeModel);
        dirTree.expandRow(0);
    }

    private void showChildren(final DefaultMutableTreeNode node) {
        //dirTree.setEnabled(false);

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

}
