/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emotion.sintactic.old;

public class Base {

    /**
     *
     */
    
    //COMPLEMENTO PARA EXPRESION DE LA MATRIZ DE TRANSICIONES
//   COLUMNA                                        0            1           2      3     4      5      6      7     8      9     10      11         12        13       14           15      16   17   18     19     20   21    22    23    24    25       26       27       28 
//   PRODUCCION                                    100          101         102    103   104    118    119    120   121    109    110    111        112       113      114          116     115  117  105    122    123  124   125   126   127   106      107      108      128  
// MATRIZ EXPRESION                ESTADOS       RESERVADA  IDENTIFICADOR  ENTERO  REAL   N.C.  SUMA   RESTA  MULTI  DIVI  ASIGN  IGUAL  MENOR   MENORIGUAL   MAYOR  MAYORIGUAL  DIFERENTE  NOT  AND  OR      (      )    [     ]     ;     ,   CHAR    STRING   COMENT     MOD                       
    public int[][] expresion =    /* 0 */      {{600,             3,          1,    1,     1,   600,    600,   600,  600,   600,   600,   600,       600,      600,      600,       600,     2,  600, 600,     0,    0,  600,  400,  600,   0,    1,       1,      600,     600},
                                  /* 1 */      {400,            400,        400,  400,   400,     0,      0,     0,    0,   400,     0,     0,         0,        0,        0,         0,   400,    0,   0,   400,  400,  400,  400,  400, 400,  400,     400,      400,       0},
                                  /* 2 */      {600,              3,          1,    1,     1,   600,    600,   600,  600,   600,   600,   600,       600,      600,      600,       600,   600,  600, 600,   600,  600,  600,  600,  600, 600,    1,       1,      600,     600},
                                  /* 3 */      {400,            400,        400,  400,   400,     0,      0,     0,    0,   400,     0,     0,         0,        0,        0,         0,   400,    0,   0,   400,  400,    0,  400,  400, 400,  400,     400,      400,      0}};
    
    
    //MATRIZ DE PRODUCCIONES
    
    //PRODUCCION <PROGRAM>             101 900 123  *aqui iria el numero de la accion semantica*
    //                     class  (     id    )       <DECLARA>              <ESTATUTOS>         endclass
    // prod0 = new int [] { 100, 122,   101,  123,     (prod1),             (prod14),(prod15),      100
    
    //PRODUCCION <DECLARA>
    //                     declare    <ID_DIM>   <OTRO_IDDIM>         of    <TIPO>;
    //prod1 = new int []  {  100      (prod2)    (prod6),(prod7)     100     *AQUI el numero de la produccion TIPO creo que debe llevar el mismo numero de produccion es decir por ejemplo 7 produce int y 7 produce float es decir el mismo numero en la gramatica         
    
