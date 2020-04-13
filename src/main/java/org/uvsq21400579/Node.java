package org.uvsq21400579;

public class Node<T> {

  private Node<T> prev = null;
  private Node<T> next = null;
  private T element;

  public Node(T element){
    this.element = element;
  }

  public void addElement(T next) {

    if (this.next == null) {
      this.next = new Node<T>(next);
      this.next.prev = this;
    }
    else
      this.next.addElement(next);
  }

  public T getElement() {
    return this.element;
  }

  public void remove() {
    this.prev.next = this.next;
    this.next.prev = this.prev;
    this.element = null;
    this.next = null;
    this.prev = null;
  }

  public boolean hasNext() {
    return this.next != null;
  }

  public Node<T> getNext() {
    return this.next;
  }

  public Node<T> getPrevious() {
    return this.prev;
  }
}
