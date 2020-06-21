package prLIGAppClases;


public class Clasificacion implements Comparable<Clasificacion>{
	private String Equipo;
	private int puntos;
	private int golesmarcados;
	private int golesencontra;
	private int partidosjugados;
	
	public Clasificacion(String Eq, int p, int gm, int ge, int pj){
		Equipo = Eq;
		puntos = p;
		golesmarcados = gm;
		golesencontra = ge;
		partidosjugados = pj;
	}

	public String getEquipo() {
		return Equipo;
	}

	public void setEquipo(String equipo) {
		Equipo = equipo;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getGolesmarcados() {
		return golesmarcados;
	}

	public void setGolesmarcados(int golesmarcados) {
		this.golesmarcados = golesmarcados;
	}

	public int getGolesencontra() {
		return golesencontra;
	}

	public void setGolesencontra(int golesencontra) {
		this.golesencontra = golesencontra;
	}

	public int getPartidosjugados() {
		return partidosjugados;
	}

	public void setPartidosjugados(int partidosjugados) {
		this.partidosjugados = partidosjugados;
	}

	@Override
	public int compareTo(Clasificacion o) {
		return o.getPuntos() - puntos;
	}
	
	public String toString() {
		return Equipo + " " + puntos + " " + golesmarcados + " " + golesencontra + " " + partidosjugados;
	}
	
}
