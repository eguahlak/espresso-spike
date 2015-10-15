package dk.kalhauge.parser;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class Printer implements Visitor {
  private final List<StringBuilder> lines = new ArrayList<>();
  private final int tabsize = 5;
  
  private void plot(String text, int col, int row) {
    while (lines.size() <= row) lines.add(new StringBuilder());
    StringBuilder line = lines.get(row);
    while (line.length() <= col) line.append(" ");
    line.replace(col, col + text.length(), text);
    }
  
  public void print(PrintStream out) {
    lines.stream().forEach(line -> out.println("| "+line));
    }
  
  static void print(Branch branch) {
    System.out.println();
    Printer printer = new Printer();
    branch.accept(printer);
    printer.print(System.out);
    }

  @Override
  public void visit(Work work) {
    int p1 = tabsize*work.getPosition();
    int p2 = tabsize*work.getEnd().getPosition();
    String t1 = work.getText();
    String t2 = work.getEnd().getValue();
    String text = t1+" ";
    for (int p = p1 + text.length(); p < p2 - 1; p++) text += "-";
    plot(text+" "+t2, p1, work.getDepth());
    }

  @Override
  public void visit(Tree tree) {
    int pos = tabsize*tree.getPosition();
    String text = tree.getText();
//    String text = tree.toString();
    if (tree.getParent() != null && tree.getParent() instanceof Tree) {
      int parentPos = tabsize*tree.getParent().getPosition();
      if (parentPos < pos) {
        int p = parentPos + tree.getParent().getText().length();
        while (p < pos--) text = "¨"+text;
        pos++;
        }
      else if (pos < parentPos) {
        int p = parentPos - tree.getText().length();
        while (p-- > pos) text += "¨";
        }
      }
    plot(text, pos, tree.getDepth());
    }

  @Override
  public void finish(Work work) { }

  @Override
  public void finish(Tree tree) { }
  
  }
