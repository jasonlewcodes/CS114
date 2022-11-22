// Jason Lew
// CS 114 H02

import java.util.Arrays;

public class Sorts {

    public static <T extends Comparable<? super T>> void heapSort(T[] array) {
      int maxPos = array.length;
      
      for (int i = maxPos / 2 - 1; i >= 0; i--) {
         heapify(array, maxPos, i);
      }
      for (int i = maxPos - 1; i >= 0; i--) {
         T temp = array[0];
         array[0] = array[i];
         array[i] = temp;
         heapify(array, i, 0);
      }
    }
    
    public static <T extends Comparable<? super T>> void heapify(T[] array, int maxPos, int i) {
      int largestIndex = i;
      int leftIndex = 2 * i + 1;
      int rightIndex = 2 * i + 2;
      
      if (leftIndex < maxPos && array[leftIndex].compareTo(array[largestIndex]) > 0) {
         largestIndex = leftIndex;
      }
      if (rightIndex < maxPos && array[rightIndex].compareTo(array[largestIndex]) > 0) {
         largestIndex = rightIndex;
      }
      if (largestIndex != i) {
         T temp = array[i];
         array[i] = array[largestIndex];
         array[largestIndex] = temp;
         heapify(array, maxPos, largestIndex);          
      }
    }
}