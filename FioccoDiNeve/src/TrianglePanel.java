import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Questa classe rappresenta un modello astratto di triangolo.
 *
 * @author Luè Nathan
 * @version 2019.11.08
 */
public class TrianglePanel extends JPanel implements MouseListener, MouseMotionListener, PolygonListener {
    
    /**
     * Il raggio di ogni punto dei vertici di un poligono creato dall'utente.
     */
    public static final int POINT_RADIUS = 5;

    /**
     * Il rapporto di larghezza delle singole celle.
     */
    public static final int RATEO_WIDTH = 1;
    
    /**
     * Il rapporto di altezza delle singole celle.
     */
    public static final double RATEO_HEIGHT = Math.sqrt(3);
    
    /**
     * Il numero di righe del disegno.
     */
    public static final int ROWS = 1;
    
    /**
     * Il numero di colonne del disegno.
     */
    public static final int COLS = 1;
    
    /**
     * La distanza minima da mantere dai bordi.
     */
    public static final int MARGIN = 50;
    
    /**
     * Lista di poligoni.
     */
    private ArrayList<Polygon> polygons = new ArrayList();
    
    /**
     * Lista di punti del poligono creato dall'utente.
     */
    private ArrayList<Point> points = new ArrayList();
    
    /**
     * Il modello da quale prendere le misure per il responsive.
     */  
    public MatrixModel model;
    
    /**
     * La vecchia larghezza della finestra.
     */
    private int oldWidth = 0;
    
    /**
     * La vecchia finestra
     */
    private int oldHeight = 0;
    
    /**
     * Il poligono al quale vengono sottratti gli altri poligoni.
     */
    private java.awt.Polygon triangle;
    
    /**
     * Se bisogna attivare la preview mode (area sottratta), attiva (true) o disattvata (false).
     */
    private boolean preview = false;
    
    /**
     * Lo stato del checkbox: attivo (true), spento (false);
     */
    private boolean state = false;
    
    /**
     * Metodo getter dell'attributo state.
     * 
     * @return Lo stato.
     */
    public boolean getPreviewState(){
        return state;
    }
    
    /**
     * Metodo getter per l'attributo percentagePoints.
     * 
     * @return L'attributo percentagePoints.
     */
    public Point[][] getPercentagePoints(){
        Point[][] polygonPercentagePoints = new Point[this.polygons.size()][];
        for(int i = 0 ; i < this.polygons.size() ; i++){
            polygonPercentagePoints[i] = new Point[this.polygons.get(i).percentagePoints.size()];
            for(int j = 0 ; j < this.polygons.get(i).percentagePoints.size() ; j++){
                polygonPercentagePoints[i][j] = this.polygons.get(i).percentagePoints.get(j);
            }
        }
        return polygonPercentagePoints;
    }
    
    /**
     * L'area del triangolo.
     */
    private Area triangleArea = new Area();
    
    /**
     * Metodo getter dell'attributo triangleArea.
     * 
     * @return Ritorna l'attributo triangleArea.
     */
    public Area getTriangleArea(){
        return triangleArea;
    }
    
