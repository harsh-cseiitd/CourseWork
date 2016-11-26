package course1.week3.pa;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class QuickSortUtilsTest {

	private static final String filename = "/home/harshv/Documents/QuickSortArray.txt";
	ArrayList<Integer> inputList1 = null;
	ArrayList<Integer> inputList2 = null;
	ArrayList<Integer> inputList3 = null;

	private static ArrayList<Integer> readInputs(String file) {
		ArrayList<Integer> listOfIntegers = new ArrayList<Integer>();
		try (Scanner sc1 = new Scanner(new FileInputStream(file))) {
			System.out.println("Reading inputs from file : " + file);
			while(sc1.hasNext()) {
				Integer num = sc1.nextInt();
				listOfIntegers.add(num);
			}
		} catch (FileNotFoundException e) {
			System.out.println("file : " + filename + " is not available");
		}
		return listOfIntegers;
	}


	@Before
	public void setUp() throws Exception {
			inputList1 = readInputs(filename);
			inputList2 = readInputs(filename);
			inputList3 = readInputs(filename);
			System.out.println("Input list1 size: " + inputList1.size());
			System.out.println("Input list2 size: " + inputList2.size());
			System.out.println("Input list3 size: " + inputList3.size());
	}


	@Test
	public void testWithPiviotAsFirstElement() {
		System.out.println("Test case: number of comparisions with piviot first element.");
		int comparisions1 = QuickSortUtils.countComparisionsWithPiviotChoice(inputList1, QuickSortUtils.FIRST_ELEMENT);
		System.out.println("Number of comparisions with piviot first element: " + comparisions1);
	}


	@Test
	public void testWithPiviotAsLastElement() {
		System.out.println("Test case: number of comparisions with piviot last element.");
		int comparisions2 = QuickSortUtils.countComparisionsWithPiviotChoice(inputList2, QuickSortUtils.LAST_ELEMENT);
		System.out.println("Number of comparisions with piviot last element: " + comparisions2);
	}

	@Test
	public void testWithPiviotAsMedianElement() {
		System.out.println("Test case: number of comparisions with piviot median element.");
		int comparisions3 = QuickSortUtils.countComparisionsWithPiviotChoice(inputList3, QuickSortUtils.MEDIAN_OF_THREE_ELEMENT);
		System.out.println("number of comparisions with piviot median element: " + comparisions3);
	}
}
