package modeloqytetet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author josu
 */
public class Qytetet {
    private static final Scanner in = new Scanner (System.in);
    
    //Valores por defecto
    //Atributos
    public static int MAX_JUGADORES = 4;
    public static int MIN_JUGADORES = 2; //Propio
    protected static int NUM_SORPRESAS = 10;
    public static int NUM_CASILLAS = 20;
    protected static int PRECIO_LIBERTAD = 200;
    protected static int SALDO_SALIDA =  1000;
    protected static int IMPUESTO = 500;
    
    //Atributos de Referencia
    private ArrayList<Sorpresa> mazo;
    private Tablero tablero;
    private ArrayList<Jugador> jugadores;
    
    // Atributo de clase que contiene el singleton Qytetet
    private static Qytetet qytetet;
    private Dado dado;
    private Sorpresa cartaActual;
    private Jugador jugadorActual;
    private EstadoJuego estado;
    
    
    // Constructor privado
    private Qytetet() throws IllegalArgumentException{
        mazo = new ArrayList<>();
        jugadores = new ArrayList<>();
        cartaActual = null;
        jugadorActual = null;
        estado = null;
        dado = Dado.getInstance();

    }
    
    public static Qytetet getInstance(){
        if(qytetet == null){
            qytetet = new Qytetet();
        }
        return qytetet;
    }
    
    //Consultores
    protected ArrayList<Sorpresa> getMazo(){return this.mazo;}
    
    public Tablero getTablero(){return this.tablero;}
    
    public static Qytetet getQytetet(){return qytetet;}
    
    public Sorpresa getCartaActual(){return this.cartaActual;}
    
    protected Dado getDado(){return this.dado;}
    
    public Jugador getJugadorActual(){return this.jugadorActual;}
    
    public ArrayList<Jugador> getJugadores(){return this.jugadores;}
    
    public int obtenerValorDado(){return this.dado.getValor();}
    
    public EstadoJuego getEstadoJuego(){return this.estado;}
    
    //Modificadores
    private void setCartaActual(Sorpresa nuevaCarta){
        this.cartaActual = nuevaCarta;
    }
    
    public void setEstadoJuego(EstadoJuego estjue){
        this.estado = estjue;
    }
    
    
    //Metodos
    protected void actuarSiEnCasillaEdificable(){
        boolean deboPagar = this.jugadorActual.deboPagarAlquiler();
        
        if(deboPagar){
            this.jugadorActual.pagarAlquiler();
            
            if(this.jugadorActual.getSaldo() <= 0){
                setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCAROTA);
            }
        }
        
        Calle posicion = (Calle) obtenerCasillaJugadorActual();
        
        boolean tengoPropietario = posicion.tengoPropietario();
        
