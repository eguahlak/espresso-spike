package dk.kalhauge.tokenizer;

import dk.kalhauge.source.Source;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StartToken extends Token {
  private static final Map<Integer, Integer> brackets = new HashMap<>();
  static {
    brackets.put((int)'(', (int)')');
    brackets.put((int)'[', (int)']');
    brackets.put((int)'{', (int)'}');
    brackets.put((int)'^', (int)'$');
    }
  private final int start;
  private final int end;
  
  private String getPattern() {
    return ""+(char)start+(char)end;
    }
  
  public static boolean understands(Source input) throws IOException {
    return brackets.containsKey(input.peek());
    }

  public StartToken() {
    start = '^';
    end = brackets.get(start);
    }
  
  public StartToken(Source source) throws IOException {
    super(source);
    start = source.pop();
    end = brackets.get(start);
    }

  @Override
  public <T extends Token> boolean is(Class<T> type, String... values) {
    return type == StartToken.class && (values.length == 0 || in(getPattern(), values));
//    return type == StartToken.class && (values.length == 0 || in(getStart(), values));
    }
  
  public String getStart() { return ""+(char)start; }
  
  public String getEnd() { return ""+(char)end; }

  @Override
  public boolean is(String... values) {
    return in(getPattern(), values);
    }

  @Override
  public String getText() {
    return String.valueOf((char)start);
    }

  @Override
  public String getType() {
    return "Start";
    }

  @Override
  public boolean isLanguage() {
    return true;
    }
  
  }
