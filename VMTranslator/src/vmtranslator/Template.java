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
    
    public static String Init(){
        return "@256\n"
                + "D=A\n"
                + "@SP\n"
                + "M=D\n"
                + CallFunction("Sys.init", 0, 0);
    }
    
    // Pop for THIS and THAT (Pointer 
    public static String Pop(String segment, int constant){
        return  "@"+segment+"\n"
                + ((segment.equals("R5"))? "D=A\n":"D=M\n")
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
        return "@"+fileName+"."+constant+"\n"
                + "D=M\n"
                + "@SP\n"
                + "A=M\n"
                + "M=D\n"
                + "@SP\n"
                + "M=M+1\n";
    }
    // Pop for static
    public static String PopStatic(String fileName, int constant){
        return "@"+fileName+"."+constant+"\n"
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
    
    // Label
    public static String Label(String label){
        return "("+label+")\n";
    }
    
    // Goto
    public static String GoTo(String label){
        return "@"+label+"\n"
                + "0;JMP\n";
    }
    
    // If
    public static String If(String label){
        return "@SP\n"
                + "AM=M-1\n"
                + "D=M\n"
                + "@"+label+"\n"
                + "D;JGT\n"
                + "D;JLT\n";
    }
    
    // Push address for the function caller
    public static String ReturnAddress(int num){
        return "@RET_"+num+"\n"
                + "D=A\n"
                + "@SP\n"
                + "A=M\n"
                + "M=D\n"
                + "@SP\n"
                + "M=M+1\n";
    }
    
    
    // CallFunction
    public static String CallFunction(String function, int nArgs, 
            int commandNumber){
        return ReturnAddress(commandNumber)+    // push return-address
                PushPointer("LCL", 0)+          // push LCL
                PushPointer("ARG", 0)+          // push ARG
                PushPointer("THIS", 0)+         // push THIS
                PushPointer("THAT", 0)+         // push THAT
                "@"+(nArgs+5)+"\n"+             // Start repositioning ARG
                "D=A\n"                            // ARG = SP-n-5
                + "@SP\n"
                + "D=M-D\n"
                + "@ARG\n"
                + "M=D\n"
                + "@SP\n"                       // Start repositioning LCL
                + "D=M\n"                           // LCL = SP
                + "@LCL\n"
                + "M=D\n"
                + "@"+function+"\n"             // Transfer control
                + "0;JMP\n"                         // goto f
                + Label("RET_"+commandNumber);  // Declare a label for the return-address
                                                   // (return-address)
    }
    
    // Function
    public static String Function(String functionName, int numLocals){
       String output = Label(functionName);     // Declare a label for the function entry
       for(int i = 0; i < numLocals; i++){      // Initialize each local variables with 0
           output += PushConstant(0);
       }
       return output;               
    }
    
    // return
    public static String Return(){
        return "@LCL\n"                         // FRAME is a temporary variable
                + "D=M\n"
                + "@R10\n"                        // frame
                + "M=D\n"
                + "@5\n"                        // Put the return-address in a temp var
                + "A=D-A\n"
                + "D=M\n"
                + "@R9\n"
                + "M=D\n"
                + Pop("ARG", 0)                 // Reposition the return value for the caller // jañlksjefñalksdjfñalsdkjfñalskdjfñasd
                + "@ARG\n"                      // Restores SP of the caller
                + "D=M\n"
                + "@SP\n"
                + "M=D+1\n"
                + segFrame("THAT", 1)     // Restore THAT of the caller
                + segFrame("THIS", 2)     // Restore THIS of the caller
                + segFrame("ARG", 3)       // Restore ARG of the caller
                + segFrame("LCL", 4)       // Restore LCL of the caller
                + "@R9\n"                  // Goto return-address (in the caller's code)
                + "A=M\n"
                + "0;JMP\n";        
    }
    
    
    // SEG = *(FRAME - k
    public static String segFrame(String seg, int k){
        return "@R10\n"
                + "D=M\n"
                + "@"+k+"\n"
                + "A=D-A\n"
                + "D=M\n"
                + "@"+seg.toUpperCase()+"\n"
                + "M=D\n";
    }
}
