/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appserenity;


import javax.swing.JFrame;

public class Desplazamiento extends JFrame {

    public Desplazamiento() {
        initUI();
    }

    private void initUI() {
        Panel panel = new Panel();
        add(panel);
        setSize(600, 650);
        setResizable(false);
        panel.setLayout(null);
        setTitle("Serenity");

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
