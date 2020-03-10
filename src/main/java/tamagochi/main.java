package tamagochi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class main {
	public static Tamagochi t1;
	public static void main(String[] args) throws InterruptedException {
		Scanner teclado = new Scanner(System.in);
		boolean vivo;
		int op1, op2;
		
                printTitulo();
                teclado.nextLine();
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		do {
			menuInicio();
			op1=teclado.nextInt();
			teclado.nextLine();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			if(op1==1) {
				crearTamagochi();
                                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				do{
					menuTama();
					op2=teclado.nextInt();
					teclado.nextLine();
					vivo = t1.comprobarEstado();
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					
					if (vivo){
						if(op2==1) t1.darComida();
						else if(op2==2) {printDormir();  t1.dormir();  System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");}
						else if(op2==3) t1.darMedicina();
						else System.out.println("Opcion Incorrecta");
						vivo = t1.comprobarEstado();
					}
				}while(vivo  && !t1.finJuego());
				
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				if(!vivo) printMuerto();
                                else printVictoria();
				System.out.println("Pulsa ENTER para continuar....");
				teclado.nextLine();
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			}
			else if(op1==2) verInstrucciones();
			else if(op1!=0)System.out.println("Opcion Incorrecta");
		} while(op1!=0 ||(op1<0 || op1>2));
		System.out.println("Adios!!!!!!!!!!");
		teclado.close();
	}
	
	public static void menuInicio(){
		System.out.println("-----------------------------------");
		System.out.println("JUEGO TAMAGOCHI");
		System.out.println("-----------------------------------");
		System.out.println("1 - Crear Tamagochi");
		System.out.println("2 - Instrucciones");
		System.out.println("0 - Salir");
		System.out.println("-----------------------------------");
		System.out.println("Dime opcion: ");
	}
	
	public static void menuTama() {         
        System.out.println("-------------------------------------------");
        System.out.println("MENU: "+t1.getNombre().toUpperCase());
        System.out.println("-------------------------------------------");
        System.out.println("                   |||  ");
        System.out.println("                  (o.o)  -|"+t1.getFrase()+"|");
        System.out.println("                    V    ");
        System.out.println("                   /|\\   ");
        System.out.println("                  º | º ");
        System.out.println("                   / \\   ");
        System.out.println("-------------------------------------------");
        System.out.println("TIEMPO: " +t1.getTiempoJuego());
        System.out.println("ESTADO: " + t1.getEstado().toUpperCase());
        System.out.println("ULTIMA COMIDA:  " + t1.getTiempoComida());
        System.out.println("TOTAL DORMIDO:  " + t1.getTotalDormido());
        System.out.print("SALUD: ");
        if(t1.getFecEnfermo()!=null) System.out.println("Enfermo hace " + t1.getTiempoEnfermo() + " Segundos. Le diste " +t1.getCantidadMedicinas() + " medicinas.");
        else System.out.println("Esta sano");
        System.out.println("-------------------------------------------");
        System.out.print("| 1 - COMER | 2 - DORMIR | 3 - DAR MEDICINA|\n");
        System.out.println("-------------------------------------------");
        System.out.println("Dime opcion: ");
	}
	
	public static void crearTamagochi(){
		Scanner teclado = new Scanner(System.in);
		System.out.println("Dime Nombre del bicho: ");
		t1=new Tamagochi(teclado.nextLine());
	}
	
        public static void printDormir(){
            System.out.println("DURMIENDO...");
            System.out.println("-------------------------------------------");
            System.out.println("___");
            System.out.println("|  |");
            System.out.println("|  |");
            System.out.println("|  |---------|----------------------------|");
            System.out.println("|  |         |                            |");
            System.out.println("|  |   (X )-E|                            |");
            System.out.println("|  |_________|__________________________  |");
            System.out.println("|  |                                   |  |");
            System.out.println("|  |                                   |  |");
            System.out.println("----                                   ----");
            System.out.println("-------------------------------------------");
           
        }
        
	public static void printMuerto(){
          System.out.println("-------------------------------------------");
          System.out.println("              ______________  ");
          System.out.println("             |              | ");
          System.out.println("             |      RIP     | ");
          System.out.println("             |              | ");
          System.out.println("             |              | ");
          System.out.println("             |              | ");
          System.out.println("             |              | ");
          System.out.println("---------------------------------------------------------------------------------");
          System.out.println(t1.getNombre() + " ha muerto el " + formatoFecha() + ". Causa: " + t1.getCausa());
          System.out.println("---------------------------------------------------------------------------------");
        }
        
        public static void printTitulo(){
             System.out.println("_________________________________________________________________________________________________________");
             System.out.println(" _________   ________       ___ __ __      ________       _______      ______       ______       ___   ___      ________ ");
             System.out.println("/________/\\ /_______/\\     /__//_//_/\\    /_______/\\     /______/\\    /_____/\\     /_____/\\     /__/\\ /__/\\    /_______/\\");
             System.out.println("\\__.::.__\\/ \\::: _  \\ \\    \\::\\| \\| \\ \\   \\::: _  \\ \\    \\::::__\\/__  \\:::_ \\ \\    \\:::__\\/     \\::\\ \\\\  \\ \\   \\__.::._\\/ ");
             System.out.println("   \\::\\ \\    \\::(_)  \\ \\    \\:.      \\ \\   \\::(_)  \\ \\    \\:\\ /____/\\  \\:\\ \\ \\ \\    \\:\\ \\  __    \\::\\/_\\ .\\ \\     \\::\\ \\   ");
             System.out.println("    \\::\\ \\    \\:: __  \\ \\    \\:.\\-/\\  \\ \\   \\:: __  \\ \\    \\:\\\\_  _\\/   \\:\\ \\ \\ \\    \\:\\ \\/_/\\    \\:: ___::\\ \\    _\\::\\ \\__ ");
             System.out.println("     \\::\\ \\    \\:.\\ \\  \\ \\    \\. \\  \\  \\ \\   \\:.\\ \\  \\ \\    \\:\\_\\ \\ \\    \\:\\_\\ \\ \\    \\:\\_\\ \\ \\    \\: \\ \\\\::\\ \\  /__\\::\\__/\\");
             System.out.println("      \\__\\/     \\__\\/\\__\\/     \\__\\/ \\__\\/    \\__\\/\\__\\/     \\_____\\/     \\_____\\/     \\_____\\/     \\__\\/ \\::\\/  \\________\\/");
             System.out.println("_________________________________________________________________________________________________________");
     
             System.out.println("Presiona ENTER para continuar........");
        }
        public static void verInstrucciones() {
		Scanner teclado = new Scanner(System.in);
		System.out.println("-----------------------------------");
		System.out.println("INSTRUCCIONES");
		System.out.println("-----------------------------------");
		System.out.println("El objetivo del juego es mantener vivo a la criatura durante 7 minutos. Para ello, debes controlar:");
		System.out.println("   1 - El Hambre. El tamagochi debera alimentarse en intervalos maximos de 30 segundos. Avisar� que tiene hambre cada 20 segs y se enfermar� si come dos veces en menos de 5 segundos");
		System.out.println("   2 - El Sueño. El tamagochi necesita descansar un cuarto de su tiempo de vida. La duracion del sue�o varia entre 10 y 20 segundos. Al crearse se tiene en cuenta que ha dormido 20 segs");
		System.out.println("   3 - La salud. Existe la posibilidad de que el tamagochi enferme al comer y dormir (10%). Debes suministrarle 2 remedios para curarlo en los proximos 30 segundos, separados en 10 segundos minimo. Darle cura sin estar enfermo le enferma.");
		System.out.println("");
		System.out.println("Cabe destacar que el estado del tamagochi ser� mostrado en la ejecucion de cada accion del men�");
		System.out.println("");
		System.out.println("Presiona ENTER para continuar...");
		teclado.nextLine();
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
        
        public static void printVictoria(){
            System.out.println("___________________________________________________________________________________________________________");
            System.out.println("######## ######## ######## ######## ######## ######## ######## ######## ######## ######## ######## ########");
            System.out.println("##    ## #  ##  # ###  ### ##    ## ######## ##    ## ###  ### #  ##  # ###  ### #    ### ##    ## ##    ##");
            System.out.println("##    ## #  ##  # ##    ## #  ##  # ######## #  ##  # ##    ## #   #  # ##    ## #  #  ## #  ##  # ##    ##");
            System.out.println("###  ### #      # #  ##  # #    ### ######## #  ##### #  ##  # #      # #  ##  # #  ##  # #  ##  # ###  ###");
            System.out.println("######## #  ##  # #  ##  # #  ##  # ######## #  ##  # #  ##  # #  #   # #  ##  # #  #  ## #  ##  # ########");
            System.out.println("###  ### #  ##  # #  ##  # ##    ## ######## ##    ## #  ##  # #  ##  # #  ##  # #    ### ##    ## ###  ###");
            System.out.println("######## ######## ######## ######## ######## ######## ######## ######## ######## ######## ######## ########");
            System.out.println("___________________________________________________________________________________________________________");
            System.out.println("Has mantenido vido a " +t1.getNombre() + " con Exito. Has ganado!!");
            System.out.println("___________________________________________________________________________________________________________");
            
        
        }
        
        public static String formatoFecha() {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
          return t1.getMuerte().format(formatter);
        }
}
