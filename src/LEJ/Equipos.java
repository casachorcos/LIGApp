package LEJ;

import java.util.*;

public class Equipos {
  int id;
  List<Jugadores> ListaJugadores;
  String nombre;
  
  public Equipos(){
    nombre = null;
    id = 1;
    ListaJugadores = new ArrayList<Jugadores>();
    
  }
}
