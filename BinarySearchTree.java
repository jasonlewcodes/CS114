// Jason Lew
// CS 114 H02

import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> {
   private Node<E> findIOP(Node<E> curr) { // IOP
      curr = curr.left;
      while (curr.right != null) {
          curr = curr.right;
      }
      return curr;
    }
    
    public void insert(E data) { // insert
      Node<E> temp = new Node<E>(data);
      if (root == null) {
         root = temp;
      }
      else { insert(root, temp); }
    }
    
    private void insert(Node<E> curr, Node<E> temp) { // insert helper
      if (temp.data.compareTo(curr.data) <= 0) {
         if (curr.left == null) {
            curr.left = temp;
         }
         else { insert(curr.left, temp); }
      }
      else {
         if (curr.right == null) {
            curr.right = temp;
         }
         else { insert(curr.right, temp); }
      }
    }
    
    public Iterator<E> iterator() { // iterator
      vector = new Vector<E>();
      traverse(root);
      return vector.iterator();
    }
    
    private void traverse(Node<E> curr) { // iterator helper
      if (curr != null) {
          traverse(curr.left);
          vector.add(curr.data);
          traverse(curr.right);
      }
    }
    
    public void remove(E data) { // remove
      root = remove(root, data);
    }
    
    private Node<E> remove(Node<E> curr, E data) { // remover helper
      if (curr != null) {
         if (data.compareTo(curr.data) == 0) {
            if (curr.left == null) {
               return curr.right;
            }
            else if (curr.right == null) {
               return curr.left;
            }
            else {
               Node<E> iop = findIOP(curr);
               E temp = iop.data;
               iop.data = curr.data;
               curr.data = temp;
               curr.left = remove(curr.left, data); 
            }
         }
         else {
            if (data.compareTo(curr.data) < 0) {
               curr.left = remove(curr.left, data);
            }
            else if (data.compareTo(curr.data) > 0) {
               curr.right = remove(curr.right, data);
            }
         }
      }
      return curr;
    }
    
    public boolean search(E data) { // search
      Node<E> parent = null;
      return search(root, data);
    }
    
    private boolean search(Node<E> curr, E data) { // search helper
      if (curr != null) {
         if (data.compareTo(curr.data) == 0) {
            return true;
         }
         else if (data.compareTo(curr.data) < 0) {
            return search(curr.left, data);
         }
         else { 
            return search(curr.right, data);
         }
      }
      return false;  
    }
    private Vector<E> vector;
}