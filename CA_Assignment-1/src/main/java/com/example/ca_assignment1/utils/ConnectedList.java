package com.example.ca_assignment1.utils;

import java.util.Iterator;

public class ConnectedList<E> implements Iterable<E>{
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public void add(E element){
        Node<E> n = new Node<>();
        n.setContents(element);
        if(tail != null) {
            tail.next = n;
        }
        n.prev = tail;
        if (size() == 0) {
            head = n;
        }
        tail = n;
        size += 1;
    }

    public void remove(int index){
        int i = 0;
        Node<E> temp = head;
        temp.prev = null;

        if (index == 0 && size == 1){
            head.prev = null;
            size -= 1;
            return;
        }

        if (index == 0){
            head = temp.next;
            head.prev = null;
            size -= 1;
            return;
        }

        if (index == size - 1){
            tail.prev.next = null;
            tail = tail.prev;
            size -= 1;
            return;
        }

        while (i++ < index && temp != null)
            temp = temp.next;

        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        size -= 1;
    }

    public void remove(E e){
        Node<E> temp = head;
        temp.prev = null;

        if (head.contents.equals(e)){
            head = temp.next;
            head.prev = null;
            size -= 1;
            return;
        }

        if (temp.next == null){
            tail.prev.next = null;
            tail = tail.prev;
            size -= 1;
            return;
        }

        while (!temp.contents.equals(e))
            temp = temp.next;

        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        size -= 1;
    }

    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }

    public E get(int index) {
        Node<E> temp = head;
        E contents = null;
        int i = 0;

        while (temp != null){
            if (i == index){
                contents = temp.contents;
                break;
            }
            temp = temp.next;
            i++;
        }
        if (temp != null) {
            return contents;
        } else {
            return null;
        }
    }

    public void set(int index, E element) {
        Node<E> temp = head;
        int i = 0;

        while (temp != null){
            if (i == index){
                temp.contents = element;
                break;
            }
            temp = temp.next;
            i++;
        }
    }

    /*
    public int size(){
        Node<E> temp = new Node<>();
        int i = 0;
        temp.next = head;

        while (temp != null){
            temp = temp.next;
            i++;
        }

        return i;
    }
    */

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(Object o){
        boolean found = false;
        Node<E> temp = new Node<>();
        temp.next = head;

        while (temp.next != null){
            temp = temp.next;
            if (o.equals(temp.getContents())) {
                found = true;
                break;
            }
        }

        return found;
    }

    public String listAll(){
        String list = "";
        Node<E> temp = new Node<>();
        temp.next = head;

        while (temp.next != null){
            temp = temp.next;
            list = list + temp.getContents().toString();
        }
        return list;
    }

    @Override
    public Iterator<E> iterator() {
        return new ConnectedIterator<>(head);
    }

    public static class Node<N>{
        protected Node<N> next = null;
        protected Node<N> prev = null;
        private N contents;

        public Node<N> getNext() {
            return next;
        }

        public void setNext(Node<N> next) {
            this.next = next;
        }

        public Node<N> getPrev() {
            return prev;
        }

        public void setPrev(Node<N> prev) {
            this.prev = prev;
        }

        public N getContents() {
            return contents;
        }

        public void setContents(N contents) {
            this.contents = contents;
        }
    }
}
