/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appserenity;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Nave {

    private int dx;
    private int dy;
    private int x;
    private int y;
    private Image image;
    private ArrayList<Misil> missiles;
    private boolean vis = true;
    private int balas;

    public Nave(int multMisiles) {
        initCraft(275, 550);
        this.balas = multMisiles;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 60, 60);
    }

    private void initCraft(int x, int y) {
        Image ii = new ImageIcon(getClass().getResource("nave.png")).getImage();
        //    ImageIcon ii = new ImageIcon("craft.png");
        this.x = x;
        this.y = y;
        image = ii;
        this.missiles = new ArrayList();

    }

    public ArrayList<Misil> getMisiles() {
        return missiles;
    }

    public void move() {
        x += dx;
        y += dy;

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

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            if(getX()>0)
            { dx = -1;}
            else{
            dx=0;
            }
        }

        if (key == KeyEvent.VK_RIGHT) {
            if(getX()<599){
            dx = 1;}
            else{
            dx=0;
            }
        }

        if (key == KeyEvent.VK_UP) {
            if(getY()>1){
            dy = -1;}
            else{
            dy=0;
            }
        }

        if (key == KeyEvent.VK_DOWN) {
            if( getY() < 550){
            dy = 1;
            }
            else{
            dy=0;
            }
        }
    }

    public void fire() {

        if (balas == 1) {
            missiles.add(new Misil(getX() + 20, getY() - 10));
            System.out.println("x: "+getX());
            System.out.println("y: "+getY());
        } else {
            missiles.add(new Misil(getX() + 39, getY() - 10));
            missiles.add(new Misil(getX() + 9, getY() - 10));
        }

    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }

}
