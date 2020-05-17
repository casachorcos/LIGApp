package LEJ;

import java.util.ArrayList;
import java.util.List;

public class Equipos {
  int id;
  List<Jugadores> ListaJugadores;
  String nombre;
  public Equipos(){
    nombre = null;
    id = 0;
    ListaJugadores = new ArrayList<Jugadores>();
  }

}
