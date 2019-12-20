/**
 * Questa classe rappresenta un modello astratto di
 *
 * @author Luè Nathan
 * @version 2019.11.15
 */
public class MatrixModel {

    /**
     * La larghezza delle singole celle.
     */
    private static double cellW;

    /**
     * L'altezza delle singole celle.
     */
    private static  double cellH;

    /**
     * La coordinata X da cui partire per avere un disegno centrato.
     */
    private double dX;

    /**
     * La coordinata Y da cui partire per avere un disegno centrato.
     */
    private double dY;

    /**
     * Istanzia un MatrixModel definendo tutte le variabili del disegno.
     *
     * @param w La larghezza totale della finestra.
     * @param h L'altezza totale della finestra.
     * @param ratioW Il rapporto di larghezza delle singole celle.
     * @param ratioH Il rapporto di altezza delle singole celle.
     * @param row Il numero di righe del disegno.
     * @param col Il numero di colonne del disegno.
     * @param margin La distanza minima da mantere dai bordi.
     */
    public MatrixModel(double w, double h, double ratioW, double ratioH, int row, int col, double margin) {
            update(w, h, ratioW, ratioH, row, col, margin);
    }

    /**
     * Calcola le coordinate di origine e le dimensioni massime del disegno.
     *
     * @param w La larghezza totale della finestra.
     * @param h L'altezza totale della finestra.
     * @param ratioW Il rapporto di larghezza delle singole celle.
     * @param ratioH Il rapporto di altezza delle singole celle.
     * @param row Il numero di righe del disegno.
     * @param col Il numero di colonne del disegno.
     * @param margin La distanza minima da mantere dai bordi.
     */
    public void update(double w, double h, double ratioW, double ratioH, int col, int row, double margin) {
            double width = 0;
            double height = 0;
            if ((float)(w - 2 * margin) / (float)(h - 2 * margin) > (float)(col * ratioW) / (float)(row * ratioH)) {
                    dY = margin;
                    height = h - 2 * margin;
                    cellH = height / row;
                    cellW = cellH * ratioW / ratioH;
                    width = cellW * col;
                    dX = (w - width) / 2;
            } else {
                    dX = margin;
                    width = w - 2 * margin;
                    cellW = width / col;
                    cellH = cellW * ratioH / ratioW;
                    height = cellH * row;
                    dY = (h - height) / 2;
            }
    }

    /**
     * Getter per la coordinata x del disegno.
     *
     * @return La coordinata x del disegno.
     */
    public double getDX() {
            return dX;
    }

    /**
     * Getter per la coordinata y del disegno.
     *
     * @return La coordinata y del disegno.
     */
    public double getDY() {
            return dY;
    }

    /**
     * Getter per la larghezza delle singole celle del disegno.
     *
     * @return La larghezza delle singole celle del disegno.
     */
    public double getCellWidth() {
            return cellW;
    }

    /**
     * Getter per l'altezza delle singole celle del disegno.
     *
     * @return L'altezza delle singole celle del disegno.
     */
    public double getCellHeight() {
            return cellH;
    }
}
