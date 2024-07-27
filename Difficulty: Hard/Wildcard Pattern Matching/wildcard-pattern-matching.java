//{ Driver Code Starts
import java.util.*;

class WildcardPattern
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		sc.nextLine();
		while(t>0)
		{
			String pat = sc.nextLine();
			String text = sc.nextLine();
			Solution g = new Solution();
			System.out.println(g.wildCard(pat,text));
			t--;
		}
	}
}
// } Driver Code Ends


class Solution
{
    int wildCard(String pattern, String str)
    {
        // Your code goes here
        int n = str.length();
        int m =pattern.length();
        int dp[][] = new int[n+1][m+1];
        //initialization
        dp[0][0] =1;
        //p=" " -> (p is empty)
        for(int i =1 ;i<n+1;i++){
            dp[i][0] = 0;
        }
        //s=" " -> (s is empty)
        for(int j =1;j<m+1;j++){
            if(pattern.charAt(j-1)=='*'){
                dp[0][j] = dp[0][j-1];
            }
        }
        //bottom up 
        for(int i =1;i<n+1;i++){
            for(int j =1;j<m+1;j++){
                if(str.charAt(i-1)==pattern.charAt(j-1)||pattern.charAt(j-1)=='?'){
                    dp[i][j] = dp[i-1][j-1];
                }else if(pattern.charAt(j-1)=='*'){
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }else{
                    dp[i][j]=0;
                }
            }
        }
        return dp[n][m];
    }
}
