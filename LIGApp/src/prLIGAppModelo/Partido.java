package prLIGAppModelo;

import java.text.SimpleDateFormat;

import java.util.*;

import java.sql.Date;

public class Partido {

    private int codigoPartido;
    private int idLocal;
    private int idVisitante;
    private int codigoJornada;
    private int golesLocal;
    private int golesVisitante;
    private String campo;
    private Date fecha;
    private boolean jugado;
    private String hora;


    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public Partido(int cod, int idL, int idV, int codJor) {

        codigoPartido = cod;
        idLocal = idL;
        idVisitante = idV;
        codigoJornada = codJor;
        golesLocal = 0;
        golesVisitante = 0;
        campo = null;
        fecha = null;
        jugado = false;
        hora = "00:00";
    }

    public Partido(int cod, int idL, int idV, int codJor, int gL, int gV, String ca, Date f, boolean ju, String h) {

        codigoPartido = cod;
        idLocal = idL;
        idVisitante = idV;
        codigoJornada = codJor;
        golesLocal = gL;
        golesVisitante = gV;
        campo = ca;
        fecha = f;
        jugado = ju;
        hora = h;
    }

    public int getCodigoPartido() {
        return this.codigoPartido;
    }

    public int getIdLocal() {
        return this.idLocal;
    }

    public int getIdVisitante() {
        return this.idVisitante;
    }

    public int getCodigoJornada() {
        return this.codigoJornada;
    }

    public int getGolesLocal() {
        return this.golesLocal;
    }

    public int getGolesVisitante() {
        return this.golesVisitante;
    }

    public String getCampo() {
        return this.campo;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public boolean getJugado() {
        return this.jugado;
    }
        
    public String getHora() {
        return this.hora;
    }

    public void setCodigoPartido(int cod) {
        codigoPartido = cod;
    }

    public void setIdLocal(int idL) {
        idLocal = idL;
    }

    public void setIdVisitante(int idV) {
        idVisitante = idV;
    }

    public void setCodigoJornada(int codJor) {
        codigoJornada = codJor;
    }

    public void setGolesLocal(int gL) {
        golesLocal = gL;
    }

    public void setGolesVisitante(int gV) {
        golesVisitante = gV;
    }

    public void setCampo(String ca) {
        campo = ca;
    }

    public void setFecha(Date f) {
        fecha = f;
    }

    public void setJugado(boolean ju) {
        jugado = ju;
    }

    public void setHora(String h) {
        hora = h;
    }


    public String toString() {

        StringJoiner sj = new StringJoiner("; ","( "," )");
        sj.add("Partido entre los equipos " + getIdLocal() + " y " + getIdVisitante());
        sj.add("Se disputa a las " + getHora() + " horas del día " + getFecha());

        if (getJugado() == true) {

            sj.add("El partido ya se ha disputado en el campo " + getCampo() + ".");
            sj.add("El resultado ha sido: " + getGolesLocal() + " a " + getGolesVisitante());

        } else sj.add("El partido aun no se ha disputado en el campo " + getCampo() + ".");

        sj.add(". (CODIGO PARTIDO: " + getCodigoPartido() + ")");
        sj.add(". (CODIGO JORNADA: " + getCodigoJornada() + ").");

        return sj.toString();
    }
}