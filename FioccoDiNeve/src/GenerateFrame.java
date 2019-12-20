import java.awt.Dimension;
import java.awt.Point;

/**
 * Questa classe contiene il fiocco generato.
 * 
 * @author Nathan Luè
 * @version 2019.12.17
 */
public class GenerateFrame extends javax.swing.JFrame {

    /**
     * Rappresenta i punti di tutti i poligoni sottoforma di percentuale.
     * Point[0][2] -> poligono 0 (il primo), 3o punto.
     */
    private Point[][] percentagePoints;
    
    /**
     * Rappresenta la dimensione minima applicabile in larghezza per il frame.
     */
    public static final int MIN_WIDTH_FRAME = 1024;
    
    /**
     * Rappresenta la dimensione minima applicabile in altezza per il frame.
     */
    public static final int MIN_HEIGHT_FRAME = 768;
    
    /**
     * Metodo costruttore della classe GenerateFrame.
     * 
     * @param percentagePoints I punti di tutti i poligoni sottoforma di percentuale.
     */
    public GenerateFrame(Point[][] percentagePoints) {
        initComponents();
        if(percentagePoints != null){
            this.percentagePoints = new Point[percentagePoints.length][];
            for(int i = 0 ; i < percentagePoints.length ; i++){
                this.percentagePoints[i] = new Point[percentagePoints[i].length];
                for(int j = 0 ; j < percentagePoints[i].length ; j++){
                    this.percentagePoints[i][j] = percentagePoints[i][j];
                }
            }
        }
        snowFlakePanel.setPercentagePoints(this.percentagePoints);
        this.setMinimumSize(new Dimension(MIN_WIDTH_FRAME, MIN_HEIGHT_FRAME));
        //Rende il frame non ridimensionabile.
        this.setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        snowFlakePanel = new SnowFlakePanel();
        backButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        savePngButton = new javax.swing.JButton();
        saveSvgButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout snowFlakePanelLayout = new javax.swing.GroupLayout(snowFlakePanel);
        snowFlakePanel.setLayout(snowFlakePanelLayout);
        snowFlakePanelLayout.setHorizontalGroup(
            snowFlakePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        snowFlakePanelLayout.setVerticalGroup(
            snowFlakePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        getContentPane().add(snowFlakePanel, java.awt.BorderLayout.CENTER);

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton, java.awt.BorderLayout.SOUTH);

        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        savePngButton.setText("Save image Png");
        savePngButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePngButtonActionPerformed(evt);
            }
        });
        jPanel1.add(savePngButton);

        saveSvgButton.setText("Save image Svg");
        saveSvgButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSvgButtonActionPerformed(evt);
            }
        });
        jPanel1.add(saveSvgButton);

        getContentPane().add(jPanel1, java.awt.BorderLayout.EAST);
        jPanel1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Si attiva alla pressione del pulsante backButton.
     * Permette di tornare al TriangleFrame, rendendo visibile il nuovo oggetto e rendendo invisibile questo.
     * 
     * @param evt Evento attivato.
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.setVisible(false);
        (new TriangleFrame()).setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * Si attiva alla pressione del pulsante savePngButton.
     * Permette di salvare il fiocco in un immagine Png richiamando il metodo all'interno di snowFlakePanel.
     * 
     * @param evt Evento attivato.
     */
    private void savePngButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePngButtonActionPerformed
        snowFlakePanel.savePngImage();
    }//GEN-LAST:event_savePngButtonActionPerformed

    private void saveSvgButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveSvgButtonActionPerformed
        snowFlakePanel.saveSvgImage();
    }//GEN-LAST:event_saveSvgButtonActionPerformed

    /**
     * Metodo principale della classe GenerateFrame.
     * 
     * @param args Array di stringhe.
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GenerateFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerateFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerateFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerateFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerateFrame(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton savePngButton;
    private javax.swing.JButton saveSvgButton;
    private SnowFlakePanel snowFlakePanel;
    // End of variables declaration//GEN-END:variables
}
