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

import cn.lingnan.DAO.StaffDAO;
import cn.lingnan.dto.Staff;
import cn.lingnan.view.subview.StaffView;



public class StaffDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;

	private JPanel idPanel,namePanel,JobPanel,GenderPanel,StaffPasswordPanel,opePanel;
	
	private JLabel idLabel,nameLabel,JobLabel,GenderLabel,StaffPasswordLabel;
	private JTextField idTF,nameTF,JobTF,GenderTF,StaffPasswordTF;
	
	private JButton saveBtn,cancelBtn;
	
	private StaffDAO staffDAO = new StaffDAO();
	
	//private StaffView staffView;
	
	private Staff staff;
	
	public StaffDialog(JFrame parent,StaffView staffView) {
		super(parent,"添加");
		//this.staffView = staffView;
		
		setSize(350,300);
		
		setLocationRelativeTo(null);
		
		setModal(true);
		setResizable(false);
		 
		this.setLayout(new FlowLayout());
		
		initView();
	}
	
	public StaffDialog(JFrame parent,StaffView staffView,Staff staff) {
		 this(parent,staffView);
		 this.staff = staff;
		 setTitle("修改");
		 //回显
		 this.idTF.setText(String.valueOf(staff.getID()));
		 this.nameTF.setText(staff.getName());
		 this.JobTF.setText(staff.getJob());
		 this.GenderTF.setText(String.valueOf(staff.getGender()));
		 this.StaffPasswordTF.setText(String.valueOf(staff.getPassword()));
		 //this.loginNamePanel.setVisible(false);
	}
	
	private void initView() {
		idPanel = new JPanel();
		idLabel = new JLabel("员工号");
		idTF = new JTextField(15);
		idPanel.add(idLabel);
		idPanel.add(idTF);
				
		namePanel = new JPanel();
		nameLabel = new JLabel("员工名");
		nameTF = new JTextField(15);
		namePanel.add(nameLabel);
		namePanel.add(nameTF);
		
		JobPanel = new JPanel();
		JobLabel = new JLabel("职位");
		JobTF = new JTextField(15);
		JobPanel.add(JobLabel);
		JobPanel.add(JobTF);
		
		GenderPanel = new JPanel();
		GenderLabel = new JLabel("性别");
		GenderTF = new JTextField(15);
		GenderPanel.add(GenderLabel);
		GenderPanel.add(GenderTF);
		
		StaffPasswordPanel = new JPanel();
		StaffPasswordLabel = new JLabel("密码");
		StaffPasswordTF = new JTextField(15);
		StaffPasswordPanel.add(StaffPasswordLabel);
		StaffPasswordPanel.add(StaffPasswordTF);
		
		opePanel = new JPanel();
		saveBtn = new JButton("保存");
		cancelBtn = new JButton("取消");
		saveBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		opePanel.add(saveBtn);
		opePanel.add(cancelBtn);
		
		Container container = getContentPane();
		container.add(idPanel);
		container.add(namePanel);
		container.add(JobPanel);
		container.add(GenderPanel);
		container.add(StaffPasswordPanel);
		container.add(opePanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source==saveBtn){
			String id = idTF.getText();
			String name = nameTF.getText();
			String job = JobTF.getText();
			String gender = GenderTF.getText();
			String password = StaffPasswordTF.getText();
			

			if(this.staff==null) {	
				//TODO 参数校验
				Staff staff = new Staff();
				staff.setID(Integer.valueOf(id));
				staff.setName(name);
				staff.setJob(job);
				staff.setGender(gender);
				staff.setPassword(password);
				
				
				boolean result = staffDAO.addStaff(staff);
				if(result) {
					JOptionPane.showMessageDialog(this,"添加成功","提示",JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
					
					//刷新
					//staffView.refresh();
				}else {
					JOptionPane.showMessageDialog(this,"添加失败","提示",JOptionPane.ERROR_MESSAGE);
				}
			}else {
				//更新
				Staff staff = new Staff();
				staff.setID(Integer.valueOf(id));
				staff.setName(name);
				staff.setJob(job);
				staff.setGender(gender);
				staff.setPassword(password);
				
				boolean result = staffDAO.editStaff(staff);
				if(result==true) {
					JOptionPane.showMessageDialog(this,"更新成功","提示",JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
					//刷新
					//staffView.refresh();
				}else {
					JOptionPane.showMessageDialog(this,"更新失败","提示",JOptionPane.ERROR_MESSAGE);
				}
			}
		}else if(source == cancelBtn) {
			
			this.dispose();
		}
	}
}
