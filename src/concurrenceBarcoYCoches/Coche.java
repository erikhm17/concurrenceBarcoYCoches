/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrenceBarcoYCoches;


import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Erik hammer
 */
public class Coche extends Thread {

    private Color color;
    /* default*/
    private int coordenadaX;
    private int coordenadaY;
    private JPanel panel;
    private Graphics graphics;
    private int index;
    /* delay*/
    private int delay;

    public Coche(int index,
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
    private boolean terminar = true;

    @Override
    public void run() {
        while (true) {
            try {
                if (estaEnElPuente() ){
                    avanzar();
                    Lienzo.mutex.Wait();
                        Lienzo.carrosEnPuente = true;
                    Lienzo.mutex.Signal();
                }else if (salioDelPuente()){
                    avanzar();
                    Lienzo.mutex.Wait();
                        Lienzo.carrosEnPuente = false;
                    Lienzo.mutex.Signal();

                }else if (antesDelPuente()){
                        Lienzo.puenteLibre.Wait();
                        avanzar();
                        Lienzo.puenteLibre.Signal();
                }

                sleep(delay);

            } catch (InterruptedException ex) {
                Logger.getLogger(Barco.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    public void avanzar() {
        coordenadaX = coordenadaX + 1;
        getPanel().repaint();
    }

    public boolean antesDelPuente() {
        if (coordenadaX < 550) {
            return true;
        }
        return false;
    }

    private boolean salioDelPuente() {
        if (coordenadaX >= 650) {
            return true;
        }
        return false;
    }

    public boolean estaEnElPuente() {
        if (coordenadaX >= 550 && coordenadaX < 650) {
            return true;
        }
        return false;
    }
}
