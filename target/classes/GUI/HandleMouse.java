package GUI;

import servce.Record;
import model.Animal;
import model.Point;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.applet.*;

/*
    计时功能
 */

public class HandleMouse extends JPanel implements MouseListener, ActionListener {
    Point[] point;
    ArrayList<Integer> step;
    int spendTime = 0;
    javax.swing.Timer recordTime;
    boolean countTime = false;
    JTextField showTime;
    File musicFile;
    AudioClip clip;
    File gradeFile;
    Record record;

    HandleMouse() {
        try {

            step = new ArrayList<Integer>();
            recordTime = new javax.swing.Timer(1000, this);
            record = new Record();
            musicFile = new File("qwe.wav");//todo 音乐文件在这里
            URI uri = musicFile.toURI();
            URL url = uri.toURL();
            showTime = new JTextField(26);
            showTime.setEditable(false);
            showTime.setHorizontalAlignment(JTextField.CENTER);
            showTime.setFont(new Font("UTF8", Font.BOLD, 18));
            this.add(new JLabel("点击动物图片开始游戏并计时:", JLabel.CENTER));
            this.add(showTime);
            this.setBackground(Color.pink);
            this.clip = Applet.newAudioClip(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setPoint(Point[] point) {
        this.point = point;
    }

    public void initStep() {
        this.step.clear();
    }

    public void initSpendTime() {
        this.spendTime = 0;
        this.showTime.setText(null);
        this.recordTime.stop();
    }

    public ArrayList<Integer> getStep() {
        return step;
    }

    public void setCountTime(boolean b) {
        this.countTime = b;
    }

    public void mousePressed(MouseEvent e) {
        if (countTime) {
            this.recordTime.start();
            this.clip.play();
        } else {
            this.showTime.setText("计时取消");
        }
        Animal animal;
        animal = (Animal) e.getSource();
        int w = animal.getBounds().width;
        int h = animal.getBounds().height;
        int m = -1;
        Point startPoint = animal.getAtPoint();
        for (int i = 0; i < point.length; i++) {
            if (point[i].equals(startPoint)) {
                m = i;
                break;
            }
        }
        if (animal.getIsLeft() && m <= point.length - 2) {
            if (!point[m + 1].isHaveAnimal()) {
                animal.setLocation(point[m + 1].getX() - w / 2, point[m + 1].getY() - h);
                animal.setAtPoint(point[m + 1]);
                point[m + 1].setThisAnimal(animal);
                point[m + 1].setIsHaveAnimal(true);
                startPoint.setIsHaveAnimal(false);
                step.add(m);
                step.add(m + 1);
            } else if (m + 1 <= point.length - 2 && !point[m + 2].isHaveAnimal()) {
                animal.setLocation(point[m + 2].getX() - w / 2, point[m + 2].getY() - h);
                animal.setAtPoint(point[m + 2]);
                point[m + 2].setThisAnimal(animal);
                point[m + 2].setIsHaveAnimal(true);
                startPoint.setIsHaveAnimal(false);
                step.add(m);
                step.add(m + 2);
            }
        } else if (!animal.getIsLeft() && m >= 1) {
            if (!point[m - 1].isHaveAnimal()) {
                animal.setLocation(point[m - 1].getX() - w / 2, point[m - 1].getY() - h);
                animal.setAtPoint(point[m - 1]);
                point[m - 1].setThisAnimal(animal);
                point[m - 1].setIsHaveAnimal(true);
                startPoint.setIsHaveAnimal(false);
                step.add(m);
                step.add(m - 1);
            } else if (m - 1 >= 1 && !point[m - 2].isHaveAnimal()) {
                animal.setLocation(point[m - 2].getX() - w / 2, point[m - 2].getY() - h);
                animal.setAtPoint(point[m - 2]);
                point[m - 2].setThisAnimal(animal);
                point[m - 2].setIsHaveAnimal(true);
                startPoint.setIsHaveAnimal(false);
                step.add(m);
                step.add(m - 2);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        spendTime++;
        showTime.setText("您的用时:" + spendTime + "秒");
    }

    public boolean isSuccess() {
        boolean boo = true;
        int i = 0;
        for (i = 0; i < point.length / 2; i++) {
            if (point[i].getThisAnimal().getIsLeft()
                    || !point[i + point.length / 2 + 1].getThisAnimal().getIsLeft()) {

                boo = false;
                break;
            }
        }
        return boo;
    }

    public void mouseReleased(MouseEvent e) {
        if (isSuccess()) {
            recordTime.stop();
            record.setTime(spendTime);
            record.setVisible(true);
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }
}