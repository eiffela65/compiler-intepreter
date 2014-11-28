/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emotion.semantic;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Neftali
 */
public class Base {
    public Map<Integer,Integer> semanticActions = new HashMap<Integer,Integer>();
    
    public Base(){
        initMap();
    }
    
    private void initMap(){
        semanticActions.put(3, 900);
        semanticActions.put(6, 901);
        semanticActions.put(11, 902);
    }
    
    public int getActionByProduction(int production){
        return semanticActions.get(production);
    }
        
    public boolean hasSemanticAction(int production){
        boolean semanticAction = semanticActions.containsKey(production);
        return semanticAction;
    }
    
}
