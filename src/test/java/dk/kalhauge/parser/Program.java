package dk.kalhauge.parser;

import dk.kalhauge.util.Path;

public class Program {
  
  private static Path<Token> pathOf(String text) {
    String[] values = (text + " $").split(" ");
    Path<Token> path = null;
    for (int i = values.length - 1; i >= 0; i--) {
      Token token = new Token(values[i], i + 1);
      path = new Path<>(token, path);
    }
    return path;
  }
  
  private static void checkSimpleMinusPlus() {
    Work work = new Work(pathOf("2 - 3 + 5").iterator());
    Printer.print(work);
    work.processInfixDyadics("+", "-");
    Printer.print(work);
  }
  
  private static void checkSimpleParanthesis() {
    Work work = new Work(pathOf("3 * ( 2 + ( 2 * 5 - 3 ) * 7 ) - 2").iterator());
    Printer.print(work);
    work.processInfixDyadics("*", "/");
    work.processInfixDyadics("+", "-");
    Printer.print(work);
    Branch normalised = work.normalised();
    Printer.print(normalised);
    normalised.reposition(0);
    Printer.print(normalised);
//    list.processParanthesis();
//    print(list);
  }
  
  private static void checkSimpleStatement() {
    Work work = new Work(pathOf(
            "if ( 3 + a ) * 7 > 8 { a = 56 ; y } else z"
    ).iterator());
    Printer.print(work);
    work.processInfixDyadics("*", "/");
    work.processInfixDyadics("+", "-");
    work.processInfixDyadics(">", "<", ">=", "<=", "==", "!=");
    work.processInfixDyadics("=");
    work.processInfixDyadics(",", ";");
    work.processPrefixDyadics("if", "while");
    work.processInfixDyadics("else");
    Printer.print(work);
    Branch normalised = work.normalised();
    normalised.reposition(0);
    Printer.print(normalised);
  }
  
  public static void main(String[] args) {
//    checkSimpleMinusPlus();
//    checkSimpleParanthesis();
    checkSimpleStatement();
  }
  
}
