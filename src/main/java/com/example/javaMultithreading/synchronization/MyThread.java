package com.example.javaMultithreading.synchronization;

public class MyThread extends Thread {

    private Counter counter;

    public MyThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
         //   synchronized (counter){ we can synchronize this block too when there share same counter instance
                counter.increment();


        }
    }
}
