package com.github.julidr.lcdrefactor.refactor;


public class ProcesadorIO {
		
	//Espacio entre Digitos
	private final int espacioDig = 1;
	
	private ImpresorLCD impresorLCD;
	private String resultadoImpresion;

	
	public ProcesadorIO() {
		this.impresorLCD = new ImpresorLCD();
		this.resultadoImpresion = "";
	}
	
	
    /**
    *
    * Metodo encargado de procesar la entrada que contiene el size del segmento
    * de los digitos y los digitos a imprimir
    *
    * @param input Entrada que contiene el size del segmento de los digitos
    * y el numero a imprimir
    */ 
	public void procesarInput(String input) {
		
        String[] parametros;
        
        int size;
        
        if (!input.contains(",")) {
            throw new IllegalArgumentException("Cadena " + input
                    + " no contiene caracter ,");
        }
        
        parametros = input.split(",");
        
        //Valida la cantidad de parametros
        if(parametros.length>2) {
           throw new IllegalArgumentException("Cadena " + input
                    + " contiene mas caracteres ,"); 
        }
        
        //Valida la cantidad de parametros
        if(parametros.length<2) {
           throw new IllegalArgumentException("Cadena " + input
                    + " no contiene los parametros requeridos"); 
        }
        
        //Valida que el parametro size sea un numerico
        if(isNumeric(parametros[0])) {
            size = Integer.parseInt(parametros[0]);
            
            // se valida que el size este entre 1 y 10
            if(size <1 || size >10) {
                throw new IllegalArgumentException("El parametro size ["+size
                        + "] debe estar entre 1 y 10");
            }
        } else {
            throw new IllegalArgumentException("Parametro Size [" + parametros[0]
                    + "] no es un numero");
        }
        
        // Realiza la impresion del numero
        this.resultadoImpresion = impresorLCD.obtenerResultado(size, parametros[1], espacioDig);
        
	}
	
    /**
    *
    * Metodo que devuelve el resultado obtenido como un String
    *
    *@return resultadoImpresion String que representa el resultado
    *de la matriz
    */ 
	public String obtenerResultado(){
		return this.resultadoImpresion;
	}
	
    /**
    *
    * Metodo encargado de validar si una cadena es numerica
    *
    * @param cadena Cadena
    */  
	public boolean isNumeric(String cadena) {
       try {
           Integer.parseInt(cadena);
           return true;
       } catch (NumberFormatException ex) {
           return false;
       }
   }

}
