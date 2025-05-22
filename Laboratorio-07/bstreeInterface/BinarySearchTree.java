package bstreeInterface;

import Exceptions.ItemDuplicated;

public interface BinarySearchTree<E> {
    void insert(E data) throws ItemDuplicated;
    E search(E data) throws ItemDuplicated;
    void delete(E data) throws ItemDuplicated;
    boolean isEmpty();
}
