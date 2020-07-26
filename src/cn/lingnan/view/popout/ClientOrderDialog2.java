package cn.lingnan.view.popout;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.lingnan.DAO.OrderDAO;
import cn.lingnan.dto.Order;
import cn.lingnan.view.subview.StaffOrderView2;

public class ClientOrderDialog2 extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private JPanel nowidPanel, nownamePanel, nowstatusPanel, nowcountPanel, opePanel;
	private JLabel nowidLabel, nownameLabel, nowstatusLabel, nowcountLabel;
	private JTextField nowidTF, nownameTF, nowstatusTF, nowcountTF;
	private JButton saveBtn, cancelBtn;
	
	private OrderDAO orderDAO = new OrderDAO();
	private Order menu;
	
	
	public ClientOrderDialog2(JFrame parent,StaffOrderView2 menuView) {
		super(parent,"添加");
		//this.menuView = menuView;
		
		setSize(350,300);
		
		setLocationRelativeTo(null);
		
		setModal(true);
		setResizable(false);
		 
		this.setLayout(new FlowLayout());
		
		initView();
	}
	
	public ClientOrderDialog2(JFrame parent,StaffOrderView2 menuView,Order menu) {
		 this(parent,menuView);
		 this.menu = menu;
		 setTitle("修改");
		 //回显
		 this.nowidTF.setText(String.valueOf(menu.getTable_ID()));
		 this.nownameTF.setText(String.valueOf(menu.getMenu_Name()));
		 this.nowcountTF.setText(String.valueOf(menu.getMenu_Count()));
		 this.nowstatusTF.setText(String.valueOf(menu.getOrderStatus()));
		 //this.loginNamePanel.setVisible(false);
	}
	
	private void initView() {
		nowidPanel = new JPanel();
		nowidLabel = new JLabel("新桌号");
		nowidTF = new JTextField(15);
		nowidPanel.add(nowidLabel);
		nowidPanel.add(nowidTF);
		
		nownamePanel = new JPanel();
		nownameLabel = new JLabel("新菜名");
		nownameTF = new JTextField(15);
		nownamePanel.add(nownameLabel);
		nownamePanel.add(nownameTF);
		
		nowcountPanel = new JPanel();
		nowcountLabel = new JLabel("菜量");
		nowcountTF = new JTextField(15);
		nowcountPanel.add(nowcountLabel);
		nowcountPanel.add(nowcountTF);
		
		nowstatusPanel = new JPanel();
		nowstatusLabel = new JLabel("新状态");
		nowstatusTF = new JTextField(15);
		nowstatusPanel.add(nowstatusLabel);
		nowstatusPanel.add(nowstatusTF);
		

		
		opePanel = new JPanel();
		saveBtn = new JButton("保存");
		cancelBtn = new JButton("取消");
		saveBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		opePanel.add(saveBtn);
		opePanel.add(cancelBtn);
		
		Container container = getContentPane();
//		container.add(oldidPanel);
//		container.add(oldnamePanel);
//		container.add(oldstatusPanel);
		container.add(nowidPanel);
		container.add(nownamePanel);
		container.add(nowcountPanel);
		container.add(nowstatusPanel);
		container.add(opePanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source==saveBtn){
			String oldid = String.valueOf(menu.getTable_ID());
			String oldname = menu.getMenu_Name();
			String oldstatus = String.valueOf(menu.getOrderStatus());
			String nowid = nowidTF.getText();
			String nowname = nownameTF.getText();
			String nowcount = nowcountTF.getText();
			String nowstatus = nowstatusTF.getText();
			

			if(this.menu==null) {	
				//TODO 参数校验
//				Order order = new Order();
//				order.setTable_ID(Integer.valueOf(id));
//				order.setMenu_ID(Integer.valueOf(name));
//	//			order.setPrice(Integer.valueOf(price));
//				order.setMenu_Count(Integer.valueOf(count));
//	//			order.setRowPrice(Integer.valueOf(rowprice));
//				order.setOrderStatus(Integer.valueOf(status));
//				
//				boolean result = orderDAO.AddOrder(order);
//				if(result) {
//					JOptionPane.showMessageDialog(this,"添加成功","提示",JOptionPane.INFORMATION_MESSAGE);
//					this.dispose();
//					
//					//刷新
//				//	menuView.refresh();
//				}else {
//					JOptionPane.showMessageDialog(this,"添加失败","提示",JOptionPane.ERROR_MESSAGE);
//				}
			}else {
				//更新
				Order old = new Order();
				Order now = new Order();
				
				old.setTable_ID(Integer.valueOf(oldid));
				old.setMenu_Name(oldname);
				old.setOrderStatus(Integer.valueOf(oldstatus));
				now.setTable_ID(Integer.valueOf(nowid));
				now.setMenu_Name(nowname);
				now.setMenu_Count(Integer.valueOf(nowcount));
				now.setOrderStatus(Integer.valueOf(nowstatus));

				
			boolean result = orderDAO.EditOrder(now, old);
				if(result==true) {
					JOptionPane.showMessageDialog(this,"更新成功","提示",JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
					//刷新
				//	menuView.refresh();
				}else {
					JOptionPane.showMessageDialog(this,"更新失败","提示",JOptionPane.ERROR_MESSAGE);
				}
			}
		}else if(source == cancelBtn) {
			
			this.dispose();
		}
	}
}
