package dk.kalhauge.tokenizer;

import dk.kalhauge.util.CharacterSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Program {
  
  public static void main(String[] args) {
    try {
      String path = Program.class.getResource("Person.espresso").getPath();
      System.out.println(">>"+path+"<<");
      CharacterSource source = new CharacterSource(new FileInputStream(path));
      System.out.println("----");
      }
    catch (IOException ex) {
      Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  
  }
