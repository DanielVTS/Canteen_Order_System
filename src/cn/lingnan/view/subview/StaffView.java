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

import cn.lingnan.DAO.StaffDAO;
import cn.lingnan.dto.Staff;
import cn.lingnan.view.model.StaffInfoTableModel;
import cn.lingnan.view.popout.StaffDialog;

//import com.lingnan.catering.dialog.UserDialog;

public class StaffView extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private StaffDAO sd;
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
	private JTable staffTable;

	// 下面
	private JPanel bottomPanel;
	private JLabel countInfoLabel;

	private JFrame jFrame;

	public StaffView(JFrame jFrame) {
		this.setLayout(new BorderLayout());
		initView();
		this.jFrame = jFrame;
	}

	private void initView() {

		toolBarPanel = new JPanel(new BorderLayout());

		searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		nameLabel = new JLabel("员工编号");
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
		sd = new StaffDAO();
		Vector<Staff> vm = sd.findAllStaff();
		StaffInfoTableModel model = new StaffInfoTableModel(vm);
		staffTable = new JTable(model);
		tableScrollPane = new JScrollPane(staffTable);

		// 下面
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String str = Integer.toString(model.getRowCount());
		countInfoLabel = new JLabel("总共" + str + "条");
		bottomPanel.add(countInfoLabel);

		this.add(toolBarPanel, "North");
		this.add(tableScrollPane, "Center");
		this.add(bottomPanel, "South");

		setVisible(true);
//			refresh();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StaffDAO sd = new StaffDAO();
		Object source = e.getSource();
		if (e.getSource() == searchBtn) {
			if (nameSearchTF.getText().isEmpty()) {
				Vector<Staff> vm = sd.findAllStaff();
				this.updateTable(vm);
				return;
			} else {
				Vector<Staff> vm = sd.find("Staff_Id", Integer.parseInt(nameSearchTF.getText()));
				this.updateTable(vm);
				return;
			}
		}
		if (addBtn == source) {
			StaffDialog staffDialog = new StaffDialog(jFrame, this);
			staffDialog.setVisible(true);
			Vector<Staff> vm = sd.findAllStaff();
			this.updateTable(vm);
		} else if (updateBtn == source) {
			Staff staff = new Staff();
			int rowIndex = staffTable.getSelectedRow();
			if (rowIndex == -1) {
				JOptionPane.showMessageDialog(this, "请选中一条");
				return;
			}
			int id = (Integer) staffTable.getValueAt(rowIndex, 0);
			staff = sd.FindStaff(id);

			if (staff == null) {
				JOptionPane.showMessageDialog(this, "数据不存在,请刷新");
				return;
			}
			StaffDialog staffDialog = new StaffDialog(jFrame, this, staff);
			staffDialog.setVisible(true);
			Vector<Staff> vm = sd.findAllStaff();
			this.updateTable(vm);
		} else if (deleteBtn == source) {
			// 获取选中记录
			int rowIndex = staffTable.getSelectedRow();
			if (rowIndex == -1) {
				JOptionPane.showMessageDialog(this, "请选中一条");
				return;
			}
			int id = (Integer) staffTable.getValueAt(rowIndex, 0);
			Staff s = new Staff();
			s.setID(id);
			if (sd.DeleteStaff(s) == true) {
				JOptionPane.showMessageDialog(this, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
				// refresh();
			} else {
				JOptionPane.showMessageDialog(this, "删除失败", "提示", JOptionPane.ERROR_MESSAGE);
			}
			Vector<Staff> vm = sd.findAllStaff();
			this.updateTable(vm);
		}

	}

	public void updateTable(Vector<Staff> vm) {
		this.remove(tableScrollPane);
		StaffInfoTableModel model = new StaffInfoTableModel(vm);
		staffTable = new JTable(model);
		tableScrollPane = new JScrollPane(staffTable);
		this.add(tableScrollPane, "Center");
		bottomPanel.remove(countInfoLabel);
		String str = Integer.toString(model.getRowCount());
		countInfoLabel = new JLabel("总共" + str + "条");
		bottomPanel.add(countInfoLabel);
		bottomPanel.validate();
		this.validate();
//			this.repaint();

	}
}
