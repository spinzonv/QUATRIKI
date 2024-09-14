package co.edu.unbosque.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.unbosque.model.persistence.RegistrosDeHistorial;


public class Historial {

 
    private List<String> historialResultados;
    private int numeroPartida;
    private long tiempoInicio;
    private RegistrosDeHistorial registros;
   
    public Historial() {
        this.historialResultados = new ArrayList<>();
        numeroPartida = 1;
        registros = new RegistrosDeHistorial();
    }


    public void agregarResultado(String color, long tiempoInicio) {
        long tiempoFin = System.currentTimeMillis();
        long duracion = (tiempoFin - tiempoInicio) / 1000;
        String resultadoColor = (color != null) ? color : "Desconocido";
        String resultado = "Partida " + numeroPartida + ": " + resultadoColor + " - Duración: " + duracion + " segundos";
        historialResultados.add(resultado);
        numeroPartida++;
        registros.guardarResultado(resultado); 
  
        
        
    }
    public List<String> obtenerHistorial() {
        
        return new ArrayList<>(historialResultados);
    }


    public int contarVictorias(String color) {
        int conteo = 0;
        for (String resultado : historialResultados) {
            if (resultado.equals(color)) {
                conteo++;
            }
        }
        return conteo;
    }

   
    public int contarTotalPartidas() {
        return historialResultados.size();
    }
}