import java.util.*;

public class TokenBucket {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the token bucket capacity:");
        int tokenCapacity = sc.nextInt();

        System.out.println("Enter the token generation rate:");
        int tokenRate = sc.nextInt();
        System.out.println("Enter the output rate");
        int outputrate=sc.nextInt();

        System.out.println("Enter the number of packets:");
        int n = sc.nextInt();

        int[] packetsizes = new int[n];
        System.out.println("Enter the packet sizes:");
        for (int i = 0; i < n; i++)
            packetsizes[i] = sc.nextInt();

        int tokens = 0;

        System.out.println("\nTime\tPacket\tTokens Before\t Status\tTokens After");

        for (int t = 0; t < n; t++) {

            // Add tokens at each time unit
            tokens += tokenRate;
            if (tokens > tokenCapacity)
                tokens = tokenCapacity;

            int pkt = packetsizes[t];   // FIXED: use t, not i
            int tokensBefore = tokens;
            int tokensAfter;

            if (pkt <= tokens && pkt<=outputrate) {
                // Packet is transmitted
                tokens -= pkt;
                tokensAfter = tokens;
                System.out.println(t + "\t" + pkt + "\t\t" + tokensBefore + "\t\t"+"Accepted"+"\t\t" + tokensAfter);
            } else {
                // Packet is dropped
                tokensAfter = tokens;
                System.out.println(t + "\t" + pkt + "\t\t" + tokensBefore + "\t\t"+"Dropped"+"\t\t\t" + tokensAfter);
            }


        }

        sc.close();
    }
}
