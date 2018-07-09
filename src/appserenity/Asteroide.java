/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appserenity;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class Asteroide {

    private final int FINAL_Y = 650;
    private int x;
    private int y;
    private Image image;
    private boolean vis = true;

    public Asteroide(int x, int y) {
        Image ii = new ImageIcon(getClass().getResource("asteroid.png")).getImage();
        image = ii;
        this.x = x;
        this.y = y;

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 15, 15);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public boolean isVisible() {
        return vis;
    }

    public void move() {

        if (y < FINAL_Y) {
            y = y + 1;
        } else {
            y = 1;
        }
    }


    public void setVisible(Boolean visible) {
        this.vis = visible;
    }

}
