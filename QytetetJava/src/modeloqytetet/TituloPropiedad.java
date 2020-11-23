package modeloqytetet;

/**
 *
 * @author josu
 */
public class TituloPropiedad {
    
    //Atributos
    private String nombre;
    private boolean hipotecada;
    private int precioCompra;
    private int alquilerBase;
    private float factorRevalorizacion;
    private int hipotecaBase;
    private int precioEdificar;
    private int numHoteles;
    private int numCasas;
    
    //De Referencia
    private Casilla calle;
    private Jugador propietario;
    
    //Constructor por parametros
    public TituloPropiedad (String nom, int prCom, int alBas, float faRev, int hiBase, int prEdi){
        this.nombre = nom;
        this.hipotecada = false;
        this.precioCompra = prCom;
        this.alquilerBase = alBas;
        this.factorRevalorizacion = faRev;
        this.hipotecaBase = hiBase;
        this.precioEdificar = prEdi;
        this.numHoteles = 0;
        this.numCasas = 0;
        this.calle = null;
        this.propietario = null;
    }
    
    
    //Consultores
    protected String getNombre(){return this.nombre;}
    
    protected boolean getHipotecada(){return this.hipotecada;}
    
    protected int getPrecioCompra(){return this.precioCompra;}
    
    protected int getAlquilerBase(){return this.alquilerBase;}
    
    protected float getFactorRevalorizacion(){return this.factorRevalorizacion;}
    
    protected int getHipotecaBase(){return this.hipotecaBase;}
    
    protected int getPrecioEdificar(){return this.precioEdificar;}
    
    protected int getNumHoteles(){return this.numHoteles;}
    
    protected int getNumCasas(){return this.numCasas;}
    
    protected Jugador getPropietario(){return this.propietario;}
    
    
    //Modificadores
    public void setHipotecada(){this.hipotecada=!this.hipotecada;}
    
    protected void setPropietario(Jugador propietario){this.propietario = propietario;}
    
    
    //Metodos
    protected int calcularCosteCancelar (){
        int costeAnular = 0;
        
        costeAnular = (int) (calcularCosteHipotecar() * 0.10);
        
        return costeAnular;
    }
    
    protected int calcularCosteHipotecar (){
        int costeHipotecar = 0;
        
        costeHipotecar = (int) (hipotecaBase + (numCasas*0.5*hipotecaBase) + (numHoteles*hipotecaBase) );
        
        return costeHipotecar;
    }
    
    protected int calcularImporteAlquiler (){
        int importe = 0;
        
        importe = (int) ( alquilerBase + (numCasas*0.5 + numHoteles*2) );
        
        return importe;
    }
    
    protected int calcularPrecioVenta (){
        int precioVenta = 0;
        
        precioVenta = (int) (precioCompra + ( ( (numCasas + numHoteles)*precioEdificar) * factorRevalorizacion) );
                
        return precioVenta;
    }
    
    protected void cancelarHipoteca (){
        this.hipotecada = false;
    }
    
    protected void edificarCasa (){
        this.numCasas = this.numCasas + 1;
    }
    
    protected void edificarHotel (){
        this.numHoteles = this.numHoteles + 1;
        this.numCasas = 0;
    }

    protected int hipotecar (){
        //Cambia siempre el valor que tuviera al contrario hipotecada= (!hipotecada)
        setHipotecada();
        int costeHipoteca = calcularCosteHipotecar();
        
        return costeHipoteca;
    }
    
    protected int pagarAlquiler (){
        int costeAlquiler = calcularImporteAlquiler();
        this.propietario.modificarSaldo(costeAlquiler);
        
        return costeAlquiler;
    }
    
    protected boolean propietarioEncarcelado (){
        return this.propietario.getEncarcelado();
    }
    
    protected boolean tengoPropietario (){
        return this.propietario != null;
    }
    
    
    @Override
    public String toString() {
        return "TituloPropiedad {Nombre: " + this.nombre + "\t Hipotecada: " + (this.hipotecada == false ? "No esta hipotecada" : "Esta hipotecada") + "\t Precio Compra: " + this.precioCompra + "\t Alquiler Base: " + this.alquilerBase + "\t Factor Revalorizacion: " + this.factorRevalorizacion + "\t Hipoteca Base: " + hipotecaBase + "\t Precio Edificar: " + precioEdificar + "\t Numero Casas: " + this.numCasas + "\t Numero Hoteles: " + this.numHoteles +"\t Propietario: "+ (this.propietario == null ? "No tiene propietario " : this.propietario.getNombre()) + '}';
    }
    
}
