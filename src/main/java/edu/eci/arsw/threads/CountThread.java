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
public class CountThread extends Thread  {
    private  int maximo;
    private  int minimo;
    private  String name;

    public CountThread(int intA, int intB, String name){
        this.maximo = intB;
        this.minimo= intA;
        this.name = name;
    }
    public void run(){
        for (int i = minimo; i<maximo;i++){
            System.out.println("ejecucion del hilo" + name + ": " + i);
        }
    }
}



