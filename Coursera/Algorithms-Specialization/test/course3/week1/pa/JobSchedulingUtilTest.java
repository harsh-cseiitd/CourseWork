package course3.week1.pa;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class JobSchedulingUtilTest {
	
	private static final String testcase1_filename = "/home/harshv/Documents/testFiles/jobs.txt";
	
	private static List<JobInfo> readJobRecords(String filename) {
		ArrayList<JobInfo> jobRecords = new ArrayList<JobInfo>();
		try (Scanner sc1 = new Scanner(new FileInputStream(filename))) {
			System.out.println("Reading inputs from file: " + filename);

			// Reading number of Jobs: which should be first line
			if(sc1.hasNextLine()) {
				String[] tokens  = sc1.nextLine().split("\\s+");
				int numberOfJobs = Integer.parseInt(tokens[0].trim());
				jobRecords = new ArrayList<>(numberOfJobs);
				System.out.println("Total number of Job Records: " + numberOfJobs);
			}

			// Reading job records: a key value pair of (weight length)
			while(sc1.hasNextLine()) {
				String[] tokens = sc1.nextLine().split("\\s+");
				int weight = Integer.parseInt(tokens[0].trim());
				int length = Integer.parseInt(tokens[1].trim());
				jobRecords.add(new JobInfo(weight, length));
			}
		} catch (FileNotFoundException e) {
			System.out.println("file : " + filename + " is not available");
		}
		return jobRecords;
	}


	@Test
	public void testScheduleWithRatioChoice() {
		List<JobInfo> jobRecords = readJobRecords(testcase1_filename);
		long wCompletionTime = JobSchedulingUtil.getWeightedCompletionTime(jobRecords, true);
		System.out.println("Weighted completion time with ratio scheme is : " + wCompletionTime);
	}


	@Test
	public void testScheduleWithDiffChoice() {
		List<JobInfo> jobRecords = readJobRecords(testcase1_filename);
		long wCompletionTime = JobSchedulingUtil.getWeightedCompletionTime(jobRecords, false);
		System.out.println("Weighted completion time with diff scheme is : " + wCompletionTime);
	}
	
}
