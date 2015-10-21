package dk.kalhauge.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

public class CharacterStack {
  private final Reader in;
  private CharacterNode first = null;
  
  public CharacterStack(InputStream stream) {
    in = new InputStreamReader(stream);
    }
  
  public CharacterStack(String string) {
    in = new StringReader(string);
    }
  
  public boolean isEmpty() throws IOException { return peek() < 0; }
  
  public int pop() throws IOException {
    if (first == null) return in.read();
    int value = first.value;
    first = first.next;
    return value;
    }
  
  public int peek() throws IOException {
    if (first == null) first = new CharacterNode(in.read(), first);
    return first.value;
    }
  
  public void push(int character) {
    first = new CharacterNode(character, first);
    }

  private class CharacterNode {
    private int value;
    private CharacterNode next;
    
    public CharacterNode(int value, CharacterNode next) {
      this.value = value;
      this.next = next;
      }

    public int getValue() { return value; }

    public CharacterNode getNext() { return next; }
    
    }
  
  }
