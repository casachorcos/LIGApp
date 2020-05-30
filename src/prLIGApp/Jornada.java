package prLIGApp;

import java.text.SimpleDateFormat;

import java.util.*;

import java.sql.Date;

public class Jornada {
	
	private int codigoJornada;
	private int numeroJornada;
	private Date fechaInicio;
	private Date fechaFin;
	private int idLiga;
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			
	public Jornada(int cod) {
		
		codigoJornada = cod;
		fechaInicio = new Date(Calendar.getInstance().getTime().getTime());
		fechaFin = new Date(fechaInicio.getTime() + 6 * 24 * 60 * 60 * 1000);
		this.idLiga = 0;
		this.numeroJornada = 1;
	}
	
	public Jornada(int cod, int id) {
		
		codigoJornada = cod;
		fechaInicio = new Date(Calendar.getInstance().getTime().getTime());
		fechaFin = new Date(fechaInicio.getTime() + 6 * 24 * 60 * 60 * 1000);
		this.idLiga = id;
		this.numeroJornada = 1;
	}
	
	public Jornada(int cod, int numJor, int id, Date fIni) {
		
		codigoJornada = cod;
		fechaInicio = fIni;
		fechaFin = new Date(fechaInicio.getTime() + 6 * 24 * 60 * 60 * 1000);
		this.idLiga = id;
		this.numeroJornada = numJor;
	}
	
	public Jornada(int cod, int numJor, int id, Date fIni, Date fFin) {
		
		codigoJornada = cod;
		fechaInicio = fIni;
		fechaFin = fFin;
		this.idLiga = id;
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
	
	public int getIdLiga() {
		return this.idLiga;
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
	
	public void setNombreLiga(int id) {
		idLiga = id;
	}
	
	public String toString() {
		
		StringJoiner sj = new StringJoiner("; ","( "," )");
		sj.add("Jornada numero " + getNumeroJornada() + " de la liga con id " + getIdLiga());
		sj.add("Fecha de Inicio: " + formatter.format(getFechaInicio()));
		sj.add("Fecha de Fin: " + formatter.format(getFechaFin())+ ". (CODIGO JORNADA: "
		+ getCodigoJornada() + ").");
		
		return sj.toString();
	}
}