//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class GFG {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        while (test-- > 0) {
            int n = sc.nextInt(); // Take size of both the strings as input
            int m = sc.nextInt();

            String str1 = sc.next(); // Take both the string as input
            String str2 = sc.next();

            Solution obj = new Solution();

            System.out.println(obj.lcs(n, m, str1, str2));
        }
    }
}
// } Driver Code Ends


class Solution {
    // Function to find the length of longest common subsequence in two strings.
    static int lcs(int n, int m, String str1, String str2) {
        // your code here
        int dp[][] = new int [n+1][m+1];
        for(int i =0 ; i<n+1;i++){
            for(int j=0;j<m+1;j++){
                if(i==0||j==0){
                    dp[i][j]=0;
                }
            }
        }
        for(int i =1;i<n+1;i++){
            for(int j =1;j<m+1;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                else{
                    int ans1 = dp[i-1][j];
                    int ans2 = dp[i][j-1];
                    dp[i][j] = Math.max(ans1,ans2);
                }
            }
        }
        return dp[n][m];
    }
}