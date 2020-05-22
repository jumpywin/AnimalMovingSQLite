package model;

/*
 *空格类
 */
public class Point {
    int x, y;
    boolean haveAnimal;
    Animal animal = null;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isHaveAnimal() {
        return haveAnimal;
    }

    public void setIsHaveAnimal(boolean boo) {
        haveAnimal = boo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Point p) {
        return p.getX() == this.getX() && p.getY() == this.getY();
    }

    public void setThisAnimal(Animal animal) {
        this.animal = animal;
    }

    public Animal getThisAnimal() {
        return animal;
    }

}
