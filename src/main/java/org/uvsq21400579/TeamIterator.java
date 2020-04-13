package org.uvsq21400579;

import java.io.Serializable;
import java.util.Iterator;

public class TeamIterator<Team> implements Iterator<Team>, Serializable {

  private Node<Team> nodes;

  @Override
  public boolean hasNext() {
    return nodes != null;
  }

  @Override
  public Team next() {
    Team tmp = nodes.getElement();
    nodes = nodes.getNext();
    return tmp;
  }

  public void add(Team n) {
    if(nodes == null) {
      this.nodes = new Node<Team>(n);
    }
    else {
      this.nodes.addElement(n);
    }
  }

}
