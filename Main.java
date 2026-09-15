package PCprogs;

public class Main {

    public static void main(String[] args) {
    	    Box sharedBox = new Box();
        Producer producer = new Producer(sharedBox);
        Consumer consumer = new Consumer(sharedBox);
        	producer.start();
        	consumer.start();
    }
}
class Box {
    private int item;
    private  boolean availability;
    synchronized void put(int num) {
        while (availability) {
            try {
               System.out.println("Producer(waiting)...");
               wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        item = num;
        availability = true;
        System.out.println("Producer added: " + num);
        notifyAll();
    }
synchronized int take() {
	while(!availability) {
		try {
             System.out.println("Consumer(waiting)...");
             wait();
            }catch (InterruptedException e) {
             Thread.currentThread().interrupt();
             return -1;
            }
        }

        int value = item;
        availability = false;
        System.out.println("Consumer received: " + value);
        notifyAll();
        return value;
}
}
class Producer extends Thread {
    private Box sharedBox;
    Producer(Box box) {
    sharedBox = box;
}
public void run() {
	for (int number = 1; number <= 8; number++) {
		sharedBox.put(number);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
class Consumer extends Thread {
private Box sharedBox;
    Consumer(Box box) {
        sharedBox = box;
    }
    public void run() {
        for (int count = 1; count <= 8; count++) {
            sharedBox.take();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}