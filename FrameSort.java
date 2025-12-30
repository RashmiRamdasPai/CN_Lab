import java.util.*;

public class FrameSort{
        public static class Frame{
                int fnum;
                String content;
                Frame(int n,String s){
                        this.fnum=n;
                        this.content=s;
                }}
        public static void sorting(int n,Frame[] F){
                for(int i=0;i<n;i++){
                        for(int j=0;j<n-i-1;j++){
                                if(F[j].fnum>F[j+1].fnum){
                                        int a=F[j].fnum;
                                        int b=F[j+1].fnum;
                                        String s1=F[j].content;
                                        String s2=F[j+1].content;
                                        F[j].fnum=b;
                                        F[j+1].fnum=a;
                                        F[j].content=s2;
                                        F[j+1].content=s1;
                                        }}}}
        public static void main(String args[]){
                Scanner sc=new Scanner(System.in);
                System.out.println("Enter the number of frames");
                int n=sc.nextInt();
                Frame[] F=new Frame[n];
                System.out.println("Enter the frame details:");
                for(int i=0;i<n;i++){
                        System.out.println("Enter the frame number:");
                        int a=sc.nextInt();
                        System.out.println("Enter the content of the frame:");
                        String b=sc.next();
                        F[i]=new Frame(a,b);
                }
                  System.out.println("Before sorting:");
                for(int i=0;i<n;i++)
                        System.out.print(F[i].content+" ");
                List<Frame> framelist=new ArrayList<>(Arrays.asList(F));
                Collections.shuffle(framelist);
                F=framelist.toArray(new Frame[0]);

                sorting(n,F);
                System.out.println("After sorting:");
                for(int i=0;i<n;i++)
                        System.out.print(F[i].content+" ");
                sc.close();
        }}
~
~
