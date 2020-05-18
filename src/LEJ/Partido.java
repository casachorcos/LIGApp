package LEJ;

import java.util.Date;

public class Partido implements Comparable<Partido> {
	//Relacionamos con los dos equipos y la jornada en la que se juega
	private Equipo local;
	private Equipo visitante;
	private Jornada jornada;
	//Datos del partido
	private int golesLocal;
	private int golesVisitante;
	private String campo;
	private Date fecha;
	private int hora;
	private boolean jugado;

	public Partido(Equipo loc, Equipo vis, Jornada jor, String c, Date f, int h) {
		//Inicializamos
		local = loc;
		visitante = vis;
		jornada = jor;
		golesLocal = 0; //Goles a cero al principio
		golesVisitante = 0;
		campo = new String(c);
		fecha = f;
		hora = h;
		jugado = false; //No se ha jugado cuando lo construimos
	}

	public Equipo getLocal() {
		return local;
	}

	public Equipo getVisitante() {
		return visitante;
	}

	public Jornada getJornada() {
		return jornada;
	}

	public int getGolesLocal() {
		return golesLocal;
	}

	public void incrementaGolesLocal() {
		golesLocal++;
	}

	public int getGolesVisitante() {
		return golesVisitante;
	}

	public void incrementaGolesVisitante() {
		golesVisitante++;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public boolean haSidoJugado() {
		return jugado;
	}

	public void setJugado(boolean jugado) {
		this.jugado = jugado;
	}

	@Override
	public int compareTo(Partido p) {
		return getFecha().compareTo(p.getFecha());
	}

}
