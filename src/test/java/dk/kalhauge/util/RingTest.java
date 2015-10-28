package dk.kalhauge.util;

import static org.hamcrest.Matchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

public class RingTest {
    
    @Test
    public void testSimpleRing() {
        Ring<String> ring = new Ring("Kurt");
        assertThat(ring.getData(), is("Kurt"));
        assertThat(ring.getNext(), is(ring));
        assertThat(ring.getPrevious(), is(ring));
        }
    
    @Test
    public void testDoubleRing() {
        Ring<String> first = new Ring("Kurt");
        Ring<String> second = new Ring("Sonja", first);
        assertThat(first.getNext(), is(second));
        assertThat(second.getNext(), is(first));
        assertThat(first.getPrevious(), is(second));
        assertThat(second.getPrevious(), is(first));
        }
 
    @Test
    public void testTrippleRing() {
        Ring<String> first = new Ring("Kurt");
        Ring<String> second = new Ring("Sonja", first);
        Ring<String> third = new Ring("Ida", second);
        assertThat(first.getNext(), is(second));
        assertThat(second.getNext(), is(third));
        assertThat(third.getNext(), is(first));
        assertThat(first.getPrevious(), is(third));
        assertThat(second.getPrevious(), is(first));
        assertThat(third.getPrevious(), is(second));
        }

    }
