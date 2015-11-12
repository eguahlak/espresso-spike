package dk.kalhauge.grinder;

import dk.kalhauge.source.Source;
import dk.kalhauge.tokenizer.Token;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Program {
  
  public static void main(String[] args) {
    try {
      String path = dk.kalhauge.tokenizer.Program.class.getResource("Person.espresso").getPath();
      System.out.println(">>"+path+"<<");
      Source source = new Source(new FileInputStream(path));
      Work work = new Work(Token.iterate(source));

      System.out.println("!!!!");
      Printer.print(work);
//      Grinder printGrinder = new PrintGrinder();
//      work.process(printGrinder);
      System.out.println("====");
      work.process(new DyadicOperationGrinder(".", "@"));
      Printer.print(work);
      
      System.out.println("====");
      System.out.println("monadic -");
      work.process(new MonadicOperationGrinder("-"));
      System.out.println("dyadic * /");
      work.process(new DyadicOperationGrinder("*", "/"));
      System.out.println("dyadic + -");
      work.process(new DyadicOperationGrinder("+", "-"));
      Printer.print(work);

//      System.out.println("====");
//      work.process(new SentenceGrinder());
//      Printer.print(work);

      
//      System.out.println("====");
//      Grinder comma = new DyadicOperationGrinder(",");
//      work.process(comma);
//      Printer.print(work);
      
      System.out.println("----");
      }
    catch (IOException ex) {
      Logger.getLogger(dk.kalhauge.tokenizer.Program.class.getName()).log(Level.SEVERE, null, ex);
      }

    }
  
  }
