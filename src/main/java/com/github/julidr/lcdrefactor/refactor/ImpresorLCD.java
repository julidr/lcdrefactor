package com.github.julidr.lcdrefactor.refactor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImpresorLCD {

    // Puntos fijos
    private final int[] pf1;
    private final int[] pf2;
    private final int[] pf3;
    private final int[] pf4;
    private final int[] pf5;
    private String[][] matrizImpr;

    static final String CARACTER_VERTICAL = "|";
    static final String CARACTER_HORIZONTAL = "-";

    //Tamaño de los numeros
    private int size;

    //Filas y columnas de la matriz
    private int filasDig;
    private int columDig;
    private int totalFilas;
    private int totalColum;

    public ImpresorLCD() {
        // Inicializa los puntos fijos
        this.pf1 = new int[2];
        this.pf2 = new int[2];
        this.pf3 = new int[2];
        this.pf4 = new int[2];
        this.pf5 = new int[2];
    }

    /**
     *
     * Metodo encargado de añadir una linea a la matriz de Impresion
     *
     * @param matriz Matriz Impresion
     * @param punto Punto Pivote
     * @param posFija Posicion Fija
     * @param size Tamaño Segmento
     * @param caracter Caracter Segmento
     */    
    private void adicionarLinea(int[] punto, int size, String caracter) {

        if (caracter.equalsIgnoreCase(CARACTER_HORIZONTAL)) {
            for (int j = 1; j <= size; j++) {
                int valorJ = punto[1] + j;
                this.matrizImpr[punto[0]][valorJ] = caracter;
            }
        } else {
            for (int i = 1; i <= size; i++) {
                int valorI = punto[0] + i;
                this.matrizImpr[valorI][punto[1]] = caracter;
            }
        }
    }

    /**
     *
     * Metodo encargado de un segmento a la matriz de Impresion
     *
     * @param segmento Segmento a adicionar
     */  
    private void adicionarSegmento(int segmento) {

        switch (segmento) {
            case 1:
                adicionarLinea(this.pf1, this.size, CARACTER_VERTICAL);
                break;
            case 2:
                adicionarLinea(this.pf2, this.size, CARACTER_VERTICAL);
                break;
            case 3:
                adicionarLinea(this.pf5, this.size, CARACTER_VERTICAL);
                break;
            case 4:
                adicionarLinea(this.pf4, this.size, CARACTER_VERTICAL);
                break;
            case 5:
                adicionarLinea(this.pf1, this.size, CARACTER_HORIZONTAL);
                break;
            case 6:
                adicionarLinea(this.pf2, this.size, CARACTER_HORIZONTAL);
                break;
            case 7:
                adicionarLinea(this.pf3, this.size, CARACTER_HORIZONTAL);
                break;
            default:
                break;
        }
    }

    /**
     *
     * Metodo encargado de definir los segmentos que componen un digito y
     * a partir de los segmentos adicionar la representacion del digito a la matriz
     *
     * @param numero Digito
     */
    private void adicionarDigito(int numero) {

        // Establece los segmentos de cada numero
        List<Integer> segList = new ArrayList<>();

        switch (numero) {
            case 1:
                segList.add(3);
                segList.add(4);
                break;
            case 2:
                segList.add(5);
                segList.add(3);
                segList.add(6);
                segList.add(2);
                segList.add(7);
                break;
            case 3:
                segList.add(5);
                segList.add(3);
                segList.add(6);
                segList.add(4);
                segList.add(7);
                break;
            case 4:
                segList.add(1);
                segList.add(6);
                segList.add(3);
                segList.add(4);
                break;
            case 5:
                segList.add(5);
                segList.add(1);
                segList.add(6);
                segList.add(4);
                segList.add(7);
                break;
            case 6:
                segList.add(5);
                segList.add(1);
                segList.add(6);
                segList.add(2);
                segList.add(7);
                segList.add(4);
                break;
            case 7:
                segList.add(5);
                segList.add(3);
                segList.add(4);
                break;
            case 8:
                segList.add(1);
                segList.add(2);
                segList.add(3);
                segList.add(4);
                segList.add(5);
                segList.add(6);
                segList.add(7);
                break;
            case 9:
                segList.add(1);
                segList.add(3);
                segList.add(4);
                segList.add(5);
                segList.add(6);
                segList.add(7);
                break;
            case 0:
                segList.add(1);
                segList.add(2);
                segList.add(3);
                segList.add(4);
                segList.add(5);
                segList.add(7);
                break;
            default:
                break;
        }

        Iterator<Integer> iterator = segList.iterator();

        while (iterator.hasNext()) {
            adicionarSegmento(iterator.next());
        }
    }
    
    /**
    *
    * Metodo encargado de inicializar la matriz con su respectivo tamaño
    * de filas y columnas
    *
    * @param numImp Numero a Imprimir
    * @param espacio Espacio Entre digitos
    */ 
    private void iniciarMatriz(String numImp, int espacio) {
    	
        // Calcula el numero de filas de cada digito
        this.filasDig = (2 * this.size) + 3;

        // Calcula el numero de columna de cada digito
        this.columDig = this.size + 2;

        // Calcula el total de filas de la matriz en la que se almacenaran los digitos
        this.totalFilas = this.filasDig;

        // Calcula el total de columnas de la matriz en la que se almacenaran los digitos
        this.totalColum = (this.columDig * numImp.length())
                + (espacio * numImp.length());

        // crea matriz para almacenar los numero a imprimir
        this.matrizImpr = new String[this.totalFilas][this.totalColum];
        
        // Inicializa matriz
        for (int i = 0; i < this.totalFilas; i++) {
            for (int j = 0; j < this.totalColum; j++) {
                this.matrizImpr[i][j] = " ";
            }
        }
    }
    
    /**
    *
    * Metodo encargado de calcular el valor de los puntos fijos
    *
    * @param digitos Lista de digitos a imprimir
    * @param espacio Espacio Entre digitos
    */  
    private void calcularPuntosFijos(char[] digitos, int espacio) {
        int pivotX = 0;
        
        // Calcula los puntos fijos en su primera posicion
        this.pf1[0] = 0;
        this.pf2[0] = (this.filasDig / 2);
        this.pf3[0] = (this.filasDig - 1);
        this.pf4[0] = (this.columDig - 1);
        this.pf5[0] = 0;
        
        for (char digito : digitos) {
            
            //Valida que el caracter sea un digito
            if( ! Character.isDigit(digito)) {
                throw new IllegalArgumentException("Caracter " + digito
                    + " no es un digito");
            }

            int numero = Integer.parseInt(String.valueOf(digito));

            //Calcula puntos fijos en su segunda posicion
            this.pf1[1] = 0 + pivotX;

            this.pf2[1] = 0 + pivotX;

            this.pf3[1] = 0 + pivotX;

            this.pf4[1] = (this.filasDig / 2) + pivotX;

            this.pf5[1] = (this.columDig - 1) + pivotX;

            pivotX = pivotX + this.columDig + espacio;

            adicionarDigito(numero);
        }
    }

    /**
     *
     * Metodo encargado de imprimir un numero
     *
     * @param size Tamaño Segmento Digitos
     * @param numImp Numero a Imprimir
     * @param espacio Espacio Entre digitos
     */    
    public void imprimirNumero(int size, String numImp, int espacio) {
        char[] digitos;

        this.size = size;

        // Crea el arreglo de digitos
        digitos = numImp.toCharArray();
        
        // Inicializa la matriz en la que se almacenaran los digitos
        iniciarMatriz(numImp, espacio);

        // Realiza el calculo de los valores para los puntos fijos
        calcularPuntosFijos(digitos, espacio);
        
        // Imprime matriz
        for (int i = 0; i < this.totalFilas; i++) {
            for (int j = 0; j < this.totalColum; j++) {
                System.out.print(this.matrizImpr[i][j]);
            }
            System.out.println();
        }
    }
}