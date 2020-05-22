package servce;

import JDBC.dao.GradeTools;
import JDBC.dao.UserTools;
import JDBC.model.Grade;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;


/*
    todo 基本ok 样式很丑 还没有加入原游戏程序
 */


public class Rank extends JFrame {

    private JTextArea tf;

    public Rank() {

        tf = new JTextArea();
        tf.setBackground(Color.LIGHT_GRAY);
        tf.setBounds(0, 0, 300, 500);
        tf.setEditable(false);//文本框设置为不可编辑,且取消光标

        this.add(tf);
        this.setBounds(400, 200, 300, 500);
        this.setTitle("排行榜");
        this.setDefaultCloseOperation(2);
    }

    /**
     * 展示排行榜
     */
    public void showRank(int difficulty) throws IOException {
        if (difficulty == 1) {
            this.setTitle("初级排行榜");
        } else if (difficulty == 2) {
            this.setTitle("中级排行榜");
        } else {
            this.setTitle("高级排行榜");
        }

        List<Grade> ranklist = GradeTools.getRankingList(difficulty);//获取数据库的数据
        tf.removeAll();
        tf.append("ncik\tcompletion_time");
        for (Grade a : ranklist) {
            tf.append("\n" + UserTools.findNickById(a.getUserid()) + "\t" + a.getCompletion_time());
        }
        this.setVisible(true);
    }
}
