import java.awt.Dimension;

/**
 * Questa classe rappresenta la home page del programma.
 * 
 * @author Nathan Luè
 * @version 2019.12.13
 */
public class HomeFrame extends javax.swing.JFrame {

    /**
     * Rappresenta la dimensione minima applicabile in larghezza per il frame.
     */
    public static final int MIN_WIDTH_FRAME = 1024;
    
    /**
     * Rappresenta la dimensione minima applicabile in altezza per il frame.
     */
    public static final int MIN_HEIGHT_FRAME = 768;
    
    /**
     * Metodo costruttore della classe HomeFrame.
     * Al suo interno si setta la dimensione minima del frame su 1024x768.
     */
    public HomeFrame() {
        initComponents();
        this.setSize(MIN_WIDTH_FRAME, MIN_HEIGHT_FRAME);
        this.setMinimumSize(new Dimension(MIN_WIDTH_FRAME, MIN_HEIGHT_FRAME));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        createSnowFlake = new javax.swing.JButton();
        aboutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/download.png"))); // NOI18N
        jLabel1.setText("Benvenuto nel programma Fiocco di Neve");
        jLabel1.setToolTipText("Clicca \"Crea il tuo Fiocco\" per iniziare");
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.pink, java.awt.Color.lightGray, java.awt.Color.black, java.awt.Color.darkGray));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1, java.awt.BorderLayout.CENTER);

        jLabel2.setText("By Nathan Luè");
        jPanel1.add(jLabel2, java.awt.BorderLayout.SOUTH);

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));
        jPanel2.setPreferredSize(new java.awt.Dimension(100, 210));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel3.setBackground(new java.awt.Color(0, 204, 204));
        jLabel3.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SnowFlake Generator");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setMaximumSize(new java.awt.Dimension(310, 290));
        jLabel3.setMinimumSize(new java.awt.Dimension(310, 290));
        jLabel3.setPreferredSize(new java.awt.Dimension(310, 290));
        jPanel2.add(jLabel3, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        createSnowFlake.setBackground(new java.awt.Color(0, 255, 255));
        createSnowFlake.setText("Crea il tuo Fiocco");
        createSnowFlake.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        createSnowFlake.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createSnowFlakeActionPerformed(evt);
            }
        });
        getContentPane().add(createSnowFlake, java.awt.BorderLayout.SOUTH);

        aboutButton.setText("About");
        aboutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutButtonActionPerformed(evt);
            }
        });
        getContentPane().add(aboutButton, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Si attiva alla pressione del pulsante createSnowFlake.
     * Questo bottone rende visibile il frame per la creazione del fiocco.
     * 
     * @param evt Evento attivato.
     */
    private void createSnowFlakeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createSnowFlakeActionPerformed
        this.setVisible(false);
        (new TriangleFrame()).setVisible(true);
    }//GEN-LAST:event_createSnowFlakeActionPerformed

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutButtonActionPerformed
        this.setVisible(false);
        (new AboutFrame()).setVisible(true);
    }//GEN-LAST:event_aboutButtonActionPerformed

    /**
     * Metodo principale della classe HomeFrame.
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
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aboutButton;
    private javax.swing.JButton createSnowFlake;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
