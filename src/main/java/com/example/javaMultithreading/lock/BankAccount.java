package com.example.javaMultithreading.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class BankAccount {

    private int balance =100;

    private final Lock lock = new ReentrantLock();

    public void withdraw(int amount){
        System.out.println(Thread.currentThread().getName() +" attempting to process");
        try{
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                if(balance>=amount){
                    try{
                        System.out.println(Thread.currentThread().getName()+ " : Attempting Withdraw");
                        Thread.sleep(3000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName()+ " : Completed Withdraw");
                    }catch (Exception e){
                        Thread.currentThread().interrupt();

                    } finally {
                        lock.unlock();
                    }

                }
                else {
                    System.out.println(Thread.currentThread().getName()+" : Insufficient Balance");
                }
            }
            else {
                System.out.println(Thread.currentThread().getName() + " : Could not acquire the lock , try later");
            }
        } catch (Exception e){
            Thread.currentThread().interrupt();
        }
        if(Thread.currentThread().isInterrupted()){
            System.out.println("it is interrupted" + Thread.currentThread().getName());
        }
    }
}
