package controladorqytetet;

import java.util.ArrayList;
import java.util.Collections;
import modeloqytetet.EstadoJuego;
import modeloqytetet.MetodoSalirCarcel;
import modeloqytetet.Qytetet;

import modeloqytetet.Calle;
import modeloqytetet.Casilla;

/**
 *
 * @author josu
 */
public class ControladorQytetet {
    
    private ArrayList<String> nombreJugadores;
    
    //La instancia del controlador
    private static ControladorQytetet controlador;
    private static Qytetet modelo = Qytetet.getInstance();
    private OpcionMenu opcionElegida;
    
    //Propia
    private boolean obtenido = false; //Controla si ha sido obtenido el ranking

    // El constructor es privado, no permite que se genere un constructor por defecto.
    private ControladorQytetet() throws IllegalArgumentException{
        nombreJugadores = new ArrayList<>();
    }

    public static ControladorQytetet getInstance() throws IllegalArgumentException{
        if (controlador == null){
            controlador = new ControladorQytetet();
        }
        modelo = Qytetet.getInstance();
        
        return controlador;
    }
    
    public void setNombreJugadores(ArrayList<String> nomJug){
        this.nombreJugadores = nomJug;
    }
    
    public ArrayList<Integer> obtenerOperacionesJuegoValidas(){
        ArrayList<Integer> opcionesValidas = new ArrayList<>();
        
        //Si el juego aun no se ha iniciado
        if(modelo.getJugadores().isEmpty()){
            opcionesValidas.add(OpcionMenu.INICIARJUEGO.ordinal());
            
        }else{
            
            if(modelo.getEstadoJuego() == EstadoJuego.RANKINGOBTENIDO){
                opcionesValidas.add(OpcionMenu.TERMINARJUEGO.ordinal());
                
            }else{
               //Opciones seguras que se añaden a las validas
                opcionesValidas.add(OpcionMenu.TERMINARJUEGO.ordinal());
                opcionesValidas.add(OpcionMenu.MOSTRARJUGADORACTUAL.ordinal());
                opcionesValidas.add(OpcionMenu.MOSTRARJUGADORES.ordinal());
                opcionesValidas.add(OpcionMenu.MOSTRARTABLERO.ordinal());
                opcionesValidas.add(OpcionMenu.OBTENERRANKING.ordinal());

                switch(modelo.getEstadoJuego()){

                    case JA_PREPARADO:
                        opcionesValidas.add(OpcionMenu.JUGAR.ordinal());
                    break;

                    case JA_ENCARCELADO:
                        opcionesValidas.add(OpcionMenu.PASARTURNO.ordinal());
                    break;

                    case JA_ENCARCELADOCONOPCIONDELIBERTAD:
                        opcionesValidas.add(OpcionMenu.INTENTARSALIRCARCELPAGANDOLIBERTAD.ordinal());
                        opcionesValidas.add(OpcionMenu.INTENTARSALIRCARCELTIRANDODADO.ordinal());
                    break;

                    case JA_PUEDECOMPRAROGESTIONAR:
                        opcionesValidas.add(OpcionMenu.PASARTURNO.ordinal());
                        opcionesValidas.add(OpcionMenu.COMPRARTITULOPROPIEDAD.ordinal());
                        opcionesValidas.add(OpcionMenu.VENDERPROPIEDAD.ordinal());
                        opcionesValidas.add(OpcionMenu.HIPOTECARPROPIEDAD.ordinal());
                        opcionesValidas.add(OpcionMenu.CANCELARHIPOTECA.ordinal());
                        opcionesValidas.add(OpcionMenu.EDIFICARCASA.ordinal());
                        opcionesValidas.add(OpcionMenu.EDIFICARHOTEL.ordinal());
                    break;

                    case JA_PUEDEGESTIONAR:
                        opcionesValidas.add(OpcionMenu.PASARTURNO.ordinal());
                        opcionesValidas.add(OpcionMenu.VENDERPROPIEDAD.ordinal());
                        opcionesValidas.add(OpcionMenu.HIPOTECARPROPIEDAD.ordinal());
                        opcionesValidas.add(OpcionMenu.CANCELARHIPOTECA.ordinal());
                        opcionesValidas.add(OpcionMenu.EDIFICARCASA.ordinal());
                        opcionesValidas.add(OpcionMenu.EDIFICARHOTEL.ordinal());
                    break;

                    case JA_CONSORPRESA:
                        opcionesValidas.add(OpcionMenu.APLICARSORPRESA.ordinal());
                    break;

                    case ALGUNJUGADORENBANCAROTA:
                        opcionesValidas.add(OpcionMenu.OBTENERRANKING.ordinal());
                    break;
                
                }//Fin del Switch 
            
            }//Fin del if Ranking
            
        }//Fin dle if jugadores vacio
               
        Collections.sort(opcionesValidas);
        
        return opcionesValidas;
    }
    
