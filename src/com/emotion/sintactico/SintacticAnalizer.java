/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emotion.sintactico;

import java.util.ArrayList;
import java.util.Stack;
import com.emotion.semantic.SemanticAnalizer;
import com.emotion.semantic.Symols;
import java.util.List;

//import org.apache.commons.collections.MultiMap;
public class SintacticAnalizer {

    private ArrayList<Integer> lexemas = new ArrayList(); //numeros de producciones dados por el lexico
    private ArrayList<String> tokens = new ArrayList();   //nombres tambien dados por el lexico
    private int rowGramar = 0;  //renglon
    private int columnGramar = 0;   //columna
    private Stack<Integer> productions = new Stack<Integer>();
    private com.emotion.sintactico.Base sintacticBase = new Base();
    private String fileName;
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
        boolean semanticStatus = true;
        int len = tokens.size();
        int semanticAction = 0;
        int i = 0;
        this.fileName = fileName;
        System.out.println("************  ANALISIS SINTACTICO  **************");
        while (i < len) {
            if(productions.empty() && rowGramar == 0)
                status = fillFirtsProduction(lexemas.get(i), tokens.get(i));
            
            int currentElement = productions.peek();
            System.out.println("Tope de la pila:    " + currentElement);
            System.out.println("Elemento del Lexico:    " + lexemas.get(i));
            if(currentElement == lexemas.get(i)){  // comparacion con el resultado de lexico
                productions.pop();
                i++;
                System.out.println("_______________________________________________________________________________________");
                continue;
            }
            else{
                if(currentElement < 100){
                    System.out.println("Procesando a un no terminal");
                    productions.pop();
                    status = isValidToken(lexemas.get(i), tokens.get(i), currentElement);
                    System.out.println("_______________________________________________________________________________________");
                    if(status)
                        continue;
                }else if(currentElement > 899){
                    productions.pop();
                    if(!runSemanticAction(currentElement, tokens.get(i-1))){
                        semanticStatus = false;
                        status = false;
                        break;
                    }
                    continue;
                }
                else
                    status = false;
            }
            
            if(!status){
                System.out.println("ERROR SINTACTICO cerca de: " + tokens.get(i));
                break;
            }
            i++;
            System.out.println("_______________________________________________________________________________________");
        }
        
        if(!productions.empty()){
            System.out.println("ERROR SINTACTICO: La pila de producciones no esta vacia.");
            status = false;
            showStack();
        }
        
        if(!semanticStatus)
            System.out.println("ERROR SEMANTICO: " + semanticErrorMessage);
        
        return status;
    }
    
    private void showStack(){
        int size = productions.size();
        for(int i = 0; i < size; i++){
            System.out.println("Elemento de la pila de producciones - " + productions.pop());
        }
    }
    
    private boolean fillFirtsProduction(int lexema, String token){
        int column = sintacticBase.getColumnByToken(lexema, token);
        System.out.println("[" + rowGramar + " - " + column + "]");
        int prod = sintacticBase.gramar[rowGramar][column];
        System.out.println("El valor de la matriz es:   " + prod);
        if(prod == 600)
            return false;  
        insertNewElements(prod);
        return true;
    }
    
    private boolean isValidToken(int lexema, String token, int row){
        int column = sintacticBase.getColumnByToken(lexema, token);
        int prod = sintacticBase.gramar[row][column];
        System.out.println("Columna en base a lexema: " + token);
        System.out.println("Produccion obtenida en posicion [" + row + " - " + column + "]: " + prod);
        if(prod == 600)
            return false;
        insertNewElements(prod);
        return true;
    }

    public void insertNewElements(int production) {
        List poductionLine = sintacticBase.getPoduccionesByIndex(production);
        int size = poductionLine.size();
        //Collections.reverse(poductionLine);
        System.out.println("Elementos de la produccion: " + production + "\n");
        for(int i = size - 1; i >= 0 ; i--){
            System.out.print((int) poductionLine.get(i) + "   ");
            productions.add((int) poductionLine.get(i));
        }
        System.out.println("\n");    
        
    }

    private boolean runSemanticAction(int semanticAction, String value) {
        System.out.println("Accion: " + semanticAction + "  Valor a analizar: " + value + "  fileName " + fileName);
        boolean status = true;
        Symols symbol = new Symols();
        switch (semanticAction) {
            case 900:
                if (!fileName.equals(value + ".lya")) {
                    status = false;
                    semanticErrorMessage = "Nombre de la clase no es el mismo al del archivo fuente.";
                }
                break;
            case 901:
                if (!sematicAalizer.isVariable(value)) {
                    symbol.setName(value);
                    sematicAalizer.semanticSymbolTable.put(value, symbol);  //se agrega la variable a la tabla de simbolos
                    variables.push(value);
                } else {
                    status = false;    //La variable ya existe
                    semanticErrorMessage = "La variable ya ha sido declarada con anterioridad.";
                }
                break;
            case 902:
                if (variables.empty()) {
                    status = false;    //La variable ya existe
                    semanticErrorMessage = "No existen variables en la pila de variables.";
                } else {
                    for (int i = 0; i < variables.size(); i++) {
                        String variable = variables.pop();
                        if (!sematicAalizer.isVariable(variable)) {
                            status = false;    //La variable ya existe
                            semanticErrorMessage = "No se puede asignar tipo a variable que no ha sido declarada.";
                        } else {
                            if (sematicAalizer.semanticSymbolTable.get(variable).getType() != null) { //si ya tiene un tipo asignado
                                status = false;
                                semanticErrorMessage = "La variable ya tiene un tipo asignado.";
                                break;
                            }
                            symbol = sematicAalizer.semanticSymbolTable.get(variable);
                            symbol.setType(value);
                            sematicAalizer.semanticSymbolTable.put(variable, symbol);
                        }
                    }
                }
                break;
            default:
                status = false;
        }
        return status;
    }
}
