package com.revature.byron_fedele_p0.util.customCollections;

public interface List<E> extends Collection<E> {// List extends Collections interface, so it has the same methods as Collection, but it also adds this new method get below

    E get(int index);

}
