package com.example.ca_assignment1.utils;

import com.example.ca_assignment1.utils.ConnectedList;

import java.util.Iterator;

public class ConnectedIterator<E> implements Iterator<E> {
    private ConnectedList.Node<E> pos;

    public ConnectedIterator(ConnectedList.Node<E> node){pos = node;}

    @Override
    public boolean hasNext(){
        return pos != null;
    }

    @Override
    public E next(){
        ConnectedList.Node<E> temp = pos;
        pos = pos.next;
        return temp.getContents();
    }
}
