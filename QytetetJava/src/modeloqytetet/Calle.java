package modeloqytetet;

/**
 *
 * @author josu
 */
public class Calle extends Casilla{
    
    //Atributos
    TipoCasilla tipoCasilla;
    TituloPropiedad titulo;
    
    
    //Constructor por parámetros, usando el de la clase que hereda
    public Calle(int numCas, TituloPropiedad tit) {
        super(numCas);
        titulo = tit;
        setCoste(tit.getPrecioCompra());
        tipoCasilla = TipoCasilla.CALLE;
    }
    
    
    //CONSULTORES
    
    @Override
    protected TipoCasilla getTipo() {
        return this.tipoCasilla;
    }

    @Override
    protected TituloPropiedad getTitulo() {
        return this.titulo;
    }
    
    
    //MODIFICADORES
    
    private void setTitulo(TituloPropiedad tit){
        this.titulo = tit;
    }
    
    
    //METODOS
    
    protected TituloPropiedad asignarPropietario(Jugador jug){
        this.titulo.setPropietario(jug);
        return this.titulo;
    }
    
    public int pagarAlquiler(){
        int costeAlquiler = this.titulo.pagarAlquiler();
        
        return costeAlquiler;
    }
    
    @Override
    protected boolean soyEdificable(){
        return this.tipoCasilla == TipoCasilla.CALLE;
    }
    
    @Override
    public boolean tengoPropietario(){
        return this.titulo.tengoPropietario();
    }
    
    /*protected boolean propietarioEncarcelado(){
        return this.tituloPropiedad.propietarioEncarcelado();
    }*/
    
    
    @Override
    public String toString() {
        String salida =  "Calle\t{Numero: " + getNumeroCasilla() + "\t Tipo de Casilla: " + tipoCasilla + "\t\tCoste: " + getCoste() + "\t" + titulo.toString() + '}';
        
        return salida;
    }
    
}
