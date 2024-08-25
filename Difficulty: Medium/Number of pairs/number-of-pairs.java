//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int g = 0; g < t; g++) {
            String[] str = (br.readLine()).trim().split(" ");
            int x[] = new int[str.length];
            for (int i = 0; i < str.length; i++) x[i] = Integer.parseInt(str[i]);
            str = (br.readLine()).trim().split(" ");
            int[] y = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                y[i] = Integer.parseInt(str[i]);
            }
            System.out.println(new Solution().countPairs(x, y, x.length, y.length));
        }
    }
}

// } Driver Code Ends


//Back-end complete function Template for Java
class Solution {
    // Function to count number of pairs such that x^y is greater than y^x.
    public long countPairs(int x[], int y[], int M, int N) {
        // Precompute count of numbers in y array.
        long[] count = new long[5];
        for (int i = 0; i < N; i++) {
            if (y[i] < 5) {
                count[y[i]]++;
            }
        }
        
        // Sort y array to use binary search later.
        Arrays.sort(y);
        
        long totalPairs = 0;
        
        // Loop through each element in x array.
        for (int i = 0; i < M; i++) {
            if (x[i] == 0) {
                continue; // 0 raised to any power is 0, so no valid pairs.
            }
            if (x[i] == 1) {
                totalPairs += count[0]; // only 1^0 > 0^1
                continue;
            }
            
            // Find the number of y[j] > x[i] using binary search.
            int pos = upperBound(y, N, x[i]);
            totalPairs += (N - pos); // All y[j] > x[i] are valid.
            
            // For x = 2, handle y[j] = 3 and y[j] = 4 specially.
            if (x[i] == 2) {
                totalPairs -= (count[3] + count[4]);
                totalPairs += count[0] + count[1];
            }
            
            // For x > 2, add the number of valid pairs.
            if (x[i] > 2) {
                totalPairs += count[0] + count[1];
                if (x[i] == 3) {
                    totalPairs += count[2];
                }
            }
        }
        
        return totalPairs;
    }

    // Function to find index of first number greater than x in sorted array y.
    private int upperBound(int y[], int N, int x) {
        int low = 0, high = N - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (y[mid] > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}