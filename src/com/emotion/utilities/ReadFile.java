package com.emotion.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.emotion.exception.FileException;
import com.emotion.exception.LexicoException;
import com.emotion.lexico.Base;
import com.emotion.sintactico.SintacticAnalizer;
import java.util.ArrayList;

public class ReadFile {

    private static int rowLexema = 0;
    private static int columnLexema = 0;
//    private static final SintacticAnalizer sintactic = new SintacticAnalizer(0,0);

    private static ArrayList<String> tokens = new ArrayList();
    private static ArrayList<Integer> lexemas = new ArrayList();
    private static boolean status = true;

    /**
     *
     */
    public static final String newLine = System.getProperty("line.separator");

    public static boolean validateFile(File source) {
        return source != null;
    }

    public static boolean readFile(File file) throws FileException, LexicoException {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line = br.readLine();
            int count = 1;
            System.out.println("************  LEXICALCAL ANALISIS  **************");
            while (line != null) {
                isLexema(line);
                line = br.readLine();
                count++;
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new FileException("File is not found");
        } catch (IOException e) {
            throw new FileException("File can not be read. It could be corrupted");
        }
        if (!status) {
            throw new LexicoException("Lexical error was found at line: ");
        }
        SintacticAnalizer sintactic = new SintacticAnalizer();
        sintactic.setRowGramar(0);
        sintactic.setColumnGramar(0);
        sintactic.setLexemas(lexemas);
        sintactic.setTokens(tokens);
        sintactic.startAnalisis();
        return status;
    }

    private static void isLexema(String line) {
        line += newLine;
        Base baseLexico = new Base(0, 0);
//        System.out.println("Line   " + line);
        StringBuilder token = new StringBuilder();
        int i = 0;
        while (i < line.length()) {
//            System.out.println(line.charAt(i) + "  -  [" + baseLexico.getRowLexema() + " - " + baseLexico.getColumnLexema() + "]");
            rowLexema = baseLexico.getLexema(line.charAt(i));
            if (rowLexema == 6) {
                if (line.charAt(i) != 'E') {
                    rowLexema = 507;
                }
                if (line.charAt(i) != 'e') {
                    rowLexema = 507;
                }
            }
            baseLexico.setRowLexema(rowLexema);
            if (rowLexema < 200 && rowLexema > 99) {

                if (token.toString().trim().equals("")) {
                    token.append(line.charAt(i));
                }
                if (rowLexema == 106) {
                    token.append(line.charAt(i));
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
                token.append(line.charAt(i+1));
                System.out.println("ERROR token ---->   " + token.toString().trim() + "  -  " + rowLexema);
                baseLexico.setRowLexema(0);
                token.setLength(0);
                status = false;
            }
            token.append(line.charAt(i));
            i++;
        }
    }

    public static void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, "InfoBox: " + title, JOptionPane.INFORMATION_MESSAGE);
    }

}
