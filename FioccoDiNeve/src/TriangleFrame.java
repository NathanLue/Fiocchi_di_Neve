import java.awt.Dimension;

/**
 * Questa classe rappresenta il contenitore che permette di creare un fiocco di neve tramite tagli su un triangolo.
 * 
 * @author Nathan Luè
 * @version 2019.11.08
 */
public class TriangleFrame extends javax.swing.JFrame {
    
    /**
     * Rappresenta la dimensione minima applicabile in larghezza per il frame.
     */
    public static final int MIN_WIDTH_FRAME = 1024;
    
    /**
     * Rappresenta la dimensione minima applicabile in altezza per il frame.
     */
    public static final int MIN_HEIGHT_FRAME = 768;

    /**
     * Metodo costruttore della classe TriangleFrame.
     * Al suo interno si setta la dimensione minima del frame su 1024x768.
     */
    public TriangleFrame() {
        initComponents();
        this.setSize(MIN_WIDTH_FRAME, MIN_HEIGHT_FRAME);
        this.setMinimumSize(new Dimension(MIN_WIDTH_FRAME, MIN_HEIGHT_FRAME));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        trianglePanel = new TrianglePanel();
        menuPanel = new javax.swing.JPanel();
        savePointsButton = new javax.swing.JButton();
        loadPointsButton = new javax.swing.JButton();
        completePolygonButton = new javax.swing.JButton();
        clearPolygonsButton = new javax.swing.JButton();
        generateButton = new javax.swing.JButton();
        leftMenuPanel = new javax.swing.JPanel();
        previewCheckBox = new javax.swing.JCheckBox();
        previewSnowFlakeLabel = new javax.swing.JLabel();
        previewSnowFlakePanel = new PreviewSnowFlakePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        trianglePanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                trianglePanelMouseDragged(evt);
            }
        });
        trianglePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trianglePanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                trianglePanelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                trianglePanelMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout trianglePanelLayout = new javax.swing.GroupLayout(trianglePanel);
        trianglePanel.setLayout(trianglePanelLayout);
        trianglePanelLayout.setHorizontalGroup(
            trianglePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        trianglePanelLayout.setVerticalGroup(
            trianglePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );

        getContentPane().add(trianglePanel, java.awt.BorderLayout.CENTER);

        menuPanel.setLayout(new java.awt.GridLayout(9, 1));

        savePointsButton.setText("Save Points");
        savePointsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePointsButtonActionPerformed(evt);
            }
        });
        menuPanel.add(savePointsButton);

        loadPointsButton.setText("Load Points");
        loadPointsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadPointsButtonActionPerformed(evt);
            }
        });
        menuPanel.add(loadPointsButton);

        completePolygonButton.setText("Complete current polygon");
        completePolygonButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completePolygonButtonActionPerformed(evt);
            }
        });
        menuPanel.add(completePolygonButton);

        clearPolygonsButton.setText("Clear polygons");
        clearPolygonsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearPolygonsButtonActionPerformed(evt);
            }
        });
        menuPanel.add(clearPolygonsButton);

        generateButton.setText("Generate");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });
        menuPanel.add(generateButton);

        getContentPane().add(menuPanel, java.awt.BorderLayout.EAST);

        leftMenuPanel.setLayout(new java.awt.GridLayout(3, 1));

        previewCheckBox.setText("Preview");
        previewCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewCheckBoxActionPerformed(evt);
            }
        });
        leftMenuPanel.add(previewCheckBox);

        previewSnowFlakeLabel.setText("Preview Snow Flake");
        leftMenuPanel.add(previewSnowFlakeLabel);

        javax.swing.GroupLayout previewSnowFlakePanelLayout = new javax.swing.GroupLayout(previewSnowFlakePanel);
        previewSnowFlakePanel.setLayout(previewSnowFlakePanelLayout);
        previewSnowFlakePanelLayout.setHorizontalGroup(
            previewSnowFlakePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );
        previewSnowFlakePanelLayout.setVerticalGroup(
            previewSnowFlakePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );

        leftMenuPanel.add(previewSnowFlakePanel);

        getContentPane().add(leftMenuPanel, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Viene attivato alla pressione del pulsante savePointsButton.
     * Richiama il metodo per salvare i punti contenuto in trianglePanel.
     * 
     * @param evt Evento attivato.
     */
    private void savePointsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePointsButtonActionPerformed
        trianglePanel.saveButtons();
    }//GEN-LAST:event_savePointsButtonActionPerformed

    /**
     * Si attiva se viene premuto il pulsante loadPointsButton.
     * Richiama il metodo per caricare i punti contenuto in trianglePanel.
     * 
     * @param evt Evento attivato.
     */
    private void loadPointsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadPointsButtonActionPerformed
        trianglePanel.loadButtons();
    }//GEN-LAST:event_loadPointsButtonActionPerformed

    /**
     * Si attiva alla pressione del chekBox.
     * Richiama il metodo per attivare la preview mode contenuta in trianglePanel.
     * Inoltre esegue il repaint della classe previewSnowFlakePanel.
     * 
     * @param evt Evento attivato.
     */
    private void previewCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewCheckBoxActionPerformed
        previewSnowFlakePanel.repaint();
        trianglePanel.previewCheckBox();
        if(!trianglePanel.getPreviewState()){
            previewCheckBox.setSelected(false);
        }
    }//GEN-LAST:event_previewCheckBoxActionPerformed

    /**
     * Si attiva alla pressione del mouse nel trianglePanel.
     * "Passa" i punti (sotto forma di percentuali) dal trianglePanel al previewSnowFlakePanel.
     * Inoltre esegue un repaint della classe previewSnowFlake.
     * 
     * @param evt Evento attivato.
     */
    private void trianglePanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trianglePanelMousePressed
        previewSnowFlakePanel.setPercentagePoints(trianglePanel.getPercentagePoints());        
        previewSnowFlakePanel.repaint();
    }//GEN-LAST:event_trianglePanelMousePressed

    /**
     * Si attiva quando il form viene ridimensionato.
     * "Passa" i punti (sotto forma di percentuali) dal trianglePanel al previewSnowFlakePanel.
     * Inoltre esegue un repaint della classe previewSnowFlake.
     * 
     * @param evt Evento attivato.
     */
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        previewSnowFlakePanel.setPercentagePoints(trianglePanel.getPercentagePoints());
        previewSnowFlakePanel.repaint();
    }//GEN-LAST:event_formComponentResized

    /**
     * Si attiva quando il mouse viene cliccato all'interno del trianglePanel.
     * "Passa" i punti (sotto forma di percentuali) dal trianglePanel al previewSnowFlakePanel.
     * Inoltre esegue un repaint della classe previewSnowFlake.
     * 
     * @param evt Evento attivato.
     */
    private void trianglePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trianglePanelMouseClicked
        previewSnowFlakePanel.setPercentagePoints(trianglePanel.getPercentagePoints());        
        previewSnowFlakePanel.repaint();
    }//GEN-LAST:event_trianglePanelMouseClicked

    /**
     * Si attiva quando il mouse viene rilasciato all'interno del trianglePanel.
     * "Passa" i punti (sotto forma di percentuali) dal trianglePanel al previewSnowFlakePanel.
     * Inoltre esegue un repaint della classe previewSnowFlake.
     * 
     * @param evt Evento attivato.
     */
    private void trianglePanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trianglePanelMouseReleased
        previewSnowFlakePanel.setPercentagePoints(trianglePanel.getPercentagePoints());        
        previewSnowFlakePanel.repaint();
    }//GEN-LAST:event_trianglePanelMouseReleased

    /**
     * Si attiva quando il mouse viene trascinato all'interno del trianglePanel.
     * "Passa" i punti (sotto forma di percentuali) dal trianglePanel al previewSnowFlakePanel.
     * Inoltre esegue un repaint della classe previewSnowFlake.
     * 
     * @param evt Evento attivato.
     */
    private void trianglePanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trianglePanelMouseDragged
        previewSnowFlakePanel.setPercentagePoints(trianglePanel.getPercentagePoints());        
        previewSnowFlakePanel.repaint();
    }//GEN-LAST:event_trianglePanelMouseDragged

    /**
     * Si attiva alla pressione del pulsante clearPolygonsButton.
     * Richiama il metodo per cacellare tutti i poligoni presenti (contenuto in trianglePanel).
     * "Passa" i punti (sotto forma di percentuali) dal trianglePanel al previewSnowFlakePanel.
     * Inoltre esegue un repaint della classe previewSnowFlake.
     * 
     * @param evt Evento attivato.
     */
    private void clearPolygonsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearPolygonsButtonActionPerformed
        trianglePanel.clearPolygons();
        previewSnowFlakePanel.setPercentagePoints(trianglePanel.getPercentagePoints());        
        previewSnowFlakePanel.repaint();
    }//GEN-LAST:event_clearPolygonsButtonActionPerformed

    /**
     * Si attiva alla pressione del pulsante completePolygonButton.
     * Richiama il metodo per completare il poligono in automatico (presente in trianglePanel).
     * "Passa" i punti (sotto forma di percentuali) dal trianglePanel al previewSnowFlakePanel.
     * Inoltre esegue un repaint della classe previewSnowFlake.
     * 
     * @param evt Evento attivato.
     */
    private void completePolygonButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completePolygonButtonActionPerformed
        trianglePanel.completePolygon();
        previewSnowFlakePanel.setPercentagePoints(trianglePanel.getPercentagePoints());
        previewSnowFlakePanel.repaint();
    }//GEN-LAST:event_completePolygonButtonActionPerformed

    /**
     * Si attiva alla pressione del bottone generateButton.
     * Rende visibile il frame che contiene il fiocco generato.
     * Rende invisibile questo frame.
     * 
     * @param evt Evento attivato.
     */
    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        this.setVisible(false);
        (new GenerateFrame(trianglePanel.getPercentagePoints())).setVisible(true);
    }//GEN-LAST:event_generateButtonActionPerformed

    /**
     * Metodo principale della classe TriangleFrame.
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
            java.util.logging.Logger.getLogger(TriangleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TriangleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TriangleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TriangleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TriangleFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearPolygonsButton;
    private javax.swing.JButton completePolygonButton;
    private javax.swing.JButton generateButton;
    private javax.swing.JPanel leftMenuPanel;
    private javax.swing.JButton loadPointsButton;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JCheckBox previewCheckBox;
    private javax.swing.JLabel previewSnowFlakeLabel;
    private PreviewSnowFlakePanel previewSnowFlakePanel;
    private javax.swing.JButton savePointsButton;
    private TrianglePanel trianglePanel;
    // End of variables declaration//GEN-END:variables

}
