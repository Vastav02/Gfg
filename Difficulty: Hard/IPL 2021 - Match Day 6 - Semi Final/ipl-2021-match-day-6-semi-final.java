//{ Driver Code Starts
//Initial Template for Java
import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());
		while (tc--> 0) {
			String s = br.readLine().trim();
			Solution sln = new Solution();
			String ans = sln.compress(s);
			System.out.println(ans);
		}
	}
}

// } Driver Code Ends


//User function Template for Java
class Solution {
    public List<Integer> formLPS(String s) {
        int n = s.length();
        List<Integer> v = new ArrayList<>(Collections.nCopies(n, 0));

        int i = 0;
        for (int j = 1; j < n; j++) {
            while (i != 0 && s.charAt(i) != s.charAt(j)) {
                i = v.get(i - 1);
            }

            if (s.charAt(i) == s.charAt(j)) {
                v.set(j, i + 1);
                i += 1;
            }
        }
        return v;
    }

    public String compress(String s) {
        int n = s.length();
        List<Integer> v = formLPS(s);

        StringBuilder st = new StringBuilder();
        int i = n;
        while (i > 0) {
            if (i % 2 == 1) {
                st.append(s.charAt(i - 1));
                i -= 1;
            } else {
                if (v.get(i - 1) >= i / 2) {
                    if (i % (2 * (i - v.get(i - 1))) == 0) {
                        st.append('*');
                        i /= 2;
                    } else {
                        st.append(s.charAt(i - 1));
                        i -= 1;
                    }
                } else {
                    st.append(s.charAt(i - 1));
                    i -= 1;
                }
            }
        }
        return st.reverse().toString();
    }
    
}


