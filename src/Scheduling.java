import java.util.*;
import java.io.*;

public class Scheduling {
    //will be using this for arrival

    public static void main(String [] args) throws IOException {

        String fileLocation = null;
        String schedulingChoice = null;

        if(args.length == 2){
            fileLocation = args[0];
            schedulingChoice = args[1];

        }else{
            throw(new IllegalArgumentException());
        }


        //Prepare for parsing
        BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
        List<Job> jobList = new ArrayList<>();
        String currentLine;


        //Parse tab delimited information into Job objects and put them into jobsList
        while( (currentLine = reader.readLine()) != null){
            String [] scheduleInput = currentLine.split("\\t");

            char jobId = scheduleInput[0].charAt(0);
            int arrivalTime = Integer.parseInt(scheduleInput[1]);
            int serviceTime = Integer.parseInt(scheduleInput[2]);

            Job temp = new Job(jobId, arrivalTime, serviceTime);

            //System.out.println(temp.toString());
            jobList.add(temp);
        }
        //Before submitting jobs, need to organize by arrival time
        jobList.sort(Comparator.comparing(s -> s.getJobID()));

        switch(schedulingChoice){
            case "RR":
                roundRobin(new ArrayList<>(jobList));
                break;
            case "SRT":
                shortestRemainingTime(new ArrayList<>(jobList));
                break;
            case "FB":
                feedback(new ArrayList<>(jobList));
                break;

            case "ALL":
                roundRobin(jobList);
                shortestRemainingTime(jobList);
                feedback(jobList);
                break;
            default:
                System.out.println("Issue with input");
        }
    }

    private static void roundRobin(List<Job> jobList){
        System.out.println("Round Robin");
        for(int j = 'A'; j <= 'Z'; j++ ){
            System.out.print((char)j);
            System.out.print("\t");
        }
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        RoundRobinScheduler roundRobin = new RoundRobinScheduler(1, jobList);
        roundRobin.runJobs();

    }

    private static void shortestRemainingTime(List<Job> jobList){
        System.out.println("Shortest Remaining Time");
        for(int j = 'A'; j <= 'Z'; j++ ){
            System.out.print((char)j);
            System.out.print("\t");
        }
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        ShortestRemainingTimeScheduler srt = new ShortestRemainingTimeScheduler(jobList);
        srt.runJobs();

    }

    private static void feedback(List<Job> jobList){
        System.out.println("Feedback Scheduler with 3 queues");
        for(int j = 'A'; j <= 'Z'; j++ ){
            System.out.print((char)j);
            System.out.print("\t");
        }
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        FeedBackScheduler fb = new FeedBackScheduler(1, jobList);
        fb.runJobs();

    }
}
