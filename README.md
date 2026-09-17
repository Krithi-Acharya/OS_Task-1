Producer–Consumer Program

This Java program implements the Producer–Consumer problem using two threads: a Producer and a Consumer. The Producer generates numbers from 1 to 8 and places them into a shared buffer called Box, while the Consumer takes and consumes those numbers. The Box can store only one item at a time. If the Box is full, the Producer waits, and if the Box is empty, the Consumer waits. The program uses the Thread class, synchronized, wait(), and notifyAll() methods to coordinate the threads and prevent race conditions.

Matrix Multiplication Program

This Java program performs multiplication of two 100 × 100 matrices using multithreading. It uses ExecutorService with a fixed thread pool of 10 worker threads. The multiplication work is divided into smaller tasks, and each task calculates a partial product for an element of the result matrix. The tasks are submitted using executor.submit(), and the program uses Runnable, shutdown(), and awaitTermination() to manage task execution. The input matrices are filled with random numbers, and the final result matrix is displayed in the console
