package model;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/*
   动物类
   被移动的动物
 */
public class Animal extends JPanel {
    Point point;
    boolean isLeft;
    Toolkit tool;
    File leftImage, rightImage;

    public Animal() {
        tool = getToolkit();
        String x = point.toString();
        System.out.println("好耶"+x);
    }

    public void setLeftImage(File f) {
        leftImage = f;
    }

    public void setRightImage(File f) {
        rightImage = f;
    }

    public void setIsLeft(boolean boo) {
        isLeft = boo;
    }

    public boolean getIsLeft() {
        return isLeft;
    }

    public void setAtPoint(Point p) {
        point = p;
    }

    public Point getAtPoint() {
        return point;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getBounds().width;
        int h = getBounds().height;
        try {
            if (isLeft) {
                Image image = tool.getImage(leftImage.toURI().toURL());
                g.drawImage(image, 0, 0, w, h, this);
            } else {
                Image image = tool.getImage(rightImage.toURI().toURL());
                g.drawImage(image, 0, 0, w, h, this);
            }
        } catch (Exception ignored) {
        }
    }

}
