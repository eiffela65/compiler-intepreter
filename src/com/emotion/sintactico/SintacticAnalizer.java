/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emotion.sintactico;

import java.util.ArrayList;
import java.util.Stack;

//import org.apache.commons.collections.MultiMap;
public class SintacticAnalizer {

    private ArrayList<Integer> lexemas = new ArrayList();
    private ArrayList<String> tokens = new ArrayList();
    private int rowGramar = 0;
    private int columnGramar = 0;
    private int nextRowGramar = 0;
    private boolean matrix = true;
    private Stack <Integer> expresion = new Stack <Integer>();
    private int parenthesis = 0;
    
    public SintacticAnalizer(){
        setLexemas(lexemas);
        setTokens(tokens);
    }
    
    public void setRowGramar(int rowGramar){
        this.rowGramar = rowGramar;
    }
    
    public void setColumnGramar(int columnGramar){
        this.columnGramar = columnGramar;
    }
    
    public void setLexemas(ArrayList<Integer> lexemas){
        this.lexemas = lexemas;
    }
    
    public ArrayList<Integer> getLexemas(){
        return lexemas;
    }
    
    public void setTokens(ArrayList<String> tokens){
        this.tokens = tokens;
    }
    
    public ArrayList<String> getTokens(){
        return tokens;
    }
    
    public boolean startAnalisis(){
        int len = tokens.size();
        boolean status = false;
        String lexema="";
        Base baseSintactical = new Base();
        int lastParenthesis = 0;
        int lastRow = 0;
        int i = 0;
        System.out.println("************  ANALISIS SINTACTICO  **************");
        while(i < len){
            System.out.println("-------------------------------------------------------");
            System.out.println(tokens.get(i) + " ----> [" + rowGramar + " - " + Base.getColumn(lexemas.get(i)) + "]");
            
            columnGramar = Base.getColumn(lexemas.get(i));
            baseSintactical.setColumnGrammar(columnGramar);
            baseSintactical.setRowGrammar(rowGramar);
            rowGramar = baseSintactical.getNewRow(matrix);
//            System.out.println("Nuevo estado   " + rowGramar);
            if(rowGramar == 600){
                lexema = tokens.get(i);
                break;
            }
            if(rowGramar == 400){
                int element = expresion.peek();
                if(expresion.empty()){
//                    System.out.println("Pila de expresiones vacia");
                    break;
                }
                if(columnGramar == 20){
                    parenthesis--;
                    if(element < 301)
                        i++;
//                    System.out.println("@@@@@@@@@@ se quito un parentesis");
                }
                if(parenthesis > 0){
                    matrix = false;
                    rowGramar = 1;
                }else{
                    matrix = true;
                    rowGramar = nextRow(expresion.pop());
                }
                continue;
            }
            if(!matrix && columnGramar == 19 && lastParenthesis != i){
//                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% se agrego un parentesis");
                parenthesis++;
            }
            if(rowGramar > 299 && rowGramar < 400){
                matrix = false;
                expresion.push(rowGramar);
                rowGramar = 0;
                if(columnGramar == 19){
                    parenthesis++;
//                    System.out.println("############### se agrego un parentesis");
                    lastParenthesis = i;
                }
                continue;
            }
            
//            System.out.println(lexemas.get(i) + "  -  " + tokens.get(i));
            
            if(rowGramar == 200)
                rowGramar = getRowByEstatus(tokens.get(i), lastRow);
            
            if(rowGramar == 37)
                status = true;
            lastRow = rowGramar;
            i++;
//            System.out.println(status);
        }
        if(!status)
            System.out.println("Error sintactico cerca de:  " + lexema);
        System.out.println("Fin de analisis sintactico");
        return status;
    }
    
    private int nextRow(int typeExpresion){
        int row = 0;
        switch (typeExpresion){
            case 300:
                row = 18;
                break;
            case 301:
                row = 16;
                break;
            case 302:
                row = 21;
                break;
            case 303:
                row = 25;
                break;
            case 304:
                row = 34;
                break;
        }
        return row;
    }
    
    private int getRowByEstatus(String word, int curentRow){
        int row = 600;
        switch(word){
            case "declare":
                row = 5;
                break;
            case "if":
                row = 19;
                break;
            case "else":
                if(curentRow == 4)
                    row = 600;
                else
                    row = 12;
                break;
            case "endif":
                if(curentRow == 4)
                    row = 600;
                else
                    row = 22;
                break;
            case "while":
                row = 23;
                break;
            case "endwhile":
                if(curentRow == 4)
                    row = 600;
                else
                    row = 26;
                break;
            case "do":
                row = 12;
                break;
            case "dowhile":
                row = 23;
                break;
            case "enddo":
                if(curentRow == 4)
                    row = 600;
                else
                    row = 27;
                break;
            case "read":
                row = 28;
                break;
            case "write":
                row = 32;
                break;
            case "endclass":
                row = 37;
                break;    
        }
        return row;
    }

}
