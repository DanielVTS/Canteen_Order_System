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

import cn.lingnan.view.model.CustomerOrderInfoTableModel1;
import cn.lingnan.view.popout.ClientOrderDialog1;
import cn.lingnan.DAO.ClientOrderDAO;
import cn.lingnan.DAO.OrderDAO;
import cn.lingnan.dto.Order;

public class StaffOrderView1 extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private ClientOrderDAO md;

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
	private JTable orderTable;
	private OrderDAO orderDAO = new OrderDAO();

	// 下面
	private JPanel bottomPanel;
	private JLabel countInfoLabel;
	private JFrame jFrame;

	public StaffOrderView1(JFrame jFrame) {
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

		// opePanel.add(addBtn);
		opePanel.add(updateBtn);
		opePanel.add(deleteBtn);

		searchPanel.add(nameLabel);
		searchPanel.add(nameSearchTF);
		searchPanel.add(searchBtn);

		toolBarPanel.add(searchPanel, "West");
		toolBarPanel.add(opePanel, "East");

		// 中间
		md = new ClientOrderDAO();
		Vector<Order> vm = md.findAllClientOrder();
		CustomerOrderInfoTableModel1 model = new CustomerOrderInfoTableModel1(vm);
		orderTable = new JTable(model);
		tableScrollPane = new JScrollPane(orderTable);

		// 下面
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String str = Integer.toString(model.getRowCount());
		countInfoLabel = new JLabel("总共" + str + "条");
		bottomPanel.add(countInfoLabel);

		this.add(toolBarPanel, "North");
		this.add(tableScrollPane, "Center");
		this.add(bottomPanel, "South");

		this.setVisible(true);
		// refresh();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ClientOrderDAO sd = new ClientOrderDAO();
		Object source = e.getSource();
		if (e.getSource() == searchBtn) {
			if (nameSearchTF.getText().isEmpty()) {
				Vector<Order> vm = sd.findAllClientOrder();
				this.updateTable(vm);
				return;
			} else {
				Vector<Order> vm = sd.find(Integer.parseInt(nameSearchTF.getText()));
				this.updateTable(vm);
				return;
			}
		}
		if (addBtn == source) {
			ClientOrderDialog1 clientOrderDialog = new ClientOrderDialog1(jFrame, this);
			// ClientOrderDialog1 clientOrderDialog = new ClientOrderDialog1(jFrame);
			clientOrderDialog.setVisible(true);
			Vector<Order> vm = sd.findAllClientOrder();
			this.updateTable(vm);
		} else if (updateBtn == source) {
			Order old = new Order();
			int rowIndex = orderTable.getSelectedRow();
			if (rowIndex == -1) {
				JOptionPane.showMessageDialog(this, "请选中一条");
				return;
			}

			int id = (Integer) orderTable.getValueAt(rowIndex, 0);
			String name = (String) orderTable.getValueAt(rowIndex, 1);
			int status = (Integer) orderTable.getValueAt(rowIndex, 5);

			old = sd.find(id, name, status);
			if (old == null) {
				JOptionPane.showMessageDialog(this, "数据不存在,请刷新");
				return;
			}
			ClientOrderDialog1 clientOrderDialog = new ClientOrderDialog1(jFrame, this, old);
			clientOrderDialog.setVisible(true);

			Vector<Order> vm = sd.findAllClientOrder();
			this.updateTable(vm);
		} else if (deleteBtn == source) {
			// 获取选中记录
			int rowIndex = orderTable.getSelectedRow();
			if (rowIndex == -1) {
				JOptionPane.showMessageDialog(this, "请选中一条");
				return;
			}
			int id = (Integer) orderTable.getValueAt(rowIndex, 0);
			int orderStatus = (Integer) orderTable.getValueAt(rowIndex, 5);
			String name = (String) orderTable.getValueAt(rowIndex, 1);
			Order s = new Order();
			s.setTable_ID(id);
			s.setMenu_Name(name);
			s.setOrderStatus(orderStatus);
			// s.setMenu_Name(name);
			if (orderDAO.DeleteOrder(s) == true) {
				JOptionPane.showMessageDialog(this, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
				// refresh();
			} else {
				JOptionPane.showMessageDialog(this, "删除失败", "提示", JOptionPane.ERROR_MESSAGE);
			}
			Vector<Order> vm = sd.findAllClientOrder();
			this.updateTable(vm);
		}

	}

	public void updateTable(Vector<Order> vm) {
		this.remove(tableScrollPane);
		CustomerOrderInfoTableModel1 model = new CustomerOrderInfoTableModel1(vm);
		orderTable = new JTable(model);
		tableScrollPane = new JScrollPane(orderTable);
		this.add(tableScrollPane, "Center");
		bottomPanel.remove(countInfoLabel);
		String str = Integer.toString(model.getRowCount());
		countInfoLabel = new JLabel("总共" + str + "条");
		bottomPanel.add(countInfoLabel);
		bottomPanel.validate();
		this.validate();
//				this.repaint();

	}
}
