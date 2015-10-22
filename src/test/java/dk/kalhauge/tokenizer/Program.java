package dk.kalhauge.tokenizer;

import dk.kalhauge.source.Source;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Program {
  
  private static String spaces(int value) {
    final String space = "                                                          ";
    return space.substring(0, value);
    }
  
  public static void main(String[] args) {
    try {
      String path = Program.class.getResource("Person.espresso").getPath();
      System.out.println(">>"+path+"<<");
      Source source = new Source(new FileInputStream(path));
      int indent = 0;
      Iterator<Token> iterator = Token.iterate(source);
      while (iterator.hasNext()) {
        Token token = iterator.next();
        System.out.println(spaces(2*indent)+token);
        if (token.is(StartToken.class)) indent++;
        if (token.is(EndToken.class)) indent--;
        }
      
      System.out.println("----");
      }
    catch (IOException ex) {
      Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  
  }
