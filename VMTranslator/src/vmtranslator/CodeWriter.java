/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmtranslator;

/**
 *
 * @author Maynor
 */
public class CodeWriter {
    // Opens the output file/stream and gets ready 
    // to write into it.
    public CodeWriter(String outputFile){
        
    }
    
    //  Informs the code wrter that the translation of a
    //& new VM file is started.
    public void setFileName(String fileName){
        
    }
    
    // Writes the assembly code that is the translation
    // of the given arithmetic command
    public void writeArithmetic(String command){
    
    }
    
    // Writes the assembly code what is the translation
    // of the given command, where command is either
    // C_PUSH or C_POP.
    public void WritePushPop(CommandType command, String segment, int index){
        
    }
    
    // Closes the output file.
    public void Close(){
        
    }
}
