package dk.kalhauge.source;

public class Position {
  private final Source source;
  private final int line;
  private final int column;

  public Position(Source source, int line, int column) {
    this.source = source;
    this.line = line;
    this.column = column;
    }

  public Source getSource() {
    return source;
    }
  
  public int getLine() {
    return line;
    }

  public int getColumn() {
    return column;
    }

  }
