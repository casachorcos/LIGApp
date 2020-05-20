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
								// mas jornadas vinculadas a una liga
	}
	
	public Date getFechaInicio() {
		return this.fechaInicio;
	}
	
	public Date getFechaFin() {
		return this.fechaFin;
	}
	
	public int getNumeroJornada() {
		return this.numeroJornada;
	}
	
	public String getNombreLiga() {
		return this.nombreLiga;
	}
	
//	public String toString() {
//		
//		StringJoiner sj = new StringJoiner("; ","( "," )");
//		sj.add("Jornada numero " + numeroJornada + "de la liga " + getNombreLiga());
//		sj.add("Fecha de Inicio: " + getFechaInicio());
//		sj.add("Fecha de Fin: " + getFechaFin() + ".");
//		
//		return sj.toString();
//	}
}
