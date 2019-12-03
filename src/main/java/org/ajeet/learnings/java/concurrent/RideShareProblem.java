package org.ajeet.learnings.java.concurrent;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class RideShareProblem {
    private final Lock LOCK = new ReentrantLock();

    private final Semaphore rideMales;
    private final Semaphore rideFemale;
    private final CyclicBarrier barrier; // All passenger thread should wait untill vehicle is not full

    private int waitingMales = 0;        // Waiting males in queue
    private int waitingFemales = 0;      // Waiting females in queue

    public RideShareProblem(int vehicleCapacity) {
        this.rideMales = new Semaphore(0);
        this.rideFemale = new Semaphore(0);
        this.barrier = new CyclicBarrier(vehicleCapacity);
    }

    void seated() {
        System.out.println(Thread.currentThread().getName() + "  seated");
    }

    void startJourney() {
        System.out.println("Uber Ride on Its way with ride leader " + Thread.currentThread().getName());
    }

    public void enterFemale() throws BrokenBarrierException, InterruptedException {
        boolean shouldStartRide = false;
        LOCK.lock();
        waitingFemales++;

        if (waitingFemales == 4){
            waitingFemales-=4;
            rideFemale.release(3);
            shouldStartRide = true;
        } else if (waitingFemales == 2 && waitingMales >= 2) {
            waitingFemales-=2;
            waitingMales-=2;
            rideFemale.release(1);
            rideMales.release(2);
            shouldStartRide = true;
        } else {
            LOCK.unlock();
            rideFemale.acquire();
        }

        seated();
        //System.out.println("Passengers has been seated, waiting for journey to start.");
        barrier.await();

        if(shouldStartRide) {
            startJourney();
            LOCK.unlock();
        }
    }

    public void enterMale() throws BrokenBarrierException, InterruptedException {
        boolean shoudStartRide = false;

        LOCK.lock();
        waitingMales++;
        if (waitingMales == 4){
            waitingMales-=4;
            rideMales.release(3);
            shoudStartRide = true;
        } else if (waitingMales == 2 && waitingFemales >= 2) {
            waitingMales-=2;
            rideMales.release(1);
            waitingFemales-=2;
            rideFemale.release(2);
            shoudStartRide = true;
        } else {
            LOCK.unlock();
            rideMales.acquire();
        }

        seated();
        barrier.await();

        if(shoudStartRide) {
            startJourney();
            LOCK.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RideShareProblem sharedRide = new RideShareProblem(4);
        Set<Thread> allThreads = new HashSet<Thread>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    sharedRide.enterMale();
                } catch (InterruptedException ie) {
                    System.out.println("We have a problem");

                } catch (BrokenBarrierException bbe) {
                    System.out.println("We have a problem");
                }
            });
            thread.setName("Male_" + (i + 1));
            allThreads.add(thread);
            Thread.sleep(50);
        }

        for (int i = 0; i < 14; i++) {
            Thread thread = new Thread(() -> {
                try {
                    sharedRide.enterFemale();
                } catch (InterruptedException ie) {
                    System.out.println("We have a problem");

                } catch (BrokenBarrierException bbe) {
                    System.out.println("We have a problem");
                }
            });
            thread.setName("Female_" + (i + 1));
            allThreads.add(thread);
            Thread.sleep(20);
        }

        for (Thread t : allThreads) {
            t.start();
        }

        for (Thread t : allThreads) {
            t.join();
        }
    }
}
