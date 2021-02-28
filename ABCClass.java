package multithreads.homework;


public class ABCClass {
    static class BearerLetter {
        boolean a = false;
        boolean b = true;
        boolean c = true;

        public void printA() {
            try {
                synchronized (this) {
                    if (a)
                        Thread.sleep(50);
                    for (int i = 0; i < 5; i++) {
                        System.out.print("A");
                        a = true;
                        b = false;
                        notifyAll();
                        while (a)
                            wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void printB() {
            try {
                synchronized (this) {
                    if (b)
                        Thread.sleep(100);
                    for (int i = 0; i < 5; i++) {
                        System.out.print("B");
                        b = true;
                        c = false;
                        notifyAll();
                        while (b)
                            wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void printC() {
            try {
                synchronized (this) {
                    if (c)
                        Thread.sleep(200);
                    for (int i = 0; i < 5; i++) {
                        System.out.print("C");
                        c = true;
                        a = false;
                        notifyAll();
                        while (c)
                            wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BearerLetter bearerLetter = new BearerLetter();
        new Thread(bearerLetter::printA).start();
        new Thread(bearerLetter::printB).start();
        new Thread(bearerLetter::printC).start();
    }
}