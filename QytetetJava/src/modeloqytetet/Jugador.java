package modeloqytetet;

import java.util.ArrayList;

/**
 *
 * @author josu
 */
public class Jugador implements Comparable {
    
    //Atributos
    private boolean encarcelado = false;
    private String nombre;
    private int saldo = 7500;
    
    //Atributos de Referencia
    private Sorpresa cartaLibertad = null;
    private ArrayList<TituloPropiedad> propiedades = new ArrayList<>();;
    private Casilla casillaActual = null;
    
    
    //Constructor
    public Jugador(String nom){
        this.nombre = nom;
    }
    
    //Constructor de copia
    protected Jugador(Jugador jug){
        encarcelado = jug.getEncarcelado();
        nombre = jug.getNombre();
        saldo = jug.getSaldo();
        cartaLibertad = jug.getCartaLibertad();
        propiedades = jug.getPropiedades();
        casillaActual = jug.getCasillaActual();
    }
    
    
    //Consultores
    protected Sorpresa getCartaLibertad(){return this.cartaLibertad;}
    
    protected Casilla getCasillaActual(){return this.casillaActual;}
    
    public boolean getEncarcelado(){return this.encarcelado;}
    
    public String getNombre(){return this.nombre;}
    
    protected ArrayList<TituloPropiedad> getPropiedades(){return this.propiedades;}
    
    public int getSaldo(){return this.saldo;}
    
    
    //Modificadores
   protected void setCartaLibertad(Sorpresa libertad){this.cartaLibertad = libertad;}
    
    protected void setCasillaActual(Casilla cas){this.casillaActual = cas;}
    
    protected void setEncarcelado(boolean encar){this.encarcelado = encar;}
    
    
    //Metodos
    protected boolean cancelarHipoteca(TituloPropiedad tit){
        boolean cancelada = false;
        int costeAnular = tit.calcularCosteCancelar();
        boolean tengoSaldo = tengoSaldo(costeAnular);
        
        if(tengoSaldo){
            modificarSaldo(-costeAnular);
            tit.setHipotecada();
            cancelada = true;
        }
        
        return cancelada;
    }
    
    protected boolean comprarTituloPropiedad(){
        boolean comprado = false;
        int costeCompra = this.casillaActual.getCoste();
        
        if(costeCompra < this.saldo){
            Calle cas = (Calle) casillaActual;
            TituloPropiedad titulo = cas.asignarPropietario(this);
            this.propiedades.add(titulo);
            modificarSaldo(-costeCompra);
            comprado=true;
        }
        
        return comprado;
    }
    
    protected Especulador convertirme(int fianza){
        Especulador espec = new Especulador (this, fianza);
        
        return espec;
    }
    
    protected int cuantasCasasHotelesTengo(){
        int totalViviendas = 0;
        
        //Por cada elemento dentro del ArrayList propiedades
        for (TituloPropiedad v : propiedades){
            totalViviendas = totalViviendas + v.getNumCasas() + v.getNumHoteles();
        }
        
        return totalViviendas;
    }
    
    protected boolean deboIrACarcel(){
        if(tengoCartaLibertad()){
            return false;
        }
        return true;
    }
    
    protected boolean deboPagarAlquiler(){
        boolean pagarAlquiler = false;
        
        TituloPropiedad titulo = this.casillaActual.getTitulo();
        
        boolean esMiPropiedad = esDeMiPropiedad(titulo);
        
        if(!esMiPropiedad){
            boolean tienePropietario = titulo.tengoPropietario();
            
            if(tienePropietario){
                boolean encarcelado = titulo.propietarioEncarcelado();
                
                if(!encarcelado){
                    boolean estaHipotecada = titulo.getHipotecada();
                    
                    if(!estaHipotecada){
                        pagarAlquiler = true;
                    }
                }
            }
        }
        
        return pagarAlquiler;
    }
    
    protected Sorpresa devolverCartaLibertad(){
        if(this.cartaLibertad != null){
            Sorpresa aux = cartaLibertad;
            cartaLibertad = null;
            return aux;
        }
        return null;
    }
    
    protected boolean edificarCasa(TituloPropiedad tit){
        boolean edificada = false;
        
        if(puedoEdificarCasa(tit)){
            tit.edificarCasa();
            int costeEdificarCasa = tit.getPrecioEdificar();
            modificarSaldo(-costeEdificarCasa);
            edificada = true;
        }
        
        return edificada;
    }
    
    protected boolean edificarHotel(TituloPropiedad tit){
        boolean edificado = false;
        
        if(puedoEdificarHotel(tit)){
            tit.edificarHotel();
            int costeEdificarHotel = tit.getPrecioEdificar();
            modificarSaldo(-costeEdificarHotel);
            edificado = true;
        }
        
        return edificado;
    }
    
