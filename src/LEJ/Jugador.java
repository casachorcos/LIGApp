package LEJ;

import java.util.List;


    public static int id = 1;
  
    private String nombre;
    private int identificacion;
    private int edad;
    private String rol;
    private int goles = 0;
    private int amarillas = 0;
    private int rojas = 0;

    

    public void setNombre(String nombre){
        this.nombre = nombre;
        identificacion = id;
        id++;
    }

    public void setEdad(int edad){
        if(edad > 0){
            this.edad = edad;
        }else{
            throw new RuntimeException("La edad no puede ser 0 o negativa");
        }
    }

    public void setRol(String rol){
        if(this.rol != null){
            this.rol = rol;
        }
    }

    public void incGoles(int g){
        goles = goles + g;
    }

    public void incAmarillas(int a){
        amarillas = amarillas + a;
    }

    private void incRojas(int r){
        rojas = rojas + r;
    }




}