        if( estado != EstadoJuego.ALGUNJUGADORENBANCAROTA){
            if(tengoPropietario){
                setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
            }else{
                setEstadoJuego(EstadoJuego.JA_PUEDECOMPRAROGESTIONAR);
            }
        }
    }
    
    protected void actuarSiEnCasillaNoEdificable(){
        setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        Casilla casillaActual;
        casillaActual = this.jugadorActual.getCasillaActual();
        
        if(casillaActual.getTipo() == TipoCasilla.IMPUESTO){
            this.jugadorActual.pagarImpuesto();
        
        }else{
            if(casillaActual.getTipo() == TipoCasilla.JUEZ){
                encarcelarJugador();
            }else{
                if(casillaActual.getTipo() == TipoCasilla.SORPRESA){
                    //Devuelve el elemento que se remueve
                    setCartaActual(mazo.remove(0));
                    setEstadoJuego(EstadoJuego.JA_CONSORPRESA);
                }
            }
        }
    }
    
    public void aplicarSorpresa(){
        setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        
        if(this.cartaActual.getTipoSorpresa() == TipoSorpresa.SALIRCARCEL){
            jugadorActual.setCartaLibertad(cartaActual);
        
        }else{
            mazo.add(cartaActual);
            
            switch ( this.cartaActual.getTipoSorpresa() ){
                
                case PAGARCOBRAR :
                    this.jugadorActual.modificarSaldo(this.cartaActual.getValor());
                
                    if(this.jugadorActual.getSaldo() <= 0){
                        setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCAROTA);
                    }
                    
                break;
                
                case IRACASILLA :
                    int valor = this.cartaActual.getValor();
                    
                    boolean casillaCarcel = this.tablero.esCasillaCarcel(valor);
                    
                    if(casillaCarcel){
                        encarcelarJugador();
                    }else{
                        this.jugadorActual.setCasillaActual(this.tablero.obtenerCasillaNumero(valor));
                    }
                    
                break;
                
                case PORCASAHOTEL :
                    int cantidad = this.cartaActual.getValor();
                    int numeroTotal = this.jugadorActual.cuantasCasasHotelesTengo();

                    this.jugadorActual.modificarSaldo(cantidad*numeroTotal);

                    if(this.jugadorActual.getSaldo() <= 0){
                        setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCAROTA);
                    }
                    
                break;
                
                case PORJUGADOR:
                    for(Jugador jug : this.jugadores){

                        if(jug != this.jugadorActual){
                            jug.modificarSaldo(this.cartaActual.getValor());

                            if(jug.getSaldo() <= 0){
                                setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCAROTA);
                            }
                        }

                        this.jugadorActual.modificarSaldo(-cartaActual.getValor());
                        if(this.jugadorActual.getSaldo() <= 0){
                            setEstadoJuego(EstadoJuego.ALGUNJUGADORENBANCAROTA);
                         }
                    }
                    
                break;
                
                case CONVERTIRME:
                    Especulador espec = this.jugadorActual.convertirme(this.cartaActual.getValor());
                    
                    int pos = 0;
                    while(this.jugadorActual != jugadores.get(pos)){
                        pos++;
                    }
                    
                    //Intercambia el objeto que haya en dicha posición por otro objeto pasado
                    jugadores.set(pos, espec);
                    jugadorActual = espec;
                    
                break;
                    
            }
        }
    }
    
    public boolean cancelarHipoteca(int numeroCasilla){
        boolean cancelada = false;
        
        Casilla cas = this.tablero.obtenerCasillaNumero(numeroCasilla);
        TituloPropiedad tit = cas.getTitulo();
        //La precondición indica que esta casilla ya se supone que este hipotecada
        cancelada = this.jugadorActual.cancelarHipoteca(tit);
        
        setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        
        return cancelada;
    }
    
    public boolean comprarTituloPropiedad(){
        boolean comprado = this.jugadorActual.comprarTituloPropiedad();
        
        if(comprado){
            setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        }
        
        return comprado;
    }
    
    public boolean edificarCasa(int numeroCasilla){
        boolean edificada;
        
        Casilla cas = this.tablero.obtenerCasillaNumero(numeroCasilla);
        
        TituloPropiedad titulo = cas.getTitulo();
        
        edificada = this.jugadorActual.edificarCasa(titulo);
        
        if(edificada){
            setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        }
        
        return edificada;
    }
    
    public boolean edificarHotel(int numeroCasilla){
        boolean edificado;
        
        Casilla cas = this.tablero.obtenerCasillaNumero(numeroCasilla);
        
        TituloPropiedad titulo = cas.getTitulo();
        
        edificado = this.jugadorActual.edificarHotel(titulo);
        
        if(edificado){
            setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        }
        
        return edificado;
    }
    
    private void encarcelarJugador(){
        
        //Si no tiene la carta Libertad
        if(this.jugadorActual.deboIrACarcel()){
            Casilla casCarcel = this.tablero.getCarcel();
            this.jugadorActual.irACarcel(casCarcel);
        
            setEstadoJuego(EstadoJuego.JA_ENCARCELADO);
        }else{
            Sorpresa carta = this.jugadorActual.devolverCartaLibertad();
            mazo.add(carta);
            
            setEstadoJuego(EstadoJuego.JA_PUEDEGESTIONAR);
        }
    }
    
    //Diagrama de Comunicacion, donde apunta la flecha padre indica de quien es el método
    public void hipotecarPropiedad(int numeroCasilla){
        Casilla cas = this.tablero.obtenerCasillaNumero(numeroCasilla);
        TituloPropiedad tit = cas.getTitulo();
        
        this.jugadorActual.hipotecarPropiedad(tit);
    }
    
    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){
        boolean encarcelado;
        
        if(metodo == MetodoSalirCarcel.TIRANDODADO){
            int resultado = tirarDado();
            if(resultado >= 5){
                this.jugadorActual.setEncarcelado(false);
            }
        }else{
            this.jugadorActual.pagarLibertad(PRECIO_LIBERTAD);
        }
        
        encarcelado = this.jugadorActual.getEncarcelado();
        
        if(encarcelado){
            setEstadoJuego(EstadoJuego.JA_ENCARCELADO);
        }else{
            setEstadoJuego(EstadoJuego.JA_PREPARADO);
        }
        
        return encarcelado;
    }
    
    public void jugar(){
        int valor = tirarDado();
        Casilla mov = tablero.obtenerCasiilaFinal(jugadorActual.getCasillaActual(), valor);
        mover(mov.getNumeroCasilla());
    }
    
    protected void mover(int numCasillaDestino){
        /*if (this.jugadorActual.getEncarcelado() ) {
            System.out.println("##############################");
            System.out.println("Estoy en la carcel\n");

            System.out.println("Opciones de salida");
            System.out.println("1.- Tirar Dado y que salga 5");
            System.out.println("2.- Pagando el precio ["+ PRECIO_LIBERTAD +"]");

            int o = in.nextInt();
            while (o != 1 && o != 2){
              System.out.println("(Las opciones solo son 1 ó 2)");
              o = in.nextInt();
            }
            //Recolector de Basura
            //in.nextInt();

            if( o == 1){
              System.out.println("Has elegido Tirando dado");
              System.out.println("##############################");
              intentarSalirCarcel(MetodoSalirCarcel.TIRANDODADO);
            }else{
              System.out.println("Has elegido Pagando");
              System.out.println("##############################");
              intentarSalirCarcel(MetodoSalirCarcel.PAGANDOLIBERTAD);
            }
        }
        
        if( !this.jugadorActual.getEncarcelado() ){
            Casilla cInicial = this.jugadorActual.getCasillaActual();
            Casilla cFinal = this.tablero.obtenerCasiilaFinal(cInicial, numCasillaDestino);
            
            this.jugadorActual.setCasillaActual(cFinal);
            
            if(cFinal.getNumeroCasilla() < cInicial.getNumeroCasilla()){
                this.jugadorActual.modificarSaldo(SALDO_SALIDA);
            }
            
            if(cFinal.soyEdificable()){
                actuarSiEnCasillaEdificable();
            }else{
                actuarSiEnCasillaNoEdificable();
            }
        }*/
        Casilla cInicial = this.jugadorActual.getCasillaActual();
        
        Casilla cFinal = this.tablero.getCasillas().get(numCasillaDestino));

        this.jugadorActual.setCasillaActual(cFinal);

        if(cFinal.getNumeroCasilla() < cInicial.getNumeroCasilla()){
            this.jugadorActual.modificarSaldo(SALDO_SALIDA);
        }

        if(cFinal.soyEdificable()){
            actuarSiEnCasillaEdificable();
        }else{
            actuarSiEnCasillaNoEdificable();
        }

    }
    
    public Casilla obtenerCasillaJugadorActual(){
        return this.jugadorActual.getCasillaActual();
    }
    
    public ArrayList<Integer> obtenerPropiedadesJugador(){
        ArrayList<Integer> numCasillas = new ArrayList<>();
        ArrayList<TituloPropiedad> prop = this.jugadorActual.getPropiedades();
        
        //Para cada casilla, se comprueba cual es del propietario
        for(Casilla tc : this.tablero.getCasillas()){
            
            for(TituloPropiedad p : prop){
                if(tc.getTitulo() == p){
                    numCasillas.add(tc.getNumeroCasilla());
                }
            }
            
        }
        
        return numCasillas;
    }
    
    public ArrayList<Integer> obtenerPropiedadesJugadorSegunEstadoHipteca(boolean estadoHipoteca){
        ArrayList<Integer> numCasillas = new ArrayList<>();
        ArrayList<TituloPropiedad> prop = this.jugadorActual.obtenerPropiedades(estadoHipoteca);
        
        //Para cada casilla, se comprueba cual es del propietario
        for(Casilla tc : this.tablero.getCasillas()){
            
            for(TituloPropiedad p : prop){
                if(tc.getTitulo() == p){
                    numCasillas.add(tc.getNumeroCasilla());
                }
            }
            
        }
        
        return numCasillas;
    }
    
    public void obtenerRanking(){
                
        Collections.sort(jugadores);
        setEstadoJuego(EstadoJuego.RANKINGOBTENIDO);
    }
    
    //PROPIO
    public String mostrar_ranking() {
        String puntuacion = "";
        int index = 1;
      
        for(Jugador jug_puntuado : this.jugadores){
            puntuacion = puntuacion + "\t" + index + ".-" + jug_puntuado.getNombre() + "\t\tCapital: " + jug_puntuado.obtenerCapital() + "\n";
            index++;
        }
      
        return puntuacion;
      
    }
    
    public int obtenerSaldoJugadorActual(){
        return this.jugadorActual.getSaldo();
    }
    
    private void salidaJugadores(){
        
        for (Jugador j: jugadores){
            j.setCasillaActual( (Casilla)tablero.obtenerCasillaNumero(0) );
        }
        
        //Un numero aleatorio entre 0 y el numero de jugadores
        int primero = (int) (Math.random()*(jugadores.size()-1));
        
        jugadorActual = jugadores.get(primero);
        
    }
    
    public void siguienteJugador(){
        
        int pos = 0;
        boolean encontrado = false;
        
        for (int i = 0; i < jugadores.size() && !encontrado; i++){
            if (jugadorActual.equals(jugadores.get(i))){
                encontrado = true;
                pos = i;
            }                
        }
        
        jugadorActual = jugadores.get( ( (pos + 1) % jugadores.size() ) );
        
        if(jugadorActual.getEncarcelado()){
            estado = EstadoJuego.JA_ENCARCELADOCONOPCIONDELIBERTAD;
        }else{
            estado = EstadoJuego.JA_PREPARADO;
        }
    }
    /*
    protected boolean jugadorActualEnCalleLibre(){
        
        if( this.jugadorActual.getCasillaActual().soyEdificable() ){
            if( this.jugadorActual.getCasillaActual().getTitulo().tengoPropietario() ){
                return true;
            }
        }
        
        return false;
    }
    
    protected boolean jugadorActualEncarcelado(){
        return jugadorActual.getEncarcelado();
    }*/
    
    protected int tirarDado(){
        return this.dado.tirar();
    }
    
    public void venderPropiedad(int numeroCasilla){
        Casilla cas = this.tablero.obtenerCasillaNumero(numeroCasilla);
        
        this.jugadorActual.venderPropiedad(cas);
    }
    
    protected void inicializarCartasSorpresa(){
        
        mazo = new ArrayList<>();
        
        mazo.add(new Sorpresa ("IrACasilla", TipoSorpresa.IRACASILLA, 7));        
        mazo.add(new Sorpresa ("IrACarcel", TipoSorpresa.IRACASILLA, 5));
        mazo.add(new Sorpresa ("SalirDeCarcel", TipoSorpresa.SALIRCARCEL, 0));
        mazo.add(new Sorpresa ("IrACasilla", TipoSorpresa.IRACASILLA, 19));
        mazo.add(new Sorpresa ("PagarCobrar", TipoSorpresa.PAGARCOBRAR, 450));
        mazo.add(new Sorpresa ("PagarCobrar", TipoSorpresa.PAGARCOBRAR, -300));
        mazo.add(new Sorpresa ("PorJugador", TipoSorpresa.PORJUGADOR, 100));
        mazo.add(new Sorpresa ("PorJugador", TipoSorpresa.PORJUGADOR, -130));
        mazo.add(new Sorpresa ("PorCasaHotel", TipoSorpresa.PORCASAHOTEL, -40));
        mazo.add(new Sorpresa ("PorCasaHotel", TipoSorpresa.PORCASAHOTEL, 35));
        mazo.add(new Sorpresa ("Conversión", TipoSorpresa.CONVERTIRME, 3000));
        mazo.add(new Sorpresa ("Conversión", TipoSorpresa.CONVERTIRME, 5000));
        
        //Ordena de forma aleatoria los elementos de una lista
        Collections.shuffle(mazo);
        
    }
    
    private void inicializarTablero(){
        //instancia del atributo
        tablero = new Tablero();
    }
    
    private void inicializarJugadores(ArrayList<String> nombres) throws IllegalArgumentException{
        if (nombres.size() < MIN_JUGADORES || nombres.size() > MAX_JUGADORES){
            throw new IllegalArgumentException("Número de jugadores inválido. Mínimo "+MIN_JUGADORES+" Maximo "+MAX_JUGADORES);
        }
        
        jugadores = new ArrayList();
        for (String nom: nombres) {
            
            jugadores.add(new Jugador( nom ) );
        }    
    }
    
    public void inicializarJuego(ArrayList<String> nombres){        
        inicializarJugadores(nombres);
        inicializarTablero();
        inicializarCartasSorpresa();
        salidaJugadores();
        estado = EstadoJuego.JA_PREPARADO;
        
    }
    
    //Metodo propio
    public String mostrarJugadores(){
        
        String datos = "\n";
        for(Jugador ju : jugadores){
            datos = datos + "\t" + ju.toString() + "\n";
        }
        return datos;
    }

    @Override
    public String toString() {
        String esquemaJuego;
        
        esquemaJuego = "\n\nMAZO\n";

        //Empieza el primer elemento del ArrayList y avanza hasta que no haya más que coger
        for(Sorpresa carta : this.mazo){
            esquemaJuego = esquemaJuego + "\t" + carta.toString() + "\n";
        }
        
        esquemaJuego = esquemaJuego + "\n\nTABLERO\n";
        esquemaJuego = esquemaJuego + this.tablero.toString();
        
        esquemaJuego = esquemaJuego + "\n\nJUGADORES\n";
        for(Jugador jugador : this.jugadores){
            esquemaJuego = esquemaJuego + "\t" + jugador.toString() + "\n";
        }
        
        return esquemaJuego;
    }
    
    
    
}
