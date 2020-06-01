package prLIGApp;


public class Usuario {
	private String nombre;
	private String correo;
	private String password;
	
	public Usuario(String n, String c, String p) {
		nombre = n;
		correo = c;
		password = p;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setNombre(String n) {
		nombre = n;
	}
	
	public void setCorreo(String c) {
		correo = c;
	}
	
	public void setPassword(String p) {
		password = p;
	}
	
	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Usuario) {
			Usuario u = (Usuario) o;
			res = nombre.equals(u.getNombre()) && password.equals(u.getPassword());
		}
		
		return res;
		
	}
	
	public String toString() {
		return "(" + nombre + ", " + correo + ", " + password + ")";
	}
}
