package dk.kalhauge.grinder;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class Printer {
  private final List<StringBuilder> lines = new ArrayList<>();
  private final int padding = 1;
  
  private void plot(String text, int col, int row) {
    while (lines.size() <= row) lines.add(new StringBuilder());
    StringBuilder line = lines.get(row);
    while (line.length() <= col) line.append(" ");
    line.replace(col, col + text.length(), text);
    }
  
  private void line(char template, int start, int end, int row) {
    throw new UnsupportedOperationException("Not implemented");
    }
  
  public void print(PrintStream out) {
    lines.stream().forEach(line -> out.println("| "+line));
    }
  
  static void print(Branch branch) {
    System.out.println();
    Printer printer = new Printer();
    printer.print(0, 0, branch);
    printer.print(System.out);
    }
  
  private int print(int depth, int indent, Branch branch) {
    if (branch instanceof Tree) return printTree(depth, indent, (Tree)branch);
    if (branch instanceof Work) return printWork(depth, indent, (Work)branch);
    throw new UnsupportedOperationException("Not implemented for other branch types than Tree and Work");
    }
  
  private int printTree(int depth, int indent, Tree tree) {
    String text = tree.getText();
    if (tree.isLeaf()) {
      plot(text, indent, depth);
      return indent + text.length() + padding;
      }
    indent = print(depth + 1, indent, tree.getBranches().get(0));
    plot(text, indent, depth);
    indent += text.length() + padding;
    for (int index = 1; index < tree.getBranches().size(); index++)
        indent = print(depth + 1, indent, tree.getBranches().get(index));
    return indent;
    }
  
  private int printWork(int depth, int indent, Work work) {
    String text = "«"+work.getText()+"»";
    plot(text, indent, depth);
    indent += text.length() + padding;
    for (Branch branch : work.getBranches()) {
      indent = print(depth + 1, indent, branch);
      }
    return indent;
    }
  
  }
