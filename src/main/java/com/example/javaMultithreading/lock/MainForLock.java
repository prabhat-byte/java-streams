package com.example.javaMultithreading.lock;

public class MainForLock {

    public static void main(String[] args) {
        BankAccount sbi = new BankAccount();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                sbi.withdraw(50);
            }
        };
        Thread t1 = new Thread(task, "Thread T1-");
        Thread t2 = new Thread(task, "Thread T2-");
        t1.start();
        t2.start();
    }


}
