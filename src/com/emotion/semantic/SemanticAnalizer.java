/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emotion.semantic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SemanticAnalizer {

    public Map<String, Symols> semanticSymbolTable = new HashMap<String, Symols>();

    public Symols getVariable(String variable) {
        if (isVariable(variable)) {
            return semanticSymbolTable.get(variable);
        }
        return null;
    }

    public boolean isVariable(String variable) {
        return semanticSymbolTable.containsKey(variable);
    }

    public void showSymbolTable() {
        System.out.println("************  ANALISIS SEMANTICO  **************");
        for (Map.Entry<String, Symols> entry : semanticSymbolTable.entrySet()) {
//            System.out.printf("Key : %s and Variable: %s and Type: %s and Value: %s %n", entry.getKey(), entry.getValue().getName(), entry.getValue().getType(), entry.getValue().getValue());
            System.out.printf("Variable: %s and Type: %s and Value: %s %n", entry.getValue().getName(), entry.getValue().getType(), entry.getValue().getValue());
        }
    }
}