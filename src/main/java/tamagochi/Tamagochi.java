package tamagochi;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Tamagochi {
	private String nombre;
	private String estado;
        private String frase;
        private String [][] frases = {{"!Delicioso!", "!Que Rico!", "¿Se puede repetir?", "!Exquisito!", "Le faltaba algo de sangre humana, pero no estaba mal"},
                                {"El descanso me ha sentado fenomenal","Menuda siesta me acabo de echar", "Estoy lleno de energía", "Que calentito se estaba en la cama!","Nada como dormir en un dia de lluvia"},
                                {"Espero que sepas lo que me estas dando","Las medicinas han de tomarse con precaución","El medico me ha recetado eso?","Espero que no me estes drogando","¿Has leido la prescripción?"}};
	private LocalDateTime fecNac;
	private LocalDateTime ultimaComida;
	private int tiempoSobada;
	private LocalDateTime fecEnfermo;
	private int cantidadMedicinas;
	private LocalDateTime fecMedicina;
	private LocalDateTime fecMuerte;
	private String causaMuerte;
	
        
	//NOTIFICACION NECESIDADES
	private boolean hambre;
	private boolean suenio;
	private boolean enfermo;
	private boolean sobredosis;
	
	//REGLAS
	private final static int LIMIT_COMIDA = 30;
	private final static int LIMIT_ENTRE_COMIDA = 5;
	private final static int MIN_SLEEP = 10;
	private final static int MAX_SLEEP = 20;
	private final static int MIN_SLEEP_TIME = 25;
	private final static int MIN_SLEEP_TIME_WARNING = 40;
	private final static int DISEASE_MAX_TIME = 30;
	private final static int DISEASE_CHANCE = 15;
	private final static int CANTIDAD_CURA = 2;
	private final static int LIMIT_ENTRE_MEDICINA = 10;
	
	Tamagochi(String nombre){
		LocalDateTime fecha = LocalDateTime.now();
		this.nombre=nombre;
		this.fecNac=fecha;
		this.ultimaComida=fecha;
                this.fecEnfermo=null;
                this.fecMedicina=null;
                this.fecMuerte=null;
		this.cantidadMedicinas=0;
		this.tiempoSobada=20;
		this.hambre=false;
		this.suenio=false;
		this.enfermo=false;
		this.estado="Bien";
                this.frase="!Hola, encantado de conocerte!";
		this.sobredosis=false;
                
	}
	
	
	public void setEstado() {
		if(!this.hambre && !this.enfermo && !this.suenio) estado = "Bien";
		else {
			estado="";
			if(this.hambre) estado += " Tengo Hambre ";
			if(this.enfermo)estado += " Estoy Enfermo ";  
			if(this.suenio)estado += " Tengo Sue�o "; 
		}
	}
	
        public String getTiempoJuego(){
            return "0"+ChronoUnit.MINUTES.between(this.fecNac, LocalDateTime.now())+":"+(int)(ChronoUnit.SECONDS.between(this.fecNac, LocalDateTime.now())%60d);
        }
        
        public String getTiempoComida(){
            return "Hace "+ChronoUnit.SECONDS.between(this.ultimaComida, LocalDateTime.now())+" Segundos";
        }
        
        public String getTotalDormido(){
            return (this.tiempoSobada-20)+" Segundos";
        }

        public LocalDateTime getFecEnfermo() {
            return fecEnfermo;
        }

        public long getTiempoEnfermo() {
            return ChronoUnit.SECONDS.between(this.fecEnfermo, LocalDateTime.now());
        }

        public int getCantidadMedicinas() {
            return cantidadMedicinas;
        }
        
        
        
	public String getEstado() {
		return this.estado;
	}

        public String getFrase() {
            return frase;
        }
        
	
	public String getNombre() {
		return this.nombre;
	}
	
	public LocalDateTime getMuerte(){
		return this.fecMuerte;
	}
	
	public void setMuerte(){
		this.fecMuerte=LocalDateTime.now();
	}
	
	public String getCausa(){
		return this.causaMuerte;
	}
	
	public boolean finJuego() {
		long tiempo = ChronoUnit.MINUTES.between(this.fecNac, LocalDateTime.now());
		if(tiempo>=7) return true;
		else return false;
	}
	public boolean comprobarEstado(){
		if(!comprobarHambre() || !comprobarSuenio() || !comprobarSalud() || this.sobredosis) {
			this.causaMuerte="";
			this.fecMuerte=LocalDateTime.now();
			if(!comprobarHambre()) this.causaMuerte+=" Hambre ";
			if(!comprobarSuenio()) this.causaMuerte+=" Falta de sue�o ";
			if(!comprobarSalud()) this.causaMuerte+=" Enfermedad ";
			if(sobredosis) this.causaMuerte+=" Sobredosis ";
			return false;
		}
		else {
			setEstado();
			return true;
			}
	}
	
	public boolean comprobarHambre(){
		long tiempo = ChronoUnit.SECONDS.between(this.ultimaComida, LocalDateTime.now()); 
			if(tiempo<LIMIT_COMIDA) {
					if(tiempo>=20) this.hambre=true;
					return true;
				}
			else return false;
		
	}
	public boolean comprobarSuenio(){
		long tiempo = ChronoUnit.SECONDS.between(this.fecNac, LocalDateTime.now()); 
		long minimoTiempo = (tiempo * MIN_SLEEP_TIME) / 100;
		if(tiempoSobada>=minimoTiempo) { 
				if(tiempoSobada<(tiempo * MIN_SLEEP_TIME_WARNING) / 100) this.suenio=true;
				return true;
			}
		else return false;
	}
	public boolean comprobarSalud(){
		boolean bien=true;
		if(fecEnfermo!=null) {
			long tiempo= ChronoUnit.SECONDS.between(fecEnfermo,LocalDateTime.now());
			if(tiempo>DISEASE_MAX_TIME) bien=false;
		}
		return bien;
	}
	
	public void darComida(){
                int valor= (int) (Math.random()*frases[0].length);
		if(this.hambre) this.hambre=false;	
		if(ChronoUnit.SECONDS.between(this.ultimaComida, LocalDateTime.now())<LIMIT_ENTRE_COMIDA || Math.random() <=(DISEASE_CHANCE/100d))  this.setEnfermo();
                
		this.ultimaComida=LocalDateTime.now();
                this.frase=this.frases[0][valor];
	}
	
	public void dormir() throws InterruptedException{
                int valor= (int) (Math.random()*frases[1].length);
		double segundos = Math.random() * ((MAX_SLEEP+1)-MIN_SLEEP) + MIN_SLEEP;
		Thread.sleep((long)(segundos*1000));
		this.tiempoSobada+=segundos;
		this.suenio=false;
                this.frase=this.frases[1][valor];
                if(Math.random() <=(DISEASE_CHANCE/100d)) this.setEnfermo();
	}
	
	//Si toma medicina sin estar enfermo se enferma. No automedicarse sin necesidad. Si no das tiempo entre medicinas le matas.
	public void darMedicina(){
                int valor= (int) (Math.random()*frases[2].length);
                this.frase=this.frases[2][valor];
		if(fecEnfermo == null) this.setEnfermo();
		else {
				if(this.cantidadMedicinas == 0 || ChronoUnit.SECONDS.between(fecMedicina, LocalDateTime.now()) >= LIMIT_ENTRE_MEDICINA ) {
					darMedicamento();
				}
				
				else if (this.cantidadMedicinas > 0) {
					if(ChronoUnit.SECONDS.between(fecMedicina, LocalDateTime.now()) >= LIMIT_ENTRE_MEDICINA) darMedicamento();
					else this.sobredosis=true;
					
				}
		}
	}
	
	//Si recae de la enfermedad antes de recuperarse, se reinician las medicinas pero no la fecha de enfermedad. Practicamente es casi como perder
	public void setEnfermo(){
		if(fecEnfermo==null)this.fecEnfermo = LocalDateTime.now();
		this.enfermo=true;
		this.cantidadMedicinas=0;
		this.fecMedicina=null;
	}
	
	public void darMedicamento() {
		this.fecMedicina=LocalDateTime.now();
		this.cantidadMedicinas+=1;
		if(this.cantidadMedicinas==CANTIDAD_CURA) {
				this.enfermo=false;
				this.fecEnfermo = null;
				this.cantidadMedicinas=0;
				this.fecMedicina=null;
			}
	}
}
