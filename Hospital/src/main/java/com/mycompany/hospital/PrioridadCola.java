/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospital;

/**
 *
 * @author diego
 */
public class PrioridadCola {
    private Paciente cabeza;

    public PrioridadCola() {
        this.cabeza = null;
    }
    
    public void agregarPaciente(String nombre, int prioridad) {
        Paciente nuevoPaciente = new Paciente(nombre, prioridad);

        if (cabeza == null || prioridad < cabeza.prioridad) {
            nuevoPaciente.siguiente = cabeza;
            cabeza = nuevoPaciente;
        } else {
            Paciente actual = cabeza;
            while (actual.siguiente != null && actual.siguiente.prioridad <= prioridad) {
                actual = actual.siguiente;
            }
            nuevoPaciente.siguiente = actual.siguiente;
            actual.siguiente = nuevoPaciente;
        }
    }

    public void mostrarSiguientePaciente() {
        if (cabeza == null) {
            System.out.println("No hay pacientes pendientes");
        } else {
            System.out.println("Siguiente paciente a atender: " + cabeza.nombre + " | Prioridad: " + cabeza.prioridad);
        }
    }

    public void eliminarPaciente() {
        if (cabeza == null) {
            System.out.println("No hay pacientes en la cola");
        } else {
            System.out.println("Eliminaste a: " + cabeza.nombre + " Prioridad: " + cabeza.prioridad);
            cabeza = cabeza.siguiente;
        }
    }

    public void mostrarPacientes() {
        if (cabeza == null) {
            System.out.println("No hay pacientes en la cola");
            return;
        }
        System.out.println("Pacientes en la cola:");
        Paciente actual = cabeza;
        while (actual != null) {
            System.out.println("Paciente: " + actual.nombre + " Prioridad: " + actual.prioridad);
            actual = actual.siguiente;
        }
    }
}
