package cn.lingnan.view.subview;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import cn.lingnan.DAO.TableDAO;
import cn.lingnan.dto.Table;
import cn.lingnan.view.model.TableInfoTableModel;
import cn.lingnan.view.popout.TableDialog;

public class StaffTableView extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private TableDAO td;
	// 上面
	private JPanel toolBarPanel;

	private JPanel searchPanel;
	private JLabel nameLabel;
	private JTextField nameSearchTF;
	private JButton searchBtn;

	private JPanel opePanel;
	private JButton addBtn, updateBtn, deleteBtn;

	// 中间
	private JScrollPane tableScrollPane;
	private JTable tableTable;

	// 下面
	private JPanel bottomPanel;
	private JLabel countInfoLabel;
	private JFrame jFrame;

	private TableDAO tableDAO = new TableDAO();

	public StaffTableView(JFrame jFrame) {
		this.setLayout(new BorderLayout());
		initView();
		this.jFrame = jFrame;
	}

	private void initView() {

		toolBarPanel = new JPanel(new BorderLayout());

		searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		nameLabel = new JLabel("桌号");
		nameSearchTF = new JTextField(10);

		searchBtn = new JButton("搜索", new ImageIcon("static\\icon\\search.png"));
		searchBtn.addActionListener(this);

		opePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		addBtn = new JButton("添加", new ImageIcon("static\\icon\\user_add.png"));
		updateBtn = new JButton("更新", new ImageIcon("static\\icon\\user_edit.png"));
		deleteBtn = new JButton("删除", new ImageIcon("static\\icon\\user_delete.png"));

		addBtn.addActionListener(this);
		updateBtn.addActionListener(this);
		deleteBtn.addActionListener(this);

		opePanel.add(addBtn);
		opePanel.add(updateBtn);
		opePanel.add(deleteBtn);

		searchPanel.add(nameLabel);
		searchPanel.add(nameSearchTF);
		searchPanel.add(searchBtn);

		toolBarPanel.add(searchPanel, "West");
		toolBarPanel.add(opePanel, "East");

		// 中间
		td = new TableDAO();
		Vector<Table> vm = td.FindAllTable();
		TableInfoTableModel model = new TableInfoTableModel(vm);
		tableTable = new JTable(model);
		tableScrollPane = new JScrollPane(tableTable);

		// 下面
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String str = Integer.toString(model.getRowCount());
		countInfoLabel = new JLabel("总共" + str + "条");
		bottomPanel.add(countInfoLabel);

		this.add(toolBarPanel, "North");
		this.add(tableScrollPane, "Center");
		this.add(bottomPanel, "South");

		this.setVisible(true);
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		TableDAO td = new TableDAO();
		Object source = e.getSource();
		if (source == searchBtn) {
			if (nameSearchTF.getText().isEmpty()) {
				Vector<Table> vm = td.FindAllTable();
				this.updateTable(vm);
				return;
			} else {
				Vector<Table> vm = td.Find("Table_Id", Integer.parseInt(nameSearchTF.getText()));
				this.updateTable(vm);
				return;
			}

		} else if (addBtn == source) {
			TableDialog tableDialog = new TableDialog(jFrame, this);
			tableDialog.setVisible(true);
			Vector<Table> vm = td.FindAllTable();
			this.updateTable(vm);
		} else if (updateBtn == source) {
			Table table = new Table();
			int rowIndex = tableTable.getSelectedRow();
			if (rowIndex == -1) {
				JOptionPane.showMessageDialog(this, "请选中一条");
				return;
			}

			int id = (Integer) tableTable.getValueAt(rowIndex, 0);
			table = td.FindTable(id);

			if (table == null) {
				JOptionPane.showMessageDialog(this, "数据不存在,请刷新");
				return;
			}
			TableDialog tableDialog = new TableDialog(jFrame, this, table);
			tableDialog.setVisible(true);
			Vector<Table> vm = td.FindAllTable();
			this.updateTable(vm);
		} else if (deleteBtn == source) {
			// 获取选中记录
			int rowIndex = tableTable.getSelectedRow();
			if (rowIndex == -1) {
				JOptionPane.showMessageDialog(this, "请选中一条");
				return;
			}
			int id = (Integer) tableTable.getValueAt(rowIndex, 0);
			Table m = new Table();
			m.setTable_Id(id);
			if (tableDAO.DeleteWork(m) == true) {
				JOptionPane.showMessageDialog(this, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
//				    refresh();
			} else {
				JOptionPane.showMessageDialog(this, "删除失败", "提示", JOptionPane.ERROR_MESSAGE);
			}
		}
		Vector<Table> vm = td.FindAllTable();
		this.updateTable(vm);
	}

	public void updateTable(Vector<Table> vm) {
		this.remove(tableScrollPane);
		TableInfoTableModel model = new TableInfoTableModel(vm);
		tableTable = new JTable(model);
		tableScrollPane = new JScrollPane(tableTable);
		this.add(tableScrollPane, "Center");
		
		bottomPanel.remove(countInfoLabel);
		String str = Integer.toString(model.getRowCount());
		countInfoLabel = new JLabel("总共" + str + "条");
		bottomPanel.add(countInfoLabel);
		bottomPanel.validate();
		
		this.validate();


	}
}
