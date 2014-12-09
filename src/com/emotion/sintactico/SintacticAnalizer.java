/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emotion.sintactico;

import com.emotion.semantic.Cuadruplos;
import java.util.ArrayList;
import java.util.Stack;
import com.emotion.semantic.SemanticAnalizer;
import com.emotion.sintactico.Base;
import com.emotion.semantic.Symols;
import java.util.Collections;
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
        this.fileName = fileName;
        int semanticAction = 0;
        int i = 0;
        System.out.println("************  ANALISIS SINTACTICO  **************");
        while (i < len) {
            if (productions.empty() && rowGramar == 0) {
                status = fillFirtsProduction(lexemas.get(i), tokens.get(i));
            }

            int currentElement = productions.peek();
          // System.out.println("Tope de la pila:    " + currentElement);
           // System.out.println("Elemento del Lexico:    " + lexemas.get(i));
            if (currentElement == lexemas.get(i)) {  // comparacion con el resultado de lexico
                productions.pop();
                i++;
             //   System.out.println("_______________________________________________________________________________________");
                continue;
            } else {
                if (currentElement < 100) {
                //    System.out.println("Procesando a un no terminal");
                    productions.pop();
                    status = isValidToken(lexemas.get(i), tokens.get(i), currentElement);
                 //   System.out.println("_______________________________________________________________________________________");
                    if (status) {
                        continue;
                    }
                } else if (currentElement > 899) {
                    productions.pop();
                    if (!runSemanticAction(currentElement, tokens.get(i - 1), lexemas.get(i - 1))) {
                        semanticStatus = false;
                        status = false;
                        break;
                    }
                    continue;
                } else {
                    status = false;
                }
            }

            if (!status) {
                System.out.println("ERROR SINTACTICO cerca de: " + tokens.get(i));
                break;
            }
            i++;
       //     System.out.println("_______________________________________________________________________________________");
        }
        if (!semanticStatus) {
            System.out.println("ERROR SEMANTICO: " + semanticErrorMessage);
        }
        if (!productions.empty()) {
            System.out.println("ERROR SINTACTICO: La pila de producciones no esta vacia.");
            status = false;
            showStack();
        }
        sematicAalizer.showSymbolTable();
        return status;
    }

    private void showStack() {
        int size = productions.size();
        for (int i = 0; i < size; i++) {
        //    System.out.println("Elemento de la pila de producciones - " + productions.pop());
        }
    }

    private boolean fillFirtsProduction(int lexema, String token) {
        int column = sintacticBase.getColumnByToken(lexema, token);
      //  System.out.println("[" + rowGramar + " - " + column + "]");
        int prod = sintacticBase.gramar[rowGramar][column];
       // System.out.println("El valor de la matriz es:   " + prod);
        if (prod == 600) {
            return false;
        }
        insertNewElements(prod);
        return true;
    }

    private boolean isValidToken(int lexema, String token, int row) {
        int column = sintacticBase.getColumnByToken(lexema, token);
        int prod = sintacticBase.gramar[row][column];
     //   System.out.println("Columna en base a lexema: " + token);
     //   System.out.println("Produccion obtenida en posicion [" + row + " - " + column + "]: " + prod);
        if (prod == 600) {
            return false;
        }
        insertNewElements(prod);
        return true;
    }

    public void insertNewElements(int production) {
        List poductionLine = sintacticBase.getPoduccionesByIndex(production);
        int size = poductionLine.size();
        //Collections.reverse(poductionLine);
     //   System.out.println("Elementos de la produccion: " + production + "\n");
        for (int i = size - 1; i >= 0; i--) {
      //      System.out.print((int) poductionLine.get(i) + "   ");
            productions.add((int) poductionLine.get(i));
        }
    //    System.out.println("\n");

    }

    private boolean runSemanticAction(int semanticAction, String value, int lexema) {
      //  System.out.println("Accion: " + semanticAction + "  Valor a analizar: " + value + "  fileName " + fileName);
        boolean status = true;
        Symols symbol = new Symols();
        switch (semanticAction) {
            case 900:  //Nombre de la clase
                if (!fileName.equals(value + ".lya")) {
                    status = false;
                    semanticErrorMessage = "Nombre de la clase no es el mismo al del archivo fuente.";
                }
                break;
            case 901: //Agregar variable a tabla de simbolos/
                if (!sematicAalizer.isVariable(value)) {
                    symbol.setName(value);
                    sematicAalizer.semanticSymbolTable.put(value, symbol);  //se agrega la variable a la tabla de simbolos
                    variables.push(value);
                } else {
                    status = false;    //La variable ya existe
                    semanticErrorMessage = "La variable ya ha sido declarada con anterioridad.";
                }
                break;
            case 902: // asignar tipo a variable
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
            case 903: // Estatuto de asignacion. Valida si variable existe, Meter en pila de operandos y pila de tipos
                status = sematicAalizer.isVariable(value);
                semanticErrorMessage = "La variable no existe.";
                if (status) {
                    sematicAalizer.operandos.push(value);
                    sematicAalizer.tipos.push(sematicAalizer.semanticSymbolTable.get(value).getType());
                }
                break;
            case 904: // Estatuto de asignacion.  Meter en pila de operadores =
                sematicAalizer.operadores.push(value);
                break;
            case 905: // Estatuto de asignacion. Validamos pila de operandos no este vacia. Validamos tipos. Generamos cuadruplo
                sematicAalizer.operandos.push(value);
                sematicAalizer.tipos.push(getType(lexema));
                if (sematicAalizer.operandos.empty() || sematicAalizer.operadores.empty() || sematicAalizer.tipos.empty()) {
                    status = false;
                    semanticErrorMessage = "No se puede generar cuadruplo, la pila de operandos esta vacia.";
                }
                if (status) {
                    String variable = sematicAalizer.operandos.pop();
                    String tipoVariable = sematicAalizer.tipos.pop();
                    if (sematicAalizer.operandos.empty() || sematicAalizer.tipos.empty()) {
                        status = false;
                        semanticErrorMessage = "No se puede generar cuadruplo, la pila de operandos esta vacia.";
                        break;
                    }
                    String operador = sematicAalizer.operadores.pop();
                    String resultado = sematicAalizer.operandos.pop();
                    String resultadoTipo = sematicAalizer.tipos.pop();
                    if (tipoVariable.equals(resultadoTipo)) {
                        Cuadruplos cuadruplo = new Cuadruplos();
                        cuadruplo.setOperador(operador);
                        cuadruplo.setOperando1(variable);
                        cuadruplo.setResultado(resultado);
                        sematicAalizer.cuadruplos.add(cuadruplo);
                    } else {
                        status = false;
                        semanticErrorMessage = "Incompatibilidad de tipos.";
                    }

                }
                break;
            case 906: // Estatuto IF push a parentesis
                sematicAalizer.operadores.push(value);
                break;
            case 907: // Estatuto IF pop a parentesis, crea el cuadruplo
                if (sematicAalizer.operandos.empty() || sematicAalizer.operadores.empty() || sematicAalizer.tipos.empty()) {
                    status = false;
                }
                if (status) {
                    String operador = "";
                    String tipoOperando1 = "";
                    String valueOperando1 = "";
                    Cuadruplos cuadruplo = null;
                    while (!sematicAalizer.operadores.peek().equals("(")) {
                        String operando = sematicAalizer.operandos.pop();
                        String operandoTipo = sematicAalizer.tipos.pop();
                        if (operador.equals("")) {
                            cuadruplo = new Cuadruplos();
                            operador = sematicAalizer.operadores.pop();
                            cuadruplo.setOperador(operador);
                            cuadruplo.setOperando1(operando);
                            tipoOperando1 = operandoTipo;
//                            valueOperando1 = getValue(operando);
                        } else {
                            if (tipoOperando1.equals(operandoTipo)) {
                                cuadruplo.setOperando2(operando);
                                String temporal = sematicAalizer.temporal.pop();
//                                String tipoTemporal = getValue(cuadruplo.getOperando1(), cuadruplo.getOperando2(), cuadruplo.getOperador(), tipoOperando1, operandoTipo);
                                cuadruplo.setResultado(temporal);
                                sematicAalizer.cuadruplos.add(cuadruplo);
                                sematicAalizer.operandos.push(temporal);
                                sematicAalizer.tipos.push(operandoTipo);
                                operador = "";
                            } else {
                                status = false;
                                semanticErrorMessage = "Error de tipos.";
                            }
                        }
                        if (sematicAalizer.operandos.empty() || sematicAalizer.operadores.empty() || sematicAalizer.tipos.empty()) {
                            status = false;
                            semanticErrorMessage = "No se pudo completar de evaluar la expresion, las pilas estan vacias.";
                            break;
                        }
                    }
                }
                break;
                
                case 908: //push pila de operadores (
                    break;
                    
                case 909: //Despues ) 
                    //1  validar pila de operandos, operadores, tipos no esta vacia
                    //2  pop () 3 pilas
                    //3  repetimos 1
                    //4  pop() pila de operandos y tipos
                    //5 validar tipos variables
                    //6  validar tipo de operador vs tipo de operando BOOLEAN?
                    //7  Hacer operacion y agregar a TS  variable temporal  GENERAR CUADRUPLO
                    //   push tabla de operandos T1
                    //    push tabla de tipos
                    //  *liberar variable temporal*
                    //   todo en ciclo hasta que se tope con (
                    
                    break;
                         
                case 910: 
                    //1 validamos pila de operadores no sea vacia
                    //2   peek() pila de operadores
                    //3 si operador == * / %
                    // validamos pila de operandos y tipos no este vacia
                    // pop() pila operandos y tipos
                    //validamos pila de operandos y tipos no este vacia 
                    //pop() pila de operandos y tipos
                    //4  validar tipos operandos
                    //5 validar operador vs operando
                    //6  hacer operacion      
                    //7 push pila operandos y tipo
                   
                    //8 GENERAR CUADRUPLO
                    //9 *SI alguno de los operandos correspondia a un temporal ENTONCES regresarlo al avail*
                    
                    //NOTA
                    //si alguno de los operandos correspondia a un temporal ENTONCES regresarlo al avail
                    //push pila de operandos (resultado)
                    //pop pila de operadores
                    
                    break;
                
                 case 911:
                     //agregar a lista de operadores
                     //validar operadores
                     // (seguir notacion polaca)
                    break;
                     
                 case 912: 
                     //agregar a lista de operadores
                     //validar operadores
                     //(seguir notacion polaca)
                    break;
                     
                  case 913: 
                      //agregar a lista de operadores
                     //validar operadores
                     //(seguir notacion polaca)
                    break;
                      
                  case 914: //DESPUES DE EXPR5
                      // similar a 910 con todos sus pasos pero evaluando operadores +  -
                      //y con cuadruplo con +  o  -
                    break;
                      
                  case 915: 
                      //agregar a lista de operadores
                      //validar operadores
                      // (seguir notacion polaca)
                    break;
                      
                   case 916: 
                    //agregar a lista de operadores
                      //validar operadores
                      // (seguir notacion polaca)
                       
                    break;
                       
                   case 917: //DESPUES DE OPEREL
                    //agregar operador a pila de operadores
                       //(seguir notacion polaca)
                       
                    break;
                       
                    case 918: 
                        
                        //1  validar pila operandos, operadores y tipos no estan vacios
                        //2  pop()  3 pilas
                        //3  validar pila de operandos y tipos no este vacia
                        //4  pop pila operandos    pop pila de tipos
                        //5  validar tipos
                        //6 operacion logica return valor, boolean  GENERAR CUADRUPLO
                        //7  push valor a pila de operandos y push boolean a pila de tipos
                    break;
                        
                    case 919: //DESPUES DE !
                        //push ! pila operadores
                    break;
                        
                    case 920: 
                        //1  validar pilas (operando,operador,tipos) no esten vacias
                        //2   pop pila de operadores
                        //3  si operador == !
                        //4  pop pila de operandos  pop pila de tipos
                        //5  validar tipo = boolean
                        // si es boolean:
                       //     5.1  cambiar valor   (REALIZA CUADRUPLO? )
                       //     5.2  push nuevo valor a pila de operandos
                        //    5.3  push boolean a pila de tipos
                        
                        //Y SINO ES BOOLEAN ES ERROR SEMANTICO NO?
                    break;
                        
                    case 921: //DESPUES DE EXPR3
                        //1   peek() pila de operadores
                        //2    si operador = &&
                        //       2.1   pop pila de tipos  pop pila de operandos   pop pila operadores
                        //       2.1  si tipos = boolean
                        //             2.1.1  pop pila de operandos   pop pila de tipos
                        //             2.1.1  si tipos = boolean
                        //                   2.1.1.1   Hacer operacion logica valor, boolean    GENERAR CUADRUPLO
                        //                   2.1.1.2   push valor y tipo a pila de operandos y pila de tipos
                        //3   sino no hacer nada
                    break;
                        
                    case 922://DESPUES DE EXPR3
                        //agregar && a pila de operadores
                    break;
                        
                    case 923: //DESPUES DE EXPR2
                        //igual que la 921 pero con ||
                         //1   peek() pila de operadores
                         //2    si operador = ||
                        //       2.1   pop pila de tipos  pop pila de operandos   pop pila operadores
                        //       2.1  si tipos = boolean
                        //             2.1.1  pop pila de operandos   pop pila de tipos
                        //             2.1.1  si tipos = boolean
                        //                   2.1.1.1   Hacer operacion logica valor, boolean    GENERAR CUADRUPLO
                        //                   2.1.1.2   push valor y tipo a pila de operandos y pila de tipos
                        //3   sino no hacer nada
                        
                    break;
                        
                    case 924://DESPUES DE EXPR2
                        //agregar || a pila de operadores
                    break;
                        
                    case 925://EST_ASIG despues de id
                        //1     validar id este en tabla de simbolos
                        //1.1   push pila de operandos
                    break;
                        
                        
                    case 926: 
                        //push = a pila de operadores
                    break;
                        
                    case 927: 
                        //1    validar pila de tipos, operandos, operadores NO esten vacias
                        //2    pop pila de operandos,  pop pila de tipos,   pop pila de operadores
                        //3    validar pila de tipos y operandos no esten vacias
                        //4    pop pila de operando, pop pila de tipos 
                        //5    validar tipos
                        //6    hacer operacion valor, tipo
                        //7    CREAR CUADRUPLO
                        //8     verificar que operando 2 este en tabla de simbolos  QUE NO VA ANTES DE HACER LA OPERACION?
                        //9    actualizar tabla de simbolos
                    break;
                         
                    case 928: //EST_IF
                        //1   validar pila operando y tipo no este vacia
                        //2   pop pila de operandos, pop pila de tipos
                        //3   validar tipo=boolean
                        //   IF condition=true    FALSE NO?
                                //CREA CUADRUPLO A  salto falso ASI:
                                
                                 //push a pila de saltos (cont) *con la posicion del contador* (METER FALSO)
                                //sacar resultado de pila de operandos
                                //generar gotofalso de resultado a una direccion pendiente por resolver ASI:

                                //(operador, operando1, operando2 resultado)
                                //(gotofalso, resultado*popPilaDeOperandos*,    , ? *pendiente por resolver*)
                        
                        //     ELSE  NEFT VERIFICA PARA ESTA PARTE COMO SE HACE EN EL BLOC DE NOTAS QUE TE ENVIE
                        // EN LA ACCION 917
                        //      CREA CUADRUPLO salto verdadero pendiente 
                        //      push pila de saltos (cont-1)
                        
                        // DESPUES DEL ELSE
                         //valida pila de saltos vacia
                        //pop pila de saltos
                        //con el apuntador    (contador)
                    break;
                         
                         
                    case 929: //DESPUES ENDIF 
                        //validar pila de saltos no este vacia
                        //pop pila de saltos
                        //actualiza cuadruplos con apuntador actual
                    break;
                        
                   case 930: //EST_WHILE
                       //igual a 928
                    break;
                       
                   case 931: //ENDWHILE
                       //Igual que 929
                    break;
                       
                    case 932: //EST_DO
                        //igual que 928
                    break;
                   
                   case 933: //ENDO
                       //Igual que 929
                    break;
                   
                  case 934: //EST_WRITE
                      //1  valida que pila de operandos no este vacia
                      //2  pop pila de operandos
                      //3   muestra operando
                      
                      //Y EL CUADRUPLO (OUTPUT, ,  ,id) ?
                      //Y SI SE PONE WRITE "HOLA" ? tambien estara en pila de operandos la cadena?
                      
                    break;
                     
                      
                  case 935: //EST_READ
                      //Meter marca de fondo falso a pila de operador (
                      //GENERAR CUADRUPLO (INPUT,  ,  ,id)
                      //cuando se encuentre con ) sacar marca de fondo falso
                    break;
            default:
                status = false;
        }
        return status;
    }

    private String getType(int lexema) {
        String type = "";
        switch (lexema) {
            case 102:
                type = "int";
                break;
            case 103:
                type = "float";
                break;
            case 104:
                type = "float";
                break;
            case 106:
                type = "char";
                break;
            case 107:
                type = "string";
                break;
        }
        return type;
    }

