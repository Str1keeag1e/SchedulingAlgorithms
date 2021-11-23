import java.util.*;

public class FeedBackScheduler {


	int timeClock = 0;
	int timeQuanta;
	int numberOfJobs;
	List<Job> jobs;


	Queue<Job>[] jobQueueArray = new Queue[3];

	FeedBackScheduler(int timeQuanta, List<Job> jobs){
		this.timeQuanta = timeQuanta;
		this.numberOfJobs = jobs.size();
		for(int i = 0; i < jobQueueArray.length; i++){
			jobQueueArray[i] = new LinkedList<Job>();
		}
		this.jobs = new ArrayList<>();
		for(Job job : jobs){
			this.jobs.add(new Job(job.getJobID(), job.getArrivalTime(), job.getServiceTime()));
		}

	}

	public void runJobs(){
		addJobsToQueue(jobQueueArray[0]);

		while(numberOfJobs > 0){
			int queueNumber = jobQueuePriorityCheck();
			jobHelper(jobQueueArray[queueNumber], queueNumber);

		}


	}

	public void jobHelper(Queue<Job> jobQueue, int queueNumber){
		Job currentJob = jobQueue.poll();
		if(queuesEmpty()) {
			while(jobQueue.isEmpty() && currentJob.getServiceTime() != 0) {
				processJob(currentJob);
			}
		}
		else{
			processJob(currentJob);
		}
		if(currentJob.getServiceTime() != 0 && queueNumber != 2) {
			jobQueueArray[queueNumber + 1].add(currentJob);
		}

		if(currentJob.getServiceTime() != 0 && queueNumber == 2){
			jobQueueArray[2].add(currentJob);
		}
	}

	public boolean queuesEmpty(){
		return (jobQueueArray[0].size() == 0 && jobQueueArray[1].size() == 0 && jobQueueArray[2].size() == 0);
	}

	public void addJobsToQueue(Queue<Job> jobQueue){
		for(Job job : jobs){
			if(job.getArrivalTime() == timeClock){
				//System.out.println("Adding job: " + job.getJobID() + " at time: " + timeClock);
				jobQueue.add(job);
			}
		}
	}



	 // Q1 [    ]
	 // Q2 [    ]
	 // Q3 [    ]

	//Q1 always takes priority, so we check at the beginning of each quanta--if it has anything in it, we run queue1.
	//else, we check the next highest priority, Q2, if Q2 size is > 0, then we run it, if not, then we run queue 2.
	public int jobQueuePriorityCheck(){
		if(jobQueueArray[0].size() > 0){
			return 0;
		}else if(jobQueueArray[1].size() > 0){
			return 1;
		}else{
			return 2;
		}
	}

	public void processJob(Job job){

		for( int i = 0; i < timeQuanta; i++) {
			job.decrementServiceTime();
			printJob(job.getJobID());

			if (job.getServiceTime() == 0) {
				numberOfJobs--;
			}
			timeClock++;
			addJobsToQueue(jobQueueArray[0]);
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
}
