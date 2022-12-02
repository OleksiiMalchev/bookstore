package proxy;

public class ProxyMe {
    public void someMethod(int i) {
        System.out.println("Invoked");
        someMethodNew(i);
    }

    public void someMethodNew(int i) {
        System.out.println("Invoked1");

    }
}
