package modeloqytetet;

/**
 *
 * @author josu
 */
public class Especulador extends Jugador {
    
    //Atributos
    private int fianza;
    
    //Constructor
    public Especulador(String nom) {
        super(nom);
    }
    
    //Constructor de copia
    protected Especulador(Jugador jug, int fian){
        super(jug);
        fianza = fian;
    }
    
    
    @Override
    protected void pagarImpuesto() {
        int cantidad = (int) (getCasillaActual().getCoste()/2);
        modificarSaldo(-cantidad);
    }
    
    @Override
    protected Especulador convertirme(int fianza) {
        return this;
    }

    @Override
    protected boolean deboIrACarcel() {
        if(tengoCartaLibertad() && !pagarFianza()){
            return true;
        }
        return false;
    }
    
    private boolean pagarFianza(){
        if(tengoSaldo(fianza)){
            modificarSaldo(-fianza);
            return true;
        }
        return false;
    }

    @Override
    protected boolean puedoEdificarCasa(TituloPropiedad titulo){
        if(tengoSaldo(titulo.getPrecioEdificar())){
            if(titulo.getNumCasas() < 8){
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    protected boolean puedoEdificarHotel(TituloPropiedad titulo){
        if(tengoSaldo(titulo.getPrecioEdificar())){
            if(titulo.getNumCasas() >= 4 && titulo.getNumHoteles() < 8){
                return true;
            }
        }
        
        return false;
    }
    
    @Override 
    public String toString(){
        String info = super.toString();
        info = info + "Especulador, Fianza: " + fianza;
        
        return info;
    }
    
}