    public boolean necesitaElegirCasilla(int opcMenu){
        
        ArrayList<Integer> deCasillas = new ArrayList<>();
        
        deCasillas.add(OpcionMenu.HIPOTECARPROPIEDAD.ordinal());
        deCasillas.add(OpcionMenu.CANCELARHIPOTECA.ordinal());
        deCasillas.add(OpcionMenu.EDIFICARCASA.ordinal());
        deCasillas.add(OpcionMenu.EDIFICARHOTEL.ordinal());
        deCasillas.add(OpcionMenu.VENDERPROPIEDAD.ordinal());
        
        for(Integer o : deCasillas){
            if(opcMenu == o){
                return true;
            }
        }
        
        return false;
    }
    
    public ArrayList<Integer> obtenerCasillasValidas(int opcMenu){
        ArrayList<Integer> CasillasOperacionRealizable = new ArrayList<>();
        
        //Enum.values() devuelve un array de valores
        //Enum.values()[integer] devuelve la opcion que corresponde a la posicion
        opcionElegida = OpcionMenu.values()[opcMenu];
        
        
        switch(opcionElegida){
            //No se puede vender una propiedad hipotecada
            case VENDERPROPIEDAD:
                CasillasOperacionRealizable = modelo.obtenerPropiedadesJugadorSegunEstadoHipteca(false);
            break;
            
            case HIPOTECARPROPIEDAD:
                CasillasOperacionRealizable = modelo.obtenerPropiedadesJugadorSegunEstadoHipteca(false);
            break;
            
            case CANCELARHIPOTECA:
                CasillasOperacionRealizable = modelo.obtenerPropiedadesJugadorSegunEstadoHipteca(true);
            break;
            
            case EDIFICARCASA:
                CasillasOperacionRealizable = modelo.obtenerPropiedadesJugadorSegunEstadoHipteca(false);
            break;
            
            case EDIFICARHOTEL:
                CasillasOperacionRealizable = modelo.obtenerPropiedadesJugadorSegunEstadoHipteca(false);
            break;
        }
        
        
        return CasillasOperacionRealizable;
        
    }
    
