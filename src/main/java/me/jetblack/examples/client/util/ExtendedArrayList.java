package me.jetblack.examples.client.util;

import java.util.*;

public class ExtendedArrayList<T> implements ExtendedList<T> {

    private ArrayList<T> source = new ArrayList<>();

    public void trimToSize() {
        source.trimToSize();
    }

    public void ensureCapacity(int minCapacity) {
        source.ensureCapacity(minCapacity);
    }

    @Override
    public int size() {
        return source.size();
    }

    @Override
    public boolean isEmpty() {
        return source.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return source.contains(o);
    }

    @Override
    public int indexOf(Object o) {
        return source.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return source.lastIndexOf(o);
    }

    @Override
    public Object clone() {
        return source.clone();
    }

    @Override
    public Object[] toArray() {
        return source.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return source.toArray(a);
    }

    @Override
    public T get(int index) {
        return source.get(index);
    }

    public T set(int index, T element) {
        return source.set(index, element);
    }

    public boolean add(T t) {
        return source.add(t);
    }

    public void add(int index, T element) {
        source.add(index, element);
    }

    @Override
    public T remove(int index) {
        return source.remove(index);
    }

    @Override
    public boolean remove(Object o) {
        return source.remove(o);
    }

    @Override
    public void clear() {
        source.clear();
    }

    public boolean addAll(Collection<? extends T> c) {
        return source.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        return source.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return source.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return source.retainAll(c);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return source.listIterator(index);
    }

    @Override
    public ListIterator<T> listIterator() {
        return source.listIterator();
    }

    @Override
    public Iterator<T> iterator() {
        return source.iterator();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return source.subList(fromIndex, toIndex);
    }

    @Override
    public boolean equals(Object o) {
        return source.equals(o);
    }

    @Override
    public int hashCode() {
        return source.hashCode();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return source.containsAll(c);
    }

    @Override
    public String toString() {
        return source.toString();
    }


}
