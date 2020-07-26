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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import cn.lingnan.DAO.ChefOrderDAO;
import cn.lingnan.DAO.ClientOrderDAO;

import cn.lingnan.dto.Order;
import cn.lingnan.view.model.OrderTableModel;

public class ChefOrderView extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	// 上面
	private JPanel toolBarPanel;

	private JPanel searchPanel;
	private JLabel nameLabel;
	private JTextField nameSearchTF;
	private JButton searchBtn;

	private JPanel opePanel;
	// 中间
	private JScrollPane tableScrollPane;
	private JTable userTable;

	// 下面
	private JPanel bottomPanel;
	private JLabel countInfoLabel;

	public ChefOrderView(JFrame jFrame) {
		this.setLayout(new BorderLayout());
		initView();
	}

	private void initView() {

		this.setOpaque(false);
		this.setBackground(null);
		toolBarPanel = new JPanel(new BorderLayout());

		searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		nameLabel = new JLabel("菜名");
		nameSearchTF = new JTextField(10);

		searchBtn = new JButton("搜索", new ImageIcon("static\\icon\\search.png"));
		searchBtn.addActionListener(this);

		opePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		searchPanel.add(nameLabel);
		searchPanel.add(nameSearchTF);
		searchPanel.add(searchBtn);

		toolBarPanel.add(searchPanel, "West");
		toolBarPanel.add(opePanel, "East");

		new ChefOrderDAO();
		ClientOrderDAO cod = new ClientOrderDAO();
		Vector<Order> v = cod.findChefOrder();
		OrderTableModel ordertablemodel = new OrderTableModel(v);

		// ordertablemodel.query();
		userTable = new JTable(ordertablemodel);
		tableScrollPane = new JScrollPane(userTable);

		// 下面
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String str = Integer.toString(ordertablemodel.getRowCount());
		countInfoLabel = new JLabel("总共" + str + "条");
		bottomPanel.add(countInfoLabel);

		this.add(toolBarPanel, "North");
		this.add(tableScrollPane, "Center");
		this.add(bottomPanel, "South");

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		e.getSource();
		ClientOrderDAO cod = new ClientOrderDAO();
		new ChefOrderDAO();
		if (e.getSource() == searchBtn) {
			if (nameSearchTF.getText().isEmpty()) {
				Vector<Order> vc = cod.findChefOrder();

				this.updateTable(vc);
				return;
			} else {
				Vector<Order> vc = cod.findChefOrderByName(nameSearchTF.getText().toString());
				this.updateTable(vc);
				return;

			}
		}

	}

	public void updateTable(Vector<Order> vc) {
		this.remove(tableScrollPane);
		OrderTableModel ordertablemodel = new OrderTableModel(vc);
		userTable = new JTable(ordertablemodel);
		tableScrollPane = new JScrollPane(userTable);

		this.add(tableScrollPane, "Center");

		bottomPanel.remove(countInfoLabel);
		String str = Integer.toString(ordertablemodel.getRowCount());
		countInfoLabel = new JLabel("总共" + str + "条");
		bottomPanel.add(countInfoLabel);
		bottomPanel.validate();
		this.validate();

	}

}
