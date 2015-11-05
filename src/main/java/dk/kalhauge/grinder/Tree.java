package dk.kalhauge.grinder;

import dk.kalhauge.tokenizer.Token;

class Tree implements Branch {
  private Token token;
  private Branch parent;
  private final Branch[] branches;

    Tree(Branch parent, Token token, Branch... branches) {
    this.parent = parent;
    this.token = token;
    this.branches = branches;
    for (Branch branch : branches) branch.setParent(this);
    }


  @Override public Branch getParent() { return parent; }

  @Override public void setParent(Branch value) { parent = value; }

  @Override public Token getToken() { return token; }

  @Override
  public Branch normalised() {
    throw new UnsupportedOperationException("Tree.normalised(...) Not supported yet.");
    }

  @Override
  public int getDepth() { 
    if (parent == null) return 0;
    return parent.getDepth() + 1;
    }

  @Override public int getPosition() { return token.getPosition(); }

  }
