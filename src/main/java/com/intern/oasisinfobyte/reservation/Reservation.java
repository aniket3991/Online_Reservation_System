/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.intern.oasisinfobyte.reservation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author 3991a
 */
public class Reservation {
    private String firstName;
    private String lastName;
    private String contact;
    private String email;
    private String trainNo;
    private String trainName;
    private String cls;
    private String date;
    private String from;
    private String to;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getCls() {
        return cls;
    }

    public String getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }
    
    @Override
    public String toString(){
        return this.getFirstName() + "," + this.getLastName() + "," + 
                this.getContact() + "," + this.getEmail() + ","  + 
                this.getTrainNo() + "," + this.getTrainName() + "," + 
                this.getCls() + "," + this.getDate() + "," +
                this.getFrom() + "," + this.getTo();
    }
    
    public String findTrain(String trainNo){
        File file = new File("src/main/java/resources/reservation/TrainDetails.txt");
        try (Scanner scan = new Scanner(file)){
           while(scan.hasNextLine()){
               String line = scan.nextLine();
               if(line.substring(0, 6).equals(trainNo)){
                   return line.substring(7);
               }
           }
        }catch(FileNotFoundException ex){
            System.out.println("Train Details are not available in Database");
        }
        return "No Train Found";
    }
    
    public int generatePNR(){
        File file = new File("src/main/java/resources/reservation/PnrTracker.txt");
        int pnr = 0;
        try(Scanner scan = new Scanner(file)){
            pnr = scan.nextInt();
            FileWriter write = new FileWriter(file);
            write.write(String.valueOf(pnr + 1));
            write.close();
        }catch(FileNotFoundException ex){
            System.out.println("Check pnrTracker Internallhy");
        }catch(IOException ex){
            System.out.println("Problem in writing into the file");
        }
        return pnr;
    }
    
    public int bookTicket(){
        int pnr = generatePNR();
        File file = new File("src/main/java/resources/reservation/pnr/" + pnr + ".txt");
        try{
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(this.toString());
            writer.close();
            
        }catch(IOException ex){
            System.out.println("Issue in file creation");
        }
        return pnr;
    }
    public static void main(String[] args) {
        Reservation r = new Reservation();
        r.bookTicket();
    }
}