    /**
     * Metodo costruttore della classe trianglePanel.
     * Al suo interno si aggiungono il mouseMotionListener ed il MouseListener.
     */
    public TrianglePanel(){
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    /**
     * Disegna nel frame il triangolo completo dei vari poligoni disegnati dall'utente.
     * 
     * @param g Oggetto nel quale disegnare.
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.model = new MatrixModel(this.getWidth(), this.getHeight(), RATEO_WIDTH, RATEO_HEIGHT, ROWS, COLS, MARGIN);
        g.setColor(Color.gray);
        int[] xTrianglesPoints = {(int)this.model.getDX(),(int)(this.model.getDX()+this.model.getCellWidth()),(int)(this.model.getDX()+this.model.getCellWidth())};
        int[] yTrianglesPoints = {(int)this.model.getDY(),(int)this.model.getDY(),(int)(this.model.getDY()+this.model.getCellHeight())};
        triangle = new java.awt.Polygon(xTrianglesPoints, yTrianglesPoints, xTrianglesPoints.length);
        triangleArea = new Area(triangle);
        for(Polygon p : polygons){
            triangleArea.subtract(new Area(p.getPolygon()));
        }
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.gray);
        g2.fill(triangleArea);
        for(int i = 0 ; i < points.size() ; i++){
            g.setColor(Color.blue);
            if(i > 0){
                g.setColor(Color.black);
                g.drawLine((int)points.get(i - 1).getX(), (int)points.get(i - 1).getY(), (int)points.get(i).getX(), (int)points.get(i).getY());
                g.setColor(Color.blue);
                g.fillOval((int)points.get(i).getX() - POINT_RADIUS, (int)points.get(i).getY() - POINT_RADIUS, 2*POINT_RADIUS, 2*POINT_RADIUS);
                g.fillOval((int)points.get(i - 1).getX() - POINT_RADIUS, (int)points.get(i - 1).getY() - POINT_RADIUS, 2*POINT_RADIUS, 2*POINT_RADIUS);
            }else{
                g.fillOval((int)points.get(i).getX() - POINT_RADIUS, (int)points.get(i).getY() - POINT_RADIUS, 2*POINT_RADIUS, 2*POINT_RADIUS);
            }
        }
        for (int i = 0 ; i < polygons.size() ; i++) {
            if(this.getWidth() != this.oldWidth || this.getHeight() != this.oldHeight){
                polygons.get(i).newPointResized((double)this.getWidth(), (double)this.getHeight());
            }
            if(!preview){
                polygons.get(i).paintComponent(g);
            }
        }
        if(this.getWidth() != this.oldWidth || this.getHeight() != this.oldHeight){
            this.oldWidth = this.getWidth();
            this.oldHeight = this.getHeight();
        }
    }
    
    /**
     * Controlla se il punto corrente (curPoint) è contenuto all'interno dell'altro punto (polPoint).
     * 
     * @param curPoint Indica il punto corrente.
     * @param polPoint Rappresenta il punto del poligono.
     * 
     * @return True se il punto corrente è contenuto nel punto del poligono, False altrimenti.
     */
    public boolean pointContains(Point curPoint, Point polPoint){
        return (Math.abs(curPoint.getX() - polPoint.getX()) <= 2*POINT_RADIUS && Math.abs(curPoint.getY() - polPoint.getY()) <= 2*POINT_RADIUS);
    }
    
    /**
     * Salva i punti correnti dei poligoni in un file JSON.
     * Il percorso di salvataggio viene definito dall'utente, così come il nome del file.
     */
    public void saveButtons(){
        if(!this.polygons.isEmpty()){
            JFileChooser jfc = new JFileChooser("./");
            FileFilter ffJSON = new FileNameExtensionFilter("JSON", "json");
            jfc.setFileFilter(ffJSON);
            jfc.setAcceptAllFileFilterUsed(false);
            int value = jfc.showSaveDialog(null);
            if(value == JFileChooser.APPROVE_OPTION){
                File filechosed = jfc.getSelectedFile();
                String path = filechosed.getAbsolutePath();
                JSONObject polygonSave = new JSONObject();
                JSONArray pointsSave = new JSONArray();
                JSONArray xySave = new JSONArray();

                for(int i = 0 ; i < polygons.size() ; i++){
                    for(int j = 0 ; j < polygons.get(i).percentagePoints.size() ; j++){
                        xySave.add(polygons.get(i).percentagePoints.get(j).getX());
                        xySave.add(polygons.get(i).percentagePoints.get(j).getY());
                    }
                    polygonSave.put(i, xySave.clone());
                    pointsSave = new JSONArray();
                    xySave = new JSONArray();
                }
                try{

                    FileWriter file = new FileWriter(path+".json");
                    file.write(polygonSave.toJSONString());
                    file.close();
                }catch(IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Carica i punti dei poligoni da un file JSON selezionato dall'utente.
     */
    public void loadButtons(){
        JFileChooser jfc = new JFileChooser("./");
        FileFilter ffJSON = new FileNameExtensionFilter("JSON", "json");
        jfc.setFileFilter(ffJSON);
        jfc.setAcceptAllFileFilterUsed(false);
        int value = jfc.showOpenDialog(null);
        if(value == JFileChooser.APPROVE_OPTION){
            File filechosed = jfc.getSelectedFile();
            String path = filechosed.getAbsolutePath();
            JSONParser parser = new JSONParser();

            try (Reader reader = new FileReader(path)) {

                JSONObject jsonObject = (JSONObject) parser.parse(reader);
                System.out.println(jsonObject);
                polygons.clear();
                for(int i = 0 ; i < jsonObject.size() ; i++){
                    ArrayList<Double> xPoints = new ArrayList<>();
                    ArrayList<Double> yPoints = new ArrayList<>();
                    JSONArray pol = (JSONArray) jsonObject.get(""+i);
                    for(int j = 0 ; j < pol.size() ; j++){
                        if(j%2 == 0){
                            xPoints.add(Double.parseDouble(pol.get(j).toString()));
                        }else{
                            yPoints.add(Double.parseDouble(pol.get(j).toString()));
                        }
                    }
                    ArrayList<Point> points = new ArrayList<>();
                    for(int j = 0 ; j < xPoints.size() ; j++){
                        points.add(new Point((int)(xPoints.get(j).doubleValue() / 100 * this.getWidth()), (int)(yPoints.get(j).doubleValue() / 100 * this.getHeight())));
                    }
                    polygons.add(new Polygon(points, POINT_RADIUS));
                    for(int j = 0 ; j < polygons.get(i).points.size() ; j++){
                        polygons.get(i).percentagePoints.add(new Point((int)xPoints.get(j).doubleValue(), (int)yPoints.get(j).doubleValue()));
                    }
                }
                System.out.println(polygons.toString());
                repaint();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Inverte lo stato della preview: se attiva (true->false) se spenta (false->true).
     */
    public void previewCheckBox(){
        if(!polygons.isEmpty()){
            state = true;
            preview = !preview;
            for(Polygon p : polygons){
                p.setPreview(preview);
            }
            repaint();
        }else{
            state = false;
        }
    }
    
    /**
     * Cancella i poligoni salvati in polygons.
     */
    public void clearPolygons(){
        //ATTENZIONE con il checkbox
        polygons.clear();
        repaint();
    }
    
    /**
     * Completa (e crea) il poligono corrente modificato dall'utente, unendo l'ultimo punto con il primo.
     */
    public void completePolygon(){
        if(!preview){
            if(!points.isEmpty()){
                if(points.size() >= 3){
                    polygons.add(new Polygon((ArrayList)points.clone() , POINT_RADIUS));
                    this.polygons.get(polygons.size() - 1).addPolygonListener(this);
                    this.addMouseListener(this.polygons.get(polygons.size() - 1));
                    this.addMouseMotionListener(this.polygons.get(polygons.size() - 1));            
                    for(int i = 0 ; i < this.points.size() ; i++){
                        this.polygons.get(this.polygons.size() - 1).percentagePoints.add(new Point((int)(this.points.get(i).getX() * 100 / this.getWidth()), (int)(this.points.get(i).getY() * 100 / this.getHeight()))); 
                    }
                    points.clear();
                    repaint();
                }
            }
        }
    }
    
    /**
     * Si avvia quando un tasto del mouse viene cliccato.
     * Al suo interno vengono aggiunti i pligoni a polygons se i punti disegnati sono maggiori di tre e l'ultimo punto è uguale al primo.
     * 
     * @param e Evento scatenante.
     */
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1 && !preview){
            if(!points.isEmpty()){
                if(points.size() >= 3 && pointContains(e.getPoint(), points.get(0))){
                    polygons.add(new Polygon((ArrayList)points.clone() , POINT_RADIUS));
                    this.polygons.get(polygons.size() - 1).addPolygonListener(this);
                    this.addMouseListener(this.polygons.get(polygons.size() - 1));
                    this.addMouseMotionListener(this.polygons.get(polygons.size() - 1));
                    for(int i = 0 ; i < this.points.size() ; i++){
                        this.polygons.get(this.polygons.size() - 1).percentagePoints.add(new Point((int)(this.points.get(i).getX() * 100 / this.getWidth()), (int)(this.points.get(i).getY() * 100 / this.getHeight()))); 
                    }
                    points.clear();
                    repaint();
                }else{
                    points.add(e.getPoint());
                    repaint();
                }
            }else{
                points.add(e.getPoint());
                repaint();
            }
        }
    }
    
    /**
     * Si avvia quando un tasto del mouse viene premuto.
     * 
     * @param e Evento scatenante.
     */
    public void mousePressed(MouseEvent e) {
    
    }

    /**
     * Si avvia quando un tasto del mouse viene rilasciato.
     * 
     * @param e Evento scatenante.
     */
    public void mouseReleased(MouseEvent e) {
        
    }

    /**
     * Si avvia quando il cursore del mouse entra nella finestra.
     * 
     * @param e Evento scatenante.
     */
    public void mouseEntered(MouseEvent e) {
        
    }

    /**
     * Si avvia quando il cursore del mouse esce dalla finestra.
     * 
     * @param e Evento scatenante.
     */
    public void mouseExited(MouseEvent e) {
        
    }

    /**
     * Si avvia quando il cursore del mouse viene trascinato (premuto e mosso).
     * 
     * @param e Evento scatenante.
     */
    public void mouseDragged(MouseEvent e) {
        
    }
    
    /**
     * Si avvia quando il cursore del mouse viene mosso.
     * 
     * @param e Evento scatenante.
     */     
    public void mouseMoved(MouseEvent e) {
        
    }

    /**
     * Si attiva quando un punto viene rimosso.
     * Al suo interno si fa il repaint del panel.
     */
    public void pointRemoved() {
        
        for(int i = 0 ; i < polygons.size() ; i++){
            polygons.get(i).newPointResized(this.getWidth(), this.getHeight());
        }
        repaint();
    }
    
    /**
     * Si attiva quando un punto viene trascinato.
     * Al suo interno si fa il repaint del panel.
     */
    public void pointDragged(){
        
        for(int i = 0 ; i < polygons.size() ; i++){
            polygons.get(i).newPointResized(this.getWidth(), this.getHeight());
        }
        repaint();
    }
    
    /**
     * Sia attiva quando un punto viene ridimensionato.
     * Al suo interno si fa il repaint del panel.
     */
    public void pointResized(){
        
        repaint();
    }
}
