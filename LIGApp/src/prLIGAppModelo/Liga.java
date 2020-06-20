package prLIGAppModelo;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Liga {

	private File Logo;
	private int id;
	private List<Equipo> ListaEquipos;
	private String nombre;
	
	public Liga(int id, String nom) {
		nombre = nom;
		this.id = id;
		ListaEquipos = new ArrayList<Equipo>(50);
	}
	
	public void aniadirEquipo(Equipo e) {
		ListaEquipos.add(e);
	}
	
	public void setLogo(File l) {
		Logo = l;
	}
	  
	public void eliminarEquipo(Equipo e) {
		ListaEquipos.remove(e);
	}

	public int getId() {
		return id;
	}
	
	public List<Equipo> getListaEquipos() {
		return ListaEquipos;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String toString() {
		return nombre;
	}
	
	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Liga) {
			Liga u = (Liga) o;
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
