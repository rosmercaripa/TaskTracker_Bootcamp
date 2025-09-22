package cl.kibernumacademy;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class TareaTest {

    private TareaManager manager;
    private static int contador = 1;

    @BeforeEach
    void setup() {
        manager = new TareaManager();
    }

    @AfterEach
    void mensajeFinal(TestInfo informacionTest) {
        System.out.println("Test #" + contador + " completado con éxito.\n");
        System.out.println("Finalizó el test: " + informacionTest.getDisplayName());
        contador++;
    }

    // Titulo, descripción, estado completado(boolean)
    /*
     * Este método se está agregando una tarea mediante un nuevo objeto y luego se
     * verifica en la obtención de la lista
     * si efectivamente esta tarea existe
     */

    @Test
    void agregarUnTareaPredeterminada() {
        Tarea tarea = new Tarea("MarcarEntrada", "Se marca entrada a las 8:30am", false);
        manager.agregarTarea(tarea);
        assertEquals(1, manager.obtenerTodos().size(), "Debe haber una tarea agregada");
    }

    /*
     * Esta prueba sirve para comprobar que la tarea pueda cambiar de estado y se
     * comprueba mediante una aserción
     */
    @Test
    void tareaComoCompletada() {
        Tarea tarea = new Tarea("EnviarInforme", "Se envia informe semanal", false);
        manager.agregarTarea(tarea);

        boolean resultadoTarea = manager.comprobarTareaCompletada("EnviarInforme");

        assertTrue(resultadoTarea, "La tarea debería marcarse como completada");
        assertTrue(tarea.isEstado(), "El estado de completado debe ser true");

    }

    // Tenemos que hacer la prueba
    @Test
    void comprobarQueExistaLaTareaAntesDeModificar() {
        boolean resultadoTarea = manager.comprobarTareaCompletada("EnviarInforme");
        assertFalse(resultadoTarea, "No debe cambiar estado de una tarea que no existe");
    }

    /*
     * Creamos 3 tareas, dos con mismo estado y una diferente para luego agregarlas
     * y así poder hacer una lista que tendra como valor
     * los valores que retorne el metodo obtenerTareasPendientes para así hacerle
     * las aserciones, primer verificar el largo de la lista
     * y luego comprobar si es verdadero las condiciones de tareas incompletas
     */
    @Test
    void mostrarTodasLasTareasQueSeanFalse() {
        Tarea tarea1 = new Tarea("Revisar correos", "Revisar y responder correos atrasados", false);
        Tarea tarea2 = new Tarea("Enviar reporte", "Reporte mensual a dirección", true);
        Tarea tarea3 = new Tarea("Preparar reunión", "Agendar reunión con equipo", false);

        manager.agregarTarea(tarea1);
        manager.agregarTarea(tarea2);
        manager.agregarTarea(tarea3);

        List<Tarea> tareasPendientes = manager.mostrarTodasLasTareasQueSeanFalse();

        assertEquals(2, tareasPendientes.size(), "Debe haber 2 tareas pendientes");

        assertTrue(tareasPendientes.contains(tarea1), "Debe contener la tarea 1");
        assertTrue(tareasPendientes.contains(tarea3), "Debe contener la tarea 3");
    }

    // Comprobar que la tarea tenga el título correcto mediante Hamcrest
    @Test
    void tareaAlSerAgregadaTieneElTituloCorrecto() {
        Tarea tarea = new Tarea("Leer documentación", "Se debe revisar documentacion de la app", false);
        manager.agregarTarea(tarea);

        Tarea tareaGuardada = manager.obtenerTodos().get(0);

        assertThat(tareaGuardada.getTitulo(), is("Leer documentación"));
        assertThat(tareaGuardada.getDescripcion(), containsString("Se debe revisar documentacion de la app"));
    }

    /*
     * De esta manera se verifica que si no hay elementos en la lista entonces el
     * test se omite, en caso de que se cumpla la condición, la que sigue en la
     * linea
     * siguiente que da el resultado no se ejecutara
     */
    @Test
    void marcarTareaSoloSiHayTareasCargadas() {
        assumeTrue(manager.obtenerTodos().size() > 0, "No hay tareas, se omite el test");

        boolean resultado = manager.comprobarTareaCompletada("Tarea falsa");

        assertFalse(resultado);
    }

    /*
     * Se completa la condición de que en caso de que haya una tarea registrada esta
     * es marcada en caso de que no ocurra
     * solo se ejecutaría la aserción que comprueba si el tamaño de la lista es 1
     */
    @Test
    void marcarTareaSoloSiExisteEnElSistema() {
        Tarea tarea = new Tarea("Actualizar sistema", "Actualizar drivers de seguridad", false);
        manager.agregarTarea(tarea);

        assumingThat(manager.obtenerTodos().size() > 0, () -> {
            boolean resultado = manager.comprobarTareaCompletada("Actualizar sistema");
            assertTrue(resultado, "La tarea debería marcarse como completada");
        });

        assertEquals(1, manager.obtenerTodos().size(), "Debe haber una tarea registrada");
    }

    /*
     * Lo que hace este test es que ingresa de manera parametrizada el título de 3
     * tareas diferentes con una descripcion genérica
     * y comprueba si estos existen en la lista que genera el metodo obtenerTodos, el stream permite que se apliquen operaciones funcionales
     * el any match permite sabe si almenos hay 1 coincidencia y el assertThat comprueba si el resultado del anymatch es correcto 
     */
    @ParameterizedTest
    @ValueSource(strings = { "Tarea A", "Tarea B", "Tarea C" })
    void tareasConTitulosDebenGuardarseCorrectamente(String titulo) {
        Tarea tarea = new Tarea(titulo, "Descripción genérica", false);
        manager.agregarTarea(tarea);

        assertTrue(
                manager.obtenerTodos().stream().anyMatch(t -> t.getTitulo().equals(titulo)),
                "La tarea con título '" + titulo + "' debe estar en la lista");
    }

}
