package vistatextualqytetet;

import java.util.ArrayList;
import controladorqytetet.*;
import java.util.Scanner;
import modeloqytetet.Qytetet;

/**
 *
 * @author josu
 */
public class VistaTextualQytetet {
    
    private static Qytetet modelo;
    private static ControladorQytetet controlador;
    private static VistaTextualQytetet vista;
    
    //prueba
    private static final Scanner teclado = new Scanner (System.in);
    
    public VistaTextualQytetet(){
        controlador = ControladorQytetet.getInstance();
        modelo = Qytetet.getInstance();
    }
    
    //Metodo propio auxiliar
    private ArrayList<String> parserArrayIntString (ArrayList<Integer> elegibles){
        ArrayList<String> validasTexto = new ArrayList<>();
        for(Integer i : elegibles){
            validasTexto.add(Integer.toString(i));
        }

        return validasTexto;
    }
    
    public ArrayList<String> obtenerNombreJugadores(){
        int i = 1;
        String n;
        ArrayList<String> nombresJugadores = new ArrayList<>();
        
        System.out.println("Nombre del jugador " + i);
        n = teclado.nextLine();

        while(!n.equalsIgnoreCase("ok") && i<4){
            nombresJugadores.add(n);
            i++;
            System.out.println("Nombre del jugador " + i);
            n = teclado.nextLine();
        };
        System.out.println("Datos recogidos, pulse una tecla para continuar");
        teclado.nextLine(); //Recolector Basura
        /*
        nombresJugadores.add("A-Player");
        nombresJugadores.add("B-Player");
        nombresJugadores.add("C-Player");
        nombresJugadores.add("D-Player");
        */
        return nombresJugadores;
    }
    
    public int elegirCasilla(int opcionMenu){
        ArrayList<Integer> validas = controlador.obtenerCasillasValidas(opcionMenu);
        
        if(validas.isEmpty()){
            return -1;
        }
        
        System.out.print("CASILLAS VALIDAS\t[ ");
        for(Integer v : validas){
            System.out.print(v + " ");
        }
        System.out.println("]");
        
        ArrayList<String> validasTexto = parserArrayIntString(validas);
        
        int resultado = Integer.parseInt(leerValorCorrecto(validasTexto));
        
        return resultado;
    }
    
    public String leerValorCorrecto(ArrayList<String> valoresCorrectos){
        String lectura;
        
        while(true){
            lectura = teclado.nextLine();
            
            for(String va : valoresCorrectos){
                if(lectura.equals(va)){
                    return lectura;
                }
            }
            
            System.out.println("Opción incorrecta, vuelve a elegirla");
        }
    }
    
    public int elegirOperacion(){
        ArrayList<Integer> validas = controlador.obtenerOperacionesJuegoValidas();
        
        //Uso de un método propio
        ArrayList<String> validasTexto = parserArrayIntString(validas);
        
        System.out.println("\nOPCIONES POSIBLES");
        for(Integer f : validas){
            System.out.println(f + ".-" + OpcionMenu.values()[f]);
        }
        System.out.println("");
        
        String resultado = leerValorCorrecto(validasTexto);
        int res = Integer.parseInt(resultado);
        
        return res;
        
    }
    
    public static void main(String[] args) {
        System.out.println("\t\t\t\tQYTETET");
        System.out.println("\t\tMODO DE JUEGO --> VISTA TEXTUAL\n");
        
        vista = new VistaTextualQytetet();
        
        int operacionElegida = -1;
        int casillaElegida = -1;
        boolean necesitaElegirCasilla;
        
        controlador.setNombreJugadores(vista.obtenerNombreJugadores());
        
        do{
            operacionElegida = vista.elegirOperacion();
            
            necesitaElegirCasilla = controlador.necesitaElegirCasilla(operacionElegida);
            
            if (necesitaElegirCasilla){
                casillaElegida = vista.elegirCasilla(operacionElegida);
            }
            
            System.out.println(controlador.realizarOperacion(operacionElegida,casillaElegida));
            
        }while(operacionElegida != OpcionMenu.TERMINARJUEGO.ordinal());
    }
}
