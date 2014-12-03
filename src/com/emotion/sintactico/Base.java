/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emotion.sintactico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Base {

    private List<List> producciones = new ArrayList();

    public Base() {
        initListas();
    }
    
    public int [][] gramar = {};
    
    private void initListas() {
        //<PROGRAM>  → class (id) <DECLARA> <ESTATUTOS> endclass
        List<Integer> prod0 = Arrays.asList(100, 122, 101, 123, 1, 100);
        producciones.add(prod0);
        //<DECLARA> → declare <B>  of  <TIPO> ; <AUX> 
        List<Integer> prod1 = Arrays.asList(100, 3, 100, 10, 126, 14);
        producciones.add(prod1);
        //<DECLARA> → €     
        List<Integer> prod2 = Arrays.asList();
//<B> → <ID_DIM> <AUX2>
        List<Integer> prod3 = Arrays.asList(4, 8);

//<ID_DIM> → id <C>
        List<Integer> prod4 = Arrays.asList(101, 5);

//<C> → [cteentera] <AUX3>     
        List<Integer> prod5 = Arrays.asList(124, 102, 125, 6);

//<AUX3> → <C>
        List<Integer> prod6 = Arrays.asList(5);

//<AUX3> → €     
        List<Integer> prod7 = Arrays.asList();

//<AUX2> → <B>   
        List<Integer> prod8 = Arrays.asList(3);

//<AUX2> → €     
        List<Integer> prod9 = Arrays.asList();

//<TIPO> →  int
        List<Integer> prod10 = Arrays.asList(100);

//12.	<TIPO> →  float (11)
        List<Integer> prod11 = Arrays.asList(100);

//13.	<TIPO> →  carácter  (12)
        List<Integer> prod12 = Arrays.asList(100);

//14.	<TIPO> →  string  (13)
        List<Integer> prod13 = Arrays.asList(100);

//15.	<AUX> →  <DECLARA>  (14)
        List<Integer> prod14 = Arrays.asList(1);

//16.	<AUX> → €  (15)
        List<Integer> prod15 = Arrays.asList();

//17.	<ESTATUTOS> →  <E> ; <AUX4> (16)
        List<Integer> prod16 = Arrays.asList(18, 126, 24);

//18.	<ESTATUTOS> →  €  (17)
        List<Integer> prod17 = Arrays.asList();

//19.	<E> →  <EST_ASIG> (18)
        List<Integer> prod18 = Arrays.asList(26);

//20.	<E> →  <EST_IF> (19)
        List<Integer> prod19 = Arrays.asList(32);

//21.	<E> →  <EST_WHILE> (20)
        List<Integer> prod20 = Arrays.asList(35);

//22.	<E> →  <EST_DO> (21)
        List<Integer> prod21 = Arrays.asList(36);

//23.	<E> →  <EST_READ> (22)
        List<Integer> prod22 = Arrays.asList(37);

//24.	<E> →  <EST_WRITE>  (23)
        List<Integer> prod23 = Arrays.asList(41);

//25.	<AUX4> →  <ESTATUTOS> (24)
        List<Integer> prod24 = Arrays.asList(16);

//26.	<AUX4> →  € (25)
        List<Integer> prod25 = Arrays.asList();

//27.	<EST_ASIG> →  <ASIG> = <EXPR> (26)
        List<Integer> prod26 = Arrays.asList(27, 109, 45);

//28.	<ASIG> →  id <DIM_ASIG> (27)
        List<Integer> prod27 = Arrays.asList(101, 28);

//29.	<DIM_ASIG> →  [<EXPR> <AUX5>] (28)
        List<Integer> prod28 = Arrays.asList(124, 45, 30, 125);

//30.	<DIM_ASIG> →  € (29)
        List<Integer> prod29 = Arrays.asList();

//31.	<AUX5> →  ,  <EXPR> <AUX5> (30)
        List<Integer> prod30 = Arrays.asList(127, 45, 30);

//32.	<AUX5> →  €  (31)
        List<Integer> prod31 = Arrays.asList();

//33.	<EST_IF> →  if(<EXPR>) <ESTATUTOS> <H> endif (32)
        List<Integer> prod32 = Arrays.asList(100, 122, 45, 123, 16, 33, 100);

//34.	<H> →  else <ESTATUTOS> (33)
        List<Integer> prod33 = Arrays.asList(100, 16);

//35.	<H> →  € (34)
        List<Integer> prod34 = Arrays.asList();

//36.	<EST_WHILE> →  while (<EXPR>) <ESTATUTOS> endwhile (35)
        List<Integer> prod35 = Arrays.asList(100, 122, 45, 123, 16, 100);

//37.	<EST_DO> → do <ESTATUTOS> dowhile (<EXPR>) enddo (36)
        List<Integer> prod36 = Arrays.asList(100, 16, 100, 122, 45, 123, 100);

//38.	<EST_READ> →  read (<I>) (37)
        List<Integer> prod37 = Arrays.asList(100, 122, 38, 123);

//39.	<I> →  id <AUX6> (38)
        List<Integer> prod38 = Arrays.asList(101, 39);

//40.	<AUX6> →  , <I>  (39)
        List<Integer> prod39 = Arrays.asList(127, 38);

//41.	<AUX6> →  €  (40)
        List<Integer> prod40 = Arrays.asList();

//42.	<EST_WRITE> →  write (<J>)  (41)
        List<Integer> prod41 = Arrays.asList(100, 122, 42, 123);

//43.	<J> →  <EXPR> <AUX7>  (42)
        List<Integer> prod42 = Arrays.asList(45, 43);

//44.	<AUX7> →  , <J>  (43)
        List<Integer> prod43 = Arrays.asList(127, 42);

//45.	<AUX7> →  €  (44)
        List<Integer> prod44 = Arrays.asList();

//46.	<EXPR> →  <EXPR2> <AUX8> (45)
        List<Integer> prod45 = Arrays.asList(48, 46);

//47.	<AUX8> →  || <EXPR2> <AUX8> (46)
        List<Integer> prod46 = Arrays.asList(105, 48, 46);

//48.	<AUX8> →  € (47)
        List<Integer> prod47 = Arrays.asList();

//49.	<EXPR2> →  <EXP3> <AUX9> (48)
        List<Integer> prod48 = Arrays.asList(51, 49);

//50.	<AUX9> →  && <EXP3> <AUX9> (49)
        List<Integer> prod49 = Arrays.asList(117, 51, 49);

//51.	<AUX9> →  € (50)
        List<Integer> prod50 = Arrays.asList();

//52.	<EXPR3> →  <NOT> <EXPR4> (51)
        List<Integer> prod51 = Arrays.asList(52, 54);

//53.	<NOT> → ! (52)
        List<Integer> prod52 = Arrays.asList(115);

//54.	<NOT> → € (53)
        List<Integer> prod53 = Arrays.asList();

//55.	<EXPR4> →  <EXPR5> <M> (54)
        List<Integer> prod54 = Arrays.asList(63, 55);

//56.	<M> →  <OPREL> <EXPR5> (55)
        List<Integer> prod55 = Arrays.asList(57, 63);

//57.	<M> → € (56)
        List<Integer> prod56 = Arrays.asList();

//58.	<OPREL> → ==  (57)
        List<Integer> prod57 = Arrays.asList(110);

//59.	<OPREL> → !=  (58)
        List<Integer> prod58 = Arrays.asList(116);

//60.	<OPREL> → <  (59)
        List<Integer> prod59 = Arrays.asList(111);

//61.	<OPREL> → <=  (60)
        List<Integer> prod60 = Arrays.asList(112);

//62.	<OPREL> → >  (61)
        List<Integer> prod61 = Arrays.asList(113);

//63.	<OPREL> → >=  (62)
        List<Integer> prod62 = Arrays.asList(114);

//64.	<EXPR5> →  <TERM> <AUX10> (63)
        List<Integer> prod63 = Arrays.asList(67, 64);

//65.	<AUX10> →  + <EXPR5>  (64)
        List<Integer> prod64 = Arrays.asList(118, 63);

//66.	<AUX10> → - <EXPR5>  (65)
        List<Integer> prod65 = Arrays.asList(63);

//67.	<AUX10> →  €  (66)
        List<Integer> prod66 = Arrays.asList();

//68.	<TERM> →  <FACT> <AUX11>  (67)
        List<Integer> prod67 = Arrays.asList(72, 68);

//69.	<AUX11> →  * <TERM>  (68)
        List<Integer> prod68 = Arrays.asList(120, 67);

//70.	<AUX11> →  / <TERM>   (69)
        List<Integer> prod69 = Arrays.asList(121, 67);

//71.	<AUX11> →  % <TERM>  (70)
        List<Integer> prod70 = Arrays.asList(128, 67);

//72.	<AUX11> →  €  (71)
        List<Integer> prod71 = Arrays.asList();

//73.	<FACT> →  <ASIG>  (72)
        List<Integer> prod72 = Arrays.asList(27);

//74.	<FACT> →  cteentera  (73)
        List<Integer> prod73 = Arrays.asList(102);

//75.	<FACT> →  ctereal  (74)
        List<Integer> prod74 = Arrays.asList(103);

//76.	<FACT> →  ctenotacion  (75)
        List<Integer> prod75 = Arrays.asList(104);

//77.	<FACT> →  ctecaracter  (76)
        List<Integer> prod76 = Arrays.asList(106);

//78.	<FACT> →  ctestring  (77)
        List<Integer> prod77 = Arrays.asList(107);

//79.	<FACT> →  ( <EXPR> )  (78)
        List<Integer> prod78 = Arrays.asList(122, 45, 123);
    }

    public List getPoduccionesByIndex(int index){
        return producciones.get(index);
    }
       
    public int getColumnByToken(int lexema, String token){
        int column = 600;
        switch(column){
            case 100:
                switch(token){
                    case "class":
                        column = 0;
                        break;
                    case "endclass":
                        column = 0;
                        break;
                    case "int":
                        column = 0;
                        break;
                    case "float":
                        column = 0;
                        break;
                    case "char":
                        column = 0;
                        break;
                    case "string":
                        column = 0;
                        break;
                    case "declare":
                        column = 0;
                        break;
                    case "of":
                        column = 0;
                        break;
                    case "if":
                        column = 0;
                        break;
                    case "else":
                        column = 0;
                        break;
                    case "endif":
                        column = 0;
                        break;
                    case "while":
                        column = 0;
                        break;
                    case "endwhile":
                        column = 0;
                        break;
                    case "do":
                        column = 0;
                        break;
                    case "dowhile":
                        column = 0;
                        break;
                    case "enddo":
                        column = 0;
                        break;
                    case "read":
                        column = 0;
                        break;
                    case "write":
                        column = 0;
                        break;
                    default:
                        column = 600;
                        break;
                }
                break;
            case 101:
                column = 0;
                break;
            case 103:
                column = 0;
                break;
            case 104:
                column = 0;
                break;
            case 105:
                column = 0;
                break;
            case 106:
                column = 0;
                break;
            case 107:
                column = 0;
                break;
            case 108:
                column = 0;
                break;
            case 109:
                column = 0;
                break;
            case 110:
                column = 0;
                break;
            case 111:
                column = 0;
                break;
            case 112:
                column = 0;
                break;
            case 113:
                column = 0;
                break;
            case 114:
                column = 0;
                break;
            case 115:
                column = 0;
                break;
            case 116:
                column = 0;
                break;
            case 117:
                column = 0;
                break;
            case 118:
                column = 0;
                break;
            case 119:
                column = 0;
                break;
            case 120:
                column = 0;
                break;
            case 121:
                column = 0;
                break;
            case 122:
                column = 0;
                break;
            case 123:
                column = 0;
                break;
            case 124:
                column = 0;
                break;
            case 125:
                column = 0;
                break;
            case 126:
                column = 0;
                break;
            case 127:
                column = 0;
                break;
            case 128:
                column = 0;
                break;
            default:
                column = 600;
                break;
        }
        return column;
    }
}
