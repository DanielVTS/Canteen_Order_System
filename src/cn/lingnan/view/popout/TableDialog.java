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

import cn.lingnan.DAO.TableDAO;
import cn.lingnan.dto.Table;
import cn.lingnan.view.subview.CustomerTableView;
import cn.lingnan.view.subview.StaffTableView;



public class TableDialog extends JDialog implements ActionListener{
	
	
	
	
	private static final long serialVersionUID = 1L;

	private JPanel idPanel,seatPanel,statusPanel,phonePanel,orderTimePanel,opePanel;
	
	private JLabel idLabel,seatLabel,statusLabel,phoneLabel,orderTimeLabel;
	private JTextField idTF,seatTF,statusTF,phoneTF,orderTimeTF;
	
	private JButton saveBtn,cancelBtn;
	
	private TableDAO tableDAO = new TableDAO();
	
	
	
	private Table table;
	
	public TableDialog(JFrame parent,CustomerTableView tableView) {
		super(parent,"添加");
		
		
		setSize(350,300);
		
		setLocationRelativeTo(null);
		
		setModal(true);
		setResizable(false);
		 
		this.setLayout(new FlowLayout());
		
		initView();
	}
	
	public TableDialog(JFrame parent,StaffTableView stafftaleview) {
		super(parent,"添加");
		
		
		setSize(350,300);
		
		setLocationRelativeTo(null);
		
		setModal(true);
		setResizable(false);
		 
		this.setLayout(new FlowLayout());
		
		initView();
	}
	
	public TableDialog(JFrame parent,CustomerTableView tableView,Table table) {
		 this(parent,tableView);
		 this.table = table;
		 setTitle("修改");
		 //回显
		 this.idTF.setText(String.valueOf(table.getTable_Id()));
		 this.seatTF.setText(String.valueOf(table.getTable_Seat()));
		 this.statusTF.setText(String.valueOf(table.getTable_Status()));
		 this.phoneTF.setText(table.getPhone());
		 this.orderTimeTF.setText(table.getOrderTime());
		 //this.loginNamePanel.setVisible(false);
	}
	
	public TableDialog(JFrame parent,StaffTableView stafftaleview,Table table) {
		 this(parent,stafftaleview);
		 this.table = table;
		 setTitle("修改");
		 //回显
		 this.idTF.setText(String.valueOf(table.getTable_Id()));
		 this.seatTF.setText(String.valueOf(table.getTable_Seat()));
		 this.statusTF.setText(String.valueOf(table.getTable_Status()));
		 this.phoneTF.setText(table.getPhone());
		 this.orderTimeTF.setText(table.getOrderTime());
		 //this.loginNamePanel.setVisible(false);
	}
	
	private void initView() {
		idPanel = new JPanel();
		idLabel = new JLabel("桌号");
		idTF = new JTextField(15);
		idPanel.add(idLabel);
		idPanel.add(idTF);
				
		seatPanel = new JPanel();
		seatLabel = new JLabel("位数");
		seatTF = new JTextField(15);
		seatPanel.add(seatLabel);
		seatPanel.add(seatTF);
		
		statusPanel = new JPanel();
		statusLabel = new JLabel("状态");
		statusTF = new JTextField(15);
		statusPanel.add(statusLabel);
		statusPanel.add(statusTF);
		
		phonePanel = new JPanel();
		phoneLabel = new JLabel("电话");
		phoneTF = new JTextField(15);
		phonePanel.add(phoneLabel);
		phonePanel.add(phoneTF);
		
		orderTimePanel = new JPanel();
		orderTimeLabel = new JLabel("预定时间");
		orderTimeTF = new JTextField(15);
		orderTimePanel.add(orderTimeLabel);
		orderTimePanel.add(orderTimeTF);
		
		opePanel = new JPanel();
		saveBtn = new JButton("保存");
		cancelBtn = new JButton("取消");
		saveBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		opePanel.add(saveBtn);
		opePanel.add(cancelBtn);
		
		Container container = getContentPane();
		container.add(idPanel);
		container.add(seatPanel);
		container.add(statusPanel);
		container.add(phonePanel);
//		container.add(orderTimePanel);
		container.add(opePanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source==saveBtn){
			String id = idTF.getText();
			String seat = seatTF.getText();
			String status = statusTF.getText();
			String phone = phoneTF.getText();
			String orderTime = orderTimeTF.getText();
			

			if(this.table==null) {	
				//TODO 参数校验
				Table table = new Table();
				table.setTable_Id(Integer.valueOf(id));
				table.setTable_Seat(Integer.valueOf(seat));
				table.setTable_Status(Integer.valueOf((status)));
				table.setPhone(phone);
				table.setOrderTime(orderTime);
				
				
				boolean result = tableDAO.AddTable(table);
				if(result) {
					JOptionPane.showMessageDialog(this,"添加成功","提示",JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
					
					//刷新
				//	menuView.refresh();
				}else {
					JOptionPane.showMessageDialog(this,"添加失败","提示",JOptionPane.ERROR_MESSAGE);
				}
			}else {
				//更新
				Table table = new Table();
				table.setTable_Id(Integer.valueOf(id));
				table.setTable_Seat(Integer.valueOf(seat));
				table.setTable_Status(Integer.valueOf((status)));
				table.setPhone(phone);
				table.setOrderTime(orderTime);
				
				boolean result = tableDAO.EditTable(table);
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
