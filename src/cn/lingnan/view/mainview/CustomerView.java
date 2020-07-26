package cn.lingnan.view.mainview;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.Timer;

import cn.lingnan.cart.ShoppingCar;
import cn.lingnan.util.DateUtil;
import cn.lingnan.util.FontUtil;
import cn.lingnan.view.base.BaseView;
import cn.lingnan.view.subview.ClientOrderView1;
import cn.lingnan.view.subview.ClientOrderView2;
import cn.lingnan.view.subview.CustomerMenuView;
import cn.lingnan.view.subview.CustomerTableView;
import cn.lingnan.view.subview.ShoppingCarView;

public class CustomerView extends BaseView implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	// 界面
	JMenuBar menuBar;
	JMenu restaurantMenu, endMenu;
	JMenuItem queryMenuItem, alterMenuItem;
	JSplitPane containerPanel;
	
	JPanel leftPanel, rightPanel,tablePanel,
	menuPanel,cartPanel,orderPanel1,orderPanel2;
	
	JLabel logoLabel, tableMenuLabel, menuMenuLabel, 
	cartMenuLabel, orderMenuLabel1, orderMenuLabel2;
	
	JPanel bottomPanel;
	JLabel timeLabel;
	//JPanel orderPanel;
	Timer timer;
	CardLayout rightPanelLayout;
	int tableID;

	// 数据
	private ShoppingCar cart;

	public CustomerView(int _tableID) {
		super(900, 700, "客户界面");
		this.tableID = _tableID;
		timer = new Timer(1000, this);
		timer.start();
	}

	@Override
	protected void initView() {
		menuBar = new JMenuBar();

		restaurantMenu = new JMenu("餐厅");

		endMenu = new JMenu("退出");

		menuBar.add(restaurantMenu);
		menuBar.add(endMenu);

		setJMenuBar(menuBar);

		// 左菜单
		leftPanel = new JPanel();
		leftPanel.setBackground(new Color(27, 50, 33));
		leftPanel.setLayout(null);
		JLabel logoLabel = new JLabel(new ImageIcon("static\\logo\\store.png"));
		leftPanel.add(logoLabel);
		logoLabel.setBounds(40, 30, 100, 100);

		tableMenuLabel = new JLabel("餐桌预订", new ImageIcon("static\\icon\\table.png"), JLabel.LEFT);
		tableMenuLabel.setFont(FontUtil.menuFont);
		tableMenuLabel.setForeground(Color.white);
		tableMenuLabel.addMouseListener(this);

		tableMenuLabel.setBounds(30, 150, 120, 32);

		leftPanel.add(tableMenuLabel);

		menuMenuLabel = new JLabel("菜单查询", new ImageIcon("static\\icon\\vegetables.png"), JLabel.LEFT);
		menuMenuLabel.setFont(FontUtil.menuFont);
		menuMenuLabel.setForeground(Color.white);
		menuMenuLabel.addMouseListener(this);
		menuMenuLabel.setBounds(30, 200, 120, 32);

		leftPanel.add(menuMenuLabel);

		cartMenuLabel = new JLabel("购物车", new ImageIcon("static\\icon\\cart.png"), JLabel.LEFT);
		cartMenuLabel.setFont(FontUtil.menuFont);
		cartMenuLabel.setForeground(Color.white);
		cartMenuLabel.addMouseListener(this);
		cartMenuLabel.setBounds(30, 250, 120, 32);

		leftPanel.add(cartMenuLabel);

		orderMenuLabel1 = new JLabel("未出订单", new ImageIcon("static\\icon\\delete.png"), JLabel.LEFT);
		orderMenuLabel1.setFont(FontUtil.menuFont);
		orderMenuLabel1.setForeground(Color.white);
		orderMenuLabel1.addMouseListener(this);
		orderMenuLabel1.setBounds(30, 300, 120, 32);

		leftPanel.add(orderMenuLabel1);

		orderMenuLabel2 = new JLabel("已出订单", new ImageIcon("static\\icon\\finish_order.png"), JLabel.LEFT);
		orderMenuLabel2.setFont(FontUtil.menuFont);
		orderMenuLabel2.setForeground(Color.white);
		orderMenuLabel2.addMouseListener(this);
		orderMenuLabel2.setBounds(30, 350, 120, 32);

		leftPanel.add(orderMenuLabel2);

		// 右，选项卡
		rightPanelLayout = new CardLayout();

		cart = new ShoppingCar();

		// 餐桌管理页
		tablePanel = new CustomerTableView(this);

		// 菜品管理页
		menuPanel = new CustomerMenuView(this, cart, tableID);

		// 订单管理页
		cartPanel = new ShoppingCarView(this, cart, tableID);

		// 已出订单管理页
		orderPanel1 = new ClientOrderView1(this,tableID);

		// 未出订单管理页
		orderPanel2 = new ClientOrderView2(this,tableID);

		rightPanel = new JPanel(rightPanelLayout);
		rightPanel.add(tablePanel, "0");
		rightPanel.add(menuPanel, "1");
		rightPanel.add(cartPanel, "2");
		rightPanel.add(orderPanel1, "3");
		rightPanel.add(orderPanel2, "4");

		// 底部
		containerPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		containerPanel.setDividerLocation(180);
		containerPanel.setDividerSize(0);

		bottomPanel = new JPanel();// 默认的布局是流式布局

		bottomPanel.setBackground(Color.WHITE);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		timeLabel = new JLabel(DateUtil.dateToString(new Date(), null));

		bottomPanel.add(timeLabel);
		Container container = getContentPane();
		container.add(containerPanel, "Center");
		container.add(bottomPanel, "South");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timeLabel.setText(DateUtil.dateToString(new Date(), null));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if (source == tableMenuLabel) {
			rightPanel.remove(tablePanel);
			tablePanel = new CustomerTableView(this);
			rightPanel.add(tablePanel, "0");
			rightPanelLayout.show(rightPanel, "0");
		}
		if (source == menuMenuLabel) {
			rightPanel.remove(menuPanel);
			menuPanel = new CustomerMenuView(this, cart, tableID);
			rightPanel.add(menuPanel, "1");
			rightPanelLayout.show(rightPanel, "1");
			
			
		}
		if (source == cartMenuLabel) {

			rightPanel.remove(cartPanel);
			cartPanel = new ShoppingCarView(this, cart, tableID);
			rightPanel.add(cartPanel, "2");
			rightPanelLayout.show(rightPanel, "2");
		}
		if (source == orderMenuLabel1) {
			rightPanel.remove(orderPanel1);
			orderPanel1=new ClientOrderView1(this,tableID);
			rightPanel.add(orderPanel1,"3");
			rightPanelLayout.show(rightPanel, "3");
		}
		if (source == orderMenuLabel2) {
			rightPanel.remove(orderPanel2);
			orderPanel2=new ClientOrderView2(this,tableID);
			rightPanel.add(orderPanel2,"4");			
			rightPanelLayout.show(rightPanel, "4");
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object source = e.getSource();
		if (source == tableMenuLabel) {
			tableMenuLabel.setForeground(new Color(255, 193, 37));
		}
		if (source == menuMenuLabel) {
			menuMenuLabel.setForeground(new Color(255, 193, 37));
		}
		if (source == cartMenuLabel) {
			// JPanel orderPanel = new ShoppingCarView(this,cart,tableID);
			
			cartMenuLabel.setForeground(new Color(255, 193, 37));
		}
		if (source == orderMenuLabel1) {
			orderMenuLabel1.setForeground(new Color(255, 193, 37));
		}
		if (source == orderMenuLabel2) {
			orderMenuLabel2.setForeground(new Color(255, 193, 37));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object source = e.getSource();
		if (source == tableMenuLabel) {
			tableMenuLabel.setForeground(Color.white);
		}
		if (source == menuMenuLabel) {
			menuMenuLabel.setForeground(Color.white);
		}
		if (source == cartMenuLabel) {
			cartMenuLabel.setForeground(Color.white);
		}
		if (source == orderMenuLabel1) {
			orderMenuLabel1.setForeground(Color.white);
		}
		if (source == orderMenuLabel2) {
			orderMenuLabel2.setForeground(Color.white);
		}
	}

	public static void main(String[] args) {
		new CustomerView(109);
	}

}
