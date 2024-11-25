/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.hospital;

import java.util.Scanner;

/**
 *
 * @author diego
 */
public class Hospital {

    public static void main(String[] args) {
        PrioridadCola colaPacientes = new PrioridadCola();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----Seleccione una opción----");
            System.out.println("1. Agregar paciente");
            System.out.println("2. Atender paciente");
            System.out.println("3. Mostrar pacientes");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese la prioridad ( 1 = Prioridad máxima): ");
                    int prioridad = scanner.nextInt();
                    colaPacientes.agregarPaciente(nombre, prioridad);
                    System.out.println("Paciente registrado");
                    break;

                case 2:
                    colaPacientes.mostrarSiguientePaciente();
                    System.out.print("¿Deseas eliminar al paciente atendido? (si o no): ");
                    String respuesta = scanner.nextLine();
                    if (respuesta.equals("si")) {
                        colaPacientes.eliminarPaciente();
                        System.out.println("Paciente eliminado de la lista.");
                    } else if (respuesta.equals("no")) {
                        System.out.println("Paciente no eliminado");
                    }
                    break;

                case 3:
                    colaPacientes.mostrarPacientes();
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
