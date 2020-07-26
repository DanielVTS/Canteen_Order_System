package cn.lingnan.view.subview;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import cn.lingnan.DAO.MenuDAO;
import cn.lingnan.DAO.OrderDAO;
import cn.lingnan.cart.ShoppingCar;
import cn.lingnan.dto.CartItem;
import cn.lingnan.dto.Menu;
import cn.lingnan.dto.Order;
import cn.lingnan.view.model.ShoppingCarTableModel;

public class ShoppingCarView extends JPanel implements ActionListener {

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
	private JTable cartTable;

	// 下面
	private JPanel bottomPanel, paypanel, sumPanel;

	private JButton payBtn;
	private JLabel sumLabel;

	private ShoppingCar cart;
	private int price;
	int tableID;

	public ShoppingCarView(JFrame jFrame, ShoppingCar _cart, int _tableID) {

		this.setLayout(new BorderLayout());
		this.cart = _cart;
		this.tableID = _tableID;
		initView();
	}

	private void initView() {

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

		// 中间

		ShoppingCarTableModel model = new ShoppingCarTableModel(cart);
		cartTable = new JTable(model);

		cartTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = cartTable.getSelectedRow();
				int column = cartTable.getSelectedColumn();
				System.out.println(row + " " + column);

				String str = cartTable.getValueAt(row, column).toString();
				String name = cartTable.getValueAt(row, 0).toString();
				System.out.println(str);
				System.out.println(name);
				Menu menu = new MenuDAO().find("Menu_Name", name).get(0);
				if (str.equalsIgnoreCase("+")) {
					cart.add(menu);
				}
				if (str.equalsIgnoreCase("-")) {
					cart.delete(menu);
				}
				updateTable();

			}
		});
		tableScrollPane = new JScrollPane(cartTable);

		// 下面
		bottomPanel = new JPanel(new BorderLayout());
		sumPanel = new JPanel(new BorderLayout());
		paypanel = new JPanel(new BorderLayout());

		this.calculatePrice();
		sumLabel = new JLabel("总额:    " + Integer.toString(price));

		payBtn = new JButton("支付");
		payBtn.addActionListener(this);
		sumPanel.add(sumLabel, "East");
		paypanel.add(payBtn, "East");

		bottomPanel.add(sumPanel, "North");
		bottomPanel.add(paypanel, "South");
		this.add(toolBarPanel, "North");
		this.add(tableScrollPane, "Center");
		this.add(bottomPanel, "South");

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		e.getSource();
		if (e.getSource() == searchBtn) {

			updateTable();
		}
		if (e.getSource() == payBtn) {
			Map<Integer, CartItem> map;
			map = cart.getContainer();
			Order o;
			OrderDAO od = new OrderDAO();
			CartItem ci = new CartItem();
			Vector<CartItem> cartVec = new Vector<CartItem>();
			for (Map.Entry entry : map.entrySet()) {
				cartVec.add((CartItem) entry.getValue());

			}
			for (int i = 0; i < cartVec.size(); i++) {
				ci = cartVec.get(i);
				ci.getCount();
				Menu menu = ci.getMenu();
				o = new Order();

				o.setTable_ID(tableID);
				o.setMenu_ID(menu.getID());
				o.setMenu_Count(ci.getCount());
				o.setOrderStatus(1);
				
				
				System.out.println();
				if(od.AddOrder(o)) {
					javax.swing.JOptionPane.showMessageDialog(null, "下单成功！");
				}else {
					javax.swing.JOptionPane.showMessageDialog(null, "下单失败！");
				}
	
			}
			cart.clear();
			updateTable();

		}
	}


	public void updateTable() {
		this.remove(tableScrollPane);
		ShoppingCarTableModel model = new ShoppingCarTableModel(cart);

		cartTable = new JTable(model);
		cartTable.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				int row = cartTable.getSelectedRow();
				int column = cartTable.getSelectedColumn();
				System.out.println(row + " " + column);

				String str = cartTable.getValueAt(row, column).toString();
				String name = cartTable.getValueAt(row, 0).toString();
				System.out.println(str);
				System.out.println(name);
				Menu menu = new MenuDAO().find("Menu_Name", name).get(0);
				if (str.equalsIgnoreCase("+")) {
					cart.add(menu);
				}
				if (str.equalsIgnoreCase("-")) {
					cart.delete(menu);
				}
				updateTable();

			}
		});
		tableScrollPane = new JScrollPane(cartTable);
		this.calculatePrice();
		sumPanel.remove(sumLabel);
		sumLabel = new JLabel("总额:    " + Integer.toString(price));
		sumPanel.add(sumLabel, "East");

		this.add(tableScrollPane, "Center");
		this.validate();

	}

	public int calculatePrice() {
		price = 0;

		for (int i = 0; i < cartTable.getRowCount(); i++) {
			price = price + (Integer) cartTable.getValueAt(i, 2);
		}
		return price;
	}

}
