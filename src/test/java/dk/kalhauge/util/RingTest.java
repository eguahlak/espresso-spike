package dk.kalhauge.util;

import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class RingTest {
    
    @Test
    public void testSimpleRing() {
        Ring<String> ring = new Ring("Kurt");
        assertThat(ring.getData(), is("Kurt"));
        assertThat(ring.getNext(), is(ring));
        assertThat(ring.getPrevious(), is(ring));
        assertThat(ring, is(emptyIterable()));
        }
    
    @Test
    public void testDoubleRing() {
        Ring<String> first = new Ring("Kurt");
        Ring<String> second = new Ring("Sonja", first);
        assertThat(first.getNext(), is(second));
        assertThat(second.getNext(), is(first));
        assertThat(first.getPrevious(), is(second));
        assertThat(second.getPrevious(), is(first));
        assertThat(first, contains("Sonja"));
        assertThat(second, contains("Kurt"));
        }
 
    @Test
    public void testTrippleRing() {
        Ring<String> ring = new Ring("$");
        Ring<String> first = new Ring("Kurt", ring);
        Ring<String> second = new Ring("Sonja", ring);
        System.out.println("0: "+ring);
        System.out.println("1: "+first);
        System.out.println("2: "+second);
        assertThat(ring.getNext(), is(first));
        assertThat(first.getNext(), is(second));
        assertThat(second.getNext(), is(ring));
        assertThat(ring.getPrevious(), is(second));
        assertThat(first.getPrevious(), is(ring));
        assertThat(second.getPrevious(), is(first));
        assertThat(ring, contains("Kurt", "Sonja"));
        assertThat(first, contains("Sonja", "$"));
        assertThat(second, contains("$", "Kurt"));
        }

//    @Ignore
    @Test
    public void testStaticCreateRing() {
        Ring<String> ring = Ring.create("$", "Kurt", "Sonja", "Ida");
        System.out.println("R: "+ring);
        assertThat(ring, contains("Kurt", "Sonja", "Ida"));
        }

    @Test
    public void testCombineRings() {
        Ring<String> ringA = Ring.create("$A", "Kurt", "Sonja", "Ida");
        System.out.println("A: "+ringA);
        Ring<String> ringB = Ring.create("Kvast", "Tot", "Cocio");
        System.out.println("B: "+ringB);
        ringB.combine(ringA);
        System.out.println("RA: "+ringA);
        System.out.println("RB: "+ringB);
        assertThat(ringA, contains("Kurt", "Sonja", "Ida", "Kvast", "Tot", "Cocio"));
        }
    
    @Test
    public void testDetach() {
        Ring<String> ring = Ring.create("$", "Kurt", "Sonja", "Kvast", "Cocio", "Tot");
        assumeThat(ring, contains("Kurt", "Sonja", "Kvast", "Cocio", "Tot"));
        Ring<String> kvast = ring.getNext().getNext().getNext();
        assertThat(kvast.getData(), is("Kvast"));
        String data = kvast.detach();
        assertThat(data, is("Kvast"));
        assertThat(ring, contains("Kurt", "Sonja", "Cocio", "Tot"));
        }

    @Test
    public void testDetachLast() {
        Ring<String> ring = new Ring("$");
        String end = ring.detach();
        assertThat(ring, is(emptyIterable()));
        assertThat(ring, is(ring));
        assertThat(end, is("$"));
        }
    
    }
