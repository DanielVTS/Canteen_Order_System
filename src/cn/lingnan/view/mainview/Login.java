package cn.lingnan.view.mainview;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.lingnan.DAO.StaffDAO;
import cn.lingnan.util.PropertiesLoader;

/**
 * 登录界面
 *
 * @author Administrator pan1是图片加文字“餐饮管理系统” pan5是账号+密码+3个按钮 其他pan是上下左右的空白处
 */

public class Login extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int screenSizeWidth;
	private int screenSizeHeight;
	private int windowWidth;
	private int windowHeight;

	JLabel label1, label2, label3, label4;

	JPanel pan1, pan2, pan3, pan4, pan5, pan6;

	JTextField tf1, tf2;

	JButton btn1, btn2, btn3;

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Statement st = null;

	public Login() {
		JFrame frm = new JFrame();

		JPanel bigpan = new JPanel();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		screenSizeWidth = (int) dimension.getWidth();
		screenSizeHeight = (int) dimension.getHeight();
		windowWidth = this.getWidth();
		windowHeight = this.getHeight();
		frm.setLocation(screenSizeWidth / 3 - windowWidth / 2, screenSizeHeight / 4 - windowHeight / 3);

		label1 = new JLabel("餐饮管理系统");
		label2 = new JLabel("账号");
		label3 = new JLabel("密码");
		label4 = new JLabel(new ImageIcon("static\\logi.png"));

		pan1 = new JPanel();
		pan2 = new JPanel();
		pan3 = new JPanel();
		pan4 = new JPanel();
		pan5 = new JPanel();
		pan6 = new JPanel();

		tf1 = new JTextField(20);
		tf2 = new JPasswordField(20);

		btn1 = new JButton("员工");
		btn2 = new JButton("厨师");
		btn3 = new JButton("顾客");

		/** 为两个按钮添加监听，只要有鼠标点击，就会有响应 */
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);

		frm.setSize(400, 550);

		frm.setResizable(false);

		bigpan.setLayout(null);
		bigpan.setSize(400, 500);

		label1.setFont(new Font("宋体", Font.BOLD, 26));
		label1.setBounds(68, 48, 200, 26);

		label4.setBounds(-20, 30, 100, 60);

		pan1.setLayout(null);
		pan1.setBounds(80, 60, 300, 90);

		// pan2.setBounds(200, 475, 300,75);

		pan5.setBounds(60, 150, 300, 400);
		// pan5.setBackground(Color.lightGray);

		pan1.add(label4);
		pan1.add(label1, JLabel.CENTER);

		pan5.setLayout(null);

		tf1.setBounds(80, 50, 160, 30);
		tf2.setBounds(80, 100, 160, 30);

		label2.setBounds(35, 48, 35, 35);
		label3.setBounds(35, 98, 35, 35);

		pan5.add(label2);
		pan5.add(label3);

		btn1.setBounds(40, 160, 65, 33);
		btn2.setBounds(110, 160, 65, 33);
		btn3.setBounds(180, 160, 65, 33);

		pan5.add(tf1);
		pan5.add(tf2);

		pan5.add(btn1);
		pan5.add(btn2);
		pan5.add(btn3);

		bigpan.add(pan1);
		bigpan.add(pan2);
		/*
		 * bigpan.add(pan3); bigpan.add(pan4);
		 */
		bigpan.add(pan5);
		// bigpan.add(pan6);

		Container container = getContentPane();
		container.add(bigpan);

		frm.add(container);

		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true);

	}

	// 事件处理程序
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			try {
				staffLogin();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} // 解决获取错误
		} else if (e.getSource() == btn2) {
			try {
				chefLogin();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == btn3) {
			customerLogin();
		}
	}

	public void staffLogin() throws SQLException, ClassNotFoundException {
		/** 获取文本框中输入的名字和密码 */
		String Staff_Name = tf1.getText();
		String StaffPassword = tf2.getText();

		String sql = "select * from staff where Staff_Name='" + Staff_Name + "'and StaffPassword='" + StaffPassword
				+ "'" + "and job !=" + "'厨师'" + "or job=" + "'经理'" + "or job=" + "'副经理'" + "or job=" + "'采购员'"
				+ "or job=" + "'服务员'";

		/** 调用loginCheck()方法，进行数据库连接验证 */

		StaffDAO sd = new StaffDAO();

		if (loginCheck(Staff_Name, StaffPassword, sql)) {
			new AdministratorView(); // 登录成功跳转到人员界面
		} else {
			this.dispose();
			new LoginUnsuccessful();
		}

	}

	public void chefLogin() throws SQLException, ClassNotFoundException {
		/** 获取文本框中输入的名字和密码 */
		String Staff_Name = tf1.getText();
		String StaffPassword = tf2.getText();

		String sql = "select * from staff where Staff_Name='" + Staff_Name + "'and StaffPassword='" + StaffPassword
				+ "'" + "and Job=" + "'厨师'";

		/** 调用loginCheck()方法，进行数据库连接验证 */

		if (loginCheck(Staff_Name, StaffPassword, sql)) {
			new ChefMainView();// 就是厨师的功能界面
		} else {
			this.dispose();
			new LoginUnsuccessful();
		}

	}

	public boolean loginCheck(String Staff_Name, String StaffPassword, String sql)
			throws ClassNotFoundException, SQLException {
		boolean flag = false;
		Properties prop = new Properties();
		prop = new PropertiesLoader("jdbc.properties").getProperties();
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");

		Class.forName(driver);// 注册驱动
		conn = DriverManager.getConnection(url, user, password); // 建立连接

		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next() == true) {
			if (rs.getString("Staff_Name").equals(Staff_Name) && rs.getString("StaffPassword").equals(StaffPassword)) {
				flag = true;
			}
		}
		conn.close(); // 关闭数据库
		return flag;
	}

	// 点击按钮btn3，直接跳转到顾客输入台号界面
	public void customerLogin() {

		new inputTableView("顾客输入台号");
	}

	public static void main(String[] args) {

		new Login();

	}

}
