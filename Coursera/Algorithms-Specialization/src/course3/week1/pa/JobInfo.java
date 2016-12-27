package course3.week1.pa;

public class JobInfo {
	private static int currentJobId = 0;
	private int jobId;
	private int length;
	private int weight;

	public JobInfo() {
		this.jobId = currentJobId;
		this.length = 0;
		this.weight = 0;
	}

	public JobInfo(int weight, int length) {
		this.jobId  = ++currentJobId;
		this.length = length;
		this.weight = weight;
	}

	public int getLength() {
		return this.length;
	}

	public int getWeight() {
		return this.weight;
	}
	
	public int getId() {
		return this.jobId;
	}
}