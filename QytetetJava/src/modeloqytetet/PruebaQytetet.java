package modeloqytetet;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author josu
 */
public class PruebaQytetet {
    
    //Atributo estático del juego
    private static Qytetet juego;
   
    //prueba
    private static final Scanner in = new Scanner (System.in);
    
    
    public static void principal(String[] args) {
        int i = 1;
        String n;
        ArrayList<String> nombresJugadores = new ArrayList<>();
/*
        System.out.println("Nombre del jugador " + i);
        n = in.nextLine();

        while(!n.equalsIgnoreCase("ok") && i<4){
            nombresJugadores.add(n);
            i++;
            System.out.println("Nombre del jugador " + i);
            n = in.nextLine();
        };
*/

        nombresJugadores.add("A-Player");
        nombresJugadores.add("B-Player");
        nombresJugadores.add("C-Player");
        nombresJugadores.add("D-Player");

        juego = Qytetet.getInstance();

        juego.inicializarJuego(nombresJugadores);


        System.out.println(juego.toString());
        
/*
        for(int g=0; g<6; g++){
            juego.getDado().tirar();
        System.out.println(juego.getDado().toString());
        }
        
*/
        System.out.println("\t\t#################################################");
        System.out.println("\t\t#\tPRUEBA DE LOS METODOS CREADOS\t\t#\n");

        System.out.println("\nEl jugador actual es " + juego.getJugadorActual().getNombre());

        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
       
/*        
        juego.siguienteJugador();
        System.out.println("\nEl jugador actual es " + juego.getJugadorActual().getNombre());
        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
        
        
        juego.siguienteJugador();
        System.out.println("\nEl jugador actual es " + juego.getJugadorActual().getNombre());
        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
        
        
        juego.siguienteJugador();
        System.out.println("\nEl jugador actual es " + juego.getJugadorActual().getNombre());
        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
*/

/*
        juego.jugar();
        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
*/

/*
        juego.mover(16);
      
        if (juego.getJugadorActual().getEncarcelado() ){
            System.out.println("Estoy la carcel por jugar con Heisenberg");
            System.out.println("En la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        }else{
            System.out.println("Aun no me han pillado");
            System.out.println("y " + juego.getJugadorActual().getNombre() + " se mueve a la casilla "+ juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        }
         
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
        
        juego.mover(3);
   
        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
*/

/*        
        juego.mover(18);
        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
        
        juego.mover(4);
        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
*/        

/*
        juego.mover(3);
        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
        
        juego.comprarTituloPropiedad();
        TituloPropiedad t = juego.obtenerCasillaJugadorActual().getTitulo();
        System.out.println("Compra la propiedad " + t.getNombre() + "con un coste de "+ t.getPrecioCompra());
        
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
        
        
        juego.siguienteJugador();
        juego.mover(3);
        
        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");      
        
        juego.siguienteJugador();
        juego.mover(3);
        
        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
        
        juego.siguienteJugador();
        juego.mover(3);
        
        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
        
        
        juego.siguienteJugador();
        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
*/

    /*
        new Sorpresa ("IR", TipoSorpresa.IRACASILLA, 7);        
        new Sorpresa ("CARCEL", TipoSorpresa.IRACASILLA, 5);
        new Sorpresa ("SALIR", TipoSorpresa.SALIRCARCEL, 0);
        new Sorpresa ("IR", TipoSorpresa.IRACASILLA, 19);
        new Sorpresa ("PAGARCOBRAR", TipoSorpresa.PAGARCOBRAR, 450;
        new Sorpresa ("PAGARCOBRAR", TipoSorpresa.PAGARCOBRAR, -300);
        new Sorpresa ("PORJUGADOR", TipoSorpresa.PORJUGADOR, 100);
        new Sorpresa ("PORJUGADOR", TipoSorpresa.PORJUGADOR, -130);
        new Sorpresa ("PORCASAHOTEL", TipoSorpresa.PORCASAHOTEL, -40);
        new Sorpresa ("PORCASAHOTEL", TipoSorpresa.PORCASAHOTEL, 35);
    
    */

/*
        juego.mover(3);
        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
        
        juego.comprarTituloPropiedad();
        TituloPropiedad t = juego.obtenerCasillaJugadorActual().getTitulo();
        System.out.println("Compra la propiedad " + t.getNombre() + "con un coste de "+ t.getPrecioCompra());
        
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
        
        
        System.out.println("Se edifica una casa. Coste: " + t.getPrecioEdificar());
        juego.edificarCasa(3);
        
        System.out.println("Se edifica un hotel. Coste: " + t.getPrecioEdificar());
        juego.edificarHotel(3);
        
        System.out.println("Se edifica una casa. Coste: " + t.getPrecioEdificar());
        juego.edificarCasa(3);

        System.out.println("Se edifica una casa. Coste: " + t.getPrecioEdificar());
        juego.edificarCasa(3);
        
        System.out.println("Se edifica una casa. Coste: " + t.getPrecioEdificar());
        juego.edificarCasa(3);
        
        System.out.println("Se edifica una casa. Coste: " + t.getPrecioEdificar());
        juego.edificarCasa(3);
        
        
        System.out.println("Se edifica una Hotel. Coste: " + t.getPrecioEdificar());
        juego.edificarHotel(3);
        
        System.out.println("Se edifica una Hotel. Coste: " + t.getPrecioEdificar());
        juego.edificarHotel(3);
        
        System.out.println("La Propiedad comprada del jugador " + juego.getJugadorActual().getNombre() + " tiene " + t.getNumCasas() + " casas");
        System.out.println("La Propiedad comprada del jugador " + juego.getJugadorActual().getNombre() + " tiene " + t.getNumHoteles() + " hoteles");
        
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
        
        System.out.println("Cuantas casas y hoteles tengo " + juego.getJugadorActual().cuantasCasasHotelesTengo());
        
        
        
        
        juego.aplicarSorpresa();
        System.out.println("Sorpresa aplicada\n");
        
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
*/

/*
        Sorpresa carta = new Sorpresa ("PORJUGADOR", TipoSorpresa.PORJUGADOR, -100);
        juego.setCartaActual(carta);
        System.out.println(carta.toString());
        
        juego.aplicarSorpresa();
        System.out.println("Sorpresa Aplicada");
        
        for(Jugador pj : juego.getJugadores()){
            System.out.println("\tEl saldo del jugador " + pj.getNombre() + " es " + pj.getSaldo());
        }

*/

/*
        Sorpresa carta = new Sorpresa ("IR", TipoSorpresa.IRACASILLA, 7);
        juego.setCartaActual(carta);
        System.out.println(carta.toString());
        
        juego.aplicarSorpresa();
        
        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());

*/

/*
        Sorpresa carta = new Sorpresa ("CARCEL", TipoSorpresa.IRACASILLA, 5);
        juego.setCartaActual(carta);
        System.out.println(carta.toString());
        
        juego.aplicarSorpresa();
        
        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        
        if(juego.getJugadorActual().getEncarcelado()){
            System.out.println("Con Heisenberg");
        }
*/

/*
        Sorpresa carta = new Sorpresa ("PAGARCOBRAR", TipoSorpresa.PAGARCOBRAR, -300);
        juego.setCartaActual(carta);
        System.out.println(carta.toString());
        
        juego.aplicarSorpresa();
        System.out.println("Sorpresa Aplicada");
        
        System.out.println("\tEl saldo del jugador " + juego.getJugadorActual().getNombre() + " es " + juego.obtenerSaldoJugadorActual());
*/

/*
        System.out.println("La primera carta del mazo es: ");
        System.out.println(juego.getMazo().get(0).toString());
        
        juego.mover(6);
        juego.aplicarSorpresa();
        
        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");

*/

/*
        Sorpresa carta = new Sorpresa ("SALIR", TipoSorpresa.SALIRCARCEL, 0);
        juego.setCartaActual(carta);
        System.out.println(carta.toString());
        
        juego.aplicarSorpresa();
        System.out.println("Sorpresa Aplicada");
        
        if(juego.getJugadorActual().getCartaLibertad() != null){
            System.out.println("Con Salvoconducto");
        }else{
            System.out.println("Corro peligro");
        }
        
        juego.mover(16);
        
        if(juego.getJugadorActual().getEncarcelado()){
            System.out.println("Con Heisenberg");
        }else{
            System.out.println("Haciendo drogas");
        }
        
*/

/*
        String salida = "El jugador " + juego.getJugadorActual().getNombre() + " tiene las propiedades [";
        
        for( Integer pr : juego.obtenerPropiedadesJugador() ){
            salida = salida + pr + " ";
        }
        salida = salida + "]\n";
        
        System.out.println(salida);
        
        
        juego.mover(3);
        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        
        juego.comprarTituloPropiedad();
        System.out.println("Propiedad comprada. Coste " + juego.obtenerCasillaJugadorActual().getTitulo().getPrecioCompra());
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
        
        
        salida = "El jugador " + juego.getJugadorActual().getNombre() + " tiene SIN HIPOTECAR las propiedades [";
        
        for( Integer pr : juego.obtenerPropiedadesJugadorSegunEstadoHipteca(false)){
            salida = salida + pr + " ";
        }
        salida = salida + "]\n";

        System.out.println(salida);
        
        salida = "El jugador " + juego.getJugadorActual().getNombre() + " tiene HIPOTECADAS las propiedades [";
        
        for( Integer pr : juego.obtenerPropiedadesJugadorSegunEstadoHipteca(true)){
            salida = salida + pr + " ";
        }
        salida = salida + "]\n";

        System.out.println(salida);
        
        salida = "El jugador " + juego.getJugadorActual().getNombre() + " tiene las propiedades [";
        
        for( Integer pr : juego.obtenerPropiedadesJugador() ){
            salida = salida + pr + " ";
        }
        salida = salida + "]\n";
        
        System.out.println(salida);
        
        
        juego.hipotecarPropiedad(3);
        System.out.println("Propiedad Hipotecada");
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
        
        
        salida = "El jugador " + juego.getJugadorActual().getNombre() + " tiene SIN HIPOTECAR las propiedades [";
        
        for( Integer pr : juego.obtenerPropiedadesJugadorSegunEstadoHipteca(false)){
            salida = salida + pr + " ";
        }
        salida = salida + "]\n";

        System.out.println(salida);
        
        salida = "El jugador " + juego.getJugadorActual().getNombre() + " tiene HIPOTECADAS las propiedades [";
        
        for( Integer pr : juego.obtenerPropiedadesJugadorSegunEstadoHipteca(true)){
            salida = salida + pr + " ";
        }
        salida = salida + "]\n";

        System.out.println(salida);
        
        salida = "El jugador " + juego.getJugadorActual().getNombre() + " tiene las propiedades [";
        
        for( Integer pr : juego.obtenerPropiedadesJugador() ){
            salida = salida + pr + " ";
        }
        salida = salida + "]\n";
        
        System.out.println(salida);
        
        
        
        juego.cancelarHipoteca(3);
        System.out.println("Hipoteca cancelada");
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
        
        salida = "El jugador " + juego.getJugadorActual().getNombre() + " tiene SIN HIPOTECAR las propiedades [";
        
        for( Integer pr : juego.obtenerPropiedadesJugadorSegunEstadoHipteca(false)){
            salida = salida + pr + " ";
        }
        salida = salida + "]\n";

        System.out.println(salida);
        
        salida = "El jugador " + juego.getJugadorActual().getNombre() + " tiene HIPOTECADAS las propiedades [";
        
        for( Integer pr : juego.obtenerPropiedadesJugadorSegunEstadoHipteca(true)){
            salida = salida + pr + " ";
        }
        salida = salida + "]\n";

        System.out.println(salida);
        
        salida = "El jugador " + juego.getJugadorActual().getNombre() + " tiene las propiedades [";
        
        for( Integer pr : juego.obtenerPropiedadesJugador() ){
            salida = salida + pr + " ";
        }
        salida = salida + "]\n";
        
        System.out.println(salida);
*/        

/*
        juego.mover(3);
        System.out.println("\n" + juego.getJugadorActual().getNombre() + " está en la casilla " + juego.obtenerCasillaJugadorActual().getNumeroCasilla());
        
        juego.comprarTituloPropiedad();
        System.out.println("Propiedad comprada. Coste " + juego.obtenerCasillaJugadorActual().getTitulo().getPrecioCompra());
        System.out.println("\tEl saldo del jugador "+ juego.getJugadorActual().getNombre() + " es " +juego.obtenerSaldoJugadorActual() + "\n\n");
        
        
        juego.siguienteJugador();
        juego.mover(3);
        
        
        juego.siguienteJugador();
        juego.mover(3);
        
        juego.siguienteJugador();
        juego.mover(3);
        
        for(Jugador pjs : juego.getJugadores()){
            System.out.println(pjs.toString());
        }
        
*/
        
        
/*
        juego.obtenerRanking();
        
        System.out.println("\n\nRanking\n");
        for(Jugador jugador : juego.getJugadores()){
            System.out.println(jugador.toString());
        }
*/
       
    }
    
}
