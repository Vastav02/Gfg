//{ Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int edge=sc.nextInt();
            int[][] adj = new int[edge][3];
            for(int i=0;i<edge;i++){
                adj[i][0]=sc.nextInt();
                adj[i][1]=sc.nextInt();
                adj[i][2]=sc.nextInt();
            }
            int src=sc.nextInt();
            int dst=sc.nextInt();
            int k=sc.nextInt();
            Solution obj = new Solution();
            int res = obj.CheapestFLight(n,adj,src,dst,k);
            System.out.println(res);
        }
    }
}

// } Driver Code Ends


class Solution {
      static class Edge{
        int src;
        int dst;
        int wt;
        public Edge(int s,int d ,int wt){
            this.src =s;
            this.dst=d;
            this.wt = wt;
        }
    }
    public static void create(int flights[][],ArrayList<Edge>graph[]){
        for(int i =0 ;i<graph.length;i++){
            graph[i] =new ArrayList<>();
        }
        for(int i =0 ;i<flights.length;i++){
           int src = flights[i][0];
           int dst = flights[i][1];
           int wt = flights[i][2]; 

           Edge e =new Edge(src, dst, wt);
           graph[src].add(e);
        }
    }
    static class Info{
        int v,cost,stop;
        public Info(int v ,int c , int s){
            this.v =v;
            this.cost =c;
            this.stop = s;
        }
    }
    
    public int CheapestFLight(int n,int flights[][],int src,int dst,int k) {
        // Code here
        ArrayList<Edge>graph[]=new ArrayList[n];
        create(flights, graph);  

        int dist[] = new int[n];
        for(int i = 0 ;i<n;i++){
            if(i!=src){
                dist[i] = Integer.MAX_VALUE;
            }
        }

        Queue<Info> q =new LinkedList<>();
        q.add(new Info(src, 0, 0));
        while(!q.isEmpty()){
            Info curr = q.remove();
            if(curr.stop>k){
                break;
            }
            for(int i =0 ;i<graph[curr.v].size();i++){
                Edge e =graph[curr.v].get(i);
                int u = e.src;
                int v =e.dst;
                int wt =e.wt;

                if(curr.cost+wt<dist[v] && curr.stop<=k){
                    dist[v] =curr.cost+wt;
                    q.add(new Info(v, dist[v], curr.stop+1));
                } 
            }
        }
        if(dist[dst]==Integer.MAX_VALUE){
            return -1;
        }else{
            return dist[dst];
        }
        
        
    }
}