/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import concurrencebarco.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Erik
 */
public class Barco extends Thread {

    private Color color;
    /* default*/
    private int coordenadaX;
    private int coordenadaY;
    private JPanel panel;
    private Graphics graphics;
    private int index;
    /* delay*/
    private int delay;

    public Barco(int index,
            JPanel panel,
            Graphics graphics,
            int coordenadaX,
            int coordenadaY,
            int delay,
            Color color
    ) {
        this.panel = panel;
        this.graphics = graphics;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.index = index;
        this.delay = delay;
        this.color = color;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;

    }

    public int getCordenadaX() {
        return coordenadaX;
    }

    public void setCordenadaX(int cordenadaX) {
        this.coordenadaX = cordenadaX;

    }

    public int getCordenadaY() {
        return coordenadaY;
    }

    public void setCordenadaY(int cordenadaY) {
        this.coordenadaY = cordenadaY;

    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    public void run() {
        while (true) {
            try {
                avanzar();
                if (llegoAlPuente()) {
                    Lienzo.puenteLibre.Wait();
                    while (!salioDelPuente()) {                        
                        avanzar();
                        sleep(delay);
                    }
                    System.out.println("puente ocupado");
                    Lienzo.puenteLibre.Signal();
                }
                /*repintar*/
                sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(Barco.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void abrirPuente() {
        System.out.println("puente abierto ");
    }

    private void avanzar() {
        coordenadaY = coordenadaY + 1;
        getPanel().repaint();
    }

    private void cerrarPuente() {
        System.out.println("cerrar puente");
    }
    public boolean llegoALimite() {
        if (coordenadaY > 200 && coordenadaY < 370) {
            return true;
        }
        return false;
    }
    
    public boolean llegoAlPuente() {
        if (coordenadaY > 260 && coordenadaY < 310) {
            return true;
        }
        return false;
    }
    public boolean salioDelPuente(){
        if (coordenadaY>310) {
            return true;
        }
        return false;
    }
}
