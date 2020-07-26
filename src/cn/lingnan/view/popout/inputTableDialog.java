package cn.lingnan.view.popout;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cn.lingnan.DAO.TableDAO;
import cn.lingnan.view.mainview.CustomerView;
 
public class inputTableDialog  extends JFrame{


		
	private static final long serialVersionUID = 1L;

		JLabel label = new JLabel("台号"); 
		
		//创建JTextField，16表示16列，用于JTextField的宽度显示而不是限制字符个数
		JTextField textField = new JTextField(16);
		JButton button = new JButton("确定");
		

		
		//构造函数
		public inputTableDialog(String title)
		{
			
			//继承父类，
			super(title);
			
			//内容面板
			Container contentPane = getContentPane();
			contentPane.setLayout(new FlowLayout());
			
			//添加控件
			contentPane.add(label);
			contentPane.add(textField);
			contentPane.add(button);
			
			//按钮点击处理 lambda表达式
			button.addActionListener((e) -> {
				onButtonOk();
			});
			
			
		}
		
		//事件处理
		private void onButtonOk()
		{
			String str = textField.getText();//获取输入内容
			//判断是否输入了
			if(str.equals(""))
			{
				Object[] options = { "OK ", "CANCEL " }; 
				JOptionPane.showOptionDialog(null, "您还没有输入 ", "提示", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.WARNING_MESSAGE,null, options, options[0]);
			}
			else{
				TableDAO td=new TableDAO();
				if(td.FindTable(Integer.parseInt(str)) != null) {
					new CustomerView (Integer.parseInt(str));
					this.dispose();
				}else {
					JOptionPane.showMessageDialog(this, "桌号有误！", "提示", JOptionPane.ERROR_MESSAGE);
				}
				
			    
			}
		}
		
	}
