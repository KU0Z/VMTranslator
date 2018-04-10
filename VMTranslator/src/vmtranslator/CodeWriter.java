/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmtranslator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Maynor
 */
public class CodeWriter {
    // Opens the output file/stream and gets ready 
    // to write into it.
    FileWriter fw;
    BufferedWriter bw;
    String FileName;
    public CodeWriter(String outputFile) throws IOException{
        fw = new FileWriter(outputFile);
        bw = new BufferedWriter(fw);
        FileName  = outputFile;
    }
    
    //  Informs the code wrter that the translation of a
    // new VM file is started.
    public void setFileName(String fileName){
        
    }
    
    // Writes the assembly code that is the translation
    // of the given arithmetic command
    public void writeArithmetic(String command) throws IOException{
        if(command.equals("add")){
            bw.append(Template.Arithmetic("+"));
        } else if(command.equals("sub")){
            bw.append(Template.Arithmetic("-"));
        } else if(command.equals("and")){
            bw.append(Template.Arithmetic("&"));
        } else if(command.equals("or")){
            bw.append(Template.Arithmetic("|"));
        } else if(command.equals("eq")){
            bw.append(Template.Comparison("EQ"));
        } else if(command.equals("gt")){
            bw.append(Template.Comparison("GT"));
        } else if(command.equals("lt")){
            bw.append(Template.Comparison("LT"));
        } else if(command.equals("neg")){
            bw.append(Template.Bool("-"));
        } else if(command.equals("not")){
            bw.append(Template.Bool("!"));
        }
    }
    
    // Writes the assembly code what is the translation
    // of the given command, where command is either
    // C_PUSH or C_POP.
    public void WritePushPop(CommandType command, String segment, int index) throws IOException{
        if(command == CommandType.C_PUSH){
            if(segment.equals("constant")){
                bw.append(Template.PushConstant(index));
            }else if(segment.equals("argument")){
                bw.append(Template.Push("ARG", index));
            }else if(segment.equals("local")){
                bw.append(Template.Push("LCL", index));
            }else if(segment.equals("this")){
                bw.append(Template.Push("THIS", index));
            }else if(segment.equals("that")){
                bw.append(Template.Push("THAT", index));
            }else if(segment.equals("temp")){
                bw.append(Template.Push("5", index));
            }            
        }else if(command == CommandType.C_POP){
            if(segment.equals("argument")){
                bw.append(Template.Pop("ARG", index));
            }else if(segment.equals("local")){
                bw.append(Template.Pop("LCL", index));
            }else if(segment.equals("this")){
                bw.append(Template.Pop("THIS", index));
            }else if(segment.equals("that")){
                bw.append(Template.Pop("THAT", index));
            }else if(segment.equals("temp")){
                bw.append(Template.Pop("5", index));
            } 
        }
    }
    
    // Closes the output file.
    public void Close() throws IOException{
        bw.close();
        fw.close();
    }
}
