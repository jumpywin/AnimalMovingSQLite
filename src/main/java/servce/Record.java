package servce;

import GUI.ChangeAnimalWindow;
import GUI.LoginPage;
import JDBC.dao.GradeTools;
import JDBC.model.Grade;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/*
    记录保存
 */
public class Record extends JDialog implements ActionListener {
    int time = 0;
    JLabel label;
    JButton enter, cancel;

    public Record() {
        setBounds(900, 400, 330, 160);
        setResizable(false);
        setModal(true);
        setVisible(false);
        enter = new JButton("确定");
        cancel = new JButton("取消");
        enter.addActionListener(this);
        cancel.addActionListener(this);
        setLayout(new GridLayout(2, 1));
        label = new JLabel();
        label.setText("恭喜您完成了游戏，是否记录本次成绩？");
        add(label);
        JPanel p = new JPanel();
        p.add(enter);
        p.add(cancel);
        add(p);
    }

    public void setTime(int time) {
        this.time = time;
    }


    public void actionPerformed(ActionEvent e) {//todo 保存记录方法

        if (e.getSource() == enter) {
            GradeTools.InsertGrade(new Grade(LoginPage.userid, ChangeAnimalWindow.difficulty, time));
            this.setVisible(false);
        }
        if (e.getSource() == cancel) {
            this.setVisible(false);
        }
        System.out.println("保存好了，偶也");
    }
}
