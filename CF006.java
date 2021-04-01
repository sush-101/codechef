/*One fine day, when everything was going good, Ankit was fired from Ustav Fest and had to leave all the work. So, he decided to become a member of gangster squad and start his new career of robbing.
Being a novice, Ankit was asked to perform a robbery task in which he was given a bag having a capacity W units. So, when he reached the College to be robbed, there was N items each having particular weight and particular profit associated with it. But, there a twist associated, He has first 10 primes with him, which he can use atmost once, if he picks any item x, then he can multiply his profit[x] with any of the first 10 primes and then put that item into his bag.
Each prime can only be used with one particular item and one item can only have atmost one prime multiplied with its profit. Its not necessary to pick all the items. If he doesn’t want to use a prime with any particular item, he can simply add the profit as it is, more specifically, 1*profit[x] for xth item will get added to its total profit, and that he can do with as many items as he wants. He cannot fill his bag more than weight W units. Each item should be picked with its whole weight, i.e it cannot be broken into several other items of lesser weight. So, now to impress his squad, he wishes to maximize the total profit he can achieve by robbing this wealthy College.
Input:
First line of input contain the single integer T representing the Test Cases.
Next Line of input containts two integers N and W. Next N lines contain information of ith item, describing profit and weight of the ith item, profit and weight are separated by single space.
Output:
Output T lines. Each of these lines should contain the maximum profit obtainable.
Constraints:
1 <= T <= 10
1 <= N <= 2000
1 <= W <= 2000
1 <= Profit[i] <= 10^9
1 <= Weight[i] <= 2000
Sample Input:
1
3 4
1 1
2 1
3 1
Sample Output:-
152
Explation::
As all, the items can be picked, so maximum profit is equal to = 1 X 19 + 2 X 23 + 3 X 29 = 152*/
import java.util.*;
import java.util.stream.*;
class Item{
	int val,wt;
    Item(int val,int wt){
		this.val = val;
        this.wt = wt;
    }
     @Override
    public String toString(){
		return this.wt +" "+ this.val;
    }
}

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		if(!sc.hasNext())return;
		int T = sc.nextInt();
		while(T--!=0){
        int n = sc.nextInt(),w = sc.nextInt(),prime[] = {29,23,19,17,13,11,7,5,3,2};
        long res=0;                                      
        Item arr[] = new Item[n];
		for(int i=0;i<n;i++)arr[i] = new Item(sc.nextInt(),sc.nextInt());
        Arrays.sort(arr,(o1,o2)-> {if(o1.val == o2.val)return o1.wt - o2.wt;
           						   return o2.val - o1.val;  });
        long ans = findPer(n,arr,w,prime);
        System.out.println(ans);
		}
	}
	static long findPer(int n,Item[] arr,int w,int prime[]){
		long dp[][] = new long[w+1][10];
       // for(long x[]:dp)Arrays.fill(x,0);
        long ans = 0;
        int item_no=0;
        while(item_no<n){
			long ndp[][] = new long[w+1][10];
            for(int i=0;i<=w;i++){
				for(int j=0;j<=9;j++){
                    int curwt = arr[item_no].wt, curval = arr[item_no].val; 
					if(curwt <= i){
                        long a = prime[j]*curval + ( j > 0 ? dp[i - curwt][j-1] : 0);
                        long b = 1*curval + dp[i - curwt][j];
						ndp[i][j] = Math.max(a,b);
                    }
                    ndp[i][j] = Math.max(dp[i][j],ndp[i][j]);
                }
                //an extra line
            }
            item_no++;
            //System.out.println("ndp");
            //for(long l[]:ndp){if(l[0]!=0)System.out.println(Arrays.toString(l));}
            dp = ndp;
        }
        //System.out.println(Arrays.toString(dp[w]));
        return Collections.max(Arrays.asList(Arrays.stream(dp[w]).boxed().toArray(Long[]::new)));
        //return dp[w][9];
    }
}
