// Jason Lew
// CS 114 H02

import java.util.Iterator;

public class SortedList<E extends Comparable<? super E>> extends List<E> 
{
   public void insert(E data) {
      Node<E> temp = new Node<E>(data);
      head = insert(head, temp);
   }
   
   private Node<E> insert(Node<E> curr, Node<E> node) {
      if (curr == null) {
         return node;
      }
      if (node.data.compareTo(curr.data) < 0) {
         node.next = curr;
         return node;
      }
      curr.next = insert(curr.next, node);
      return curr;
   }
   
   public Iterator<E> iterator() {
      return new Iterator<E>() {
         public boolean hasNext() {
            return curr != null;
         }        
         public E next() {
            E temp = curr.data;
            curr = curr.next;
            return temp;
         }
         private Node<E> curr = head;
      };
   }
   
   public void remove(E data) {
      head = remove(head, data);
   }
   
   private Node<E> remove(Node<E> curr, E data) {
      if (curr != null && data.compareTo(curr.data) == 0) {
         return curr.next;
      }
      if (curr.next != null && data.compareTo(curr.next.data) == 0) {
         curr.next = curr.next.next;
         return curr;
      }
      curr.next = remove(curr.next, data);
      return curr;
   }
   
   public E retrieve(int index) {
      return retrieve(head, index, 0);
   }
   
   private E retrieve(Node<E> curr, int index, int count) {
      if (curr.next == null) {
         return null;
      }
      if (index == count) {
         return curr.data;
      }
      return retrieve(curr.next, index, count+1);
   }
   
   public boolean search(E data) {
      return search(head, data);
   }
   
   private boolean search(Node<E> curr, E data) {
      if (curr == null) {
         return false;
      }
      if (curr != null & data.compareTo(curr.data) == 0) {
         return true;
      }
      return search(curr.next, data);
   }
}