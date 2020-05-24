package org.uvsq21400579;

public interface DAO<T> {

  void create(T object);

  T find(String path);

  void delete(String path);
}
