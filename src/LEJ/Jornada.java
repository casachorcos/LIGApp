package LEJ;

import java.util.*;

public class Jornada {
	
	private int numeroJornada;
	private Date fechaInicio;
	private Date fechaFin;
	private String nombreLiga;
	
	public Jornada() {
		
	}
	
	public Jornada(Date fIni, Date fFin, Liga l) {
		
		this.fechaInicio = fIni;
		this.fechaFin = fFin;
		this.nombreLiga = l.getNombre();
		this.numeroJornada = 0; // Deberia incrementarse segun haya 
								// más jornadas vinculadas a una liga
	}
	
	public void getFechaInicio() {
		return this.fechaInicio;
	}
	
	public void getFechaFin() {
		return this.fechaFin;
	}
	
	public void getNumeroJornada() {
		return this.numeroJornada;
	}
	
	public String toString() {
		
		StringJoiner sj = new StringJoiner("; ","( "," )");
		sj.add("Jornada número " + numeroJornada + "de la liga " this.nombreLiga);
		sj.add("Fecha de Inicio: " + getFechaInicio());
		sj.add("Fecha de Fin: " + getFechafin() + ".");
		
		return sj.toString();
	}
}
