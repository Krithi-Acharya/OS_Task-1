package matrixmul;	
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
static class MatrixThread extends Thread{
	int rows,cols;
	MatrixThread(int rows,int cols){
		this.rows=rows;
		this.cols=cols;
	}
	public void run() {
		for(int n=0;n<100;n++) {
			resultmat[rows][cols] =resultmat[rows][cols] + mat1[rows][n] * mat2[n][cols];
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
    MatrixThread[][] threads = new MatrixThread[100][100];
    for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 100; j++) {
            threads[i][j] = new MatrixThread(i, j);
            threads[i][j].start();
        }
    }
for(int i=0;i<100;i++) {
	for(int j=0;j<100;j++) {
		try {
			threads[i][j].join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
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