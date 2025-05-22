package bstreeInterface;

import Exceptions.ItemDuplicated;
import Exceptions.ItemNoFound;

public interface BinarySearchTree<E> {
    void insert(E data) throws ItemDuplicated;
    E search(E data) throws ItemDuplicated, ItemNoFound;
    void delete(E data) throws ItemDuplicated;
    boolean isEmpty();
}
