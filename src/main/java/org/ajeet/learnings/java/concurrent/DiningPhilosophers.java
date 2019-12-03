package org.ajeet.learnings.java.concurrent;

import java.util.Random;
import java.util.concurrent.Semaphore;

public final class DiningPhilosophers {
    private static Random random = new Random(System.currentTimeMillis());
    private static final Semaphore[] forks = new Semaphore[5];
    //private static final Semaphore allowedForks = new Semaphore(4);
    static {
        for(int i = 0; i< 5; i++) {
            forks[i] = new Semaphore(1);
        }
    }

    public static void startPhiliosopher(int id) throws InterruptedException {
        while (true) {
            think(id);
            eat(id);
        }
    }

    /**
     * This method will hold whole synchronization logic
     * Forks needed to eat -  left and right
     *      forks[id]
     *      forks[(id+4) % 5]
     *
     *  To avoid deadlock
     *          - Either we can restrict any one philosopher by allowing only 4 philosopher to
     *             Semaphore allowedForks = new Semaphore(4);
     *          - Or we allocate forks in clockwise oder to all philisophers except one philoshpher that will grab it in reverse order
     *            if(id == 3) {
     *                forks[(id + 1) % 5].acquire();
     *                forks[id].acquire();
     *            } else {
     *                 forks[id].acquire();
     *                 forks[(id + 1) % 5].acquire();
     *            }
     */
    private static void eat(int id) throws InterruptedException {
        //allowedForks.acquire();
        System.out.println(Thread.currentThread().getName() + " is eating ...");

        if(id == 3) {
            forks[(id + 1) % 5].acquire();
            forks[id].acquire();
        } else  {
            forks[id].acquire();
            forks[(id + 1) % 5].acquire();
        }

        Thread.sleep(100);

        forks[id].release();
        forks[(id + 1) % 5].release();
        //allowedForks.release();
    }

    private static void think(int id) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is thinking ...");
        Thread.sleep(random.nextInt(50));
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] philosophers = new Thread[5];

        for(int i =0; i<5; i++) {
            int x = i;
            philosophers[x] = new Thread(() -> {
                try {
                    startPhiliosopher(x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            philosophers[x].setName("Philosopher_" + x);
        }

        for(int i =0; i<5; i++) {
            philosophers[i].start();
        }

        for(int i =0; i<5; i++) {
            philosophers[i].join();
        }
    }
}
