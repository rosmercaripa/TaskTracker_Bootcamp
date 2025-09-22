package cl.kibernumacademy;

import java.util.ArrayList;
import java.util.Collections; // 
import java.util.List;


public class TareaManager {
    private List<Tarea> tareas;

    public TareaManager() {
        this.tareas = new ArrayList<>();
    }

    public void agregarTarea(Tarea tarea){
        tareas.add(tarea);
    }

    //El unmodifiableList se utiliza para evitar que se modifique la lista desde afuera
    public List<Tarea> obtenerTodos() {
        return Collections.unmodifiableList(tareas);
    }

    //Revisa que la tarea exista antes de cambiarle de estado 
    public boolean comprobarTareaCompletada(String titulo) {
        for (Tarea tarea : tareas) {
            if (tarea.getTitulo().equals(titulo)) {
                tarea.setEstado(true);
                return true;
            }
        }
        return false;
    }

    //Se hace filtrado de las tareas que no se encuentra completadas 
    public List<Tarea> mostrarTodasLasTareasQueSeanFalse() {
        List<Tarea> pendientes = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (!tarea.isEstado()) {
                pendientes.add(tarea);
            }
        }
        return pendientes;
    }

}
