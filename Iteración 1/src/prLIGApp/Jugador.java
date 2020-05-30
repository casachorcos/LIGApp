package prLIGApp;


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
    }

    public void setRol(String rol){
        if(this.rol != null){
            this.rol = rol;
        }
    }

    public void incGoles(int g){
        goles = goles + g;
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
   
    public void incAmarillas(int a){
        amarillas = amarillas + a;
    }

    public void incRojas(int r){
        rojas = rojas + r;
    }

    public String toString() {
		return id + " " + nombre;
	}
    
    public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Jugador) {
			Jugador u = (Jugador) o;
			res = id == u.getId();
		}
		
		return res;
	}
}
