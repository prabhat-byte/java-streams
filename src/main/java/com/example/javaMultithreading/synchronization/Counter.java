package com.example.javaMultithreading.synchronization;

public class Counter {

    private  int count =0;

    public synchronized void increment(){   //now it is synchronized when one operate other will wait
        count++;
    }

    public int getCount(){
        return count;
    }

}
