package dk.kalhauge.util;

import java.util.Iterator;

public class Ring<T> implements Iterable<T> {
    private final T data;
    private Ring<T> previous = this;
    private Ring<T> next = this;

    public Ring(T data) {
        this.data = data;
        }
    
    public Ring(T data, Ring<T> other) {
        this.data = data;
        combine(other);
        }
    
    public final void combine(Ring<T> other) {
        Ring<T> otherNext = other.next;
        Ring<T> thisPrevious = this.previous;
        previous = other;
        other.next = this;
        thisPrevious.next = otherNext;
        otherNext.previous = this;
        }

    public T detach() {
        previous.next = next;
        next.previous = previous;
        return data;
        }
    
    public T getData() {
        return data;
        }

    public Ring<T> getNext() {
        return next;
        }

    public Ring<T> getPrevious() {
        return previous;
        }

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
