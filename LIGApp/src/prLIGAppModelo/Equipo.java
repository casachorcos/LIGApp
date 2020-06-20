package prLIGAppModelo;


import java.util.*;

public class Equipo {
	
	private int id;
	private List<Jugador> ListaJugadores;
	private String nombre;
	  
	public Equipo(int id, String nom) {
		nombre = nom;
		this.id = id;
		ListaJugadores = new ArrayList<Jugador>(50);
	}
	 
	public void aniadirJugador(Jugador j) {
		ListaJugadores.add(j);
		//A�adir a la base de datos?
	}
	  
	public void eliminarJugador(Jugador j) {
		ListaJugadores.remove(j);
		//Quitar de la base de datos
	}

	public int getId() {
		return id;
	}
	
	public List<Jugador> getListaJugadores() {
		return ListaJugadores;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
		//Cambiar en la base de datos
	}
	
	public String toString() {
		return nombre;
	}
	
	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Equipo) {
			Equipo u = (Equipo) o;
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
