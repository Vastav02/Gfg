//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;

class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String arr[] = read.readLine().trim().split(" ");

            Solution ob = new Solution();
            System.out.println(ob.longestCommonPrefix(arr));
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {
    public String longestCommonPrefix(String arr[]) {
       int ans=-1;
       
       String s=arr[0];
       String answer="";
       
       for(int i=1;i<=s.length();i++){
           String p=s.substring(0,i);
           boolean r=true;
           for(int j=0;j<arr.length;j++){
               if(!arr[j].startsWith(p)){
                    r=false;
                    break;
               }
           }
           if(r==false){
               break;
           }
           else{
               if(p.length()>ans){
                   ans=Math.max(ans,p.length());
                   answer=p;
                  
               }
           }
       }
       
       if(answer.equals(""))return ""+-1;
       
       return answer;
    }
}