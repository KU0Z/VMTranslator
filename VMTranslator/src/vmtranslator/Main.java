/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vmtranslator;

import java.util.Scanner;

/**
 *
 * @author Maynor
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Bienvenido, por favor ingrese la dirección del archivo .vm");
        String entradaTeclado = "";
        Scanner entradaScanner = new Scanner(System.in);
        entradaTeclado = entradaScanner.nextLine();
        VMTranslator vmt = new VMTranslator();
        try{
            vmt.VMTranslate(entradaTeclado);
        }catch(Exception e){
            System.out.println("Ocurrió un error procesando el archivo ");
            return;
        }
        System.out.println("El archivo .asm fue creado exitosamente.");
    }
}
