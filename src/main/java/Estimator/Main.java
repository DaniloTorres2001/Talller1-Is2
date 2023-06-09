// Copyright (C) 2020
// All rights reserved
package Estimator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
	 private Main() {
	        // Constructor privado para evitar la creación de instancias
	    }
	
	private static final double ALL_INCLUSIVE_PACKAGE_COST = 200;
	private static final double ADVENTURE_ACTIVITIES_PACKAGE_COST = 150;
	private static final double SPA_AND_WELLNESS_PACKAGE_COST = 100;


	/**
	 * Método principal
	 * 
	 *
	 * @param args Los argumentos de la línea de comandos (no se utilizan en este caso)
	 */
    public static void main(String[] args) {
    	

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Ingrese el destino: ");
            String destino = scanner.nextLine();
            // 
            destino = capitalizarPalabras(destino);
            
            System.out.print("Ingrese el numero de personas: ");
            int personas = scanner.nextInt();

            System.out.print("Ingrese los dias que va a vacacionar: ");
            int estadia = scanner.nextInt();
            
            scanner.nextLine();

			List<String> complementos = seleccionarComplementos(scanner);


			scanner.close();


            VacationPackage paquete1;

            if (validarDestino(destino) && validarNumeroPersonas(personas)) {
                paquete1 = new VacationPackage(destino, personas, estadia);
                paquete1.verifySpot(paquete1);
                paquete1.verifyDiscount(paquete1);
                paquete1.verifyPenalty(paquete1);
                
                double costoComplementos = calcularCostoComplementos(complementos, personas);
				paquete1.setCost(paquete1.getCost() + costoComplementos);

                if (paquete1.getCost() < 0) {
                    System.out.println("-1"); // Imprimir -1 si los datos de entrada no son válidos
                } else {
                    int totalCost = (int) paquete1.getCost();
                    System.out.println("Costo Total del paquete de viajes: " + totalCost);
                }
                
                System.out.println(paquete1.toString());
                
                System.out.println("Paquetes seleccionados:");
				for (String complemento : complementos) {
					System.out.println(complemento);
				}
            } else {
                System.out.println("-1"); // Imprimir -1 si los datos de entrada no son válidos
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()); // Imprimir mensaje de error en caso de excepción
        }
    }

    /**
     * Valida el destino ingresado.
     * 
     * @param destino el destino a validar
     * @return true si el destino es válido, false de lo contrario
     */
    public static boolean validarDestino(String destino) {
        if (destino == null || destino.isEmpty()) {
            return false;
        }

        // Convertir el destino a minúsculas y capitalizar la primera letra
        String destinoCapitalizado = capitalizeFirstLetter(destino.toLowerCase(Locale.ROOT));

        // Validar si el destino es "Paris" o "New York City" sin importar las mayúsculas o minúsculas
        if ("Paris".equalsIgnoreCase(destinoCapitalizado) || "New York City".equalsIgnoreCase(destinoCapitalizado)) {
            return true;
        }

        // Validar si el destino capitalizado es un país (primera letra en mayúscula y las demás en minúscula)
        char firstChar = destinoCapitalizado.charAt(0);
        if (Character.isUpperCase(firstChar)) {
            return true;
        }

        return false;
    }

    


    /**
     * Valida el número de personas ingresado.
     * 
     * @param personas el número de personas a validar
     * @return true si el número de personas es válido, false de lo contrario
     */
    public static boolean validarNumeroPersonas(int personas) {
        // Implementa tus reglas de validación para el número de personas
        // Por ejemplo, si el número de personas debe estar entre 1 y 80
        return personas >= 1 && personas <= 80;
    }
    /**
     * Capitaliza la primera letra de cada palabra en una oración.
     * 
     * @param oracion la oración a capitalizar
     * @return la oración con la primera letra de cada palabra en mayúscula
     */
    public static String capitalizarPalabras(String oracion) {
        if (oracion == null || oracion.isEmpty()) {
            return oracion;
        }
        
        String[] palabras = oracion.split(" ");
        StringBuilder resultado = new StringBuilder();
        
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                String palabraCapitalizada = capitalizeFirstLetter(palabra);
                resultado.append(palabraCapitalizada).append(" ");
            }
        }
        
        return resultado.toString().trim();
    }


    // Método para capitalizar la primera letra de una palabra
    private static String capitalizeFirstLetter(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return palabra;
        }

        String firstLetter = palabra.substring(0, 1).toUpperCase(Locale.ROOT);
        String restOfString = palabra.substring(1).toLowerCase(Locale.ROOT);

        return firstLetter + restOfString;
    }
    private static List<String> seleccionarComplementos(Scanner scanner) {
		List<String> complementosDisponibles = new ArrayList<>(Arrays.asList(
				"All-Inclusive Package",
				"Adventure Activities Package",
				"Spa and Wellness Package"
		));

		List<String> complementosSeleccionados = new ArrayList<>();

		System.out.println("Seleccione los complementos opcionales:");

		int opcion;
		do {
			System.out.println("Complementos disponibles:");
			for (int i = 0; i < complementosDisponibles.size(); i++) {
				System.out.println((i + 1) + ". " + complementosDisponibles.get(i));
			}
			System.out.println("0. No seleccionar más complementos");

			System.out.print("Ingrese su opción: ");
			opcion = scanner.nextInt();
			scanner.nextLine(); // Consumir la nueva línea después de leer la opción

			if (opcion >= 1 && opcion <= complementosDisponibles.size()) {
				String complementoSeleccionado = complementosDisponibles.get(opcion - 1);
				complementosSeleccionados.add(complementoSeleccionado);
				complementosDisponibles.remove(complementoSeleccionado);
				System.out.println("Complemento seleccionado: " + complementoSeleccionado);
			} else if (opcion != 0) {
				System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
			}
		} while (opcion != 0);

		return complementosSeleccionados;
	}

    private static double calcularCostoComplementos(List<String> complementos, int personas) {
        double costoTotal = 0;

        for (String complemento : complementos) {
            switch (complemento) {
                case "All-Inclusive Package":
                    costoTotal += ALL_INCLUSIVE_PACKAGE_COST * personas;
                    break;
                case "Adventure Activities Package":
                    costoTotal += ADVENTURE_ACTIVITIES_PACKAGE_COST * personas;
                    break;
                case "Spa and Wellness Package":
                    costoTotal += SPA_AND_WELLNESS_PACKAGE_COST * personas;
                    break;
                default:
                    // Manejar casos inesperados o de fallback
                    System.out.println("Invalid package selected: " + complemento);
                    break;
            }
        }

        return costoTotal;
    }

    
}