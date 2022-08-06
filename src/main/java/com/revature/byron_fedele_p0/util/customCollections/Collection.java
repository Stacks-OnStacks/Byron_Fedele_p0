package com.revature.byron_fedele_p0.util.customCollections;

public interface Collection<E>{// the e stands for element, since we will be working with elements in a list and eventually linked list, this is why this is the generic type chosen

    boolean add(E element);// these methods' bodies are empty since this is an interface class, they will be overrided later and their bodies will be filled.

    boolean contains(E element);

    boolean isEmpty();

    boolean remove(E element);

    int size();

}
