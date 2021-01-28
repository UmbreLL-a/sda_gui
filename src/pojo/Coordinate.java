package pojo;

import java.io.Serializable;

/**
 * @program: software_define_app_v1
 * @description: 二维坐标
 * @author: LYT
 * @create: 2021-01-11 21:27
 **/

public class Coordinate implements Serializable {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean equals(Coordinate x){
        if(this.getX()==x.getX() && this.getY()==x.getY())
            return true;
        else
            return false;
    }
}
