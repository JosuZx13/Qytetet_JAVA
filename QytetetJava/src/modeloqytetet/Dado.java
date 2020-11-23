package modeloqytetet;

/**
 *
 * @author josu
 */
public class Dado {
    
    private int valor;
    private static Dado dado = null;

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private Dado() throws IllegalArgumentException{
        this.valor = 0;
    }

    public static Dado getInstance() throws IllegalArgumentException{
        if (dado == null){
            dado = new Dado();
        }
        
        return dado;
    }
    
    //Consultor
    public int getValor(){return this.valor;}
    
    
    //Metodos
    protected int tirar(){
        this.valor = (int) (Math.random()*6+1); //Devuelve un valor entre 1 y 6, sin decimales
        
        return this.valor;
    }

    @Override
    public String toString() {
        return "Al tirar el dado ha salido un " + this.valor;
    }
    
}
