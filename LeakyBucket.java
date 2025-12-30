import java.util.*;

public class LeakyBucket{
        public static void main(String args[]){
                Scanner sc=new Scanner(System.in);
                System.out.println("Enter the bucket capacity");
                int bucketcapacity=sc.nextInt();
                System.out.println("Enter the op rate:");
                int outputrate=sc.nextInt();
                System.out.println("Enter the number of packets:");
                int n=sc.nextInt();
                int[] packetSizes=new int[n];
                System.out.println("Enter the packet sizes:");
                for(int i=0;i<n;i++)
                        packetSizes[i]=sc.nextInt();
                int currentbucketsize=0;
                System.out.println("\n Packet Size \t\t Bucket Size \t\t Sent \t\t Remaining \t\t Status ");
                for(int packetsize:packetSizes){
                        if(currentbucketsize+packetsize<=bucketcapacity){
                                currentbucketsize+=packetsize;
                                 System.out.println("\n"+packetsize+" \t\t"+currentbucketsize+"\t \t"+Math.min(outputrate,currentbucketsize)+" \t\t"+Math.max(0,currentbucketsize-outputrate)+"\t\tAccepted ");
                        }
                        else{
                               System.out.println("\n"+packetsize+" \t\t"+currentbucketsize+"\t \t"+Math.min(outputrate,currentbucketsize)+" \t\t"+Math.max(0,currentbucketsize-outputrate)+"\t\t Dropped ");
                        }
                        currentbucketsize=Math.max(0,currentbucketsize-outputrate);
                }
                sc.close();
        }}
~
