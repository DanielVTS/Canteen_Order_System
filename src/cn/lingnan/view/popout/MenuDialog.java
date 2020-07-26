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

import cn.lingnan.DAO.MenuDAO;
import cn.lingnan.dto.Menu;
import cn.lingnan.view.subview.AllStaffMenuView;

public class MenuDialog extends JDialog implements ActionListener {

	
	private static final long serialVersionUID = 1L;

	private JPanel idPanel, namePanel, kindPanel, statusPanel, pricePanel, opePanel;

	private JLabel idLabel, nameLabel, kindLabel, statusLabel, priceLabel;
	private JTextField idTF, nameTF, kindTF, statusTF, priceTF;

	private JButton saveBtn, cancelBtn;

	private MenuDAO menuDAO = new MenuDAO();

	private AllStaffMenuView menuView;

	private Menu menu;

	public MenuDialog(JFrame parent, AllStaffMenuView menuView) {
		super(parent, "添加");
		this.menuView = menuView;

		setSize(350, 300);

		setLocationRelativeTo(null);

		setModal(true);
		setResizable(false);

		this.setLayout(new FlowLayout());

		initView();
	}

	public MenuDialog(JFrame parent, AllStaffMenuView menuView, Menu menu) {
		this(parent, menuView);
		this.menu = menu;
		setTitle("修改");
		// 回显
		this.idTF.setText(String.valueOf(menu.getID()));
		idTF.setEditable(false);
		this.nameTF.setText(menu.getName());
		this.kindTF.setText(menu.getKind());
		this.statusTF.setText(String.valueOf(menu.getStatus()));
		this.priceTF.setText(String.valueOf(menu.getPrice()));
		// this.loginNamePanel.setVisible(false);
	}

	private void initView() {
		idPanel = new JPanel();
		idLabel = new JLabel("菜号");
		idTF = new JTextField(15);
		idPanel.add(idLabel);
		idPanel.add(idTF);

		namePanel = new JPanel();
		nameLabel = new JLabel("菜名");
		nameTF = new JTextField(15);
		namePanel.add(nameLabel);
		namePanel.add(nameTF);

		kindPanel = new JPanel();
		kindLabel = new JLabel("菜类");
		kindTF = new JTextField(15);
		kindPanel.add(kindLabel);
		kindPanel.add(kindTF);

		statusPanel = new JPanel();
		statusLabel = new JLabel("状态");
		statusTF = new JTextField(15);
		statusPanel.add(statusLabel);
		statusPanel.add(statusTF);

		pricePanel = new JPanel();
		priceLabel = new JLabel("价格");
		priceTF = new JTextField(15);
		pricePanel.add(priceLabel);
		pricePanel.add(priceTF);

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
		container.add(kindPanel);
		container.add(statusPanel);
		container.add(pricePanel);
		container.add(opePanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String id = idTF.getText();
		String name = nameTF.getText();
		String kind = kindTF.getText();
		String status = statusTF.getText();
		String price = priceTF.getText();
		if (source == saveBtn) {
			if (idTF.isEditable()) {
				if (menuDAO.FindMenuExist(Integer.parseInt(id))) {
					JOptionPane.showMessageDialog(this, "相同ID记录已存在！");
					return;
				}				
				menu = new Menu();
				menu.setID(Integer.parseInt(id));
				menu.setName(name);
				menu.setKind(kind);
				menu.setStatus(Integer.parseInt(status));
				menu.setPrice(Integer.parseInt(price));
				boolean flag = menuDAO.AddMenu(menu);
				if (flag) {
					JOptionPane.showMessageDialog(this, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
					menuView.validate();
				} else {
					JOptionPane.showMessageDialog(this, "添加失败", "提示", JOptionPane.ERROR_MESSAGE);
				}}
			 else {
					if (menuDAO.FindMenuExist(Integer.parseInt(id))) {
						menu = new Menu();
						menu.setID(Integer.parseInt(id));
						menu.setName(name);
						menu.setKind(kind);
						menu.setStatus(Integer.parseInt(status));
						menu.setPrice(Integer.parseInt(price));
						boolean flag = menuDAO.EditMenu(menu);
						if (flag) {
							JOptionPane.showMessageDialog(this, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
							this.dispose();
							menuView.validate();
						} else {
							JOptionPane.showMessageDialog(this, "添加失败", "提示", JOptionPane.ERROR_MESSAGE);
						}
					}

					
			 }
		}else if (source == cancelBtn) {

			this.dispose();
		}
	}
}
			
		
	

