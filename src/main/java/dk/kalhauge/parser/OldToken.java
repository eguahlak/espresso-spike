package dk.kalhauge.parser;

class OldToken {
  final static OldToken SOF = new OldToken("^", 0);
  final static OldToken EOF = new OldToken("$", 0);
  private final String value;
  private int position;

  public OldToken(String value, int position) {
    this.value = value;
    this.position = position;
    }

  public boolean hasValue(String... candidates) {
    for (String candidate : candidates) if (value.equals(candidate)) return true;
    return false;
    }
  
  public String getValue() {
    return value;
    }

  public int getPosition() {
    return position;
    }

  public void setPosition(int position) {
    this.position = position;
    }
  
  @Override
  public String toString() {
    return "{"+value+"/"+position+"}";
    }
  
  }
