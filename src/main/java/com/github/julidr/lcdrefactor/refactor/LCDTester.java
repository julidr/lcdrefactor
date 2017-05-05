package com.github.julidr.lcdrefactor.refactor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class LCDTester {

    static final String CADENA_FINAL = "0,0";
    
    public static void main(String[] args) {

        List<String> listaDeInputs = new ArrayList<>();
        String input;
        ProcesadorIO procesadorIO = new ProcesadorIO();
        
        try {

            try (Scanner lector = new Scanner(System.in)) {
                do {
                    System.out.print("Entrada: ");
                    input = lector.next();
                    
                    if(!input.equalsIgnoreCase(CADENA_FINAL)) {
                        listaDeInputs.add(input);
                    }
                }while (!input.equalsIgnoreCase(CADENA_FINAL));
                
                lector.close();
            }
            
            Iterator<String> iterator = listaDeInputs.iterator();
            while (iterator.hasNext()) {
                try {
                    procesadorIO.procesarInput(iterator.next());
                } catch (Exception ex) {
                    System.out.println("Error: "+ex.getMessage());
                }
            }
            System.out.println(procesadorIO.obtenerResultado());
        } catch (Exception ex) {
            System.out.println("Error: "+ex.getMessage());
        }  
    }
}