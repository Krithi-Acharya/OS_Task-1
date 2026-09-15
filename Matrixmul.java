package matrixmul;	
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class Matrixmul {
    static int[][] mat1 = new int[100][100];
    static int[][] mat2 = new int[100][100];
    static int[][] resultmat = new int[100][100];
    
    static void fillMatrix(int[][] matrix) {
    	for(int i=0;i<100;i++) {
    		for(int j=0;j<100;j++) {
    			matrix[i][j]=(int)(Math.random()*10);
    		}
    	}
}
static class MatrixThread implements Runnable{
	int rows,cols,n;
	MatrixThread(int rows,int cols,int n){
		this.rows=rows;
		this.cols=cols;
		this.n=n;
	}
	public void run() {
		for(int n=0;n<100;n++) {
			resultmat[rows][cols] += mat1[rows][n] * mat2[n][cols];
		}
	}
}
static class MultiplicationThread extends Thread {
    int a, b;
    int product;
    MultiplicationThread(int a, int b) {
        this.a = a;
        this.b = b;
    }
    public void run() {
        product = a * b;
    }
}
public static void main(String[] args) {
    fillMatrix(mat1);
    fillMatrix(mat2);
    ExecutorService executor = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 100; j++) {
            for (int n = 0; n < 100; n++) {
                executor.execute(new MatrixThread(i, j, n));
            }
        }
    }
    executor.shutdown();
    try {
        executor.awaitTermination(1, TimeUnit.HOURS);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    
System.out.println("Result Matrix:");

for (int i = 0; i < 5; i++) {
    for (int j = 0; j < 5; j++) {
        System.out.print(resultmat[i][j] + " ");
    }
    System.out.println();
}
}
}