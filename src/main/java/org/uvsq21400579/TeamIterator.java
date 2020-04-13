package org.uvsq21400579;

import java.util.Iterator;

public class TeamIterator<Team> implements Iterator<Team> {

  private int index;
  private Node<Team> e;

  @Override
  public boolean hasNext() {
    return e != null;
  }

  @Override
  public Team next() {
    Team tmp = e.getElement();
    e = e.getNext();
    index ++;
    return tmp;
  }

  public void add(Team n) {
    if(e == null) {
      this.e = new Node<Team>(n);
    }
    else {
      this.e.addElement(n);
    }
  }

}
