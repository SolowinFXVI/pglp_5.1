package org.uvsq21400579;

import java.util.Iterator;

public class Group extends Team implements Iterable<Team>{

  private final String nom;
  private final TeamIterator<Team> head;

  public Group(String nom) {

    this.nom = nom;
    this.head = new TeamIterator<Team>();
  }

  public void addMember(Team e) {

    this.head.add(e);

  }

  public String getName() {
    return this.nom;
  }

  public void printName() {
    System.out.println(this.nom);
    for(Team e : this) {
      e.printName();
    }
  }

  @Override
  public Iterator<Team> iterator() {
    return this.head;
  }


}