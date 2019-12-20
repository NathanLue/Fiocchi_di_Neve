import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Questa classe rappresenta un modello astratto di poligono.
 *
 * @author Luè Nathan
 * @version 2019.11.08
 */
public class Polygon implements MouseListener, MouseMotionListener {
    
    /**
     * Rappresenta il raggio di un punto presente nella lista points.
     */
    private int point_radius = 5;
    
    /**
     * Indica i punti che compongono il poligono.
     */
    public ArrayList<Point> points = new ArrayList();
    
    /**
    * Lista di ascoltatori (listeners).
    */
    private ArrayList<PolygonListener> listeners = new ArrayList();
    
    /**
     * Indice del punto che viene trascinato.
     */
    private int indexPointDragged = 0;
    
    /**
     * Se un punto viene trascinato (true) altrimenti (false).
     */
    private boolean drag = false;
    
    /**
     * Lista di punti salvati in percentuale, utili per il responsive.
     */
    public ArrayList<Point> percentagePoints = new ArrayList();
    
    /**
     * Se un punto del poligono è cambiato (true) oppure no (false).
     */
    private boolean pointChanged = false;
    
    /**
     * La dimensione del pannello (larghezza).
     */
    private double width;
    
    /**
     * La dimensione del pannello (altezza).
     */
    private double height;
    
    /**
     * Se bisogna attivare la preview mode (area sottratta), attiva (true) o disattvata (false).
     */
    private boolean preview = false;
    
    /**
     * Metodo setter dell'attributo preview
     * 
     * @param preview Modalità preview attiva (true) o spenta (false).
     */
    public void setPreview(boolean preview){
        this.preview = preview;
    }
    
    /**
     * Crea un java.awt.Polygon.
     * 
     * @return Il poligono.
     */
    public java.awt.Polygon getPolygon(){
        int[] xPoints = new int[points.size()];
        int[] yPoints = new int[points.size()];
        for(int i = 0 ; i < points.size() ; i++){
            xPoints[i] = points.get(i).x;
            yPoints[i] = points.get(i).y;
        }
        return new java.awt.Polygon(xPoints, yPoints, points.size());
    }
    
    /**
     * Costruttore della classe Polygon, al suo interno si ricava la lista di punti.
     * 
     * @param points I punti del poligono.
     * @param point_radius La dimensione del raggio dei punti del poligono.
     */
    public Polygon(ArrayList<Point> points, int point_radius){
        if(points.size() >= 3){
            this.points = points;
            this.point_radius = point_radius;
        }
    }
    
    /**
     * Disegna nel frame il poligono.
     * 
     * @param g Oggetto nel quale disegnare.
     */
    public void paintComponent(Graphics g){
        int[] x = new int[points.size()];
        int[] y = new int[points.size()];
        for(int i = 0 ; i < points.size() ; i++){
            x[i] = (int)points.get(i).getX();
            y[i] = (int)points.get(i).getY();
        }
        g.setColor(Color.blue);
        g.fillPolygon(x, y, points.size());
        g.setColor(Color.black);
        for(int i = 0 ; i < points.size() ; i++){
            g.fillOval((int)points.get(i).getX() - point_radius, (int)points.get(i).getY() - point_radius, 2*point_radius, 2*point_radius);
        }
    }
    
    /**
     * Aggiunge un ascoltatore alla lista listeners.
     *
     * @param listener Elemento da aggiungere a listeners.
     */
    public void addPolygonListener(PolygonListener listener){
        listeners.add(listener);
    }

    /**
     * Rimuove un ascoltatore dalla lista listeners.
     *
     * @param listener Elemento da aggiungere a listeners.
     */
    public void removePolygonListener(PolygonListener listener){
        listeners.remove(listener);
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
        return (Math.abs(curPoint.getX() - polPoint.getX()) <= 2*point_radius && Math.abs(curPoint.getY() - polPoint.getY()) <= 2*point_radius);
    }
    
    /**
     * Calcola la percentuale di distanza nella finestra di ogni punto del poligono.
     * Utilizzato per il responsive dei punti.
     * 
     * @param width La larghezza della finestra.
     * @param height L'altezza della finestra.
     */
    public void newPointResized(double width, double height){
        System.out.println("");
            if(!this.percentagePoints.isEmpty()){
            this.width = width;
            this.height = height;
            if(pointChanged){
                    this.percentagePoints.set(indexPointDragged, new Point((int)(this.points.get(indexPointDragged).getX() * 100 / width), (int)(this.points.get(indexPointDragged).getY() * 100 / height)));
                    System.out.println(height);
                    pointChanged = false;
            }
            for(int i = 0 ; i < this.points.size() ; i++){
                this.points.set(i, new Point(
                        (int)(this.percentagePoints.get(i).getX() / 100 * width), 
                        (int)(this.percentagePoints.get(i).getY() / 100 * height)
                ));
            }
            for(PolygonListener element : listeners){
                element.pointResized();
            }
        }
    }

    /**
     * Si avvia quando un tasto del mouse viene cliccato.
     * Se si clicca con il tasto destro, viene rimosso il punto cliccato.
     * 
     * @param e Evento scatenante.
     */
    public void mouseClicked(MouseEvent e) {
        if(!this.preview){
            //Se premuto il tasto destro, si rimuove il punto
            int index = 0;
            boolean flag = false;
            if(e.getButton() == MouseEvent.BUTTON3){
                for(int i = 0 ; i < points.size() ; i++){
                    if(pointContains(e.getPoint(), points.get(i))){
                        if(points.size() > 3){
                            index = i;
                            flag = true;
                        }
                        break;
                    }
                }
                if(flag){
                    points.remove(index);
                    percentagePoints.remove(index);
                    for(PolygonListener element : listeners){
                        element.pointRemoved();
                    }
                }
            }
        }
    }

    /**
     * Si avvia quando un tasto del mouse viene premuto.
     * 
     * @param e Evento scatenante.
     */
    public void mousePressed(MouseEvent e) {
        if(!this.preview){
            for(int i = 0 ; i < points.size() ; i++){
                if(pointContains(e.getPoint(), points.get(i))){
                    indexPointDragged = i;
                    drag = true;
                }
            }
        }
    }

    /**
     * Si avvia quando un tasto del mouse viene rilasciato.
     * 
     * @param e Evento scatenante.
     */
    public void mouseReleased(MouseEvent e) {
        if(!this.preview){
            if(drag){
                drag = false;
            }
        }
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
        if(!this.preview){
            if(drag){
                points.set(indexPointDragged, new Point(e.getPoint()));
                pointChanged = true;
                for(PolygonListener element : listeners){
                    element.pointDragged();
                }
            }
        }
    }

    /**
     * Si avvia quando il cursore del mouse viene mosso.
     * 
     * @param e Evento scatenante.
     */
    public void mouseMoved(MouseEvent e) {
        
    }
}
