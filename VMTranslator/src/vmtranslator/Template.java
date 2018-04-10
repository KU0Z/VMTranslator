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
    public static String Comparison(String op){
        return "@SP\n" +
                "M=M-1\n" +
                "A=M\n" +
                "D=M\n" +
                "@SP\n" +
                "M=M-1\n" +
                "A=M\n" +
                "D=M-D\n" +
                "@TRUE\n" +
                "D;J"+op+"\n" +
                "D=0\n" +
                "@END\n" +
                "0;JMP\n" +
                "(TRUE)\n" +
                "D=-1\n" +
                "(END)\n" +
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
}
