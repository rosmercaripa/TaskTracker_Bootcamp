# 📝 TaskTracker

TaskTracker es un sistema simple para la gestión de tareas, desarrollado en **Java**, con un enfoque en **buenas prácticas de testing** mediante **JUnit 5** y **Hamcrest**.  
El proyecto sigue la filosofía **TDD (Test Driven Development)** con el ciclo **RED → GREEN → REFACTOR**.

---

## 🚀 Funcionalidades

- **Agregar tarea**  
  - Cada tarea contiene un **título**, **descripción** y un **estado de completado** (por defecto `false`).  
  - Se agrega a la lista de tareas de manera controlada.

- **Marcar tarea como completada**  
  - Permite cambiar el estado de la tarea a `true`.  
  - Se valida que la tarea exista antes de modificarla.

- **Listar tareas pendientes**  
  - Muestra solo las tareas con estado `false`.

---

## 🛠️ Tecnologías utilizadas

- **Java 17+**  
- **JUnit 5**  
- **Hamcrest**  
- **Maven** (gestión de dependencias)  

---

## 📂 Estructura del Proyecto

```bash
TaskTracker/
│
├── src/
│   ├── main/java/com/tasktracker/
│   │   ├── Task.java
│   │   └── TaskTracker.java
│   │
│   └── test/java/com/tasktracker/
│       └── TaskTrackerTest.java
│
├── pom.xml
└── README.md
