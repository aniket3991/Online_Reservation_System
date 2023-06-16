/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.intern.oasisinfobyte.reservation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Aniket
 */
public class Cancellation {
    public String[] findPNR(String pnr){
        File file = new File("src/main/java/resources/reservation/pnr/" + pnr + ".txt");
        try(Scanner scan = new Scanner(file)){
            return scan.nextLine().split(",");
        }catch(FileNotFoundException ex){
            return null;
        }
    }
    
    public void cancelTicket(String pnr){
        File file = new File("src/main/java/resources/reservation/pnr/" + pnr + ".txt");
        file.delete();
    }
}
