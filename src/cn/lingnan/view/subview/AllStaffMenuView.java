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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import cn.lingnan.DAO.MenuDAO;
import cn.lingnan.dto.Menu;
import cn.lingnan.view.model.MenuInfoTableModel;
import cn.lingnan.view.popout.MenuDialog;

public class AllStaffMenuView extends JPanel implements ActionListener{

	        
	private static final long serialVersionUID = 1L;
			private MenuDAO md; 
	        //上面
			private JPanel toolBarPanel;
			
			private JPanel searchPanel;
			private JLabel nameLabel;
			private JTextField nameSearchTF;
			private JButton searchBtn;
			
			private JPanel opePanel;
			private JButton addBtn,updateBtn,deleteBtn;
			
			//中间
			
			private JScrollPane tableScrollPane;
			private JTable userTable;
			
			//下面
			private JPanel bottomPanel;
			private JLabel countInfoLabel;
			
			
			private JFrame jFrame;
			public AllStaffMenuView(JFrame jFrame) {
				this.setLayout(new BorderLayout());
				initView();
				this.jFrame = jFrame;
			}
			
			private void initView() {
				 
				toolBarPanel = new JPanel(new BorderLayout()); 
				
				searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
				nameLabel = new JLabel("菜名编号");
				nameSearchTF = new JTextField(10);
				
				searchBtn = new JButton("搜索",new ImageIcon("static\\icon\\search.png"));
				searchBtn.addActionListener(this);
				
				opePanel =  new JPanel(new FlowLayout(FlowLayout.RIGHT)); 
				addBtn =new JButton("添加",new ImageIcon("static\\icon\\user_add.png"));
				updateBtn =new JButton("修改",new ImageIcon("static\\icon\\user_edit.png"));
				deleteBtn =new JButton("删除",new ImageIcon("static\\icon\\user_delete.png"));
				addBtn.addActionListener(this);
				updateBtn.addActionListener(this);
				deleteBtn.addActionListener(this);
				
				
				
				
				opePanel.add(addBtn);
				opePanel.add(updateBtn);
				opePanel.add(deleteBtn);
				
				 
				searchPanel.add(nameLabel);
				searchPanel.add(nameSearchTF);
				searchPanel.add(searchBtn);
				
				
				toolBarPanel.add(searchPanel,"West");
				toolBarPanel.add(opePanel,"East");
				
				
				//中间
			    //UserTableModel userTableModel = new UserTableModel();
			    //ChefOrderDAO cheforderdao=new ChefOrderDAO();
			    //cheforderdao.findAllChefOrder();
				
				md=new MenuDAO();
			    Vector<Menu> vm=md.findAllMenu();
			    MenuInfoTableModel model=new MenuInfoTableModel(vm);
			    userTable = new JTable(model);
				tableScrollPane=new JScrollPane(userTable);
				 
				
				//下面
				bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				String str=Integer.toString(model.getRowCount());
				countInfoLabel = new JLabel("总共"+str+"条");
				bottomPanel.add(countInfoLabel);
				
				
				this.add(toolBarPanel,"North");
				this.add(tableScrollPane,"Center");
				this.add(bottomPanel,"South");
				
				this.setVisible(true);
			}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		MenuDAO md=new MenuDAO();
		if (e.getSource() == searchBtn ) {			
			if(nameSearchTF.getText().isEmpty()) {
				Vector<Menu> vm=md.findAllMenu();
				this.updateTable(vm);
				return;
			}else {
				Vector<Menu> vm=md.find("Menu_Id", Integer.parseInt(nameSearchTF.getText()));
				this.updateTable(vm);
				return;
			}
						
		}else if(e.getSource() == updateBtn) {
			Menu menu=new Menu();
			int rowIndex = userTable.getSelectedRow();
			if(rowIndex==-1) {
				JOptionPane.showMessageDialog(this,"请选中一条");				
				return;
			}
			int id = (Integer)userTable.getValueAt(rowIndex,0);		
			menu = md.FindMenu(id);			
			if(menu==null) {
				JOptionPane.showMessageDialog(this,"数据不存在,请刷新");
				return;
			}
			MenuDialog menuDialog = new MenuDialog(jFrame,this,menu);
			menuDialog.setVisible(true);
			Vector<Menu> vm=md.findAllMenu();
		    this.updateTable(vm);
		}else if(e.getSource() == addBtn) {
			MenuDialog menuDialog = new MenuDialog(jFrame,this);
			menuDialog.setVisible(true);
			Vector<Menu> vm=md.findAllMenu();
		    this.updateTable(vm);
		}else if(e.getSource() == deleteBtn) {
			int rowIndex = userTable.getSelectedRow();
			if(rowIndex==-1) {
				JOptionPane.showMessageDialog(this,"请选中一条");
				return;
			}
			int id = (Integer)userTable.getValueAt(rowIndex,0);
			Menu m=new Menu();
			m.setID(id);
			if(md.DeleteMenu(m)==true) {
				JOptionPane.showMessageDialog(this,"删除成功","提示",JOptionPane.INFORMATION_MESSAGE);
			    
			}else {
				JOptionPane.showMessageDialog(this,"删除失败","提示",JOptionPane.ERROR_MESSAGE);
			}
			
			
			Vector<Menu> vm=md.findAllMenu();
		    this.updateTable(vm);
		}
		
	}


	public void updateTable(Vector<Menu> vm) {		
		this.remove(tableScrollPane);
		MenuInfoTableModel model=new MenuInfoTableModel(vm);
	    userTable = new JTable(model);
	    tableScrollPane=new JScrollPane(userTable);
		this.add(tableScrollPane,"Center");
		
		bottomPanel.remove(countInfoLabel);
		String str=Integer.toString(model.getRowCount());
		countInfoLabel = new JLabel("总共"+str+"条");
		bottomPanel.add(countInfoLabel);
		bottomPanel.validate();
		
		this.validate();
//		this.repaint();
		
	}
}
