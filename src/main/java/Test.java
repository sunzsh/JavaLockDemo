public class Test {

    int a = 0;
    int b = 0;


    public void add() {
        for (int i = 0; i < 50000; i++) {
            synchronized (this) {
                a++;
                b++;
            }
        }
    }
    public void compare() {
        for (int i = 0; i < 50000; i++) {
            int as, bs;
            synchronized (this) {
                as = a;
                bs = b;
            }

            if (as < bs) {
                System.out.println(as + ",  " + bs);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("test");
        Test test = new Test();
        new Thread(() -> test.add()).start();
        new Thread(() -> test.compare()).start();
    }

}
