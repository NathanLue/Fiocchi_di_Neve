import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;
import javax.swing.JPanel;


/**
 * Questa classe rappresenta un modello astratto di generatore di fiocco di neve.
 *
 * @author Luè Nathan
 * @version 2019.12.13
 */
public class PreviewSnowFlakePanel extends JPanel {
    
    /**
     * Il modello da quale prendere le misure per il responsive.
     */    
    public MatrixModel model;
    
    /**
     * Il poligono al quale vengono sottratti gli altri poligoni.
     */
    private java.awt.Polygon triangle;
    
    /**
     * Lista di poligoni da sottrarre al triangolo iniziale.
     */
    private ArrayList<Polygon> polygons = new ArrayList();
    
    /**
     * L'area del triangolo.
     */
    private Area triangleArea;
    
    /**
     * Rappresenta i punti di tutti i poligoni sottoforma di percentuale.
     * Point[0][2] -> poligono 0 (il primo), 3o punto.
     */
    private Point[][] percentagePoints;
    
    /**
     * Coordinate dei punti sull'asse delle x del triangolo.
     */
    private int[] xTrianglesPoints = new int[3];
    
    /**
     * Coordinate dei punti sull'asse delle y del triangolo.
     */
    private int[] yTrianglesPoints = new int[3];
    
    /**
     * Metodo setter oper l'attributo percentagePoints.
     * 
     * @param percentagePoints I punti di tutti i poligoni sottoforma di percentuale.
     */
    public void setPercentagePoints(Point[][] percentagePoints){
        if(percentagePoints != null){
            this.percentagePoints = new Point[percentagePoints.length][];
            for(int i = 0 ; i < percentagePoints.length ; i++){
                this.percentagePoints[i] = new Point[percentagePoints[i].length];
                for(int j = 0 ; j < percentagePoints[i].length ; j++){
                    this.percentagePoints[i][j] = percentagePoints[i][j];
                }
            }
        }
    }
    
    /**
     * Si occupa di creare ed agiungere alla lista di polygons. I punti vengono salvati calcolando la percentuale.
     */
    public void createPolygons(){
        if(percentagePoints != null){
            polygons.clear();
            ArrayList<Point> polygonPoints = new ArrayList<>();
            for(int i = 0 ; i < this.percentagePoints.length ; i++){
                for(int j = 0 ; j < this.percentagePoints[i].length; j++){
                    polygonPoints.add(new Point(
                        (int)(this.percentagePoints[i][j].getX() * (double)this.getWidth() / 100), 
                        (int)(this.percentagePoints[i][j].getY() * (double)this.getHeight() / 100)
                    ));  
                }
                polygons.add(new Polygon((ArrayList<Point>)polygonPoints.clone(), TrianglePanel.POINT_RADIUS));
                polygonPoints.clear();
            }
        }
    }
    
    /**
     * Metodo per disegnare nel panel.
     * 
     * @param g Elemento nel quale disegnare.
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.gray);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.model = new MatrixModel(this.getWidth(), this.getHeight(), TrianglePanel.RATEO_WIDTH, TrianglePanel.RATEO_HEIGHT, TrianglePanel.ROWS, TrianglePanel.COLS, TrianglePanel.MARGIN);
        g.setColor(Color.gray);
        xTrianglesPoints[0] = (int)this.model.getDX();
        xTrianglesPoints[1] = (int)(this.model.getDX()+this.model.getCellWidth());
        xTrianglesPoints[2] = (int)(this.model.getDX()+this.model.getCellWidth());
        yTrianglesPoints[0] = (int)this.model.getDY();
        yTrianglesPoints[1] = (int)this.model.getDY();
        yTrianglesPoints[2] = (int)(this.model.getDY()+this.model.getCellHeight());
        triangle = new java.awt.Polygon(xTrianglesPoints, yTrianglesPoints, xTrianglesPoints.length);
        createPolygons();
        triangleArea = new Area(triangle);
        for(Polygon p : polygons){
            g.setColor(Color.orange);
            triangleArea.subtract(new Area(p.getPolygon()));
        }
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.blue);
        generateSnowFlake(g2, (int)(this.getWidth()/2 - this.model.getCellWidth()), (int)(this.getHeight()/2 - this.model.getCellHeight()), 0.7);
    }
    
    /**
     * Genera il fiocco di neve (6 triangoli ruotati).
     * 
     * @param g2d Oggetto grafico.
     * @param xMovement Spostamento sull'asse delle x.
     * @param yMovement Spostamento sull'asse delle y.
     * @param rateo Rapporto di grandezza.
     */
    public void generateSnowFlake(Graphics2D g2d, int xMovement, int yMovement, double rateo) {
        for (int i = 0; i < 6; i++) {
            AffineTransform tr2 = new AffineTransform();
            tr2.scale(rateo, rateo);
            tr2.translate(xMovement, yMovement);
            tr2.rotate(Math.toRadians(60) * i, this.xTrianglesPoints[2], this.yTrianglesPoints[2]);
            g2d.setTransform(tr2);
            g2d.fill(triangleArea);

            AffineTransform tr3 = new AffineTransform();
            tr3.scale(-rateo, rateo);
            tr3.translate((-this.xTrianglesPoints[2] * 2) - xMovement, yMovement);
            tr3.rotate(Math.toRadians(60) * i, this.xTrianglesPoints[2], this.yTrianglesPoints[2]);
            g2d.setTransform(tr3);
            g2d.fill(triangleArea);
        }
    }
}
