/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThreadsMain {
    
    public static void main(String a[]){
         CountThread hilo1 = new CountThread(0,99, "A");
         CountThread hilo2 = new CountThread(99,199,"B");
         CountThread hilo3 = new CountThread(200,299,"C");

         hilo1.start();
         hilo2.start();
         hilo3.start();
    }
    
}
