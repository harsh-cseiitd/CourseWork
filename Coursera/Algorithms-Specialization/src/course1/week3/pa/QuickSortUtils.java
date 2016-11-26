/*
 * Copyright (C) 2016 Harsh Vardhan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package course1.week3.pa;

import java.util.List;

public class QuickSortUtils {

	public static final int FIRST_ELEMENT = 0;
	public static final int LAST_ELEMENT  = 1;
	public static final int MEDIAN_OF_THREE_ELEMENT = 2;
	private static int currentPiviotIndex = FIRST_ELEMENT;

	private static void setPiviotIndex(int scheme) {
		currentPiviotIndex = scheme;
	}

	private static int getPiviotIndex(List<Integer> listOfIntegers, int startIndex , int endIndex) {
		if (currentPiviotIndex == FIRST_ELEMENT) {
			return startIndex;
		} else if (currentPiviotIndex == LAST_ELEMENT) {
			return endIndex;
		} else if (currentPiviotIndex == MEDIAN_OF_THREE_ELEMENT) {
			int midIndex      = Math.floorDiv((startIndex + endIndex), 2);
			int firstElement  = listOfIntegers.get(startIndex);
			int lastElement   = listOfIntegers.get(endIndex);
			int midElement    = listOfIntegers.get(midIndex);
			
			if (firstElement < midElement && midElement < lastElement && firstElement < lastElement) {
				return midIndex;
			} else if (firstElement < midElement && lastElement < midElement && firstElement < lastElement) {
				return endIndex;
			} else if (firstElement < midElement && lastElement < midElement && lastElement  < firstElement) {
				return startIndex;	
			} else if (midElement < firstElement && lastElement < midElement && lastElement < firstElement) {
				return midIndex;
			} else if (midElement < firstElement && midElement < lastElement && lastElement < firstElement) {
				return endIndex;
			} else if (midElement < firstElement && midElement < lastElement && firstElement < lastElement) {
				return startIndex;
			}
		}
		return 0;
	}

	public static void swapElements(List<Integer> listOfIntegers, int startIndex , int endIndex) {
		int element = listOfIntegers.get(endIndex);
		listOfIntegers.set(endIndex, listOfIntegers.get(startIndex));
		listOfIntegers.set(startIndex, element);
	}

	private static int countElements(int startIndex, int endIndex) {
		return (endIndex - startIndex +1);
	}

	private static void preprocessPiviot(List<Integer> listOfIntegers, int startIndex , int endIndex) {
		int pivotIndex = getPiviotIndex(listOfIntegers, startIndex , endIndex);
		swapElements(listOfIntegers, startIndex, pivotIndex);
	}

	private static int partition(List<Integer> listOfIntegers, int startIndex , int endIndex) {
		preprocessPiviot(listOfIntegers, startIndex , endIndex);
		int pivotIndex = startIndex;
		
		int partitionIndex = startIndex + 1;
		for (int exploreIndex = startIndex + 1; exploreIndex <= endIndex; exploreIndex++) {
			if (listOfIntegers.get(exploreIndex).intValue() < listOfIntegers.get(pivotIndex).intValue()) {
				swapElements(listOfIntegers, exploreIndex , partitionIndex);
				partitionIndex++;
			}
		}
		partitionIndex = partitionIndex -1;
		swapElements(listOfIntegers, partitionIndex , pivotIndex);
		return partitionIndex;
	}


	public static int countComparisionsInQuickSort(List<Integer> listOfIntegers, int startIndex , int endIndex) {
		if (startIndex >= endIndex) {
			return 0;
		}
		int partitionIndex = partition(listOfIntegers, startIndex, endIndex);
		int comparisions = countElements(startIndex, endIndex) -1;
		comparisions = comparisions + countComparisionsInQuickSort(listOfIntegers, startIndex , partitionIndex-1);
		comparisions = comparisions + countComparisionsInQuickSort(listOfIntegers, partitionIndex+1 , endIndex);
		return comparisions;
	}


	public static int countComparisions(List<Integer> listOfIntegers) {
		setPiviotIndex(FIRST_ELEMENT);
		return countComparisionsInQuickSort(listOfIntegers, 0 , listOfIntegers.size()-1);
	}
	
	public static int countComparisionsWithPiviotChoice(List<Integer> listOfIntegers, int choice) {
		setPiviotIndex(choice);
		return countComparisionsInQuickSort(listOfIntegers, 0 , listOfIntegers.size()-1);
	}

}
