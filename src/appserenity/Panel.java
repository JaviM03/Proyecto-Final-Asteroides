/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appserenity;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener {

    private Timer timer;
    private Nave craft;
    private final int DELAY = 10;
    private ArrayList<Asteroide> asteroide;
    private boolean ingame;
    private final int B_WIDTH = 600;
    private final int B_HEIGHT = 650;
    private final int balas = 100000;
    int vidaJugador = 1;
    JLabel vidas, titulo, puntos;
    JTextField txtNombreUsuario;
    private int gameState = 0;  // Menú
    private String[] MenuOptions = {"Jugar", "Tienda", "Puntajes", "Salir"};
    private int currentMenuSelection = 0;

    private final String[] StoreOptions = {"Vida X 1", "Vida X 2", "Vida X 3", "Balas X 1", "Balas X 2"};
    private int currentStoreSelection = 0;
    KeyListener tmenu, tGame, tStore, tScore, tName, tGameOver;
    int multMisiles = 1;
    private String user = "";

    private ArrayList<String> PuntajeUsuario;
    private ArrayList<String> NombreUsuario;

    private final int[][] pos = {
        {10, 29}, {15, 59}, {50, 89},
        {70, 109}, {90, 139}, {150, 169},
        {180, 219}, {210, 250}, {230, 272},
        {280, 309}, {310, 345}, {350, 370},
        {380, 459}, {400, 380}, {410, 460},
        {450, 500}, {490, 30}, {30, 20},
        {60, 59}, {90, 150}, {125, 190},
        {150, 120}, {160, 220}, {170, 280},
        {220, 228}, {250, 370}, {270, 330},
        {293, 329}, {300, 359}, {310, 389},
        {350, 409}, {370, 39}, {390, 62},
        {412, 92}, {436, 150}, {459, 127},
        {438, 109}, {496, 245}, {500, 270},
        {43, 259}, {19, 80}, {75, 60},
        {150, 59}, {183, 30}, {190, 200},
        {200, 259}, {246, 50}, {273, 90},
        {320, 220}, {340, 20}, {360, 180},
        {382, 128}, {400, 170}, {429, 30},
        {439, 500}, {459, 400}, {479, 89},
        {499, 309}, {80, 319}, {50, 29},
        {90, 359}, {160, 20}, {190, 10},
        {180, 309}, {260, 325}, {210, 320},
        {230, 329}, {390, 330}, {330, 304},
        {340, 339}, {490, 320}, {420, 300},
        {400, 329}, {560, 310}, {540, 330},
        {510, 320}, {60, 320}, {40, 420},};

    private final int[][] pos2 = {
        {10, 29}, {15, 59}, {50, 89},
        {70, 109}, {90, 139}, {150, 169},
        {180, 219}, {210, 250}, {230, 272},
        {280, 309}, {310, 345}, {350, 370},
        {380, 459}, {400, 380}, {410, 460},
        {450, 500}, {490, 30}, {30, 20},
        {60, 59}, {90, 150}, {125, 190},
        {150, 120}, {160, 220}, {170, 280},
        {220, 228}, {250, 370}, {270, 330},
        {293, 329}, {300, 359}, {310, 389},
        {350, 409}, {370, 39}, {390, 62},
        {412, 92}, {436, 150}, {459, 127},
        {438, 109}, {496, 245}, {500, 270},
        {43, 259}, {19, 80}, {75, 60},
        {150, 59}, {183, 30}, {190, 200},
        {200, 259}, {246, 50}, {273, 90},
        {320, 220}, {340, 20}, {360, 180},
        {382, 128}, {400, 170}, {429, 30},
        {439, 500}, {459, 400}, {479, 89},
        {499, 309}, {80, 319}, {50, 29},
        {90, 359}, {160, 20}, {190, 10},
        {180, 309}, {260, 325}, {210, 320},
        {230, 329}, {390, 330}, {330, 304},
        {340, 339}, {490, 320}, {420, 300},
        {400, 329}, {560, 310}, {540, 330},
        {510, 320}, {60, 320}, {40, 420},};

    int misilesDestruidos = 0;

    public Panel() {
        PuntajeUsuario = new ArrayList();
        NombreUsuario = new ArrayList();

        initBoard();
    }

    private void initBoard() {

        switch (gameState) {
            case 0:
                // Menu
                System.out.println("case 0");

                removeKeyListener(tmenu);
                removeKeyListener(tStore);
                removeKeyListener(tScore);
                removeKeyListener(tGameOver);

                tmenu = new TMenuAdapter();
                addKeyListener(tmenu);
                setFocusable(true);
                break;
            case 1:
                //Juego
                System.out.println("case 1");
                System.out.println("KD : " + currentStoreSelection);
                int punto = 0;
                misilesDestruidos = 0;
                vidaJugador = (vidaJugador != 0)? vidaJugador: 1;
                
                vidas = new JLabel("Vidas: " + vidaJugador);
                titulo = new JLabel("Jugador: " + user);
                puntos = new JLabel("Puntos: " + punto);

                removeKeyListener(tmenu);
                removeKeyListener(tStore);
                removeKeyListener(tScore);
                removeKeyListener(tGameOver);

                tGame = new TAdapter();
                addKeyListener(tGame);
                setFocusable(true);
                setBackground(Color.BLACK);
                ingame = true;
                craft = new Nave(multMisiles);
                puntos.setBounds(470, 1, 200, 30);
                puntos.setForeground(Color.CYAN);
                titulo.setBounds(10, 1, 200, 30);
                titulo.setForeground(Color.white);
                vidas.setBounds(250, 1, 200, 30);
                vidas.setForeground(Color.yellow);
                this.add(titulo);
                this.add(vidas);
                this.add(puntos);
                initAsteroides();
                break;
            case 2:
                //Tienda
                System.out.println("case 2");
                removeKeyListener(tmenu);
                removeKeyListener(tGame);
                removeKeyListener(tScore);
                
                tStore = new TStoreAdapter();
                addKeyListener(tStore);
                setFocusable(true);
                break;
            case 3:
                //Puntajes

                removeKeyListener(tmenu);
                removeKeyListener(tGame);
                removeKeyListener(tStore);
                tScore = new TScoreAdapter();
                addKeyListener(tScore);
                setFocusable(true);
                break;
            case 11: // GameOver
                removeKeyListener(tmenu);
                removeKeyListener(tGame);
                removeKeyListener(tStore);
                removeKeyListener(tScore);

                tGameOver = new TGameOverAdapter();
                addKeyListener(tGameOver);
                setFocusable(true);

                break;
            case 12: //Nombre

                removeKeyListener(tmenu);
                removeKeyListener(tGame);
                removeKeyListener(tStore);
                removeKeyListener(tScore);

                txtNombreUsuario = new JTextField();
                txtNombreUsuario.setBounds(B_WIDTH / 2 - 95, 340, 200, 30);
                this.add(txtNombreUsuario);

                tName = new TNameAdapter();
                txtNombreUsuario.addKeyListener(tName);
                setFocusable(true);
                break;
            default:
                System.out.println("case default");
                break;
        }

        if (timer == null) {
            timer = new Timer(DELAY, this);
            timer.start();
        }

    }

    public void initAsteroides() {
        asteroide = new ArrayList();

        for (int[] p : pos) {
            asteroide.add(new Asteroide(p[0], p[1]));
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch (gameState) {
            case 0:
                g.setColor(new Color(10, 80, 150));
                g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
                for (int i = 0; i < MenuOptions.length; i++) {

                    if (i == currentMenuSelection) {
                        g.setColor(Color.GREEN);

                    } else {
                        g.setColor(Color.WHITE);
                    }
                    g.setFont(new Font("Arial", Font.PLAIN, 54));
                    g.drawString(MenuOptions[i], B_WIDTH / 2 - 95, 200 + i * 75);
                }
                break;
            case 1:
                if (ingame) {

                    doDrawing(g);

                } else {

                    if (misilesDestruidos == pos.length) {
                        drawGameWinner(g);
                    } else {
                        // Game Over
                        gameState = 11;
                        initBoard();
                    }

                }
                break;
            case 2:
                g.setColor(new Color(10, 80, 150));
                g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 54));
                g.drawString("Tienda", B_WIDTH / 2 - 95, 70);
                for (int i = 0; i < StoreOptions.length; i++) {

                    if (i == currentStoreSelection) {
                        g.setColor(Color.GREEN);

                    } else {
                        g.setColor(Color.WHITE);
                    }

                    g.setFont(new Font("Arial", Font.PLAIN, 54));
                    g.drawString(StoreOptions[i], B_WIDTH / 2 - 95, 200 + i * 75);
                    Image ii;

                    if (i > 2) {
                        ii = new ImageIcon(getClass().getResource("Balas.jpeg")).getImage();
                    } else {
                        ii = new ImageIcon(getClass().getResource("Vida.jpeg")).getImage();
                    }
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.drawImage(ii, B_WIDTH / 2 - 185, 135 + i * 80, this);

                }
                break;
            case 3:
                g.setColor(new Color(10, 80, 150));
                g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 54));
                g.drawString("Puntajes", B_WIDTH / 2 - 95, 70);
                for (int i = 0; i < NombreUsuario.size(); i++) {

                    g.setFont(new Font("Arial", Font.PLAIN, 42));
                    g.drawString(NombreUsuario.get(i), B_WIDTH / 2 - 190, 200 + i * 75);
                    g.drawString(PuntajeUsuario.get(i), B_WIDTH - 200, 200 + i * 75);

                }

                g.setFont(new Font("Arial", Font.BOLD, 32));
                g.drawString("Presiona Enter para salir...", B_WIDTH / 2 - 200, B_HEIGHT - 50);
                break;

            case 11:
                drawGameOver(g);

                break;

            case 12:
                g.setColor(new Color(10, 80, 150));
                g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 32));
                g.drawString("Ingresa tu nombre", B_WIDTH / 2 - 125, 300);

                g.setFont(new Font("Arial", Font.BOLD, 32));
                g.drawString("Presiona Enter para continuar...", B_WIDTH / 2 - 240, B_HEIGHT - 50);
                break;
            default:
                break;
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);

        ArrayList<Misil> misiles = craft.getMisiles();

        for (Misil misil1 : misiles) {
            if (misil1.isVisible()) {
                g.drawImage(misil1.getImage(), misil1.getX(), misil1.getY() + 3, this);
            }
        }

        for (Asteroide asteroide1 : asteroide) {
            if (asteroide1.isVisible()) {
                g.drawImage(asteroide1.getImage(), asteroide1.getX(), asteroide1.getY(), this);
            }
        }

    }

    private void drawGameOver(Graphics g) {
        
        
        

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);

        g.drawString("Presiona Enter para continuar...", B_WIDTH / 2 - 240, B_HEIGHT - 50);

    }

    private void drawGameWinner(Graphics g) {

        String msg = "Winner";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (gameState != 1) {
            repaint();
        } else {

            craft.move();
            updateMissiles();
            updateAsteroides();
            checkCollisions();
            repaint();
        }
    }

    private void updateMissiles() {

        ArrayList<Misil> ms = craft.getMisiles();

        for (int i = 0; i < ms.size(); i++) {

            Misil m = ms.get(i);

            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    }

    private void updateAsteroides() {

        if (asteroide.isEmpty()) {

            ingame = false;
            return;
        }

        for (int i = 0; i < asteroide.size(); i++) {

            Asteroide a = asteroide.get(i);
            if (a.isVisible()) {
                a.move();
            } else {
                asteroide.remove(i);
            }
        }
    }

    public void checkCollisions() {

        Rectangle r3 = craft.getBounds();

        for (Asteroide asteroide1 : asteroide) {
            Rectangle r2 = asteroide1.getBounds();

            if (r3.intersects(r2)) {

                asteroide1.setVisible(false);
                vidaJugador = vidaJugador - 1;

                if (vidaJugador > 0) {
                    vidas.setText("Vidas: " + vidaJugador);
                }

                if (vidaJugador <= 0) {
                    ingame = false;
                    craft.setVisible(false);
                    vidas.setText("Vidas: 0");
                    break;
                }

            }
        }

        ArrayList<Misil> ms = craft.getMisiles();

        for (Misil m : ms) {

            Rectangle r1 = m.getBounds();

            for (Asteroide asteroide1 : asteroide) {

                Rectangle r2 = asteroide1.getBounds();

                if (r1.intersects(r2)) {

                    misilesDestruidos = misilesDestruidos + 1;
                    puntos.setText("Puntos: " + misilesDestruidos);
                    m.setVisible(false);
                    asteroide1.setVisible(false);
                }
            }
        }
    }
    
    
    // Listener Adapters

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }

    private class TMenuAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case com.sun.glass.events.KeyEvent.VK_DOWN:
                    currentMenuSelection++;
                    if (currentMenuSelection >= MenuOptions.length) {
                        currentMenuSelection = 0;
                    }
                    break;
                case com.sun.glass.events.KeyEvent.VK_UP:
                    currentMenuSelection--;
                    if (currentMenuSelection < 0) {
                        currentMenuSelection = MenuOptions.length - 1;
                    }
                    break;
                case com.sun.glass.events.KeyEvent.VK_ENTER:
                    switch (currentMenuSelection) {
                        case 0:
                            gameState = 12; //Jugar
                            //removeKeyListener(tmenu);
                            System.out.println("Caimos en el case 0");
                            initBoard();
                            break;
                        case 1:
                            System.out.println("Caimos en el case 1");
                            gameState = 2; //Tienda
                            // removeKeyListener(tmenu);
                            initBoard();
                            break;
                        case 2:
                            System.out.println("Caimos en el case 2");
                            gameState = 3; //Puntajes
                            // removeKeyListener(tmenu);
                            initBoard();
                            break;
                        case 3:

                            gameState = 4; //Salida
                            //  removeKeyListener(tmenu);
                            System.exit(0);

                            break;
                        default:
                            System.out.println("Caimos en el case default");
                            break;
                    }
                    break;
                default:
                    break;
            }

        }

    }

    private class TStoreAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case com.sun.glass.events.KeyEvent.VK_DOWN:
                    currentStoreSelection++;
                    if (currentStoreSelection >= StoreOptions.length) {
                        currentStoreSelection = 0;

                    }
                    break;
                case com.sun.glass.events.KeyEvent.VK_UP:
                    currentStoreSelection--;
                    if (currentStoreSelection < 0) {
                        currentStoreSelection = StoreOptions.length - 1;

                    }
                    break;
                case com.sun.glass.events.KeyEvent.VK_ENTER:
                    switch (currentStoreSelection) {
                        // Vida X1
                        case 0:
                            gameState = 12;
                            currentStoreSelection = 0;
                            vidaJugador = 1;
                            initBoard();
                            break;
                        // Vida X2
                        case 1:
                            gameState = 12;
                            currentStoreSelection = 0;
                            vidaJugador = 2;
                            initBoard();
                            break;
                        // Vida X3
                        case 2:
                            gameState = 12;
                            currentStoreSelection = 0;
                            vidaJugador = 3;
                            initBoard();
                            break;
                        // Bala X1
                        case 3:
                            gameState = 12;
                            currentStoreSelection = 0;
                            multMisiles = 1;
                            initBoard();

                            break;
                        // Bala X2
                        case 4:
                            gameState = 12;
                            currentStoreSelection = 0;
                            multMisiles = 2;
                            initBoard();

                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }

        }

    }

    private class TScoreAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ENTER) {
                gameState = 0;
                initBoard();
            }
        }
    }

    private class TNameAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ENTER) {

                NombreUsuario.add(txtNombreUsuario.getText());
                txtNombreUsuario.getParent().remove(txtNombreUsuario);
                user = txtNombreUsuario.getText();
                gameState = 1;
                initBoard();
            }
        }
    }
        
    private class TGameOverAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ENTER) {
                                
                vidas.getParent().remove(vidas);
                titulo.getParent().remove(titulo);
                puntos.getParent().remove(puntos);
                                   
                PuntajeUsuario.add(String.valueOf( misilesDestruidos));
                gameState = 0;
                initBoard();
            }
        }
    }

}
