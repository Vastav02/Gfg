//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0){
            String line1[] = in.readLine().trim().split("\\s+");
            int N = Integer.parseInt(line1[0]);
            int W = Integer.parseInt(line1[1]);
            String line2[] = in.readLine().trim().split("\\s+");
            int val[] = new int[N];
            for(int i = 0;i < N;i++)
                val[i] = Integer.parseInt(line2[i]);
            String line3[] = in.readLine().trim().split("\\s+");
            int wt[] = new int[N];
            for(int i = 0;i < N;i++)
                wt[i] = Integer.parseInt(line3[i]);
                
            Solution ob = new Solution();
            System.out.println(ob.knapSack(N, W, val, wt));
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution{
    static int knapSack(int N, int W, int val[], int wt[])
    {
        // code here
        int dp[][] = new int[N+1][W+1];
        for(int i =0 ;i<dp.length;i++){
             dp[i][0] = 0;
        }
        for(int  j = 0; j<dp[0].length;j++){
            dp[0][j] = 0;
        }
        for(int i =1;i<N+1;i++){
            for(int j =1;j<W+1;j++){
                int v= val[i-1]; // ith item val
                int w = wt[i-1]; // ith item wt
                if(w<=j){
                    int incprofit = v + dp[i][j-w];
                    int excprofit = dp[i-1][j];
                    dp[i][j] = Math.max(incprofit,excprofit);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[N][W];
    } 
}