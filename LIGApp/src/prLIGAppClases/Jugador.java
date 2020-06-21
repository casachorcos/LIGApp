package prLIGAppClases;



public class Jugador {
    private String nombre;
    private int id;

    private int edad;
    private String rol;
    private int goles = 0;
    private int amarillas = 0;
    private int rojas = 0;
    private boolean capitan = false;

    public Jugador(int id, String nombre, int edad) {
    	this.nombre = nombre;
    	this.edad = edad;
    	this.id = id;
    	goles = 0;
    	amarillas = 0;
    	rojas = 0;
    }

    public void setRol(String rol){
    	if (rol != null) {
    		this.rol = rol.toString();
    	}
    }
    
    public String getRol(){
    	if (rol == null) {
    		return "Sin posición";
    	} else {
    		return rol;
    	}
    }

    public void setGoles(int g){
        goles = g;
    }
    
    public int getGoles() {
    	return goles;
    }
    
    public void setAmarillas(int a){
        amarillas = a;
    }
    
    public int getAmarillas() {
    	return amarillas;
    }

    public void setRojas(int r){
        rojas = r;
    }
    
    public int getRojas() {
    	return rojas;
    }

    public String getNombre() {
    	return nombre;
    }
    
    public int getEdad() {
    	return edad;
    }    

    public int getId() {
    	return id;
    }    
   
    

    public String toString() {
		return nombre;
	}
    
    public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Jugador) {
			Jugador u = (Jugador) o;
			res = id == u.getId();
		}
		
		return res;
	}
    public String code(String user) {
		String c = user, cadena = "";
		for (int i = 0; i < c.length(); i++) {
			char a = c.charAt(i);
			int x = a;
			x -= 30;
			cadena += x;
		}
		cadena = cadena.concat("00");
		int id = this.getId();
		cadena += ((id*3)+4);
		return cadena;
	}
    
}