//    private String getValue(String operando1, String operando2, String operador, String tipoOperando1, String operandoTipo) {
//        String stringValue1 = "";
//        String stringValue2 = "";
//        Object value1;
//        Object value2;
//        if (sematicAalizer.isVariable(operando1)) 
//            stringValue1 = sematicAalizer.semanticSymbolTable.get(operando1).getValue();
//        else
//            stringValue1 = operando1;
//        
//        if (sematicAalizer.isVariable(operando2)) 
//            stringValue2 = sematicAalizer.semanticSymbolTable.get(operando2).getValue();
//        else
//            stringValue2 = operando1;
//
//        if (operador.equals("int")) {
//            value1 = new Integer(stringValue1);
//        } else if (operador.equals("float")) {
//            value1 = new Float(stringValue1);
//        } else {
//            value1 = stringValue1;
//        }
//        
//        if (operador.equals("int")) {
//            value2 = new Integer(stringValue2);
//        } else if (operador.equals("float")) {
//            value2 = new Float(stringValue2);
//        } else {
//            value2 = stringValue2;
//        }
//        String resultResult;
//        switch(operador){
//            case "+":
//                Integer result;
//        result = (value1 + value2);
//        }
//    }
//    
//        
//    private Object getRealValue(String tipo, String operando){
//        if (tipo.equals("int")) {
//            return new Integer(operando);
//        } else if (tipo.equals("float")) {
//            return new Float(operando);
//        } else {
//            return new String(operando);
//        }
//    }
//
//    private void performOperation(Object value1, Object value2, String operator) {
//        Object result = null;
//        switch(operator){
//            case "+":
//                result = value1 + value2;
//                break;
//        }
//    }
}
