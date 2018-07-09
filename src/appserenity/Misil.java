/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appserenity;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class Misil {

    private int x;
    private int y;
    private Image image;
    private boolean vis = true;
    

    public Misil(int x, int y) {
        Image ii = new ImageIcon(getClass().getResource("misil_1.png")).getImage();
        //    ImageIcon ii = new ImageIcon("craft.png");
        image = ii;
        this.x = x;
        this.y = y;
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

    public void move() {

        y -= 3; //velocidad del disparo

        if (y < 1) {
            vis = false;
        }
    }

    public boolean isVisible() {
        return vis;
    }
    
     public void setVisible(Boolean visible) {
        this.vis = visible;
    }
    
          public Rectangle getBounds() {
        return new Rectangle(x, y, 10,14);
    }

}
