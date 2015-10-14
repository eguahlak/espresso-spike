package dk.kalhauge.parser;

interface Visitor {
  void visit(Work work);
  void visit(Tree tree);
  }
