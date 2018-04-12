/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmtranslator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Maynor
 */
public class CodeWriter {
    // Opens the output file/stream and gets ready 
    // to write into it.
    FileWriter fw;
    BufferedWriter bw;
    String FilePath;
    String FileName;
    StringBuilder translatedContent = new StringBuilder();
    public CodeWriter(String outputFile) throws IOException{
        this.setFileName(outputFile);
        fw = new FileWriter(FilePath);
        bw = new BufferedWriter(fw);        
    }
    
    //  Informs the code wrter that the translation of a
    // new VM file is started.
    public void setFileName(String fileName){
        File f = new File(fileName);
        FilePath = fileName.substring(0, fileName.lastIndexOf('.'))+".asm";
        FileName = new File(FilePath).getName().substring(0, new File(FilePath).getName().lastIndexOf("."));
    }
    
    // Writes the assembly code that is the translation
    // of the given arithmetic command
    public void writeArithmetic(String command, int cNum) throws IOException{
        command=command.split(" ")[0].split("\n")[0];
        if(command.equals("add")){
            bw.append(Template.Arithmetic("+"));
            translatedContent.append(Template.Arithmetic("+"));
        } else if(command.equals("sub")){
            bw.append(Template.Arithmetic("-"));
            translatedContent.append(Template.Arithmetic("-"));
        } else if(command.equals("and")){
            translatedContent.append(Template.Arithmetic("&"));
        } else if(command.equals("or")){
            translatedContent.append(Template.Arithmetic("|"));
        } else if(command.equals("eq")){
            bw.append(Template.Comparison("EQ", cNum));
            translatedContent.append(Template.Comparison("EQ", cNum));
        } else if(command.equals("gt")){
            bw.append(Template.Comparison("GT", cNum));
            translatedContent.append(Template.Comparison("GT", cNum));
        } else if(command.equals("neg")){
            bw.append(Template.Bool("-"));
            translatedContent.append(Template.Bool("-"));
        } else if(command.equals("not")){
            translatedContent.append(Template.Bool("!"));
        } else if(command.equals("lt")){
            bw.append(Template.Comparison("LT", cNum));
            translatedContent.append(Template.Comparison("LT", cNum));
        }
        //translatedContent.add("\n");
        
    }
    
    // Writes the assembly code what is the translation
    // of the given command, where command is either
    // C_PUSH or C_POP.
    public void WritePushPop(CommandType command, String segment, int index) throws IOException{
        if(command == CommandType.C_PUSH){
            if(segment.equals("constant")){
                bw.append(Template.PushConstant(index));
                translatedContent.append(Template.PushConstant(index));
            }else if(segment.equals("argument")){
                translatedContent.append(Template.Push("ARG", index));
                bw.append(Template.Push("ARG", index));
            }else if(segment.equals("local")){
                translatedContent.append(Template.Push("LCL", index));
                bw.append(Template.Push("LCL", index));
            }else if(segment.equals("this")){
                translatedContent.append(Template.Push("THIS", index));
                bw.append(Template.Push("THIS", index));
            }else if(segment.equals("that")){
                translatedContent.append(Template.Push("THAT", index));
                bw.append(Template.Push("THAT", index));
            }else if(segment.equals("temp")){
                translatedContent.append(Template.Push("5", index+5));
                bw.append(Template.Push("5", index+5));
            }else if(segment.equals("pointer") && index == 1){
                translatedContent.append(Template.PushPointer("THAT", index));
                bw.append(Template.PushPointer("THAT", index));
            }else if(segment.equals("pointer") && index == 0){
                translatedContent.append(Template.PushPointer("THIS", index));
                bw.append(Template.PushPointer("THIS", index));
            }else if(segment.equals("static")){
                translatedContent.append(Template.PushStatic(FileName, index));
                bw.append(Template.PushStatic(FileName, index));
            }
        }else if(command == CommandType.C_POP){
            if(segment.equals("argument")){
                translatedContent.append(Template.Pop("ARG", index));
                bw.append(Template.Pop("ARG", index));
            }else if(segment.equals("local")){
                translatedContent.append(Template.Pop("LCL", index));
                bw.append(Template.Pop("LCL", index));
            }else if(segment.equals("this")){
                translatedContent.append(Template.Pop("THIS", index));
                bw.append(Template.Pop("THIS", index));
            }else if(segment.equals("that")){
                translatedContent.append(Template.Pop("THAT", index));
                bw.append(Template.Pop("THAT", index));
            }else if(segment.equals("temp")){
                translatedContent.append(Template.Pop("5", index+5));
                bw.append(Template.Pop("5", index+5));
            }else if(segment.equals("pointer") && index == 1){
                translatedContent.append(Template.PopThisThat("THAT", index));
                bw.append(Template.PopThisThat("THAT", index));
            }else if(segment.equals("pointer") && index == 0){
                translatedContent.append(Template.PopThisThat("THIS", index));
                bw.append(Template.PopThisThat("THIS", index));
            }else if(segment.equals("static")){
                translatedContent.append(Template.PopStatic(FileName, index));
                bw.append(Template.PopStatic(FileName, index));
            }
        }
    }
    
    // Closes the output file.
    public void Close() throws IOException{
        bw.close();
        fw.close();
    }
}
