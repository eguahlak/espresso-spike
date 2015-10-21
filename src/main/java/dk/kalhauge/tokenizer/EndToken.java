package dk.kalhauge.tokenizer;

import dk.kalhauge.util.CharacterStack;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class EndToken extends Token {
  private static final Set<Integer> ends = new HashSet<>();
  static {
    ends.add(-1);
    ends.add((int)')');
    ends.add((int)']');
    ends.add((int)'}');
    }

  public static boolean understands(CharacterStack input) throws IOException {
    return ends.contains(input.peek());
    }

  public EndToken() { }
  
  public EndToken(CharacterStack input) throws IOException {
    if (input.isEmpty()) return;
    input.pop();
    }
  
  @Override
  public <T extends Token> boolean is(Class<T> type, String... values) {
    return type == EndToken.class && values.length == 0;
    }

  @Override
  public boolean is(Token other) {
    return other.getClass() == EndToken.class;
    }

  @Override
  public String joinWith(TokenQueue tokens) {
    return "";
    }

  @Override
  public boolean is(String... values) {
    // TODO: sanity check
    return true;
    }
  
  }
