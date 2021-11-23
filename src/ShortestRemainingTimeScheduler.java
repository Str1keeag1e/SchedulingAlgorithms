import java.util.*;

public class ShortestRemainingTimeScheduler {

	int timeClock = 0;
	int numberOfJobs;
	List<Job> jobs;

	Queue<Job> minPriorityQueue = new PriorityQueue<>(53, Comparator.comparing(s -> s.getServiceTime()));

	ShortestRemainingTimeScheduler(List<Job> jobs){
		this.numberOfJobs = jobs.size();
		this.jobs = new ArrayList<>();
		for(Job job : jobs){
			this.jobs.add(new Job(job.getJobID(), job.getArrivalTime(), job.getServiceTime()));
		}
	}

	public void runJobs(){

		while(numberOfJobs != 0){
			addJobsToQueue();
			//System.out.println(Arrays.toString(minPriorityQueue.toArray()));

			Job currentJob = minPriorityQueue.peek();
			while((currentJob != null ? currentJob.getServiceTime() : 0) != 0){
				processJob(currentJob);
				timeClock++;
				addJobsToQueue();
			}
		}
	}

	public void processJob(Job job){

		job.decrementServiceTime();
		printJob(job.getJobID());

		if(job.getServiceTime() == 0){
			minPriorityQueue.remove(job);
			numberOfJobs--;
		}
	}


	public void printJob(char jobID){
		for(int j = 'A'; j < 'Z'; j++ ){
			if(j == jobID){
				System.out.print(jobID);
			}
			System.out.print("\t");
		}
		System.out.println();
	}

	//We are building priority queue based on arrival time, but organizing it by service time.
	public void addJobsToQueue(){
		for(Job job : jobs){
			if(job.getArrivalTime() == timeClock){
				minPriorityQueue.add(job);
			}
		}
	}
}
