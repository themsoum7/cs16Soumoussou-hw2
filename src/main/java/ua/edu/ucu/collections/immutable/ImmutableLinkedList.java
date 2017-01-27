package ua.edu.ucu.collections.immutable;

public class ImmutableLinkedList implements ImmutableList {
    private Node head;
    private int lst_len = 0;
    private class Node {
        private Node nextElement = null;
        private Object data;

        Node(Object n) {
            this.data = n;
        }

        Node addNext(Object d) {
            this.nextElement = new Node(d);
            return this.nextElement;
        }

        boolean hasNext() {
            return this.nextElement != null;
        }

        Object getData() {
            return data;
        }

        Node getNext() {
            return this.nextElement;
        }

    }

    public ImmutableLinkedList() {
    }

    private ImmutableLinkedList(Node listHead, Object object, int listLen) {
        super();

        this.lst_len = listLen + 1;

        if (listHead != null) {
            this.head = new Node(listHead.getData());
            Node tail = this.head;
            tail = new_lst(tail, listHead);
            tail.addNext(object);
        } else {
            this.head = new Node(object);
        }
    }

    private ImmutableLinkedList(Node listHead, int index, Object object, int listLen) {
        super();

        this.lst_len = listLen + 1;

        Node tail;
        if (index == 0) {
            this.head = new Node(object);
            tail = this.head;
        } else {
            this.head = new Node(listHead.getData());
            tail = this.head;
            listHead = listHead.getNext();
        }
        for (int i = 1; i < this.lst_len; i++) {
            if (i == index) {
                tail = tail.addNext(object);
            } else {
                tail = tail.addNext(listHead.getData());
                listHead = listHead.getNext();
            }
        }
    }

    private ImmutableLinkedList(Node listHead, Object[] objects, int listLen) {
        super();

        this.lst_len = listLen + objects.length;

        Node tail;

        if (listHead != null) {
            this.head = new Node(listHead.getData());
            tail = this.head;
            new_lst(tail, listHead);
            tail.addNext(objects[0]);
        } else {
            this.head = new Node(objects[0]);
            tail = this.head;
        }
        for (int i = 1; i < objects.length; i++) {
            tail = tail.addNext(objects[i]);
        }
    }

    private ImmutableLinkedList(Node listHead, int index, Object[] objects, int listLen) {
        super();

        this.lst_len = listLen + objects.length;

        Node tail;
        if (index == 0) {
            this.head = new Node(objects[0]);
            tail = this.head;
            for (int i = 1; i < objects.length; i++) {
                tail = tail.addNext(objects[i]);
            }
            tail = tail.addNext(listHead.getData());
        } else {
            this.head = new Node(listHead.getData());
            tail = this.head;
            for (int i = 1; i < index; i++) {
                listHead = listHead.getNext();
                tail = tail.addNext(listHead.getData());
            }
            for (int i = 0; i < objects.length; i++) {
                tail = tail.addNext(objects[i]);
            }
        }
        new_lst(tail, listHead);
    }

    private ImmutableLinkedList(Node listHead, int index, int listLen) {
        super();

        this.lst_len = listLen - 1;

        Node tail;
        if (index != 0) {
            this.head = new Node(listHead.getData());
            tail = this.head;
            for (int i = 1; i < lst_len; i++) {
                listHead = listHead.getNext();
                if (i == index) {
                    listHead = listHead.getNext();
                }
                tail = tail.addNext(listHead.getData());

            }
        } else {
            if(lst_len != 0){
                this.head = new Node(listHead.getNext().getData());
                tail = this.head;
                listHead = listHead.getNext();
                new_lst(tail, listHead);}
        }

    }

    private Node new_lst(Node tail, Node listHead) {
        while (listHead.hasNext()) {
            listHead = listHead.getNext();
            tail = tail.addNext(listHead.getData());
        }
        return tail;
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return new ImmutableLinkedList(head, e, lst_len);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        try {
            if (index > lst_len) {
                throw new Exception("Smth went wrong XD...");
            }
        } catch (Exception ex) {
            return null;
        }
        return new ImmutableLinkedList(head, index, e, lst_len);

    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return new ImmutableLinkedList(head, c, lst_len);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        try {
            if (index > lst_len) {
                throw new Exception("Smth went wrong XD...");
            }
        } catch (Exception ex) {
            return null;
        }
        return new ImmutableLinkedList(head, index, c, lst_len);
    }

    @Override
    public Object get(int index) {
        try {
            if (index >= lst_len) {
                throw new Exception("Smth went wrong XD...");
            }
        } catch (Exception ex) {
            return null;
        }
        Node tail = this.head;
        for (int i = 0; i < index; i++) {
            tail = tail.getNext();
        }
        return tail.getData();
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        try {
            if (index >= lst_len) {
                throw new Exception("Smth went wrong XD...");
            }
        } catch (Exception ex) {
            return null;
        }
        return new ImmutableLinkedList(head, index, lst_len);
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return this.lst_len == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] c = new Object[this.lst_len];
        Node tail = this.head;
        for (int i = 0; i < this.lst_len; i++) {
            c[i] = tail.getData();
            tail = tail.getNext();
        }
        return c;
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        try {
            if (index >= lst_len) {
                throw new Exception("Smth went wrong XD...");
            }
        } catch (Exception ex) {
            return null;
        }
        return remove(index).add(index, e);
    }

    @Override
    public int indexOf(Object e) {
        Node tail = this.head;
        for (int i = 0; i < this.lst_len; i++) {
            if (tail.getData().equals(e)) {
                return i;
            }
            tail = tail.getNext();
        }
        return -1;
    }

    @Override
    public int size() {
        return lst_len;
    }

    @Override
    public String toString() {
        String result = " ";
        Node tail = this.head;
        for (int i = 0; i < this.lst_len - 1; i++) {
            result += tail.getData().toString() + ", ";
            tail = tail.getNext();
        }
        result += tail.getData().toString();
        return result;
    }

    public ImmutableLinkedList addFirst(Object e) {
        return add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return add(e);
    }

    public Object getFirst() {
        return head.getData();
    }

    public Object getLast() {
        return get(this.lst_len - 1);
    }

    public ImmutableLinkedList removeFirst() {
        return remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return remove(this.lst_len - 1);
    }
}
