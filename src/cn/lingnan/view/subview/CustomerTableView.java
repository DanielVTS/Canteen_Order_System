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

public class CustomerTableView extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	// 上面
	private JPanel toolBarPanel;

	private JPanel searchPanel;
	private JLabel idLabel;
	private JTextField nameSearchTF;
	private JButton searchBtn;

	private JPanel opePanel;
	private JButton updateBtn;

	// 中间
	private JScrollPane tableScrollPane;
	private JTable TableTable;

	// 下面
	private JPanel bottomPanel;
	private JLabel countInfoLabel;
	private JFrame jFrame;

	private TableDAO td;

	public CustomerTableView(JFrame jFrame) {
		this.setLayout(new BorderLayout());
		initView();
		this.jFrame = jFrame;
	}

	private void initView() {

		toolBarPanel = new JPanel(new BorderLayout());

		searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		idLabel = new JLabel("餐桌状态");
		nameSearchTF = new JTextField(10);

		searchBtn = new JButton("搜索", new ImageIcon("static\\icon\\search.png"));

		opePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		updateBtn = new JButton("预订", new ImageIcon("static\\icon\\user_edit.png"));

		updateBtn.addActionListener(this);
		searchBtn.addActionListener(this);

		opePanel.add(updateBtn);

		searchPanel.add(idLabel);
		searchPanel.add(nameSearchTF);
		searchPanel.add(searchBtn);

		toolBarPanel.add(searchPanel, "West");
		toolBarPanel.add(opePanel, "East");

		// 中间
		td = new TableDAO();
		Vector<Table> vm = td.FindAllTable();
		TableInfoTableModel model = new TableInfoTableModel(vm);
		TableTable = new JTable(model);
		tableScrollPane = new JScrollPane(TableTable);

		// 下面
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String str = Integer.toString(model.getRowCount());
		countInfoLabel = new JLabel("总共" + str + "条");
		bottomPanel.add(countInfoLabel);

		this.add(toolBarPanel, "North");
		this.add(this.tableScrollPane, "Center");
		this.add(bottomPanel, "South");

		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TableDAO md = new TableDAO();
		Object source = e.getSource();
		if (source == searchBtn) {
			if (nameSearchTF.getText().isEmpty()) {
				Vector<Table> vm = md.FindAllTable();
				this.updateTable(vm);
				return;
			} else {
				Vector<Table> vm = md.Find("Table_Status", Integer.parseInt(nameSearchTF.getText()));
				this.updateTable(vm);
				return;
			}

		}
		if (updateBtn == source) {
			Table table = new Table();
			int rowIndex = TableTable.getSelectedRow();
			if (rowIndex == -1) {
				JOptionPane.showMessageDialog(this, "请选中一条");
				return;
			}

			TableDialog tableDialog = new TableDialog(jFrame, this, table);

			tableDialog.setVisible(true);
			Vector<Table> vm = md.FindAllTable();
			this.updateTable(vm);
		}

	}

	public void updateTable(Vector<Table> vm) {
		this.remove(tableScrollPane);
		TableInfoTableModel model = new TableInfoTableModel(vm);
		TableTable = new JTable(model);
		tableScrollPane = new JScrollPane(TableTable);
		this.add(tableScrollPane, "Center");

		bottomPanel.remove(countInfoLabel);
		String str = Integer.toString(model.getRowCount());
		countInfoLabel = new JLabel("总共" + str + "条");
		bottomPanel.add(countInfoLabel);
		bottomPanel.validate();

		this.validate();

	}
}