import java.util.*;
public class RED{
        public static class RandomEarlyDetection{
                private double minThreshold;
                private double maxThreshold;
                private double maxDropProb;
                private int queueSize,currentQueue;
                public RandomEarlyDetection(double min,double max,double prob,int size){
                        minThreshold=min;
                        maxThreshold=max;
                        maxDropProb=prob;
                        queueSize=size;
                        currentQueue=0;
                }


                public boolean enqueue(){
                        if(currentQueue>=queueSize){
                                System.out.println("Packet Dropped(Queue Full)");
                                return false;
                        }
                        double dropProb=calcDropProb();
                        if(dropProb>0 && shouldDrop(dropProb)){
                                System.out.println("Packet Dropped(RED)");
                                return false;
                        }
                        currentQueue++;
                        System.out.println("Packet enqueued.Current Queue size :"+currentQueue);
                        return true;
                }

                private double calcDropProb(){
                        if(currentQueue<minThreshold) return 0.0;
                        else if(currentQueue>=maxThreshold) return 1.0;
                        else
                                return maxDropProb*((currentQueue-minThreshold)/(maxThreshold-minThreshold));
                }

                private boolean shouldDrop(double prob){
                        Random random=new Random();
                        return random.nextDouble()<prob;
                }
        }

        public static void main(String args[]){
                Scanner sc=new Scanner(System.in);
                System.out.println("Enter minimum threshold:");
                double min=sc.nextDouble();
                System.out.println("Enter maximum threshold:");
                double max=sc.nextDouble();
                System.out.println("Enter the maximum drop probabilty(0-1)");
                double prob=sc.nextDouble();
                System.out.println("Enter the queue size:");
                int size=sc.nextInt();
                System.out.println("Enter the number of packets:");
                int n=sc.nextInt();
                RandomEarlyDetection red=new RandomEarlyDetection(min,max,prob,size);
                for(int i=0;i<n;i++)
                        red.enqueue();
                sc.close();
        }}
