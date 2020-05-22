package GUI;

import JDBC.dao.UserTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
/*
    登录，注册管理 和界面全在这
 */

public class LoginPage extends JFrame implements ActionListener {

    public static int userid;

    JPanel jp2, jp3;
    JTextField nick;
    JPasswordField passwd;
    JLabel str1, str2;

    JButton login, create;

    LoginPage() {

        str1 = new JLabel("请输入账号");

        str2 = new JLabel("请输入密码");

        nick = new JTextField(30);
        nick.setFont(new Font("楷体", Font.BOLD, 16));

        passwd = new JPasswordField(30);
        passwd.setFont(new Font("楷体", Font.BOLD, 16));
        passwd.setEchoChar('*');

        login = new JButton("登录");
        login.addActionListener(this);
        create = new JButton("注册");
        create.addActionListener(this);


        jp2 = new JPanel();    //创建面板
        jp2.add(str1);
        jp2.add(nick);
        jp2.add(str2);
        jp2.add(passwd);
        jp2.add(login);
        jp2.add(create);


        this.add(jp2);

        this.setTitle("登录");
        this.setBounds(500, 200, 380, 200);

        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /*
        登录校验放在这里，
     */
    public void checkLogin() {
        String account = nick.getText();
        String password = new String(passwd.getPassword());
        String Daccount = null;
        String Dpasswd = null;
        try {
            Daccount = UserTools.findUserByNick(account).getNick();
            Dpasswd = UserTools.findUserByNick(account).getPasswd();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "该账号没有注册");
        }

        if (password.equals(Dpasswd)) {
            JOptionPane.showMessageDialog(null, "登录成功");//todo 登录成功，应该修改最后登录时间,但是我更本就不想加
            try {
                LoginPage.userid = UserTools.findUserByNick(account).getId();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            this.dispose();//关闭窗口
            new ChangeAnimalWindow();
        }else{
            JOptionPane.showMessageDialog(null, "密码有误，请重试");
        }
    }


    public void createAccount() {
        String account = nick.getText();
        String password = new String(passwd.getPassword());

        /*
            检查账号是否合规
         */
        if (account.length() < 3) {
            JOptionPane.showMessageDialog(null, "账号不合规，长度必须为3或以上");
        }
        try {
            if (UserTools.findUserByNick(account) == null) {//检查账号是否存在
                /*
                    检查密码是否合规
                */
                if (password.length() < 4 || password == null) {
                    JOptionPane.showMessageDialog(null, "密码不合规，长度必须为4或者以上");
                } else {//todo 检测通过 写入数据库 时间相关的都没有做
                    UserTools.createAccout(account, password);
                    JOptionPane.showMessageDialog(null, "账号创建成功，点击确定返回登录");
                }
            } else {
                JOptionPane.showMessageDialog(null, "账号已存在");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            this.checkLogin();
        } else if (e.getSource() == create) {
            this.createAccount();
        }
    }

    public static void main(String[] args) {
        new LoginPage();
        //new ChangeAnimalWindow();
    }
}
