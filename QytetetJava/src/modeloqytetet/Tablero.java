package modeloqytetet;

import java.util.ArrayList;

/**
 *
 * @author josu
 */
public class Tablero {
    
    //Atributos
    private ArrayList<Casilla> casillas;
    private Casilla carcel;
    
    
    //Constructor por defecto
    public Tablero(){
        inicializar();
    }
    
    
    //Consultores
    protected ArrayList<Casilla> getCasillas(){return this.casillas;}
    
    protected Casilla getCarcel(){return this.carcel;}
    
    
    //Metodos
    protected boolean esCasillaCarcel(int numeroCasilla){
        return numeroCasilla == carcel.getNumeroCasilla();
    }
    
    protected Casilla obtenerCasiilaFinal(Casilla cas, int despl){
        return casillas.get( (cas.getNumeroCasilla() + despl)%Qytetet.NUM_CASILLAS );
    }
    
    protected Casilla obtenerCasillaNumero(int numeroCasilla){
       return casillas.get(numeroCasilla);
    }
    
    private void inicializar(){
        
         // Propiedades (Calles)
        ArrayList<TituloPropiedad> propiedades = new ArrayList();
        //String nom, int prCom, int alBas, float faRev, int hiBase, int prEdi
        propiedades.add(new TituloPropiedad("Calle Atenea",         350, 35,    0.17f,  175,    100));
        propiedades.add(new TituloPropiedad("Calle Artemisa",       350, 40,    -0.14f, 175,    120));
        propiedades.add(new TituloPropiedad("Casino Loki",          450, 200,   13f,    340,    130));
        propiedades.add(new TituloPropiedad("Avenida de Dioniso",   500, 120,   0.2f,   60,     200));
        propiedades.add(new TituloPropiedad("Herreria Hefesto",     550, 65,    -0.2f,  80,     260));
        propiedades.add(new TituloPropiedad("Mar Poseidón",         350, 150,   0.15f,  135,    325));
        propiedades.add(new TituloPropiedad("Palacio de Afrodita",  460, 110,   0.1f,   160,    360));
        propiedades.add(new TituloPropiedad("Altar de Freyja",      480, 230,   0.1f,   280,    540));
        propiedades.add(new TituloPropiedad("Coliseo de Zeus",      580, 150,   0.19f,  200,    425));
        propiedades.add(new TituloPropiedad("Aposentos de Odín",    600, 195,   -0.14f, 195,    395));
        propiedades.add(new TituloPropiedad("Olimpo",               700, 260,   0.1f,   210,    430));
        propiedades.add(new TituloPropiedad("Valhalla",             750, 280,   0.2f,   223,    480));
    
        casillas = new ArrayList();
        int indexPropiedad = 0;
        int indexCasilla = 0;
        
        // Casillas van de la 0 a la 19
        this.casillas = new ArrayList<>();
        
        //index++, escribe 0 y suma 1 para el siguiente, posfijo
        casillas.add(new OtraCasilla(indexCasilla++, TipoCasilla.SALIDA));//Casilla 0
        casillas.add(new Calle(indexCasilla++, propiedades.get(indexPropiedad++)));
        casillas.add(new Calle(indexCasilla++, propiedades.get(indexPropiedad++)));
        casillas.add(new Calle(indexCasilla++, propiedades.get(indexPropiedad++)));
        casillas.add(new Calle(indexCasilla++, propiedades.get(indexPropiedad++)));
        casillas.add(new OtraCasilla(indexCasilla++, TipoCasilla.CARCEL)); //Casilla 5
        casillas.add(new OtraCasilla(indexCasilla++, TipoCasilla.SORPRESA));
        casillas.add(new Calle(indexCasilla++, propiedades.get(indexPropiedad++)));
        casillas.add(new Calle(indexCasilla++, propiedades.get(indexPropiedad++)));
        casillas.add(new Calle(indexCasilla++, propiedades.get(indexPropiedad++)));
        casillas.add(new OtraCasilla(indexCasilla++, TipoCasilla.IMPUESTO)); //Casilla 10
        casillas.add(new Calle(indexCasilla++, propiedades.get(indexPropiedad++)));
        casillas.add(new OtraCasilla(indexCasilla++, TipoCasilla.SORPRESA));
        casillas.add(new OtraCasilla(indexCasilla++, TipoCasilla.PARKING));
        casillas.add(new Calle(indexCasilla++, propiedades.get(indexPropiedad++)));
        casillas.add(new Calle(indexCasilla++, propiedades.get(indexPropiedad++))); //Casilla 15
        casillas.add(new OtraCasilla(indexCasilla++, TipoCasilla.JUEZ));
        casillas.add(new Calle(indexCasilla++, propiedades.get(indexPropiedad++)));
        casillas.add(new OtraCasilla(indexCasilla++, TipoCasilla.SORPRESA));
        casillas.add(new Calle(indexCasilla, propiedades.get(indexPropiedad))); //Casilla 19
        
        carcel = casillas.get(5); //Casilla 6, aunque en el Array y el Tablero se cuenta de 0 a 19
        
    }

    
    @Override
    public String toString() {
        String salida = "";
        
        for (Casilla c : casillas){
            salida = salida + "\t" + c.toString() + "\n";
        }
        
        salida = salida + "\n\tCarcel: " + carcel.toString() + "\n";
        
        return salida;
    }
    
}
