package com.objects.queue;

import com.interfaces.Aggregate;
import com.interfaces.IIterator;
import com.objects.TemplateIterator;

import java.util.*;

/**
 * Created by Sergey on 27.01.2016.
 */
public class MyQueue<E> implements Queue, Aggregate {
    List<E> list;

    public MyQueue() {
        list = new ArrayList<E>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public IIterator getIterator() {
        return new TemplateIterator<E>(list);
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public E[] toArray(Object[] a) {
        return (E[]) list.toArray();
    }

    @Override
    public boolean add(Object o) {
        return list.add((E) o);
    }

    @Override
    public boolean offer(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean addAll(Collection c) {
        return list.addAll(c);
    }

    @Override
    public boolean retainAll(Collection c) {
        return list.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection c) {
        return retainAll(c);
    }

    @Override
    public boolean containsAll(Collection c) {
        return containsAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Object remove() {
        return list.remove(list.size() - 1);
    }

    @Override
    public E poll() {
        E e = list.get(0);
        list.remove(0);
        return e;
    }

    @Override
    public Object element() {
        return peek();
    }

    @Override
    public Object peek() {
        return list.get(0);
    }
}
