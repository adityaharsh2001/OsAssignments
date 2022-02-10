import java.io.*;
import java.util.*;

class jobs_scheduling {
  public static void main(String[] args) {
   List<Job> jobs = Arrays.asList(new Job(3, 10, 20),new Job(1, 2, 50), new Job(6, 19, 100),new Job(2, 100, 200));
    System.out.println("Max profit = " + findMax(jobs));
  }
  
  private static int findMax(List<Job> jobs){
    
    if(jobs == null || jobs.isEmpty()){
      return 0;
    }

    int n = jobs.size();
    Collections.sort(jobs, new Comparator<Job>() {
      public int compare(Job j1, Job j2){
        return (int)(j1.end - j2.end);
      }
    });
    
    int[] profit = new int[n];
    profit[0] = jobs.get(0).profit;
    
    for(int i = 1; i < n; i++){
      int includingJobProfit = jobs.get(i).profit;
      int nonConflictingJobIndx = findNonConflictingJob(jobs, i);
      if(nonConflictingJobIndx >= 0){
        includingJobProfit += profit[nonConflictingJobIndx];
      }
      
      profit[i] = Math.max(includingJobProfit, profit[i-1]);
    }
    
    return profit[n-1];
  }
  
  private static int findNonConflictingJob(List<Job> jobs, int i){
    
    int lo = 0;
    int hi = i-1;
    int lastSuccMid = -1;
    
    while(lo <= hi){
      int mid = (lo + hi) >>> 1;
      if(jobs.get(mid).end <= jobs.get(i).start){
        lo = mid+1;
        lastSuccMid = mid;
      } else {
        hi = mid-1;
      }
    }
    return lastSuccMid;
  }
}
 class Job {
    final long start;
    final long end;
    final int profit;
    
    Job(long start, long end, int profit){
      this.start = start;
      this.end = end;
      this.profit = profit;
    }
  }