
/**
 * Questa interfaccia è un ascoltatore di eventi di Polygon.
 *
 * @author Nathan Luè
 * @version 2019.11.08
 */
public interface PolygonListener {
    
    /**
     * Notifica la rimozione di un punto.
     */
    public void pointRemoved();
    
    /**
     * Notifica il trascinamento di un punto.
     */
    public void pointDragged();
    
    /**
     * Notifica il ridimensionamento di un punto.
     */
    public void pointResized();
    
}
