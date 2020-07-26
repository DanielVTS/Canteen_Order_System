package cn.lingnan.view.mainview;

import javax.swing.*;

public class LoginUnsuccessful {
    //登录失败则自动弹出该窗口，必须点确定才能进行重新登录
    public LoginUnsuccessful(){
        JOptionPane.showMessageDialog(new JFrame(),
                "请输入正确的账号/密码! 请按确定进行重新登录", "错误提示信息", JOptionPane.ERROR_MESSAGE);
    }


}