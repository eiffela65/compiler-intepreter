/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emotion.sintactico;

import java.util.ArrayList;
import java.util.Stack;
import com.emotion.semantic.SemanticAnalizer;
import com.emotion.sintactico.Base;
import com.emotion.semantic.Symols;
import java.util.List;
import java.util.ListIterator;

//import org.apache.commons.collections.MultiMap;
public class SintacticAnalizer {

    private ArrayList<Integer> lexemas = new ArrayList(); //numeros de producciones dados por el lexico
    private ArrayList<String> tokens = new ArrayList();   //nombres tambien dados por el lexico
    private int rowGramar = 0;  //renglon
    private int columnGramar = 0;   //columna
    private Stack<Integer> productions = new Stack<Integer>();
    private com.emotion.sintactico.Base sintacticBase = new Base();
    private com.emotion.semantic.Base semanticBase = new com.emotion.semantic.Base();
    private SemanticAnalizer sematicAalizer = new SemanticAnalizer();
    private Stack<String> variables = new Stack<String>();
    private String semanticErrorMessage;

    public SintacticAnalizer() {
        setLexemas(lexemas);
        setTokens(tokens);

    }

    public void setRowGramar(int rowGramar) {  //que comiencen desde 0 para el analisis
        this.rowGramar = rowGramar;
    }

    public void setColumnGramar(int columnGramar) {
        this.columnGramar = columnGramar;
    }

    public void setLexemas(ArrayList<Integer> lexemas) { //pasar lexemas 
        this.lexemas = lexemas;
    }

    public ArrayList<Integer> getLexemas() {
        return lexemas;
    }

    public void setTokens(ArrayList<String> tokens) { //pasar tokens
        this.tokens = tokens;
    }

    public ArrayList<String> getTokens() { //meter tokens en arrayList 
        return tokens;
    }

    public boolean startAnalisis(String fileName) {
        boolean status = true;
        int len = tokens.size();
        int semanticAction = 0;
        int i = 0;
        System.out.println("************  ANALISIS SINTACTICO  **************");
        while (i < len) {
            if(productions.empty() && rowGramar == 0)
                status = fillFirtsProduction(lexemas.get(i), tokens.get(i));
            
            int currentElement = productions.peek();
            if(currentElement == lexemas.get(i)){  // comparacion con el resultado de lexico
                productions.pop();
                i++;
                continue;
            }
            else{
                if(currentElement == 300){
                    productions.pop();
                    status = isValidToken(lexemas.get(i), tokens.get(i));
                }
                else
                    status = false;
            }
            
            if(!status)
                break;
//            status = isValidToken(lexemas.get(i), tokens.get(i));
//            if(!status)
//                break;
//            
//            status = compareToken();
        }
        
        return status;
    }
    
    private boolean fillFirtsProduction(int lexema, String token){
        int column = sintacticBase.getColumnByToken(lexema, token);
        int prod = sintacticBase.gramar[rowGramar][column];
        System.out.println("El valor de la matriz es:   " + prod);
        if(prod == 600)
            return false;  
        getRowByProduction(prod);
        insertNewElements(prod);
        return true;
    }
    
    private boolean isValidToken(int lexema, String token){
        int column = sintacticBase.getColumnByToken(lexema, token);
        int prod = sintacticBase.gramar[rowGramar][column];
        if(prod == 600)
            return false;
        getRowByProduction(prod);
        insertNewElements(prod);
        return true;
    }

    public void insertNewElements(int production) {
        List poductionLine = sintacticBase.getPoduccionesByIndex(production);
        // Generate an iterator. Start just after the last element.
        ListIterator li = poductionLine.listIterator(poductionLine.size());

// Iterate in reverse.
        while (li.hasPrevious()) {
            System.out.println(li.previous());
//            productions.add(li.previous());
        }
    }

