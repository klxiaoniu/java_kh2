import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int ls1[] = {1, 3, 5, 7, 9};
        int ls2[] = {2, 4, 6, 8, 10};
        shell(ls1, ls2);
    }

    public static void shell(int a[], int b[]) {
        Thread thread1 = new Thread() {

            @Override
            public void run() {
                for (Integer i :
                        a) {
                    //synchronized (a)
                    //TODO 没学完多线程qwq 下次一定
                    System.out.println(i);


                }
            }
        };
        Thread thread2 = new Thread() {
            public volatile boolean running = true;
            public List<Integer> ls = List.of(2, 4, 6, 8, 10);

            @Override
            public void run() {
                for (Integer i :
                        b) {
                    if (running) {
                        System.out.println(i);
                    }
                }
            }
        };
        thread1.run();
        thread2.run();
    }
}