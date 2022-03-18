//Jiawei Lu
//20007605

import java.util.ArrayList;

public class IDLList<E> {

    private static class Node<E> {

        private E data;
        private Node<E> next;
        private Node<E> prev;

        // a constructor that creates a node holding elem
        private Node(E elem) {
            this.data = elem;
        }

        // a constructor that creates a node holding elem, with next as next and prev as prev
        public Node(E elem, Node<E> prev, Node<E> next) {
            this.data = elem;
            this.prev = prev;
            this.next = next;
        }
    }

    //four data fields
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;

    //createsanemptydouble-linkedlist
    public IDLList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.indices = new ArrayList<>();
    }

    public static void main(String[] args){
        
    }
    
    //adds elem at position index
    public boolean add(int index, E elem) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + Integer.toString(index)+ ", Size: " +indices.size());
        }

        if (index == 0) {
            add(elem);
            return true;
        } 

            Node<E> node = indices.get(index);

            Node<E> dummyNode = new Node<>(elem);

            node.prev.next = dummyNode;
            node.prev = dummyNode;
            dummyNode.next = node;
            dummyNode.prev = node.prev;
            size++;

            indices.add(index, dummyNode);
            return true;

    }

    // adds elem at the head
    public boolean add(E elem) {

        if (head == null) {
            head = new Node<>(elem);
            tail = head;
            size++;
            indices.add(head);
            return true;
        } 

        Node<E> Dummy = new Node<>(elem);

        Dummy.next = head;
        head.prev = Dummy;
        head = Dummy;
        size++;

        indices.add(0, Dummy);
        return true;
    }
    //adds elem as the new last element of the list
    public boolean append(E elem) {

        if (head == null) {
            add(elem);
            return true;
        }


        Node<E> lastDummy = new Node<>(elem);

        lastDummy.prev = tail;
        tail.next = lastDummy;
        tail = lastDummy;
        size++;

        indices.add(lastDummy);
        return true;
    }

    //return the object at position index from the head

    public E get(int index) {
        return indices.get(index).data;

    }
    //return the object at the head

    public E getHead () {

        return head.data;
    }
    //return the object at the tail

    public E getLast() {

        return tail.data;
    }

    //return the list size

    public int size() {
        return size;
    }


    //remove the element at the head

    public E remove() {

        if (head == null) 
        {
            return null;
        }

        Node<E> removeDummy = head;
        
        head = head.next;
        head.prev = null;
        indices.remove(0);
        size--;

        return removeDummy.data;

    }
    //removes and returns the element at the tail

    public E removeLast() {

        if (tail == null){
            return null;
        }

        Node<E> removeLastDummy = tail;
        tail = tail.prev;
        tail.next = null;
        indices.remove(size - 1);
        size--;

        return removeLastDummy.data;
    }


    //remove and return the element at the index index

    public E removeAt(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + Integer.toString(index)+ ", Size: " +indices.size());
        }

        if (index == 0) {
            return remove();
        }

        if (index == size - 1) {
            return removeLast();
        }

        Node<E> removeNode = indices.get(index);
        removeNode.next.prev = removeNode.prev;
        removeNode.prev.next = removeNode.next;
        size--;

        indices.remove(index);

        return removeNode.data;

    }


    //removes the first occurrence of elem in the list

    public boolean remove(E elem) {
        for (int index = 0; index < size; index ++) {

            if (indices.get(index).data == elem) {
                if (index == 0) {
                    remove();
                    return true;
                }
        
                if (index == size - 1) {
                    removeLast();
                    return true;
                }
        
                Node<E> removeNode = indices.get(index);
                removeNode.next.prev = removeNode.prev;
                removeNode.prev.next = removeNode.next;
                size--;
        
                indices.remove(index);
                return true;
            }
        }
        return false;
    }
    //presents a string representation of the list
    public String toString() {
        Node cur = head;
        String str = "";
        if (cur.next == null){
            str += cur.data.toString() + " --> null";
            cur = cur.next;
        }
        else {
            while (cur != null) {
            if (str == "") {
                str += cur.data.toString() + " --> ";
                cur = cur.next;
            } else if (cur.next == null){
                str += cur.data.toString() + " --> null";
                cur = cur.next;
            }
            else {
                str += cur.data.toString() + " <==> ";
                cur = cur.next;
            }
        }
    }
        return str;
    }
}
