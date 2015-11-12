package dk.kalhauge.util;

import java.util.Iterator;

public class Ring<T> implements Iterable<T> {
    private T data;
    private Ring<T> previous = this;
    private Ring<T> next = this;

    public Ring(T data) {
        this.data = data;
        }
    
    public Ring(T data, Ring<T> other) {
        this.data = data;
        combine(other);
        }
    
    public static <T> Ring<T> create(T end, T... items) {
        Ring<T> ring = new Ring(end);
        for (T item : items) new Ring(item, ring);
        return ring;
        }
    
    public final void combine(Ring<T> other) {
        Ring<T> otherPrevious = other.previous;
        Ring<T> thisPrevious = this.previous;
        otherPrevious.next = this;
        this.previous = otherPrevious;
        thisPrevious.next = other;
        other.previous = thisPrevious;
        }

    public void add(T data) {
      new Ring(data, this);
      }
    
    @Override
    public String toString() {
        String text = String.valueOf(data);
        for (T item : this) text += "-"+String.valueOf(item);
        return text;
        }

    public T detach() {
        previous.next = next;
        next.previous = previous;
        return data;
        }
    
    public T getData() { return data; }

    public void setData(T value) { data = value; }
    
    public Ring<T> getNext() {
        return next;
        }

    public Ring<T> getPrevious() {
        return previous;
        }

    /**
     * Iterates through all items in the ring, exept the item referenced.
     * 
     * @return an iterator of items in the ring 
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Ring<T> ring = next;
            
            @Override
            public boolean hasNext() {
                return ring != Ring.this;
                }

            @Override
            public T next() {
                T data = ring.data;
                ring = ring.next;
                return data;
                }
        
            };
        }
    
    }
