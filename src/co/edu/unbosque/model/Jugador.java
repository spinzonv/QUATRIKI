package co.edu.unbosque.model;

public class Jugador {
    private String nombre;
    private String color;
    private int victorias;

   
    public Jugador(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
        this.victorias = 0; 
    }

   
    public String getNombre() {
        return nombre;
    }

   
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   
    public String getColor() {
        return color;
    }

   
    public void setColor(String color) {
        this.color = color;
    }

   
    public int getVictorias() {
        return victorias;
    }

   
    public void incrementarVictorias() {
        this.victorias++;
    }
}