    public String realizarOperacion(int opcElegida, int casElegida){
        String realizar = "";
        Casilla cas;
        
        opcionElegida = OpcionMenu.values()[opcElegida];
        
        switch (opcionElegida){
        
            case INICIARJUEGO:
                realizar = "\n\t\tEL JUEGO HA COMENZADO\n\n";
                modelo.inicializarJuego(nombreJugadores);
            break;
                
            case APLICARSORPRESA:
                realizar = "\nCarta Sorpresa aplicada:\n";
                realizar = realizar + "\t" + modelo.getCartaActual().toString() + "\n";
                
                modelo.aplicarSorpresa();
            break;
                
            case CANCELARHIPOTECA:
                
                if (casElegida == -1){
                    realizar = "\nNo tienes ninguna propiedad a la que se le pueda cancelar la hipoteca\n";
                }else{
                    realizar = "\nLa propiedad " + casElegida + " va a cancelar su hipoteca\n";
                modelo.cancelarHipoteca(casElegida);
                }
                
            break;
            
            case COMPRARTITULOPROPIEDAD:
                Calle cal = (Calle) modelo.obtenerCasillaJugadorActual();
                casElegida = cal.getNumeroCasilla();
                
                if(modelo.comprarTituloPropiedad()){
                    realizar = "\nLa Propiedad " + casElegida + " ha sido comprada\n";
                }else{
                    realizar = "\nLa Propiedad " + casElegida + " no ha podido comprarse\n";
                }
                
            break;
            
            case EDIFICARCASA:
                
                if(casElegida == -1){
                    realizar = "\nNo tienes ninguna propiedad en la que construir una casa\n";
                }else{
                
                    if(modelo.edificarCasa(casElegida)){
                        realizar = "\nEn la Propiedad " + casElegida + " se construirá una casa\n";
                    }else{
                        realizar = "\nNo es posible construir una casa en la Propiedad "+ casElegida + "\n";
                    }
                }
                
            break;
            
            case EDIFICARHOTEL:
                
                if(casElegida == -1){
                    realizar = "\nNo tienes ninguna propiedad en la que construir un hotel\n";
                }else{
                
                    if(modelo.edificarHotel(casElegida)){
                        realizar = "\nEn la Propiedad " + casElegida + " se construirá un hotel\n";
                    }else{
                        realizar = "\nNo es posible construir un hotel en la Propiedad "+ casElegida + "\n";
                    }
                }
                
            break;
            
            case HIPOTECARPROPIEDAD:
                
                if(casElegida == -1){
                    realizar = "\nNo tienes ninguna Propiedad que se pueda hipotecar\n";
                }else{
                    realizar = "\nLa Propiedad " + casElegida + " pasará a hipotecarse\n\n";
                    modelo.hipotecarPropiedad(casElegida);
                }
            break;
            
            case INTENTARSALIRCARCELPAGANDOLIBERTAD:
                realizar = "\nEl Jugador " + modelo.getJugadorActual().getNombre() + " saldrá de la carcel pagando la libertad\n";
                modelo.intentarSalirCarcel(MetodoSalirCarcel.PAGANDOLIBERTAD);
            break;
            
            case INTENTARSALIRCARCELTIRANDODADO:
                realizar = "\nEl Jugador " + modelo.getJugadorActual().getNombre() + " intentará salir de la cárcel tirando el dado\n\n";
                modelo.intentarSalirCarcel(MetodoSalirCarcel.TIRANDODADO);
                
                if(modelo.getJugadorActual().getEncarcelado()){
                    realizar = realizar + "\n\tNo tuvo suerte en salir de la carcel. Valor del dado: " + modelo.obtenerValorDado() + "\n";
                }else{
                    realizar = realizar + "\n\tLa suerte ha sonreido y de la carcel ha salido. Valor del dado: " + modelo.obtenerValorDado() + "\n";
                }
                    
            break;
            
            case JUGAR:
                realizar = "\nEl jugador " + modelo.getJugadorActual().getNombre() + " se dispone a jugar su turno\n";
                cas = modelo.obtenerCasillaJugadorActual();
                realizar = realizar + "\n\tCasilla Actual del jugador " + cas.getNumeroCasilla() + "\n";
                modelo.jugar();
                cas = modelo.obtenerCasillaJugadorActual();
                realizar = realizar + "\tAl lanzar el dado ha salido " + modelo.obtenerValorDado() + " y ha avanzado hasta la casilla " + cas.getNumeroCasilla() + "\n" ;
                
                realizar = realizar + "\n\t"+ cas.toString() + "\n";
                
            break;
            
            case MOSTRARJUGADORACTUAL:
                realizar = "\nDatos del jugador actual:\n";
                realizar = realizar + "\t" + modelo.getJugadorActual().toString() + "\n";
            break;
            
            case MOSTRARJUGADORES:
               realizar = "\nDatos de los jugadores:";
               realizar = realizar + modelo.mostrarJugadores();
            break;
            
            case MOSTRARTABLERO:
                realizar = "\nTablero\n";
                realizar = realizar + modelo.getTablero().toString();
            break;
            
            case OBTENERRANKING:
                realizar = "\nRecopilando información para establecer un ranking...\n";
                modelo.obtenerRanking();
                
                obtenido = true;
                
                realizar = realizar + "\n\t\tRESULTADO DE LA PARTIDA\n";
                realizar = realizar + modelo.mostrar_ranking();
                
            break;
            
            case PASARTURNO:
                realizar = "\nEl jugador " + modelo.getJugadorActual().getNombre() + " ha finalizado su turno\n";
                modelo.siguienteJugador();
                
                realizar = realizar + "\nTurno del jugador " + modelo.getJugadorActual().getNombre() + "\n";
                
            break;
            
            case TERMINARJUEGO:
                
                if(obtenido){
                    realizar = "\n\t\tEL JUEGO HA FINALIZADO\n\n";
                }else{
                    realizar = "\n\t\tEL JUEGO HA FINALIZADO\n\n";
                    modelo.obtenerRanking();
                    realizar = realizar + "\n\tRESULTADO DE LA PARTIDA\n";
                    realizar = realizar + modelo.mostrar_ranking();
                }
                
            break;
            
            case VENDERPROPIEDAD:
                
                if(casElegida == -1){
                    realizar = "\nNo tienes ninguna propiedad que se pueda vender\n";
                }else{
                    realizar = "\nLa Propiedad " + casElegida + " va a ser vendida\n";
                    modelo.venderPropiedad(casElegida);
                }
                
            break;
        }
        
        return realizar;
    }
}
