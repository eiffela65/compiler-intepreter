package com.emotion.lexico;

import java.io.File;

import com.emotion.exception.FileException;
import com.emotion.exception.LexicoException;
import com.emotion.sintactico.SintacticAnalizer;
import com.emotion.utilities.ReadFile;
import static com.emotion.utilities.ReadFile.newLine;
import java.util.ArrayList;

public class LexicAnalizer {
	private boolean status = true;
        private static int rowLexema = 0;
        private static ArrayList<String> tokens = new ArrayList();
        private static ArrayList<Integer> lexemas = new ArrayList();
	
	public void startAnalisis(File file){
		try {
			this.status = ReadFile.readFile(file);
			
		} catch (FileException | LexicoException e) {
			setStatus(false);
		}
	}
        
        public void startAnalisis(String text){
		try {
			this.status = readText(text);
			
		} catch (LexicoException e) {
			setStatus(false);
		}
	}

	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

    private boolean readText(String text) throws LexicoException {
        text += newLine;
        Base baseLexico = new Base(0, 0);
//        System.out.println("Line   " + line);
        StringBuilder token = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
//            System.out.println(line.charAt(i) + "  -  [" + baseLexico.getRowLexema() + " - " + baseLexico.getColumnLexema() + "]");
            rowLexema = baseLexico.getLexema(text.charAt(i));
            if (rowLexema == 6) {
                if (text.charAt(i) != 'E') {
                    rowLexema = 507;
                }
                if (text.charAt(i) != 'e') {
                    rowLexema = 507;
                }
            }
            baseLexico.setRowLexema(rowLexema);
            if (rowLexema < 200 && rowLexema > 99) {

                if (token.toString().trim().equals("")) {
                    token.append(text.charAt(i));
                }
                if (rowLexema == 106) {
                    token.append(text.charAt(i));
                    i++;
                }
                if (!Base.isReserved(token.toString().trim()) && rowLexema == 100) {
                    rowLexema = 101;
                }

                System.out.println("token ---->   " + token.toString().trim() + "  -  " + rowLexema);
                tokens.add(token.toString().trim());
                lexemas.add(rowLexema);
                token.setLength(0);
                baseLexico.setRowLexema(0);
                if (rowLexema > 117) {
                    i++;
                }
                continue;

            } else if (rowLexema > 199) {
                token.append(text.charAt(i+1));
                System.out.println("ERROR token ---->   " + token.toString().trim() + "  -  " + rowLexema);
                baseLexico.setRowLexema(0);
                token.setLength(0);
                status = false;
            }
            token.append(text.charAt(i));
            i++;
        }
        
        if(!status)
            throw new LexicoException("Lexical Error");
        
        SintacticAnalizer sintactic = new SintacticAnalizer();
        sintactic.setRowGramar(0);
        sintactic.setColumnGramar(0);
        sintactic.setLexemas(lexemas);
        sintactic.setTokens(tokens);
        sintactic.startAnalisis();
        return true;
    }
}
