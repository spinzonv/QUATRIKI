package co.edu.unbosque.model.persistence;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegistrosDeHistorial {

    private String rutaArchivo;

    public RegistrosDeHistorial() {
    	this.rutaArchivo = "src/co/edu/unbosque/model/persistence/registros.txt";
;
    }

    public void guardarResultado(String resultado) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            bw.write(resultado);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el resultado en el archivo: " + e.getMessage());
        }
    }
}