    public void getRowByProduction(int production){
        switch(production){
            case 0:
                rowGramar = 1;
                break;
            case 1:
                rowGramar = 1;
                break;
            case 2:
                rowGramar = 1;
                break;
            case 3:
                rowGramar = 2;
                break;
            case 4:
                rowGramar = 3;
                break;
            case 5:
                rowGramar = 4;
                break;
            case 6:
                rowGramar = 5;
                break;
            case 7:
                rowGramar = 5;
                break;
            case 8:
                rowGramar = 6;
                break;
            case 9:
                rowGramar = 6;
                break;
            case 10:
                rowGramar = 7;
                break;
            case 11:
                rowGramar = 7;
                break;
            case 12:
                rowGramar = 7;
                break;
            case 13:
                rowGramar = 7;
                break;
            case 14:
                rowGramar = 8;
                break;
            case 15:
                rowGramar = 8;
                break;
            case 16:
                rowGramar = 9;
                break;
            case 17:
                rowGramar = 9;
                break;
            case 18:
                rowGramar = 10;
                break;
            case 19:
                rowGramar = 10;
                break;
            case 20:
                rowGramar = 10;
                break;
            case 21:
                rowGramar = 10;
                break;
            case 22:
                rowGramar = 10;
                break;
            case 23:
                rowGramar = 10;
                break;
            case 24:
                rowGramar = 11;
                break;
            case 25:
                rowGramar = 11;
                break;
            case 26:
                rowGramar = 12;
                break;
            case 27:
                rowGramar = 13;
                break;
            case 28:
                rowGramar = 14;
                break;
            case 29:
                rowGramar = 14;
                break;
            case 30:
                rowGramar = 15;
                break;
            case 31:
                rowGramar = 15;
                break;
            case 32:
                rowGramar = 16;
                break;
            case 33:
                rowGramar = 17;
                break;
            case 34:
                rowGramar = 17;
                break;
            case 35:
                rowGramar = 18;
                break;
            case 36:
                rowGramar = 19;
                break;
            case 37:
                rowGramar = 20;
                break;
            case 38:
                rowGramar = 21;
                break;
            case 39:
                rowGramar = 22;
                break;
            case 40:
                rowGramar = 22;
                break;
            case 41:
                rowGramar = 23;
                break;
            case 42:
                rowGramar = 24;
                break;
            case 43:
                rowGramar = 25;
                break;
            case 44:
                rowGramar = 25;
                break;
            case 45:
                rowGramar = 26;
                break;
            case 46:
                rowGramar = 27;
                break;
            case 47:
                rowGramar = 27;
                break;
            case 48:
                rowGramar = 28;
                break;
            case 49:
                rowGramar = 29;
                break;
            case 50:
                rowGramar = 29;
                break;
            case 51:
                rowGramar = 30;
                break;
            case 52:
                rowGramar = 31;
                break;
            case 53:
                rowGramar = 31;
                break;
            case 54:
                rowGramar = 32;
                break;
            case 55:
                rowGramar = 33;
                break;
            case 56:
                rowGramar = 33;
                break;
            case 57:
                rowGramar = 34;
                break;
            case 58:
                rowGramar = 34;
                break;
            case 59:
                rowGramar = 34;
                break;
            case 60:
                rowGramar = 34;
                break;
            case 61:
                rowGramar = 34;
                break;
            case 62:
                rowGramar = 34;
                break;
            case 63:
                rowGramar = 35;
                break;
            case 64:
                rowGramar = 36;
                break;
            case 65:
                rowGramar = 36;
                break;
            case 66:
                rowGramar = 36;
                break;
            case 67:
                rowGramar = 37;
                break;
            case 68:
                rowGramar = 38;
                break;
            case 69:
                rowGramar = 38;
                break;
            case 70:
                rowGramar = 38;
                break;
            case 71:
                rowGramar = 38;
                break;
            case 72:
                rowGramar = 39;
                break;
            case 73:
                rowGramar = 39;
                break;
            case 74:
                rowGramar = 39;
                break;
            case 75:
                rowGramar = 39;
                break;
            case 76:
                rowGramar = 39;
                break;
            case 77:
                rowGramar = 39;
                break;
            case 78:
                rowGramar = 39;
                break;
            case 79:
                rowGramar = 4;
                break;
        }
    }
}
