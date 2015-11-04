package Utils;

import java.util.Comparator;

/*MergeSort users Comparator interface instead of Comparable
 *Catch: using Comparator interface allows sort a data type with different sort key.
 *Eg. Student(ID,Name,DOB), key = ID or Name or DOB*/

public class MergeSort {
	//Practical improvement
	//To increase the effectiveness of merge sort: If the array is small,
	//just use Insertion sort
	@SuppressWarnings("unused")
	private static final int CUTOFF = 7; 	  
	
	public MergeSort() {}
	
	public static void sort (Object[] a, @SuppressWarnings("rawtypes") Comparator c) {
		Object [] aux = new Object[a.length]; 	
		sort(c, a, aux, 0, a.length -1);
	}
	
	/*
	 * sort */
	public static void sort(@SuppressWarnings("rawtypes") Comparator c, Object[] a, Object[] aux, int lo, int hi) {
		//TODO: Run Insertion sort if (hi -lo) <= CUTOFF -1
		int mid = lo + (hi - lo)/2;
		sort(c, a,aux,lo,mid);
		sort(c, a,aux,mid+1,hi);
		if (less(c, a[mid],a[mid+1])) return; //already sorted, don't need to merge...
		merge(c,a,aux,lo,mid,hi);
	}
	
	/*
	 * merge */
	public static void merge (@SuppressWarnings("rawtypes") Comparator c, Object[] a, Object[] aux, int lo, int mid, int hi) {
		
		assert isSorted(c, a, lo, mid);
		assert isSorted(c, a, mid+1, hi);
		
		//Copy to auxiliary array
		for (int k=lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		
		int i = lo, j = mid +1;
		//Blind copy from KW and SW
		for (int k = lo; k <= hi; k++) {
			if (i > mid)	
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(c, aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
		}
		assert isSorted(c, a, lo, hi);
	}
	
	/*
	 * swap element */
	@SuppressWarnings("unused")
	private static void exch(Object[] a, int j, int i) {
		//TODO: Do the exchange
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	/*
	 * implement binary order less (<) */
	@SuppressWarnings("unchecked")
	private static boolean less(@SuppressWarnings("rawtypes") Comparator c, Object v, Object u) {
		//TODO: Do the exchange
		return (c.compare(v,u) < 0);
	}
	
	
	/*
	 * check if the array is sorted */
	public static boolean isSorted(@SuppressWarnings("rawtypes") Comparator c, Object[] a, int lo, int hi) {
		for (int i = lo; i <= hi; i++)
			if (less(c, a[i+1],a[i])) {
				return false; 
			}
		return true;
	}
}
