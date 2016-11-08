/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrenceBarcoYCoches;


import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Erik
 */
public class Lienzo extends javax.swing.JPanel implements Runnable {

    /**
     * Creates new form Lienzo
     */
    public static ArrayList<Barco> arrayBarcos = new ArrayList<>();
    public static ArrayList<Coche> arrayCoches = new ArrayList<>();
    int salidaBarcos = 0;

    ArrayList<Color> colores;

    public Lienzo() {
        initComponents();

        colores = new ArrayList<>();
        colores.add(Color.BLACK);
        colores.add(Color.BLUE);
        colores.add(Color.YELLOW);
        colores.add(Color.CYAN);
        colores.add(Color.DARK_GRAY);
        colores.add(Color.GREEN);
        colores.add(Color.LIGHT_GRAY);
        colores.add(Color.ORANGE);
        colores.add(Color.RED);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 942, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void paint(Graphics g) {
        /* color del fondo*/
        g.setColor(new Color(246, 236, 236));
        /* tamaño del fondo*/
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.DARK_GRAY);
        /* DIBUJANDO A  */
        g.fillRect(500, 10, 10, 250);
        /* DIBUJANDO B */
        g.fillRect(600, 10, 10, 250);
        /* DIBUJANDO C*/
        g.fillRect(10, 260, 500, 10);


        /*dibujando E*/
        g.fillRect(610, 260, 500, 10);
        /* dibujando F*/
        g.fillRect(10, 310, 500, 10);
        /* dibujando H*/
        g.fillRect(610, 310, 500, 10);
        /* dibujando I*/
        g.fillRect(500, 310, 10, 250);
        /* dibujando J*/
        g.fillRect(600, 310, 10, 250);

        if (llegoBarco){
            g.setColor(Color.RED);
        }
        /*dibujando  D*/
        g.fillRect(510, 260, 100, 10);
        /* dibujando G*/
        g.fillRect(510, 310, 100, 10);

        /* dibujando barcos */
        for (int i = 0; i < arrayBarcos.size(); i++) {
            arrayBarcos.get(i).setGraphics(g);
            arrayBarcos.get(i).getGraphics().setColor(arrayBarcos.get(i).getColor());
            g.fillOval(arrayBarcos.get(i).getCordenadaX(),
                    arrayBarcos.get(i).getCordenadaY(),
                    30, 30);
        }
        /* dibujando carritos */
        for (int i = 0; i < arrayCoches.size(); i++) {
            arrayCoches.get(i).setGraphics(g);
            arrayCoches.get(i).getGraphics().setColor(arrayCoches.get(i).getColor());
            g.fillOval(arrayCoches.get(i).getCordenadaX(),
                    arrayCoches.get(i).getCordenadaY(),
                    30, 30);
        }
        // System.out.println("repaint tamaño : "+mujers.size());
    }

    static Semaforo puenteLibre = new Semaforo(1); // true
    static Semaforo mutex = new Semaforo(1);
    /* variable */
    static boolean llegoBarco = false;
    static int intBarco = 0;
    static boolean carrosEnPuente = false;
    /* consumidor para la salida de los varones */
    @Override
    public void run() {
        int i = 0;

        while (true) {
            try {

                repaint();
                generarCoches(i);
                salidaBarcos++;
                if (salidaBarcos % 4 == 0) {
                    generarBarcos(intBarco);
                    intBarco++;
                }

                //c.start();
                Thread.sleep(3000);
                i++;
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Lienzo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void iniciarTodo() {
        for (int i = 0; i < arrayBarcos.size(); i++) {
            arrayBarcos.get(i).start();
        }
    }

    public void generarCoches(int i) {
        arrayCoches.add(new Coche(0,
                this,
                null,
                50,// coordenada X
                275,// coordenada Y
                15,//delay
                colores.get((int) (Math.random() * colores.size()))
        ));

        arrayCoches.get(i).start();

    }

    public void generarBarcos(int i) {
        arrayBarcos.add(new Barco(0,
                this,
                null,
                550,// coordenada X
                110,// coordenada Y
                70,//delay
                colores.get((int) (Math.random() * colores.size()))
        ));
        arrayBarcos.get(i).start();

    }

}