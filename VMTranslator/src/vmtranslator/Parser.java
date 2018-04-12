/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmtranslator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Maynor
 */
public class Parser {
    private FileReader fr;
    private BufferedReader br;
    private String currentCommand = null;
    private String nextCommand = null;
    public ArrayList<String> originalContent = new ArrayList<>();
    
    public String getCommand(){
        return currentCommand;
    }
    
    // Opens the input file and gets ready 
    // to parser it
    public Parser(String path) throws FileNotFoundException, IOException{
        fr = new FileReader(path);
        br = new BufferedReader(fr);
        currentCommand = br.readLine();
    }
    
    // Are ther more commands in the input?
    public Boolean hasMoreCommands() throws IOException{
        String probNext = br.readLine();
        if(probNext != null){
            nextCommand = probNext;
            return true;
        }else{
            br.close();
            fr.close();
            return false;
        }
    }
    
    // Reads the next command from the input and makes
    // it the current command. Should be called only if
    // hasMoreCommands() is true. Initially there is no
    // current command.
    public void advance(){
        currentCommand = nextCommand;
        originalContent.add(currentCommand);
    }
    
    // Returns the type of the current VM command,
    // C_ARITHMETIC is returned for all the arithmetic
    // commands.
    public CommandType commandType(){
        String[] tokens = currentCommand.split(" ");
        int nTokens = tokens.length;
        if(nTokens == 1){
           if(currentCommand.equals("add") || currentCommand.equals("sub")
                   || currentCommand.equals("neg")|| currentCommand.equals("eq")
                   || currentCommand.equals("gt") || currentCommand.equals("lt")
                   || currentCommand.equals("and")|| currentCommand.equals("or")
                   || currentCommand.equals("not")){
               return CommandType.C_ARITHMETIC;
           } 
        } else if(nTokens == 2){
            if(tokens[0].equals("label")){
               return CommandType.C_LABEL;
           } else if(tokens[0].equals("goto")){
               return CommandType.C_GOTO;
           } else if(tokens[0].equals("if-goto")){
               return CommandType.C_IF;
           }
        } else if(nTokens == 3){
            if(tokens[0].equals("push")){
                return CommandType.C_PUSH;
            } else if(tokens[0].equals("pop")){
                return CommandType.C_POP;
            } else if(tokens[0].equals("function")){
                return CommandType.C_FUNCTION;
            } else if(tokens[0].equals("call")){
                return CommandType.C_CALL;
            } else if(tokens[0].equals("return")){
                return CommandType.C_RETURN;
            }
        }
        
        return null;
    }
    
    // Returns the first argument of the current command.
    // In the case of C_ARITHMETIC, the command itself(add,
    // sub, etc.) is returned. Should not be called if the
    // current command is C_RETURN
    public String arg1(){
        String[] tokens = currentCommand.split(" ");
        return tokens[1];
    }
    
    // Returns the second argument of the current command.
    // Should be called only if the current commands is C_PUSH,
    // C_POP, C_FUNCTION, or C_CALL.
    public int arg2(){
        String[] tokens = currentCommand.split(" ");
        return Integer.parseInt(tokens[2]);
    }
    
  
}
