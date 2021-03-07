package org.example;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class ThreadExamplet1t2t3 {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger("Thread Example");
        RunnableTask task = new RunnableTask();
        Thread t1 = new Thread(task, "t1");
        Thread t2 = new Thread(task, "t2");
        Thread t3 = new Thread(task, "t3");

        t1.start();
        logger.info("t1 started");
        t2.start();
        logger.info("t2 started");

        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t3.start();
        logger.info("t3 started after t1 and t2 start");

        logger.info("main end");

    }
}

class RunnableTask implements Runnable {

    Logger logger = Logger.getLogger("RunnableTask");

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("t1")){
            m1();
        }else if (Thread.currentThread().getName().equals("t2")){
            m2();
        }else if (Thread.currentThread().getName().equals("t3")) {
            m3();
        }
    }

    public void m1(){
        Thread t = Thread.currentThread();
        logger.info(t.getName() + " waiting for lock on RunnableTask obj");
        synchronized (this){
            logger.info( t.getName() + " acquired lock on RunnableTask obj");
            try {
                logger.info(t.getName() + " executing on RunnableTask obj");
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info(t.getName() + " released lock on RunnableTask obj");
    }

    public void m2(){
        Thread t = Thread.currentThread();
        logger.info(t.getName() + " waiting for lock on RunnableTask obj");
        synchronized (this){
            logger.info(t.getName() + " acquired lock on RunnableTask obj");
            try {
                logger.info(t.getName() + " executing on RunnableTask obj");
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info(t.getName() + " released lock on RunnableTask obj");
    }

    public void m3(){
        Thread t = Thread.currentThread();
        try {
            logger.info(t.getName() + " executing on RunnableTask obj");
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(t.getName() + " completed on RunnableTask obj");
    }
}


/*
Feb 27, 2021 1:54:37 PM org.example.ThreadExample1 main
INFO: t1 started
Feb 27, 2021 1:54:37 PM org.example.RunnableTask m1
INFO: t1 waiting for lock on RunnableTask obj
Feb 27, 2021 1:54:37 PM org.example.RunnableTask m1
INFO: t1 acquired lock on RunnableTask obj
Feb 27, 2021 1:54:37 PM org.example.RunnableTask m2
INFO: t2 waiting for lock on RunnableTask obj
Feb 27, 2021 1:54:37 PM org.example.ThreadExample1 main
INFO: t2 started
Feb 27, 2021 1:54:37 PM org.example.RunnableTask m1
INFO: t1 executing on RunnableTask obj
Feb 27, 2021 1:54:40 PM org.example.ThreadExample1 main

INFO: t3 started after t1 and t2 start
Feb 27, 2021 1:54:40 PM org.example.RunnableTask m3
INFO: t3 executing on RunnableTask obj
Feb 27, 2021 1:54:40 PM org.example.ThreadExample1 main
INFO: main end
Feb 27, 2021 1:54:42 PM org.example.RunnableTask m3
INFO: t3 completed on RunnableTask obj

Feb 27, 2021 1:54:47 PM org.example.RunnableTask m1
INFO: t1 released lock on RunnableTask obj
Feb 27, 2021 1:54:47 PM org.example.RunnableTask m2
INFO: t2 acquired lock on RunnableTask obj
Feb 27, 2021 1:54:47 PM org.example.RunnableTask m2
INFO: t2 executing on RunnableTask obj
Feb 27, 2021 1:54:57 PM org.example.RunnableTask m2
INFO: t2 released lock on RunnableTask obj
 */
