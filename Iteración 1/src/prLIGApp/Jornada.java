package prLIGApp;

import java.text.SimpleDateFormat;

import java.util.*;

import java.sql.Date;

public class Jornada {
	
	private int codigoJornada;
	private int numeroJornada;
	private Date fechaInicio;
	private Date fechaFin;
	private String nombreLiga;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			
	public Jornada(int cod) {
		
		codigoJornada = cod;
		fechaInicio = new Date(Calendar.getInstance().getTime().getTime());
		fechaFin = new Date(fechaInicio.getTime() + 6 * 24 * 60 * 60 * 1000);
		this.nombreLiga = null;
		this.numeroJornada = 1;
	}
	
	public Jornada(int cod, String nomLiga) {
		
		fechaInicio = new Date(Calendar.getInstance().getTime().getTime());
		fechaFin = new Date(fechaInicio.getTime() + 6 * 24 * 60 * 60 * 1000);
		this.nombreLiga = nomLiga;
		this.numeroJornada = 1;
	}
	
	public Jornada(int cod, int numJor, String nomLiga, Date fIni) {
		
		fechaInicio = fIni;
		fechaFin = new Date(fechaInicio.getTime() + 6 * 24 * 60 * 60 * 1000);
		this.nombreLiga = nomLiga;
		this.numeroJornada = numJor;
	}
	
	public Jornada(int cod, int numJor, String nomLiga, Date fIni, Date fFin) {
		
		fechaInicio = fIni;
		fechaFin = fFin;
		this.nombreLiga = nomLiga;
		this.numeroJornada = numJor;
	}
	
	public int getCodigoJornada() {
		return this.codigoJornada;
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
	
	public void setCodigoJornada(int cod) {
		codigoJornada = cod;
	}
	
	public void setFechaInicio(Date fIni) {
		fechaInicio = fIni;
	}
	
	public void setFechaFin(Date fFin) {
		fechaFin = fFin;
	}
	
	public void setNumeroJornada(int numJor) {
		numeroJornada = numJor;
	}
	
	public void setNombreLiga(Liga l) {
		nombreLiga = l.getNombre();
	}
	
	public String toString() {
		
		StringJoiner sj = new StringJoiner("; ","( "," )");
		sj.add("Jornada numero " + getNumeroJornada() + " de la liga " + getNombreLiga());
		sj.add("Fecha de Inicio: " + formatter.format(getFechaInicio()));
		sj.add("Fecha de Fin: " + formatter.format(getFechaFin())+ ". (CODIGO JORNADA: " + getCodigoJornada() + ").");
		
		return sj.toString();
	}
}