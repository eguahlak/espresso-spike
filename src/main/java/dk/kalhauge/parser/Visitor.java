package dk.kalhauge.parser;

interface Visitor {
  void visit(Work work);
  void visit(Tree tree);
  
  void finish(Work work);
  void finish(Tree tree);
  }
