/* 
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor. 
 */ 
package vmtranslator; 
 
import java.io.File;
import java.util.Scanner; 
 
/** 
 * 
 * @author Maynor 
 */ 
public class Main { 
    public static void main(String[] args){ 
        System.out.println("Bienvenido, por favor ingrese la dirección del archivo o carpeta .vm"); 
        String entradaTeclado = ""; 
        String ruta = "";
        Scanner entradaScanner = new Scanner(System.in); 
        ruta = entradaScanner.nextLine();
            VMTranslator vmt = new VMTranslator();
        System.out.println("¿Desea añadir el código Bootstrap? s/n");
        entradaTeclado = entradaScanner.nextLine();
        Boolean addBootstrap;
        if(entradaTeclado.toLowerCase().equals("s")){
            addBootstrap = true;
        }else{
            addBootstrap = false;
        }
        try{ 
            vmt.VMTranslate(ruta, addBootstrap); 
        }catch(Exception e){ 
            System.out.println("Ocurrió un error procesando el archivo\n"+e.getMessage()); 
            return; 
        } 
        System.out.println("El archivo .asm fue creado exitosamente."); 
    } 
}