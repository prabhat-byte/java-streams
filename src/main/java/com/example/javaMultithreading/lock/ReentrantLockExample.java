package com.example.javaMultithreading.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is example of deadlock is ReentrantLock is not there
 * becuase same main thread is being locked again but it is allowed in ReentrantLock
 */

public class ReentrantLockExample {

    private final Lock lock = new ReentrantLock();

    public void outerMethod(){
        lock.lock();
        try {
            System.out.println("OuterMethod");
            innerMethod();
        }
        finally {
            lock.unlock();
        }
    }
    public void innerMethod(){
        lock.lock();
        try {
            System.out.println("Inner Method");
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample reentrantLockExample = new ReentrantLockExample();
        reentrantLockExample.outerMethod();
    }
}
