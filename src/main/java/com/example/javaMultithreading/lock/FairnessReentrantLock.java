package com.example.javaMultithreading.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairnessReentrantLock {

    private final Lock lock = new ReentrantLock(true); //this will give fairness and thread will be in order

    public void accessResource() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " Acquiring the lock");
            Thread.sleep(1000);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " Releasing lock");
        }
    }

    public static void main(String[] args) {
        FairnessReentrantLock fair = new FairnessReentrantLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                fair.accessResource();
            }
        };

        Thread t1 = new Thread(runnable, "Thread 1-");
        Thread t2 = new Thread(runnable, "Thread 2-");
        Thread t3 = new Thread(runnable, "Thread 3-");

        t1.start();
        t2.start();
        t3.start();

    }
}
