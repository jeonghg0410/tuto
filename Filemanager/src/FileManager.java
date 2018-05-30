import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileFilter;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Filemanager extends JFrame {
	Panel p1 = new Panel(new BorderLayout());
	Panel p2 = new Panel(new BorderLayout());
	Panel p3 = new Panel(new BorderLayout());
	Panel pan = new Panel(new BorderLayout());
	JComboBox<String> ch1 = new JComboBox();
	JLabel l1 = new JLabel("File Explorer");
	
	
	JTree tree;
	private DefaultMutableTreeNode leaf3;
	private DefaultMutableTreeNode leaf1;
	private DefaultTreeModel treeModel;
	private String colName[] = { "Name", "Size", "Modified"};
	DefaultTableModel model;
	JTable table;

	
	void Model(){
	model = new DefaultTableModel(null, colName);
	table = new JTable(model);
	pan.add(new JScrollPane(table));
	}
	
	
	public Filemanager() {
		super("/home/");
		DefaultMutableTreeNode branch = new DefaultMutableTreeNode("내 컴퓨터");
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("디스크 드라이브(C:)");
		DefaultMutableTreeNode root1 = new DefaultMutableTreeNode("디스크 드라이브(D:)");
		treeModel = new DefaultTreeModel(branch);
		tree = new JTree(treeModel);
		File directory = new File("C:\\");
		File[] files = directory.listFiles(); 
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File file) { 
				return file.isDirectory();
			}
		};
		File dir1 = new File("D:\\");
		File[] files1 = dir1.listFiles(); 
		FileFilter fileFilter1 = new FileFilter() {
			public boolean accept(File file1) { 
				return file1.isDirectory();
			}
		};
		files = directory.listFiles(fileFilter);
		if (files.length == 0) {
			System.out.println("Either dir does not exist or is not a directory");
			JOptionPane.showMessageDialog(null, "폴더가 존재하지 않습니다.");
		} else {
			for (int i = 0; i < files.length; i++) {
				File filename = files[i];
				if (filename.toString().contains("$") || filename.toString().contains("Recovery")
						|| filename.toString().contains("System") || filename.toString().contains("Temp")
						|| filename.toString().contains("PerfLogs"))

					continue;
				else {	
						treeModel = new DefaultTreeModel(root);
						treeModel.insertNodeInto(root, branch, 0);
						Object a = filename;
						String w = a.toString();
						String e[] = w.split("\\\\");
						leaf3 = new DefaultMutableTreeNode(e[1]);
						root.add(leaf3);
					}
				}
			}
		
		files1 = dir1.listFiles(fileFilter1);
		if (files1.length == 0) {
			System.out.println("Either dir does not exist or is not a directory");
			JOptionPane.showMessageDialog(null, "폴더가 존재하지 않습니다.");
		} else {
			for (int i = 0; i < files1.length; i++) {
				File filename1 = files1[i];
				if (filename1.toString().contains("$") || filename1.toString().contains("Recovery")
						|| filename1.toString().contains("System") || filename1.toString().contains("Temp")
						|| filename1.toString().contains("PerfLogs"))

					continue;
				else {	
						treeModel = new DefaultTreeModel(root1);
						treeModel.insertNodeInto(root1, branch, 0);
						Object b = filename1;
						String c = b.toString();
						String d[] = c.split("\\\\");
					leaf1 = new DefaultMutableTreeNode(d[1]);
						root1.add(leaf1);
					}
				}
			}
	 	ch1.addItem("English");
		ch1.addItem("한국어");	
		add(p1,BorderLayout.SOUTH);
		add(p2,BorderLayout.WEST);
		add(p3,BorderLayout.NORTH);
		add(pan,BorderLayout.EAST);
		p1.add(l1,BorderLayout.WEST);
		p1.add(ch1,BorderLayout.EAST);
		p2.add(tree);
		Model();
		ChangeLangName();
		this.add(pan);	
		pack();
		setVisible(true);
		GUIView();
		this.setBounds(100, 100, 500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		
	}

	public static void main(String[] args) {
		 new Filemanager();
		 
	}
	
				
	void ChangeLangName() {
		ch1.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getItem().equals("한국어")){
					l1.setText("파일 탐색기");
					colName[0] = "이름";
					colName[1] = "크기";
					colName[2] = "수정한 날짜";	
					Model();
				}
				else{
					l1.setText("File Manager");
					colName[0] = "Name";
					colName[1] = "Size";
					colName[2] = "Modified";	
					Model();
				
				}
			}
		});
	}
	void GUIView(){
		Container ctp;
		JPopupMenu jpm;
		JMenuItem jmiShowItemintheFolder;
		JMenuItem jmiCopy;
		JMenuItem jmiPaste;
		JMenuItem jmiDelete;
		ctp = getContentPane();
		jpm = new JPopupMenu("Edit");
		jmiShowItemintheFolder = new JMenuItem("Show Item in the Folder");
		jmiCopy = new JMenuItem("Copy");
		jmiPaste = new JMenuItem("Paste");
		jmiDelete= new JMenuItem("Delete");
		ctp.addMouseListener(new MouseAdapter() {
			
		});
	}
}
