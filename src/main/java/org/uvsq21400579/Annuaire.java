package org.uvsq21400579;

import java.io.Serializable;
import java.util.Iterator;

public class Annuaire implements Iterable<Team>, Serializable {

  private static Annuaire ANNUAIRE;
  private final TeamIterator<Team> head;

  private Annuaire() {
    head = new TeamIterator<Team>();
  }

  public static Annuaire getInstance() {
    if (ANNUAIRE == null) {
      ANNUAIRE = new Annuaire();
    }
    return ANNUAIRE;
  }

  @Override
  public Iterator<Team> iterator() {
    return this.head;
  }


  public void addTeam(Team e) {
    this.head.add(e);
  }
}
