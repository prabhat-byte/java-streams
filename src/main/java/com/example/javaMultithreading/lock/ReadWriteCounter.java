package com.example.javaMultithreading.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCounter {

    private int count = 0;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Lock write = readWriteLock.writeLock();
    private final Lock read = readWriteLock.readLock();

    public void increment() throws InterruptedException {
        write.lock();
        try {
            count++;
            Thread.sleep(50);
        }finally {
            write.unlock();
        }

    }

    public int getCount() {
        read.lock(); //mutlitple threads can accquire read lock;
        try {
            return count;
        } finally {
            read.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteCounter counter = new ReadWriteCounter();

        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "read " + counter.getCount());
                }

            }
        };

        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "write incremented");
                }
            }
        };

        Thread writeThread = new Thread(writeTask, "Thread1-");
        Thread t1 = new Thread(readTask, "ReadThread1-");
        Thread t2 = new Thread(readTask, "ReadThread2-");

        writeThread.start();
        t1.start();
        t2.start();

        writeThread.join();
        t1.join();
        t2.join();


    }


}

