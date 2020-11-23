package modeloqytetet;

/**
 *
 * @author josu
 */
public abstract class Casilla {
    
    //Atributos
    private int numeroCasilla;
    private int coste;
    
    //De Referencia
    
    
    //Constructor Generico
    protected Casilla(int numCas){
        this.numeroCasilla = numCas;
        this.coste = 0;
    }
    
    
    //Consultores
    protected int getCoste(){return this.coste;}
    
    public int getNumeroCasilla(){return this.numeroCasilla;}
    
    protected abstract TipoCasilla getTipo();
    
    protected abstract TituloPropiedad getTitulo();
    
    public void setCoste(int cos){this.coste = cos;}
    
    protected abstract boolean soyEdificable();
    
    protected abstract boolean tengoPropietario();
    
    @Override
    public abstract String toString();
}
