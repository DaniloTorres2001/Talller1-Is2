package Estimator;
import java.util.Scanner;

public class Main {
    // CHECKSTYLE:OFF
    public static void main(String[] args) {
    // CHECKSTYLE:ON
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

            scanner.close();

            VacationPackage paquete1;

            if (validarDestino(destino) && validarNumeroPersonas(personas)) {
                paquete1 = new VacationPackage(destino, personas, estadia);
                paquete1.verifySpot(paquete1);
                paquete1.verifyDiscount(paquete1);
                paquete1.verifyPenalty(paquete1);

                if (paquete1.getCost() < 0) {
                    System.out.println("-1"); // Imprimir -1 si los datos de entrada no son válidos
                } else {
                    int totalCost = (int) paquete1.getCost();
                    System.out.println("Total cost of the vacation package: " + totalCost);
                }
                System.out.println(paquete1.toString());
            } else {
                System.out.println("-1"); // Imprimir -1 si los datos de entrada no son válidos
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()); // Imprimir mensaje de error en caso de excepción
        }
    }

    // Método para validar el destino
    public static boolean validarDestino(String destino) {
        // Convertir el destino a minúsculas y capitalizar la primera letra
        String destinoCapitalizado = destino.substring(0, 1).toUpperCase() + destino.substring(1).toLowerCase();

        // Validar si el destino es "Paris" o "New York City"
        if (destinoCapitalizado.equals("Paris") || destinoCapitalizado.equals("New York City")) {
            return true;
        }

        // Validar si el destino capitalizado es un país (primera letra en mayúscula y las demás en minúscula)
        char firstChar = destinoCapitalizado.charAt(0);
        if (Character.isUpperCase(firstChar)) {
            return true;
        }

        return false;
    }

    // Método para validar el número de personas
    public static boolean validarNumeroPersonas(int personas) {
        // Implementa tus reglas de validación para el número de personas
        // Por ejemplo, si el número de personas debe estar entre 1 y 80
        return personas >= 1 && personas <= 80;
    }
    public static String capitalizarPalabras(String oracion) {
        if (oracion == null || oracion.isEmpty()) {
            return oracion;
        }
        
        String[] palabras = oracion.split(" ");
        StringBuilder resultado = new StringBuilder();
        
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                String palabraCapitalizada = palabra.substring(0, 1).toUpperCase() + palabra.substring(1).toLowerCase();
                resultado.append(palabraCapitalizada).append(" ");
            }
        }
        
        return resultado.toString().trim();
    }
}