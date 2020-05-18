package LEJ;

import java.util.*;

public class Equipo {
	
	private int id;
	private List<Jugador> ListaJugadores;
	private String nombre;
	  
	public Equipo(String nom) {
		nombre = nom;
		id = 0;
		ListaJugadores = new ArrayList<Jugador>(50);
	}
	  
	public Equipo(){
		new Equipo(null);
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
	
}
