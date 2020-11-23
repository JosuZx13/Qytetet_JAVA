package modeloqytetet;

/**
 *
 * @author josu
 */
public class Sorpresa {
    
    //Atributos
    private String texto;
    private TipoSorpresa tipo;
    private int valor;
    
    
    //Contructor por Parámetros
    public Sorpresa(String te, TipoSorpresa ti, int va){
        this.texto = te;
        this.tipo = ti;
        this.valor = va;
        
    }
    
    
    //Consultores
    protected String getTexto(){return this.texto;}
    
    protected TipoSorpresa getTipoSorpresa(){return this.tipo;}
    
    protected int getValor(){return this.valor;}
    
    
    //Sobreescritura del método toString
    @Override
    public String toString(){
        return "Sorpresa {Texto: " + this.texto + "\t Tipo: " + tipo +
                "\t Valor: " + Integer.toString(valor) + "}";
    }
}
