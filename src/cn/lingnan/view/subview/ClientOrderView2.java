package cn.lingnan.view.subview;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import cn.lingnan.view.model.CustomerOrderInfoTableModel2;
import cn.lingnan.DAO.ClientOrderDAO;
import cn.lingnan.dto.Order;

public class ClientOrderView2 extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	// 上面
	private JPanel toolBarPanel;
	private JPanel searchPanel;
	private JLabel nameLabel;

	// 中间
	private JScrollPane tableScrollPane;
	private JTable orderTable;

	// 下面
	private JPanel bottomPanel;
	private JLabel countInfoLabel;
	
	//
	private int tableID;
	
	public ClientOrderView2(JFrame jFrame,int _tableID) {
		this.setLayout(new BorderLayout());
		this.tableID=_tableID;
		initView();

	}

	private void initView() {

		toolBarPanel = new JPanel(new BorderLayout());

		searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		nameLabel = new JLabel("已出订单");

		searchPanel.add(nameLabel);

		toolBarPanel.add(searchPanel, "West");

		// 中间
		ClientOrderDAO sd = new ClientOrderDAO();
		Vector<Order> vm = sd.find2(tableID);// 102为选择的参数
		CustomerOrderInfoTableModel2 model = new CustomerOrderInfoTableModel2(vm);
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

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
