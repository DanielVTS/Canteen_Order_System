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

import cn.lingnan.DAO.MenuDAO;
import cn.lingnan.cart.ShoppingCar;
import cn.lingnan.dto.Menu;
import cn.lingnan.view.model.CustomerMenuInfoTableModel;

public class CustomerMenuView extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private MenuDAO md;
	// 上面
	private JPanel toolBarPanel;

	private JPanel searchPanel;
	private JLabel nameLabel;
	private JTextField nameSearchTF;
	private JButton searchBtn;

	private JPanel opePanel;

	// 中间
	private JScrollPane tableScrollPane;
	private JTable menuTable;

	// 下面
	private JPanel bottomPanel;
	private JLabel countInfoLabel;

	// 数据
	private ShoppingCar cart;
	int tableID;

	public CustomerMenuView(JFrame jFrame, ShoppingCar _cart, int _tableID) {
		this.setLayout(new BorderLayout());
		this.cart = _cart;
		this.tableID = _tableID;
		initView();

	}

	private void initView() {

		toolBarPanel = new JPanel(new BorderLayout());

		searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		nameLabel = new JLabel("菜类");
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
		md = new MenuDAO();
		Vector<Menu> vm = md.findAllMenu();
		CustomerMenuInfoTableModel model = new CustomerMenuInfoTableModel(vm, cart);
		menuTable = new JTable(model);
		tableScrollPane = new JScrollPane(menuTable);

		// 下面
		bottomPanel = new JPanel(new BorderLayout());

		String str = Integer.toString(model.getRowCount());
		countInfoLabel = new JLabel("总共" + str + "条");
		bottomPanel.add(countInfoLabel, "West");

		this.add(toolBarPanel, "North");
		this.add(tableScrollPane, "Center");
		this.add(bottomPanel, "South");

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MenuDAO md = new MenuDAO();
		Object source = e.getSource();
		if (source == searchBtn) {
			if (nameSearchTF.getText().isEmpty()) {
				Vector<Menu> vm = md.findAllMenu();
				int[] IDmap = new int[vm.size()];
				for (int i = 0; i < vm.size(); i++) {
					IDmap[i] = vm.get(i).getID();
				}
				cart.setIDmap(IDmap);
				this.updateTable(vm);
				return;
			} else {
				System.out.println(nameSearchTF.getText().toString());
				Vector<Menu> vm = md.find("Menu_Kind", nameSearchTF.getText().toString());
				int[] IDmap = new int[vm.size()];
				for (int i = 0; i < vm.size(); i++) {
					IDmap[i] = vm.get(i).getID();
				}
				cart.setIDmap(IDmap);
				this.updateTable(vm);
				return;
			}

		}
		Vector<Menu> vm = md.findAllMenu();
		this.updateTable(vm);
	}

	public void updateTable(Vector<Menu> vm) {
		this.remove(tableScrollPane);
		CustomerMenuInfoTableModel model = new CustomerMenuInfoTableModel(vm, cart);
		menuTable = new JTable(model);
		tableScrollPane = new JScrollPane(menuTable);
		this.add(tableScrollPane, "Center");

		bottomPanel.remove(countInfoLabel);
		String str = Integer.toString(model.getRowCount());
		countInfoLabel = new JLabel("总共" + str + "条");
		bottomPanel.add(countInfoLabel);
		bottomPanel.validate();

		this.validate();

	}
}
