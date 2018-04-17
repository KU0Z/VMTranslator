/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmtranslator;

import java.awt.List;
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
    public Boolean AddBootstrap;
    public void VMTranslate(String inputPath, Boolean addBootstrap) throws IOException, Exception{
        AddBootstrap = addBootstrap;
        if(new File(inputPath).isFile()){ // Its a file
            p = new Parser(inputPath);
            cw = new CodeWriter(inputPath);
            if (this.AddBootstrap)
                cw.writeinit();
            int commandNumber = 1;
        
            do{
                if(p.commandType()==CommandType.C_ARITHMETIC){
                   cw.bw.append("// "+p.getCommand()+"\n");
                   cw.writeArithmetic(p.getCommand(), commandNumber++);
                }else if(p.commandType()==CommandType.C_PUSH || p.commandType()==CommandType.C_POP){
                    cw.bw.append("// "+p.getCommand()+"\n");
                    cw.WritePushPop(p.commandType(), p.arg1(), p.arg2());
                }else if(p.commandType()==CommandType.C_LABEL){
                    cw.bw.append("// "+p.getCommand()+"\n");
                    cw.writeLabel(p.arg1());
                }else if(p.commandType()==CommandType.C_GOTO){
                    cw.bw.append("// "+p.getCommand()+"\n");
                    cw.writeGoto(p.arg1());
                }else if(p.commandType()==CommandType.C_IF){
                    cw.bw.append("// "+p.getCommand()+"\n");
                    cw.writeIf(p.arg1());
                }else if(p.commandType() == CommandType.C_CALL){
                    cw.bw.append("// "+p.getCommand()+"\n");
                    cw.writeCall(p.arg1(), p.arg2(), commandNumber++);
                }else if(p.commandType() == CommandType.C_FUNCTION){
                    cw.bw.append("// "+p.getCommand()+"\n");
                    cw.writeFunction(p.arg1(), p.arg2());
                }else if(p.commandType() == CommandType.C_RETURN){
                    cw.bw.append("// "+p.getCommand()+"\n");
                    cw.writeReturn();
                }

                if(p.hasMoreCommands()){
                    p.advance();
                }else{
                    break;
                }
            }while(true);
        }else if(new File(inputPath).isDirectory()){
            cw = new CodeWriter(inputPath);
            File f = new File(inputPath);
            String[] fileList = f.list();
            ArrayList vmFileList = new ArrayList();
            for(int i = 0; i < fileList.length; i++){
                if(fileList[i].endsWith(".vm")){
                    vmFileList.add(inputPath+"\\"+fileList[i]);
                }
            }
            if(vmFileList.size()==0)
                throw new Exception();
            if(this.AddBootstrap)
                cw.writeinit();
            int commandNumber = 1;
            for(int i = 0; i < vmFileList.size(); i++){
                p = new Parser((String)(vmFileList.get(i)));
                cw.setReadingFileName((String)(vmFileList.get(i)));
                do{
                    if(p.commandType()==CommandType.C_ARITHMETIC){
                       cw.bw.append("// "+p.getCommand()+"\n");
                       cw.writeArithmetic(p.getCommand(), commandNumber++);
                    }else if(p.commandType()==CommandType.C_PUSH || p.commandType()==CommandType.C_POP){
                        cw.bw.append("// "+p.getCommand()+"\n");
                        cw.WritePushPop(p.commandType(), p.arg1(), p.arg2());
                    }else if(p.commandType()==CommandType.C_LABEL){
                        cw.bw.append("// "+p.getCommand()+"\n");
                        cw.writeLabel(p.arg1());
                    }else if(p.commandType()==CommandType.C_GOTO){
                        cw.bw.append("// "+p.getCommand()+"\n");
                        cw.writeGoto(p.arg1());
                    }else if(p.commandType()==CommandType.C_IF){
                        cw.bw.append("// "+p.getCommand()+"\n");
                        cw.writeIf(p.arg1());
                    }else if(p.commandType() == CommandType.C_CALL){
                        cw.bw.append("// "+p.getCommand()+"\n");
                        cw.writeCall(p.arg1(), p.arg2(), commandNumber++);
                    }else if(p.commandType() == CommandType.C_FUNCTION){
                        cw.bw.append("// "+p.getCommand()+"\n");
                        cw.writeFunction(p.arg1(), p.arg2());
                    }else if(p.commandType() == CommandType.C_RETURN){
                        cw.bw.append("// "+p.getCommand()+"\n");
                        cw.writeReturn();
                    }

                    if(p.hasMoreCommands()){
                        p.advance();
                    }else{
                        break;
                    }
                }while(true);
            }
            
        }
        

        
       
        cw.Close();
    }    
}
