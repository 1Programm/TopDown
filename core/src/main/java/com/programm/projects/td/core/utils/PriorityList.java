package com.programm.projects.td.core.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PriorityList<T> implements Iterable<T> {

    private final List<PriorityNode<T>> list = new ArrayList<>();

    public void add(T t, int priority) {
        int i = 0;
        for (PriorityNode<T> node : list) {
            if (node.priority <= priority) break;
            ++i;
        }
        list.add(i, new PriorityNode<>(t, priority));
    }

    public boolean remove(T obj) {
        for (PriorityNode<T> node : list) {
            if (node.obj == obj) {
                return list.remove(node);
            }
        }
        return false;
    }

    public T remove(int i) {
        return list.remove(i).obj;
    }

    public T get(int i) {
        return list.get(i).obj;
    }

    public int size() {
        return list.size();
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<PriorityNode<T>> iter = list.iterator();
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public T next() {
                return iter.next().obj;
            }
        };
    }
}
