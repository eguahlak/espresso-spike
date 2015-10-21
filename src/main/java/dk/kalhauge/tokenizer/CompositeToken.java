package dk.kalhauge.tokenizer;

import dk.kalhauge.util.CharacterStack;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CompositeToken extends Token {
  private static final Map<Integer, Integer> brackets = new HashMap<>();
  static {
    brackets.put((int)'(', (int)')');
    brackets.put((int)'[', (int)']');
    brackets.put((int)'{', (int)'}');
    }
  private final int start;
  private final int end;
  private final TokenQueue children;
  
  private String getPattern() {
    return ""+(char)start+(char)end;
    }
  
  public static boolean understands(CharacterStack input) throws IOException {
    return brackets.containsKey(input.peek());
    }

  public CompositeToken(CharacterStack input) throws IOException {
    start = input.pop();
    end = brackets.get(start);
    children = new TokenQueue(input);
    }

  @Override
  public TokenQueue children() {
    return children;
    }

  @Override
  public <T extends Token> boolean is(Class<T> type, String... values) {
    return type == CompositeToken.class && (values.length == 0 || in(getPattern(), values));
    }
  
  public String getStart() { return ""+(char)start; }
  
  public String getEnd() { return ""+(char)end; }

  @Override
  public String toString() {
    return ""+getStart()+children()+getEnd();
    }

  @Override
  public String joinWith(TokenQueue tokens) {
    return ""+getStart()+children.joined()+getEnd()+tokens.joined();
    }

  @Override
  public boolean is(String... values) {
    return in(getPattern(), values);
    }
  
  }
