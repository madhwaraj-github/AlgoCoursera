
public class OrderCount {

	private static void orderCount(int N) {
		long sum = 0;
		for (int i = 0; i < N; i++)
		    for (int j = i+1; j < N; j++)
		        for (int k = i+1; k < N; k++)
		            sum++;
		System.out.println(N + ", " + sum);
	}
	
	private static void ex2limit(int N) {
		int sum = 0;
		int j = 0;
		for (int i = 0; i < N; i++) {
			int is = 0;
		    for (j = 1; j <= N*N; j = j*2) {
		        sum++;
		        is++;
		    }
		    System.out.print(is + ", ");
		}
		int l = (int)(Math.log10(N*N)/Math.log10(2)) + 1;
		int lsum = l * N;
		int order = N * (1 + (int)(Math.log10(N*N)/Math.log10(2)));
		System.out.println("N: " + N + ", j: " + j + ", sum: " + sum + ", l: " + l + ", lsum: " + lsum + ", order: " + order);
	}
	
	private static void limit(int N) {
		int sum = 0;
		int j = 0;
		for (int i = 1; i <= N; i = 4*i) {
			int is = 0;
		    for (j = 0; j < i; j++) {
		        sum++;
		        is++;
		    }
		    System.out.print(is + ", ");
		}
		int l = (int)(Math.log10(N)/Math.log10(4));
		int lsum = l * N;
		int order = N * ((int)(Math.log10(N)/Math.log10(4)));
		System.out.println("N: " + N + ", j: " + j + ", sum: " + sum + ", l: " + l + ", lsum: " + lsum + ", order: " + order);
	}

	public static void main(String[] args) {
		/*
		orderCount(1);
		orderCount(2);
		orderCount(4);
		orderCount(8);
		orderCount(16);
		orderCount(32);
		orderCount(64);
		orderCount(128);
		orderCount(256);
		orderCount(512);
		orderCount(1024);
		orderCount(2048);
		orderCount(4096);
		orderCount(8192);
		*/
		limit(1);
		limit(2);
		limit(3);
		limit(4);
		limit(5);
		limit(6);
		limit(7);
		limit(8);
		limit(9);
		limit(10);
		limit(11);
		limit(12);
		limit(13);
		limit(14);
		limit(15);
		limit(16);
	}

}
