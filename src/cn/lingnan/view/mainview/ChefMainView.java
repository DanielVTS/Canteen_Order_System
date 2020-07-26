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
import cn.lingnan.view.subview.ChefOrderView;

public class ChefMainView extends BaseView implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	JMenuBar menuBar;
	JMenu settingMenu, helpMenu;

	JMenuItem skinMenuItem, configMenuItem;

	JSplitPane containerPanel;

	CardLayout rightPanelLayout;
	JPanel leftPanel, rightPanel;

	JLabel logoLabel, userMenuLabel, productMenuLabel;

	JPanel bottomPanel;

	JLabel timeLabel;

	//
	Timer timer;

	public ChefMainView() {
		super(900, 700, "欢迎来到订餐管理系统@厨师界面");
		timer = new Timer(1000, this);
		timer.start();
	}

	@Override
	protected void initView() {

		menuBar = new JMenuBar();

		settingMenu = new JMenu("设置");

		helpMenu = new JMenu("帮助");

		skinMenuItem = new JMenuItem("切换皮肤", new ImageIcon("static\\icon\\setting.png"));

		configMenuItem = new JMenuItem("参数设置", new ImageIcon("static\\icon\\setting.png"));

		settingMenu.add(configMenuItem);
		settingMenu.add(skinMenuItem);

		menuBar.add(settingMenu);
		menuBar.add(helpMenu);

		setJMenuBar(menuBar);

		leftPanel = new JPanel();
		leftPanel.setBackground(new Color(27, 50, 33));

		leftPanel.setLayout(null);

		JLabel logoLabel = new JLabel(new ImageIcon("static\\logo\\store.png"));
		leftPanel.add(logoLabel);
		logoLabel.setBounds(40, 30, 100, 100);

		userMenuLabel = new JLabel("订单管理", new ImageIcon("static\\icon\\food.png"), JLabel.LEFT);
		userMenuLabel.setFont(FontUtil.menuFont);
		userMenuLabel.addMouseListener(this);
		userMenuLabel.setForeground(Color.white);
		userMenuLabel.setBounds(30, 150, 120, 40);

		leftPanel.add(userMenuLabel);

		productMenuLabel = new JLabel("菜品管理", new ImageIcon("static\\icon\\vegetables.png"), JLabel.LEFT);
		productMenuLabel.setFont(FontUtil.menuFont);
		productMenuLabel.setForeground(Color.white);
		productMenuLabel.addMouseListener(this);
		productMenuLabel.setBounds(30, 200, 120, 32);

		leftPanel.add(productMenuLabel);

		rightPanelLayout = new CardLayout();

		// 用户的列表
		JPanel userPanel = new ChefOrderView(this);

		JPanel productPanel = new AllStaffMenuView(this);

		rightPanel = new JPanel(rightPanelLayout);
		rightPanel.add(userPanel, "0");
		rightPanel.add(productPanel, "1");
		// rightPanel.setBackground(Color.black);
		// rightPanel.setLayout(null);

		containerPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		containerPanel.setDividerLocation(180);
		containerPanel.setDividerSize(0);

		bottomPanel = new JPanel();// 默认的布局是流式布局

		bottomPanel.setBackground(Color.white);
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
		if (source == userMenuLabel) {
			rightPanelLayout.show(rightPanel, "0");
		}
		if (source == productMenuLabel) {
			rightPanelLayout.show(rightPanel, "1");
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
		if (source == userMenuLabel) {
			userMenuLabel.setIcon(new ImageIcon("static\\icon\\food.png"));
			userMenuLabel.setForeground(new Color(255, 193, 37));
		}
		if (source == productMenuLabel) {
			productMenuLabel.setIcon(new ImageIcon("static\\icon\\vegetables.png"));
			productMenuLabel.setForeground(new Color(255, 193, 37));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object source = e.getSource();
		if (source == userMenuLabel) {
			userMenuLabel.setIcon(new ImageIcon("static\\icon\\food.png"));
			userMenuLabel.setForeground(Color.white);
		} else if (source == productMenuLabel) {
			productMenuLabel.setIcon(new ImageIcon("static\\icon\\vegetables.png"));
			productMenuLabel.setForeground(Color.white);
		}

	}

	public static void main(String[] args) {
		new ChefMainView();
	}
}
