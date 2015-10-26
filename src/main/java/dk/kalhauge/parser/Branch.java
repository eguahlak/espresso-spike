package dk.kalhauge.parser;

abstract class Branch implements Visible {
  private Branch parent;
  private final OldToken token;

  public Branch(Branch parent, OldToken token) {
    this.parent = parent;
    this.token = token;
    }

  boolean hasTokenValue(String... values) {
    return token.hasValue(values);
    }
  
  int getDepth() {
    if (parent == null) return 0;
    return parent.getDepth() + 1;
    }
  
  int getPosition() {
    return token.getPosition();
    }

  int reposition(int start) {
    token.setPosition(start);
    return start + 1;
    }
  
  Branch getParent() {
    return parent;
    }
  
  void setParent(Branch value) {
    parent = value;
    }
  
  public OldToken getToken() {
    return token;
    }
  
  public String getText() {
    return token.getValue();
    }
  
  abstract Branch normalised();
  
  }
