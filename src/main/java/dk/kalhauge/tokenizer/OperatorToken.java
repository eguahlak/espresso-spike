package dk.kalhauge.tokenizer;

import dk.kalhauge.util.CharacterSource;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OperatorToken extends Token {
  // TODO: Convert to maps
  private static Set<Integer> parts = null;
  private static Set<String> combinations = null;
  
  private static final int[] operatorParts = {
    '+', '-', '*', '/', '^', '!', '=', '<', '>', '&', '|', '%', '~', '.', ',', ';', '@'
    };
  private static final String[] operatorCombinations = {
    "=", "%", ".", ",", ";", "...", "@",
    "+", "-", "*", "/", "^", "!", "&", "|", "&&", "||", "<", "<=", "=", "==", ">=", ">",
    "<<", ">>", "~",
    "++", "--", "+=", "-=", "*=", "/=", "^=", "<<=", ">>=", "~="
    };
  private String name;
  
  private static boolean isPart(int ch) {
    if (parts == null) {
      parts = new HashSet<>();
      for (int part : operatorParts) parts.add(part);
      }
    return parts.contains(ch);
    }
  
  private static boolean isCombination(String name) {
    if (combinations == null) {
      combinations = new HashSet<>();
      combinations.addAll(Arrays.asList(operatorCombinations));
      }
    return combinations.contains(name);
    }
  
  public static boolean understands(CharacterSource input) throws IOException {
    return isPart(input.peek());
    }

  public OperatorToken(CharacterSource input) throws IOException {
    int ch = input.pop();
    name = Character.toString((char)ch);
    ch = input.pop();
    while (isCombination(name+(char)ch)) {
      name += (char)ch;
      ch = input.pop();
      }
    input.push(ch);
    }

  public String getName() {
    return name;
    }

  @Override
  public String toString() {
    return name;
    }

  @Override
  public <T extends Token> boolean is(Class<T> type, String... values) {
    return type == OperatorToken.class && in(name, values);
    }

  @Override
  public boolean is(Token other) {
    return other.is(OperatorToken.class, name);
    }

  @Override
  public boolean is(String... values) {
    return in(name, values);
    }
  
  }
