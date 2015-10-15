package dk.kalhauge.parser;

import java.io.PrintStream;

class Compressor implements Visitor {
  private final StringBuilder text = new StringBuilder();
  
  static String compress(Branch branch) {
    Compressor compressor = new Compressor();
    branch.accept(compressor);
    return compressor.getText();
    }
  
  @Override
  public void visit(Work work) {
    text.append(work.getText());
  //  text.append("[");
    }

  @Override
  public void visit(Tree tree) {
    text.append(tree.getText());
    text.append("(");
//    text.append("‹");
    }

  public String getText() {
    return text.toString();
    }
  
  public void print(PrintStream out) {
    out.println(text);
    }

  @Override
  public void finish(Work work) {
    int last = text.length() - 1;
    if (text.charAt(last) == ' ') text.replace(last, last + 1, work.getEnd().getValue());
    else text.append(work.getEnd().getValue());
    text.append(" ");
    }

  @Override
  public void finish(Tree tree) {
    int last = text.length() - 1;
    if (text.charAt(last) == '(') text.setCharAt(last, ' ');
    else if (text.charAt(last) == ' ') text.setCharAt(last, ')');
    else text.append(")");
    }
  
//  @Override
//  public void finish(Tree tree) {
//    int last = text.length() - 1;
//    if (text.charAt(last) == '‹') text.setCharAt(last, ' ');
//    else if (text.charAt(last) == ' ') text.setCharAt(last, '›');
//    else text.append("›");
//    }
//  
  }
