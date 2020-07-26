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

import cn.lingnan.util.DateUtil;
import cn.lingnan.util.FontUtil;
import cn.lingnan.view.base.BaseView;
import cn.lingnan.view.subview.AllStaffMenuView;
import cn.lingnan.view.subview.StaffOrderView1;
import cn.lingnan.view.subview.StaffOrderView2;
import cn.lingnan.view.subview.StaffTableView;
import cn.lingnan.view.subview.StaffView;

public class AdministratorView extends BaseView implements ActionListener, MouseListener{

	
	private static final long serialVersionUID = 1L;
	JMenuBar menuBar;
	JMenu restaurantMenu,endMenu;	
	JMenuItem queryMenuItem,alterMenuItem;
	JSplitPane containerPanel;
	JPanel leftPanel,rightPanel;	
	JLabel logoLabel,tableMenuLabel,menuMenuLabel,staffMenuLabel,orderLabel1,orderLabel2,orderLabel3;	
	JPanel bottomPanel;	
	JLabel timeLabel;
	Timer timer;
	CardLayout rightPanelLayout;
	
	public AdministratorView() {
		super(900,700,"管理员界面");
		timer = new Timer(1000,this);
		timer.start();
	}

	@Override
	protected void initView() {
		menuBar = new JMenuBar();
		
		restaurantMenu = new JMenu("餐厅管理");
		
		endMenu = new JMenu("退出管理");
		
		
		menuBar.add(restaurantMenu);
		menuBar.add(endMenu);
		
		setJMenuBar(menuBar);

	
	
	
	//左菜单
	leftPanel = new JPanel();
	leftPanel.setBackground(new Color(27,50,33));
	leftPanel.setLayout(null);
	JLabel logoLabel = new JLabel(new ImageIcon("static\\logo\\store.png"));
    leftPanel.add(logoLabel);
	logoLabel.setBounds(40, 30,100,100);
	
	
	tableMenuLabel = new JLabel("餐桌管理",new ImageIcon("static\\icon\\table.png"),JLabel.LEFT);
	tableMenuLabel.setFont(FontUtil.menuFont);
	tableMenuLabel.setForeground(Color.white);
	tableMenuLabel.addMouseListener(this);
	tableMenuLabel.setBounds(30, 150,120,32);
	
	leftPanel.add(tableMenuLabel);
	
	menuMenuLabel = new JLabel("菜品管理",new ImageIcon("static\\icon\\vegetables.png") ,JLabel.LEFT);
	menuMenuLabel.setFont(FontUtil.menuFont);
	menuMenuLabel.setForeground(Color.white);
	menuMenuLabel.addMouseListener(this);
	menuMenuLabel.setBounds(30, 200,120,32);
	
	leftPanel.add(menuMenuLabel);
	
	
	staffMenuLabel = new JLabel("员工管理",new ImageIcon("static\\icon\\staff.png") ,JLabel.LEFT);
	staffMenuLabel.setFont(FontUtil.menuFont);
	staffMenuLabel.setForeground(Color.white);
	staffMenuLabel.addMouseListener(this);
	staffMenuLabel.setBounds(30, 250,120,32);
	
	leftPanel.add(staffMenuLabel);
	
	orderLabel1=new JLabel("未出订单",new ImageIcon("static\\icon\\delete.png") ,JLabel.LEFT);
	orderLabel1.setFont(FontUtil.menuFont);
	orderLabel1.setForeground(Color.white);
	orderLabel1.addMouseListener(this);
	orderLabel1.setBounds(30, 300,120,32);
	
	leftPanel.add(orderLabel1);
	
	orderLabel2=new JLabel("已出订单",new ImageIcon("static\\icon\\finish_order.png") ,JLabel.LEFT);
	orderLabel2.setFont(FontUtil.menuFont);
	orderLabel2.setForeground(Color.white);
	orderLabel2.addMouseListener(this);
	orderLabel2.setBounds(30, 350,120,32);
	
	leftPanel.add(orderLabel2);
	
	//右，选项卡
	rightPanelLayout = new CardLayout();
	
	//餐桌管理页
	JPanel tablePanel = new StaffTableView(this);

	//菜品管理页
	JPanel menuPanel = new AllStaffMenuView(this);

	//员工管理页
	JPanel staffPanel = new StaffView(this);
	
	//订单管理页
	JPanel orderPanel1 = new StaffOrderView1(this);
	
	//订单管理页
	JPanel orderPanel2 = new StaffOrderView2(this);
	
	rightPanel = new JPanel(rightPanelLayout);
	rightPanel.add(tablePanel, "0");
	rightPanel.add(menuPanel, "1");
	rightPanel.add(staffPanel, "2");
	rightPanel.add(orderPanel1, "3");
	rightPanel.add(orderPanel2, "4");

	
	 //底部
	containerPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftPanel,rightPanel);
	containerPanel.setDividerLocation(180);
	containerPanel.setDividerSize(0);
	
	bottomPanel = new JPanel();//默认的布局是流式布局
	 
	bottomPanel.setBackground(Color.WHITE);
	bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	
	
	timeLabel = new JLabel(DateUtil.dateToString(new Date(),null));
	
	bottomPanel.add(timeLabel);
	Container container = getContentPane();
	container.add(containerPanel,"Center");
	container.add(bottomPanel,"South");
	
	
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timeLabel.setText(DateUtil.dateToString(new Date(),null));
	}
	
	public static void main(String[] args) {
		new AdministratorView ();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if(source==tableMenuLabel) {
			rightPanelLayout.show(rightPanel,"0");
		}
		if(source==menuMenuLabel) {
			rightPanelLayout.show(rightPanel,"1");
		}
		if(source==staffMenuLabel) {
			rightPanelLayout.show(rightPanel,"2");
		}
		if(source==orderLabel1) {
			rightPanelLayout.show(rightPanel,"3");
		}
		if(source==orderLabel2) {
			rightPanelLayout.show(rightPanel,"4");
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
		if(source==tableMenuLabel) {
			tableMenuLabel.setForeground(new Color(255,193,37));
		}
		if(source==menuMenuLabel) {
			menuMenuLabel.setForeground(new Color(255,193,37));
		}
		if(source==staffMenuLabel) {
			staffMenuLabel.setForeground(new Color(255,193,37));
		}if(source==orderLabel1) {
			orderLabel1.setForeground(new Color(255,193,37));
		}if(source==orderLabel2) {
			orderLabel2.setForeground(new Color(255,193,37));
		}if(source==endMenu) {
			   this.setVisible(false);
			   Login login = new Login();
			   login.setVisible(true);
		}		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object source = e.getSource();
		if(source==tableMenuLabel) {
			tableMenuLabel.setForeground(Color.white);
		}
		if(source==menuMenuLabel) {
			menuMenuLabel.setForeground(Color.white);
		}
		if(source==staffMenuLabel) {
			staffMenuLabel.setForeground(Color.white);
		}if(source==orderLabel1) {
			orderLabel1.setForeground(Color.white);
		}if(source==orderLabel2) {
			orderLabel2.setForeground(Color.white);
		}				
	}
	
	
}