    private void eliminarDeMisPropiedades(TituloPropiedad tit){
        propiedades.remove(tit);
        tit.setPropietario(null);
    }
    
    private boolean esDeMiPropiedad(TituloPropiedad tit){
        for(TituloPropiedad p : propiedades){
            if(p == tit){
                return true;
            }
        }
        return false;
    }
    
    protected void hipotecarPropiedad(TituloPropiedad tit){
        int costeHipoteca = tit.hipotecar();
        
        modificarSaldo(costeHipoteca);
    }
    
    protected void irACarcel(Casilla cas){
        setCasillaActual(cas);
        setEncarcelado(true);
    }
    
    protected int modificarSaldo(int cantidad){
        
        this.saldo = this.saldo + cantidad;
        
        return this.saldo;
    }
    
    protected int obtenerCapital(){
        int capital = this.saldo;
        
        capital = capital + this.valorPropiedades();
        
        
        return capital;
    }
    
    //Metodo propio
    private int valorPropiedades(){
        int valor = 0;
        int valorProp;
        
        for (TituloPropiedad p : propiedades){
            
            valorProp = p.getPrecioCompra();
            
            valorProp = valorProp + ( ( p.getNumCasas() + p.getNumHoteles() )*p.getPrecioEdificar() );
            
            if(p.getHipotecada()){
                valorProp = valorProp - p.getHipotecaBase();
            }
            
            valor = valor + valorProp;
        }
        
        return valor;
        
    }
    
    protected ArrayList<TituloPropiedad> obtenerPropiedades(boolean hipotecada){
        ArrayList<TituloPropiedad> aux = new ArrayList();
        for (TituloPropiedad p : propiedades){
            //Depende del atributo pasado, se coleccionarán las hipotecadas o las no hipotecadas
            if (p.getHipotecada() == hipotecada){
                aux.add(p);
            }
        }
        return aux;
    }
    
    protected void pagarAlquiler(){
        Calle cas = (Calle) this.casillaActual;
        
        int costeAlquiler = cas.pagarAlquiler();
        
        modificarSaldo(-costeAlquiler);
    }
    
    protected void pagarImpuesto(){
        this.saldo = this.saldo - (this.casillaActual.getCoste());
    }
    
    protected void pagarLibertad(int cantidad){
        boolean tengoSaldo = tengoSaldo(cantidad);
        
        if(tengoSaldo){
            setEncarcelado(false);
            modificarSaldo(-cantidad);
        }
    }
    
    protected boolean puedoEdificarCasa(TituloPropiedad titulo){
        if(tengoSaldo(titulo.getPrecioEdificar())){
            if(titulo.getNumCasas() < 4){
                return true;
            }
        }
        
        return false;
    }
    
    protected boolean puedoEdificarHotel(TituloPropiedad titulo){
        if(tengoSaldo(titulo.getPrecioEdificar())){
            if(titulo.getNumCasas() == 4 & titulo.getNumHoteles() < 4){
                return true;
            }
        }
        
        return false;
    }
    
    protected boolean tengoCartaLibertad(){
        
        return this.cartaLibertad != null;
    }
    
    protected boolean tengoSaldo(int necesario){
        
        return this.saldo > necesario;
    }
    
    protected void venderPropiedad(Casilla cas){
        TituloPropiedad tit = cas.getTitulo();
        eliminarDeMisPropiedades(tit);
        
        int precioVenta = tit.calcularPrecioVenta();
        
        modificarSaldo(precioVenta);
    }

    @Override
    public String toString() {
        String info;
        info = "Jugador {Nombre: " + this.nombre + "\t Saldo: " + this.saldo + "\t Capital: "+ this.obtenerCapital() + "\t Encarcelado: ";
        if(this.encarcelado){
            info = info + "Si\t Carta Libertad: ";
        }else{
            info = info + "No\t Carta Libertad: ";
        }
        
        if(this.cartaLibertad == null){
            info = info + "No tiene\t Casilla Actual: ";
        }else{
            info = info + "Si tiene\t Casilla Actual: ";
        }
        
        if(this.casillaActual == null){
            info = info + "No está en ninguna casilla\t Propiedades: ";
        }else{
            info = info + this.casillaActual.getNumeroCasilla() + "\t Propiedades: ";
        }
        
        if(this.propiedades.isEmpty()){
            info = info + "No tiene\t}";
        }else{
            info = info + this.propiedades.size() + "\t\t}";
        }
        
        return info;
    }

    @Override
    public int compareTo(Object obj) {
        
        if(this.obtenerCapital() > ((Jugador) obj).obtenerCapital() ){
            return -1; //Porque va primero
        }else{
            if(this.obtenerCapital() < ((Jugador) obj).obtenerCapital()){
                return 1;
            }
        }
        
        return 0;
    }
    
    
}
