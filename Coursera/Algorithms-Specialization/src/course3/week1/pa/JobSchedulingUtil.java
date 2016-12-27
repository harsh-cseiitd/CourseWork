package course3.week1.pa;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JobSchedulingUtil {

	public static long getWeightedCompletionTime(List<JobInfo> jobRecords, boolean isRatioMetric) {
		long wCompletionTime = 0;
		Comparator<JobInfo> sortingComparator;
		if (jobRecords.size() > 0) {
			if (isRatioMetric) {
				sortingComparator = Collections.reverseOrder(new RatioComparator());
			} else {
				sortingComparator = Collections.reverseOrder(new DifferenceComparator());
			}
			Collections.sort(jobRecords, sortingComparator);
			wCompletionTime = schedule(jobRecords);
		}
		return wCompletionTime;
	}


	public static long schedule(List<JobInfo> sortedJobRecords) {
		long completionTime  = 0;
		long wCompletionTime = 0;
		for (int  i = 0 ; i < sortedJobRecords.size(); i++) {
			JobInfo jobRecord = sortedJobRecords.get(i);
			completionTime    = completionTime + jobRecord.getLength();
			wCompletionTime   = wCompletionTime + (completionTime * jobRecord.getWeight());
			//System.out.println("schedule: jobId: " + jobRecord.getId() + " weight" + jobRecord.getWeight() + " length:" + jobRecord.getLength());
			//System.out.println("schedule: : completionTime" + completionTime + " wCompletionTime: " + wCompletionTime);
		}
		return wCompletionTime;
	}


	private static class DifferenceComparator implements Comparator<JobInfo> {

		@Override
		public int compare(JobInfo jobRecord1, JobInfo jobRecord2) {
			int diffMetric1 = jobRecord1.getWeight() - jobRecord1.getLength();
			int diffMetric2 = jobRecord2.getWeight() - jobRecord2.getLength();
			
			if (diffMetric1 == diffMetric2) {
				return jobRecord1.getWeight() - jobRecord2.getWeight();
			}
			return diffMetric1 - diffMetric2;
		}
	}


	private static class RatioComparator implements Comparator<JobInfo> {

		@Override
		public int compare(JobInfo jobRecord1, JobInfo jobRecord2) {
			double ratioMetric1 = ((jobRecord1.getWeight() * 1.0) / jobRecord1.getLength());
			double ratioMetric2 = ((jobRecord2.getWeight() * 1.0) / jobRecord2.getLength());
			
			if (ratioMetric1 == ratioMetric2) {
				return jobRecord1.getWeight() - jobRecord2.getWeight();
			} else if (ratioMetric1 > ratioMetric2) {
				return 1;
			} else {
				return -1;
			}
		}
	}
}


