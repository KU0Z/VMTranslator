/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmtranslator;

import java.io.IOException;

/**
 *
 * @author Maynor
 */
public class VMTranslator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String inputPath = "C:\\Users\\Maynor\\Desktop\\Arqui\\Grupo_00-grupo_10-cf15225bdcb7361465c6d6685a4ac392824cabbc\\nand2tetris\\projects\\07\\StackArithmetic\\SimpleAdd\\SimpleAdd.vm";
        Parser p = new Parser(inputPath);
        CodeWriter cw = new CodeWriter(inputPath+".txt");
        
        while(p.hasMoreCommands()){
            if(p.commandType()==CommandType.C_ARITHMETIC){
                cw.writeArithmetic(p.getCommand());
            }else if(p.commandType()==CommandType.C_PUSH || p.commandType()==CommandType.C_POP){
                cw.WritePushPop(p.commandType(), p.arg1(), p.arg2());
            }
            p.advance();
        }
        cw.Close();
    }
    
}
