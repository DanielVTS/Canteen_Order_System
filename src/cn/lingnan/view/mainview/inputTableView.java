package cn.lingnan.view.mainview;
import javax.swing.JFrame;

import cn.lingnan.view.popout.inputTableDialog;



public class inputTableView extends inputTableDialog {

	
	private static final long serialVersionUID = 1L;

	public inputTableView(String title) {
		super(title);
		createGUI();
	}
	
	private static void createGUI()
	{
		//创建一个窗口，创建一个窗口
		inputTableDialog frame = new inputTableDialog("输入台号");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//设置窗口大小
		frame.setSize(400, 200);
		frame.setLocation(1050, 400);
		
		//显示窗口
		frame.setVisible(true);
	}
 
}

