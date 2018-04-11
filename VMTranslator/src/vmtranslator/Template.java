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
public class Template {
    // Arithmetic Template
    public static String Arithmetic(String op){
        return "@SP\n" +
                "M=M-1\n" +
                "A=M\n" +
                "D=M\n" +
                "A=A-1\n" +
                "M=M"+op+"D\n";
    }
    
    // Comparison Template
    public static String Comparison(String op, int cNum){
        return "@SP\n" +
                "M=M-1\n" +
                "A=M\n" +
                "D=M\n" +
                "@SP\n" +
                "M=M-1\n" +
                "A=M\n" +
                "D=M-D\n" +
                "@TRUE"+cNum+"\n" +
                "D;J"+op+"\n" +
                "D=0\n" +
                "@END"+cNum+"\n" +
                "0;JMP\n" +
                "(TRUE"+cNum+")\n" +
                "D=-1\n" +
                "(END"+cNum+")\n" +
                "@SP\n" +
                "A=M\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M+1\n";
    }
    
    // Boolean template
    public static String Bool(String op){
        return "@SP\n" +
                "A=M-1\n" +
                "M="+op+"M\n";
    }
    
    // PushConstant template
    public static String PushConstant(int constant){
        return "@"+ constant + "\n" +
                "D=A\n" +
                "@SP\n" +
                "A=M\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M+1\n";
    }
    
    // Push for any memory segment but constant nor static
    public static String Push(String segment, int constant){
        return "@"+segment+"\n" +
                "D=M\n" +
                "@"+constant+"\n" +
                "A=D+A\n" +
                "D=M\n" +
                "@SP\n" +
                "A=M\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M+1\n";
    }
    
    // Pop for any memory segment
    public static String PopThisThat(String segment, int constant){
        return "@"+segment+"\n" +
                "D=A\n" +
                "@R13\n" +
                "M=D\n" +
                "@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +
                "@R13\n" +
                "A=M\n" +
                "M=D\n";
    }
    
    // Pop for THIS and THAT (Pointer)
    public static String Pop(String segment, int constant){
        return  "@"+segment+"\n"
                + "D=M\n"
                + "@"+constant+"\n"
                + "D=D+A\n"
                + "@R13\n"
                + "M=D\n"
                + "@SP\n"
                + "AM=M-1\n"
                + "D=M\n"
                + "@R13\n"
                + "A=M\n"
                + "M=D\n";
    }
    
    // Push for THIS and THAT (Pointer)
    public static String PushPointer(String segment, int constant){
        return "@"+segment+"\n" +
                "D=M\n" +
                "@SP\n" +
                "A=M\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M+1\n";
    }
    
    // Push for static
    public static String PushStatic(String fileName, int constant){
        return "@"+fileName+constant+"\n"
                + "D=M\n"
                + "@SP\n"
                + "A=M\n"
                + "M=D\n"
                + "@SP\n"
                + "M=M+1\n";
    }
    // Pop for static
    public static String PopStatic(String fileName, int constant){
        return "@"+fileName+constant+"\n"
                + "D=A\n"
                + "@R13\n"
                + "M=D\n"
                + "@SP\n"
                + "AM=M-1\n"
                + "D=M\n"
                + "@R13\n"
                + "A=M\n"
                + "M=D\n";
    }
}
