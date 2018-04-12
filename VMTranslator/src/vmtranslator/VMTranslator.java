/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmtranslator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Maynor
 */
public class VMTranslator {
    public CodeWriter cw;
    public Parser p;
    public void VMTranslate(String inputPath) throws IOException{
        
        if(new File(inputPath).exists()){ // Its a file
            
        }
        p = new Parser(inputPath);
        cw = new CodeWriter(inputPath);
        int commandNumber = 0;
        
        do{
            if(p.commandType()==CommandType.C_ARITHMETIC){
               cw.bw.append("// "+p.getCommand()+"\n");
               cw.writeArithmetic(p.getCommand(), commandNumber++);
            }else if(p.commandType()==CommandType.C_PUSH || p.commandType()==CommandType.C_POP){
                cw.bw.append("// "+p.getCommand()+"\n");
                cw.WritePushPop(p.commandType(), p.arg1(), p.arg2());
            }
            if(p.hasMoreCommands()){
                p.advance();
            }else{
                break;
            }
        }while(true);
        
       
        cw.Close();
    }    
}
