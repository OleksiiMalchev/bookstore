package proxy;

public class ProxyMeEx extends ProxyMe{
    @Override
    public void someMethod(int i){
        System.out.println("Transaction started");
        super.someMethod(i);
        System.out.println("Transaction commited");
    }

    @Override
    public void someMethodNew(int i){
        System.out.println("Transaction started");
        super.someMethodNew(i);
        System.out.println("Transaction commited");
    }
}
