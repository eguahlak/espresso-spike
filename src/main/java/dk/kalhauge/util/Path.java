package dk.kalhauge.util;

import java.util.Iterator;

public class Path<T> implements Iterable<T> {
  private final T step;
  private final Path<T> rest;

  public Path(T first, Path<T> rest) {
    this.step = first;
    this.rest = rest;
    }
  
  public Path(T... steps) {
//    this(0, steps);
    Path<T> path = null;
    for (int index = steps.length - 1; index > 0; index--) {
      path = new Path(steps[index], path);
      }
    step = steps[0];
    rest = path;
    }

  private Path(int index, T... steps) {
    step = steps[index++];
    rest = index >= steps.length ? null : new Path(index++, steps);
    }
  
  public T getStep() {
    return step;
    }

  public Path<T> getRest() {
    return rest;
    }

  @Override
  public Iterator<T> iterator() {
    return new PathIterator(this);
    }
  
  private class PathIterator implements Iterator<T> {
    private Path<T> path;

    public PathIterator(Path<T> path) {
      this.path = path;
      }
    
    @Override
    public boolean hasNext() { return path != null; }

    @Override
    public T next() {
      T next = path.step;
      path = path.rest;
      return next;
      }
    
    }
  
  }
