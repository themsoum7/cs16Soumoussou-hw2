package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList immutableLinkedList;

    public Queue() {
        immutableLinkedList = new ImmutableLinkedList();
    }

    public Object peek() {
        return immutableLinkedList.getFirst();
    }

    public Object dequeue() {
        Object obj = immutableLinkedList.getFirst();
        immutableLinkedList = immutableLinkedList.removeFirst();
        return obj;
    }

    public void enqueue(Object e) {
        immutableLinkedList = immutableLinkedList.add(e);
    }
    public int getSize(){
        return immutableLinkedList.size();
    }
}
