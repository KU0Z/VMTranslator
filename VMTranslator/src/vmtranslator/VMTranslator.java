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
    public void VMTranslate(String inputPath) throws IOException{
        Parser p = new Parser(inputPath);
        CodeWriter cw = new CodeWriter(inputPath);
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
