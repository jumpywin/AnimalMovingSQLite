package GUI;

import servce.Rank;
import model.Animal;
import model.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import javax.swing.filechooser.*;

/*
    主JFrame窗口类
 */

public class ChangeAnimalWindow extends JFrame implements ActionListener {

    public static int difficulty = 1;

    int amountOfAnimal = 6;
    int distance = 80;
    Animal[] animal;
    model.Point[] point;
    HandleMouse handleMouse;
    File leftImageFile, rightImageFile;
    File fileOneGrade, fileTwoGrade, fileThreeGrade;
    JButton renew, quit;
    JMenuBar bar;
    JMenu menuGrade, menuImage, menuHero, menuabout;
    JMenuItem oneGradeResult, twoGradeResult, threeGradeResult;
    JMenuItem oneGradeItem, twoGradeItem, threeGradeItem;
    JMenuItem leftIamge, rightIamge, defaultImage;
    JMenuItem about, rules;

    JPanel pCenter;

    ChangeAnimalWindow() {

        //中间容器
        pCenter = new JPanel();
        pCenter.setBackground(Color.white);
        pCenter.setLayout(null);

        handleMouse = new HandleMouse();

        //todo 指定默认动物图片目录
        leftImageFile = new File("qwe/cat.jpg");
        rightImageFile = new File("qwe/dog.jpg");
        this.init();

        //菜单栏
        bar = new JMenuBar();
        menuGrade = new JMenu("选择游戏难度");
        menuImage = new JMenu("自定义择动物图像");
        menuHero = new JMenu("英雄榜(排行榜)");
        menuabout = new JMenu("关于");

        oneGradeItem = new JMenuItem("初    级");
        twoGradeItem = new JMenuItem("中    级");
        threeGradeItem = new JMenuItem("高    级");

        leftIamge = new JMenuItem("左面动物的图像");
        rightIamge = new JMenuItem("右面动物的图像");
        defaultImage = new JMenuItem("恢复默认图像");

        oneGradeResult = new JMenuItem("初级英雄榜");
        twoGradeResult = new JMenuItem("中级英雄榜");
        threeGradeResult = new JMenuItem("高级英雄榜");

        rules = new JMenuItem("游戏规则");
        about = new JMenuItem("制作说明");

        menuGrade.add(oneGradeItem);
        menuGrade.add(twoGradeItem);
        menuGrade.add(threeGradeItem);

        menuImage.add(leftIamge);
        menuImage.add(rightIamge);
        menuImage.add(defaultImage);
        menuHero.add(oneGradeResult);
        menuHero.add(twoGradeResult);
        menuHero.add(threeGradeResult);

        menuabout.add(rules);
        menuabout.add(about);

        bar.add(menuGrade);
        bar.add(menuImage);
        bar.add(menuHero);
        bar.add(menuabout);

        this.setJMenuBar(bar);

        menuabout.addActionListener(this);

        oneGradeItem.addActionListener(this);
        twoGradeItem.addActionListener(this);
        threeGradeItem.addActionListener(this);

        leftIamge.addActionListener(this);
        rightIamge.addActionListener(this);
        defaultImage.addActionListener(this);

        oneGradeResult.addActionListener(this);
        twoGradeResult.addActionListener(this);
        threeGradeResult.addActionListener(this);

        rules.addActionListener(this);
        about.addActionListener(this);

        //JPanel容器中按钮
        renew = new JButton("重新开始");
        renew.addActionListener(this);
        quit = new JButton("撤消");
        quit.addActionListener(this);

        JPanel north = new JPanel();
        north.add(renew);
        north.add(quit);

        //在JFrame窗口底部添加按钮
        this.add(north, BorderLayout.AFTER_LAST_LINE);
        //动物图片
        this.add(pCenter, BorderLayout.CENTER);

        //将容纳计时器的窗口添加到顶部
        JPanel south = new JPanel();
        south.add(handleMouse);
        this.add(south, BorderLayout.NORTH);

        //todo Frame窗体属性设置
        this.setVisible(true);
        this.setTitle("AnimalJump");
        this.setBackground(Color.white);
        this.setBounds(600, 400, 710, 300);
        this.validate();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    // 游戏核心代码
    public void init() {
        animal = new Animal[amountOfAnimal];
        point = new model.Point[amountOfAnimal + 1];

        int space = distance;
        for (int i = 0; i < point.length; i++) {
            point[i] = new model.Point(space, 100);
            space = space + distance;
        }
        for (int i = 0; i < animal.length; i++) {
            animal[i] = new Animal();
            animal[i].addMouseListener(handleMouse);
            if (i < animal.length / 2) {
                animal[i].setIsLeft(true);
            } else {
                animal[i].setIsLeft(false);
            }
        }
        for (int i = 0; i < animal.length; i++) {
            animal[i].setSize(distance * 6 / 7, distance * 3 / 4);
            int w = animal[i].getBounds().width;
            int h = animal[i].getBounds().height;
            pCenter.add(animal[i]);
            if (i < animal.length / 2) {
                animal[i].setIsLeft(true);
                animal[i].setLeftImage(leftImageFile);
                animal[i].repaint();
                animal[i].setLocation(point[i].getX() - w / 2, point[i].getY() - h);
                animal[i].setAtPoint(point[i]);
                point[i].setThisAnimal(animal[i]);
                point[i].setIsHaveAnimal(true);
            } else {
                animal[i].setIsLeft(false);
                animal[i].setRightImage(rightImageFile);
                animal[i].repaint();
                animal[i].setLocation(point[i + 1].getX() - w / 2, point[i + 1].getY() - h);
                animal[i].setAtPoint(point[i + 1]);
                point[i + 1].setThisAnimal(animal[i]);
                point[i + 1].setIsHaveAnimal(true);
            }
        }
        handleMouse.setPoint(point);
        handleMouse.setCountTime(true);
    }

    public void setAmountOfAnimal(int m) {
        if (m >= 2 && m % 2 == 0)
            amountOfAnimal = m;
    }

    public void removeAnimal() {
        for (Point value : point) {
            if (value.getThisAnimal() != null)
                pCenter.remove(value.getThisAnimal());
        }
        pCenter.validate();
        pCenter.repaint();
    }

    //重开
    public void needDoing() {
        init();
        handleMouse.initStep();
        handleMouse.initSpendTime();
        handleMouse.setCountTime(true);
    }

    //鼠标监听事件
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == oneGradeItem) {
            difficulty = 1;
            handleMouse.gradeFile = fileOneGrade;
            distance = 80;
            removeAnimal();
            setAmountOfAnimal(6);
            needDoing();
        } else if (e.getSource() == twoGradeItem) {
            difficulty = 2;
            handleMouse.gradeFile = fileTwoGrade;
            distance = 70;
            removeAnimal();
            setAmountOfAnimal(8);
            needDoing();
        } else if (e.getSource() == threeGradeItem) {
            difficulty = 3;
            handleMouse.gradeFile = fileThreeGrade;
            distance = 60;
            removeAnimal();
            setAmountOfAnimal(10);
            needDoing();
        } else if (e.getSource() == about) {
            JOptionPane.showMessageDialog(null, "这个动物移动小游戏来自银牌混子倾情制作");
        } else if (e.getSource() == renew) {//重新开始
            removeAnimal();
            needDoing();
        } else if (e.getSource() == rules) {//游戏规则
            JOptionPane.showMessageDialog(null, "1.游戏目标：将所有左边的动物和右边的动物交换位置\n" +
                    "2.每次每个动物只能移动一格或者跳过一个动物，不能移动多个格子或者跳过空格和多个动物\n" +
                    "3.点击动物图片开始游戏并自动计时");
        } else if (e.getSource() == quit) {//撤销
            ArrayList<Integer> step = handleMouse.getStep();
            int length = step.size();
            int start = -1, end = -1;
            if (length >= 2) {
                end = step.get(length - 1);
                start = step.get(length - 2);
                step.remove(length - 1);
                step.remove(length - 2);
                Animal ani = point[end].getThisAnimal();
                int w = ani.getBounds().width;
                int h = ani.getBounds().height;
                ani.setLocation(point[start].getX() - w / 2, point[start].getY() - h);
                ani.setAtPoint(point[start]);
                point[start].setThisAnimal(ani);
                point[start].setIsHaveAnimal(true);
                point[end].setIsHaveAnimal(false);
            }
        } else if (e.getSource() == leftIamge) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG & GIF PNG Images", "jpg", "png", "gif");
            chooser.setFileFilter(filter);
            int state = chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            if (file != null && state == JFileChooser.APPROVE_OPTION) {
                leftImageFile = file;
                for (Animal value : animal) {
                    if (value.getIsLeft()) {
                        value.setLeftImage(leftImageFile);
                        value.repaint();
                    }
                }
            }
        } else if (e.getSource() == rightIamge) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG & GIF & PNG Images", "jpg", "png", "gif");
            chooser.setFileFilter(filter);
            int state = chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            if (file != null && state == JFileChooser.APPROVE_OPTION) {
                rightImageFile = file;
                for (Animal value : animal) {
                    if (!value.getIsLeft()) {
                        value.setRightImage(rightImageFile);
                        value.repaint();
                    }
                }
            }
        } else if (e.getSource() == defaultImage) {
            leftImageFile = new File("recource/dog.jpg");
            rightImageFile = new File("recource/cat.jpg");
            for (Animal value : animal) {
                if (value.getIsLeft())
                    value.setLeftImage(leftImageFile);
                else
                    value.setRightImage(rightImageFile);
                value.repaint();
            }
        } else if (e.getSource() == oneGradeResult) {
            try {
                new Rank().showRank(1);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == twoGradeResult) {
            try {
                new Rank().showRank(2);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == threeGradeResult) {
            try {
                new Rank().showRank(3);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        this.validate();
    }
}