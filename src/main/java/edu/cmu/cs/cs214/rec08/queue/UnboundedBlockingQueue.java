package edu.cmu.cs.cs214.rec08.queue;

import java.util.ArrayDeque;
import java.util.Deque;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Modify this class to be thread-safe and be an UnboundedBlockingQueue.
 */
@ThreadSafe
public class UnboundedBlockingQueue<E> implements SimpleQueue<E> {
    @GuardedBy("this")
    private Deque<E> queue = new ArrayDeque<>();

    public UnboundedBlockingQueue() { }

    public boolean isEmpty() { return queue.isEmpty(); }

    public int size() { return queue.size(); }

    public E peek() { return queue.peek(); }

    public void enqueue(E element) { 
        if (element == null) {
            throw new NullPointerException("Cannot enqueue null element");
        }
        queue.addLast(element);
        // Wake up one waiting consumer thread if any
        notifyAll();
    }


    /**
     * TODO:  Change this method to block (waiting for an enqueue) rather
     * than throw an exception.  Completing this task may require
     * modifying other methods.
     * @throws InterruptedException 
     */
    public E dequeue() throws InterruptedException { 
        while (queue.isEmpty()){
            wait();

        }
        return queue.remove(); 
    }

    public String toString() { return queue.toString(); }
}
