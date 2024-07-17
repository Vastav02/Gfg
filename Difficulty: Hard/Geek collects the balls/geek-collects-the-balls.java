//{ Driver Code Starts
import java.io.*;
import java.util.*;

class IntArray {
    public static int[] input(BufferedReader br, int n) throws IOException {
        String[] s = br.readLine().trim().split(" ");
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(s[i]);

        return a;
    }

    public static void print(int[] a) {
        for (int e : a) System.out.print(e + " ");
        System.out.println();
    }

    public static void print(ArrayList<Integer> a) {
        for (int e : a) System.out.print(e + " ");
        System.out.println();
    }
}

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t;
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {

            String[] nm = br.readLine().split(" "); // Read a single line and split it
            int n = Integer.parseInt(nm[0]);        // Parse the first integer
            int m = Integer.parseInt(nm[1]);

            int[] a = IntArray.input(br, n);

            int[] b = IntArray.input(br, m);

            Solution obj = new Solution();
            int res = obj.maxBalls(n, m, a, b);

            System.out.println(res);
        }
    }
}

// } Driver Code Ends



class Solution {
    // Function to recursively solve the problem and find the maximum number of balls
    public int solve(int i, int f, int n, int m, ArrayList<Integer> v1, ArrayList<Integer> v2, HashMap<Integer, Integer>[] mp, HashMap<Integer, Integer>[] np, int[][] dp) {
        // Base case: if all balls are distributed
        if (i >= n && f == 0)
            return 0;
        // Base case: if all boxes are distributed
        else if (i >= m && f == 1)
            return 0;

        // If the answer for the current subproblem is already computed, return it
        if (dp[i][f] != -1)
            return dp[i][f];

        int x = 0, k = 0;

        // Determine the current ball type based on the value of f
        if (f == 0)
            k = v1.get(i);
        else
            k = v2.get(i);

        // Calculate the score for the current ball type
        int s = (mp[0].getOrDefault(k, 0) + mp[1].getOrDefault(k, 0)) * k;

        // If there are at least 2 boxes with the current ball type
        if (mp[0].getOrDefault(k, 0) > 1 && mp[1].getOrDefault(k, 0) > 1) {
            // Calculate the score if we skip distributing the current box
            int g = (s - k) + solve(np[f ^ 1].getOrDefault(k, 0) + 1, f ^ 1, n, m, v1, v2, mp, np, dp);
            x = Math.max(x, g);

            // Calculate the score if we distribute the current box to the same type of
            // box and skip distributing the next box
            g = (s - (2 * k)) + solve(i + 1, f, n, m, v1, v2, mp, np, dp);
            x = Math.max(x, g);
        }
        // If there is at least 1 box with each type of box
        else if (mp[0].getOrDefault(k, 0) > 0 && mp[1].getOrDefault(k, 0) > 0) {
            // Calculate the score if we distribute the current box to the same type of
            // box and continue with the next box
            x = Math.max(x, (s - k) + solve(i + 1, f, n, m, v1, v2, mp, np, dp));

            // Calculate the score if we distribute the current box to the different
            // type of box and skip distributing the next box of the same type
            x = Math.max(x, (s - k) + solve(np[f ^ 1].getOrDefault(k, 0) + 1, f ^ 1, n, m, v1, v2, mp, np, dp));
        } else {
            // Calculate the score if we distribute the current box to any available box
            // of any type
            x = Math.max(x, s + solve(i + 1, f, n, m, v1, v2, mp, np, dp));
        }

        // Store the answer for the current subproblem
        dp[i][f] = x;
        return x;
    }

    // Function to find the maximum number of balls
    public int maxBalls(int n, int m, int [] a, int [] b) {
        HashMap<Integer, Integer>[] mp = new HashMap[2];
        HashMap<Integer, Integer>[] np = new HashMap[2];

        // Initialize the array of HashMaps
        for (int i = 0; i < 2; i++) {
            mp[i] = new HashMap<>();
            np[i] = new HashMap<>();
        }
        ArrayList<Integer> v1 = new ArrayList<>();
        ArrayList<Integer> v2 = new ArrayList<>();

        // Preprocess the input arrays to remove duplicates and store the indices of
        // each unique ball type
        for (int i = 0; i < n; i++) {
            int ballType = a[i];
            if (!mp[0].containsKey(ballType)) {
                np[0].put(ballType, v1.size());
                v1.add(a[i]);
            }
            mp[0].put(ballType, mp[0].getOrDefault(ballType, 0) + 1);
        }
        for (int i = 0; i < m; i++) {
            int ballType = b[i];
            if (!mp[1].containsKey(ballType)) {
                np[1].put(ballType, v2.size());
                v2.add(b[i]);
            }
            mp[1].put(ballType, mp[1].getOrDefault(ballType, 0) + 1);
        }

        // Find the maximum value of n and m
        int maxN = v1.size();
        int maxM = v2.size();

        // Initialize the dp array with -1
        int[][] dp = new int[Math.max(maxN, maxM) + 1][2];
        for (int i = 0; i <= Math.max(maxN, maxM); i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }

        // Find the maximum score by trying all possible starting boxes and ball types
        int ans = solve(0, 0, maxN, maxM, v1, v2, mp, np, dp);
        ans = Math.max(ans, solve(0, 1, maxN, maxM, v1, v2, mp, np, dp));

        return ans;
    }
}
