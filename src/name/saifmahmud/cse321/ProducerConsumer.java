package name.saifmahmud.cse321;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

abstract class ProducerConsumer extends Thread
{
    private final static int BUFFER_SIZE = 6;

    static Semaphore lock = new Semaphore(1);
    static Semaphore empty = new Semaphore(BUFFER_SIZE);
    static Semaphore full = new Semaphore(0);

    static ConcurrentLinkedQueue<Item> items = new ConcurrentLinkedQueue<>();

    abstract public void run();
}
