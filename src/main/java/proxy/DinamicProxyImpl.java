package proxy;

public class DinamicProxyImpl implements DinamicProxy {
    @Override
    public void dinamicProxy() {
        System.out.println("Invoked");
        dinamicProxyNew();
    }

    @Override
    public void dinamicProxyNew() {
        System.out.println("Invoked1");
    }

    public void dinamicProxyNewNew() {
        System.out.println("Invoked150");
    }
}
