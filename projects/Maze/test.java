package Maze;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a[][]=new int[13][13];
		aroundMatris(a, 10);
		show(a,10);
	}

	public static void aroundMatris(int a[][], int n) {
		int i = 1, j = 1, q = 0, c = 1;
		while (j < n - q)
			a[i][j++] = c;
		while (i < n - q)
			a[i++][j] = c;
		while (j > q + 1)
			a[i][j--] = c;
		while (i > q + 1)
			a[i--][j] = c;
		q++;
	}

	public static void show(int a[][], int b) {
		for (int i = 1; i <= b; i++) {
			for (int j = 1; j <= b; j++)
				System.out.print(a[i][j]+" ");
			System.out.println();
		}
	}
}
