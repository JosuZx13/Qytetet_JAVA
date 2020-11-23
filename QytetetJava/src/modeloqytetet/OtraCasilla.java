package modeloqytetet;

/**
 *
 * @author josu
 */
public class OtraCasilla extends Casilla {
    
    TipoCasilla tipoCasilla;
    
    public OtraCasilla(int numCasilla, TipoCasilla tip){
        super(numCasilla);
        tipoCasilla = tip;

        if(tip == TipoCasilla.IMPUESTO){
            setCoste(Qytetet.IMPUESTO);
        }
    }

    @Override
    protected TipoCasilla getTipo() {
        return tipoCasilla;
    }

    @Override
    protected TituloPropiedad getTitulo() {
        return null;
    }

    @Override
    protected boolean soyEdificable() {
        return false;
    }
    
    @Override
    protected boolean tengoPropietario() {
        return false;
    }
    
    @Override
    public String toString() {
        String salida =  "Casilla\t{Numero: " + getNumeroCasilla() + "\t Tipo de Casilla: " + tipoCasilla + '}';
        
        return salida;
    }

}
