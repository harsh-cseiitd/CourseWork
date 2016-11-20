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


package course1.week2.pa;


import java.util.ArrayList;
import java.util.List;

public class ComputeInversions {
	
	private static long countSplitInversions(List<Integer> listOfIntegers, int startIndex, int midIndex, int endIndex) {
		List<Integer> tempList = new ArrayList<Integer>(endIndex - startIndex + 1);
		long inversions = 0;
		int l1 = startIndex;
		int l2 = midIndex+1;
		
		while(l1 <= midIndex && l2 <= endIndex) {
			if (listOfIntegers.get(l1) > listOfIntegers.get(l2)) {
				inversions = inversions + (midIndex - l1 +1);
				tempList.add(listOfIntegers.get(l2));
				l2 = l2 + 1;
			} else {
				tempList.add(listOfIntegers.get(l1));
				l1 = l1 + 1;
			}
		}

		while(l1 <= midIndex) {
			tempList.add(listOfIntegers.get(l1));
			l1 = l1 + 1;
		}

		while(l2 <= endIndex) {
			tempList.add(listOfIntegers.get(l2));
			l2 = l2 + 1;
		}

		int l = 0;
		while(l < tempList.size()) {
			listOfIntegers.set(startIndex+l, tempList.get(l));
			l++;
		}
		return inversions;
	}

	private static long countInversionsBySorting(int step, List<Integer> listOfIntegers, int startIndex, int endIndex) {
		if (endIndex <= startIndex) {
			return 0;
		}
		int midIndex = Math.floorDiv((startIndex + endIndex), 2);
		long leftInversions  = countInversionsBySorting(step+1, listOfIntegers, startIndex, midIndex);
		long rightInversions = countInversionsBySorting(step+1, listOfIntegers, midIndex+1, endIndex);
		long splitInversions = countSplitInversions(listOfIntegers, startIndex, midIndex, endIndex);
		return (leftInversions + rightInversions + splitInversions);
	}

	public static long countInversions(List<Integer> listOfIntegers) {
		return countInversionsBySorting(1, listOfIntegers, 0 , listOfIntegers.size()-1);
	}

}