    //   COLUMNA                                    0            1           2      3     4      5      6      7     8      9     10      11         12        13      14           15      16   17   18     19     20   21    22    23    24    25       26       27       28           
    //   PRODUCCION                                100          101         102    103   104    118    119    120   121    109    110    111        112       113     114          116     115  117  105    122    123  124   125   126   127   106      107      108      128    
    //MATRIZ DE TRANSICIONES       ESTADOS      RESERVADA  IDENTIFICADOR  ENTERO  REAL   N.C.  SUMA   RESTA  MULTI  DIVI  ASIGN  IGUAL  MENOR   MENORIGUAL   MAYOR  MAYORIGUAL  DIFERENTE  NOT  AND  OR      (      )    [     ]     ;     ,   CHAR    STRING   COMENT     MOD    
    public int[][] grammar =       /* 0 */     {{1,            600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,  600,  600,  600,  600,     600,     600,    600},
                                   /* 1 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,    2,   600,  600,  600,  600,  600,  600,     600,     600,    600},
                                   /* 2 */     {600,            3,         600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,  600,  600,  600,  600,     600,     600,    600},
                                   /* 3 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,   4,   600,  600,  600,  600,  600,     600,     600,    600},
                                   /* 4 */     {200,            13,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,  600,  600,  600,  600,     600,     600,    600},
                                   /* 5 */     {600,            6,         600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,  600,  600,  600,  600,     600,     600,    600},
                                   /* 6 */     {10,            600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,   7,   600,  600,   5,   600,     600,     600,    600},
                                   /* 7 */     {600,           600,         8,     600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,  600,  600,  600,  600,     600,     600,    600},
                                   /* 8 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,   9,   600,  600,  600,     600,     600,    600},
                                   /* 9 */     {10,            600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,  600,  600,   5,   600,     600,     600,    600},
                                  /* 10 */     {11,            600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,  600,  600,  600,  600,     600,     600,    600},
                                  /* 11 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,  600,   12,  600,  600,     600,     600,    600},
                                  /* 12 */     {200,           13,         600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,  600,  600,  600,  600,     600,     600,    600},
                                  /* 13 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,   14,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,   15,  600,  600,  600,  600,     600,     600,    600},
                                  /* 14 */     {300,           300,        300,    300,  300,  300,    300,   300,   300,  300,   300,   300,      300,       300,     300,        300,    300, 300, 300,   300,  300,  300,  300,  300,  300,  300,     300,     300,    300},
                                  /* 15 */     {301,           301,        301,    301,  301,  301,    301,   301,   301,  301,   301,   301,      301,       301,     301,        301,    301, 301, 301,   301,  301,  301,  301,  301,  301,  301,     301,     301,    301},
                                  /* 16 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,   17,  600,   15,  600,     600,     600,    600},
                                  /* 17 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,   14,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,  600,  600,  600,  600,     600,     600,    600},
                                  /* 18 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,  600,   12,  600,  600,     600,     600,    600},
                                  /* 19 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,    20,  600,  600,  600,  600,  600,  600,     600,     600,    600},
                                  /* 20 */     {302,           302,        302,    302,  302,  302,    302,   302,   302,  302,   302,   302,      302,       302,     302,        302,    302, 302, 302,   302,  302,  302,  302,  302,  302,  302,     302,     302,    302},
                                  /* 21 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,   12,  600,  600,  600,  600,  600,     600,     600,    600},
                                  /* 22 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,  600,   12,  600,  600,     600,     600,    600},
                                  /* 23 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,    24,  600,  600,  600,  600,  600,  600,     600,     600,    600},
                                  /* 24 */     {303,           303,        303,    303,  303,  303,    303,   303,   303,  303,   303,   303,      303,       303,     303,        303,    303, 303, 303,   303,  303,  303,  303,  303,  303,  303,     303,     303,    303},
                                  /* 25 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,   12,  600,  600,  600,  600,  600,     600,     600,    600},
                                  /* 26 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,  600,   12,  600,  600,     600,     600,    600},
                                  /* 27 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,  600,   12,  600,  600,     600,     600,    600},
                                  /* 28 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,    29,  600,  600,  600,  600,  600,  600,     600,     600,    600},
                                  /* 29 */     {600,            30,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,  600,  600,  600,  600,     600,     600,    600},
                                  /* 30 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,   31,  600,  600,  600,   29,  600,     600,     600,    600},
                                  /* 31 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,  600,   12,  600,  600,     600,     600,    600},
                                  /* 32 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,    33,  600,  600,  600,  600,  600,  600,     600,     600,    600},
                                  /* 33 */     {304,           304,        304,    304,  304,  304,    304,   304,   304,  304,   304,   304,      304,       304,     304,        304,    304, 304, 304,   304,  304,  304,  304,  304,  304,  304,     304,     304,    304},
                                  /* 34 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,   35,  600,  600,  600,  600,  600,     600,     600,    600},
                                  /* 35 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,   36,  600,  600,   12,   33,  600,     600,     600,    600},
                                  /* 36 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,  600,   12,  600,  600,     600,     600,    600},
                                  /* 37 */     {600,           600,        600,    600,  600,  600,    600,   600,   600,  600,   600,   600,      600,       600,     600,        600,    600, 600, 600,   600,  600,  600,  600,  600,  600,  600,     600,     600,    600}};

    private int rowGrammar = 0;
    private int columnGrammar = 0;

    public void setRowGrammar(int row) {
        this.rowGrammar = row;
    }

    public int getRowGrammar() {
        return rowGrammar;
    }

    public void setColumnGrammar(int row) {
        this.columnGrammar = row;
    }

    public int getColumnGrammar() {
        return columnGrammar;
    }

    public int getNewRow(boolean matrixType) { //cambia de la matriz principal a la de expresion
//        System.out.println("Matriz = " + matrixType);
        if(matrixType)
            return grammar[rowGrammar][columnGrammar];
        else
            return expresion[rowGrammar][columnGrammar];
    }
    
    public static int getColumn(int lexema){ //SE COMPARA DEPENDIENDO DEL LEXEMA LEIDO CON EL EXCEL PARA ASIGNAR LA COLUMNA
        int column = 0;
        switch(lexema){
            case 100:
                column = 0;
                break;
            case 101:
                column = 1;
                break;
            case 102:
                column = 2;
                break;
            case 103:
                column = 3;
                break;
            case 104:
                column = 4;
                break;
            case 105:
                column = 18;
                break;
            case 106:
                column = 25;
                break;
            case 107:
                column = 26;
                break;
            case 108:
                column = 27;
                break;
            case 109:
                column = 9;
                break;
            case 110:
                column = 10;
                break;
            case 111:
                column = 11;
                break;
            case 112:
                column = 12;
                break;
            case 113:
                column = 13;
                break;
            case 114:
                column = 14;
                break;
            case 115:
                column = 16;
                break;
            case 116:
                column = 15;
                break;
            case 117:
                column = 17;
                break;
            case 118:
                column = 5;
                break;
            case 119:
                column = 6;
                break;
            case 120:
                column = 7;
                break;
            case 121:
                column = 8;
                break;
            case 122:
                column = 19;
                break;
            case 123:
                column = 20;
                break;
            case 124:
                column = 21;
                break;
            case 125:
                column = 22;
                break;
            case 126:
                column = 23;
                break;
            case 127:
                column = 24;
                break;
            case 128:
                column = 28;
                break;
        }
        return column;
    }

}
