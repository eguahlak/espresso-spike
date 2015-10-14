package dk.kalhauge.parser;

class Tree extends Branch {
  private final Branch[] branches;
  
  Tree(Branch parent, Token token, Branch... branches) {
    super(parent, token);
    this.branches = branches;
    for (Branch branch : branches) branch.setParent(this);
    }

  boolean isLeaf() {
    return branches.length == 0;
    }

  @Override
  public String toString() {
    return "T("+getToken()+")";
    }
  
  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
    for (Branch branch : branches) branch.accept(visitor);
    }

  @Override
  Branch normalised() {
    for (int index = 0; index < branches.length; index++) {
      branches[index] = branches[index].normalised();
      branches[index].setParent(this);
      }
    return this;
    }

  @Override
  int reposition(int start) {
    if (branches.length > 0) start = branches[0].reposition(start);
    start = super.reposition(start);
    for (int index = 1; index < branches.length; index++)
      start = branches[index].reposition(start);
    return start;
    }

  }
