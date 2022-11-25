package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
//        ProxyMeEx proxyMeEx = new ProxyMeEx();
//        proxyMeEx.someMethod(5);
//        proxyMeEx.someMethodNew(10);
        DinamicProxyImpl dinamicProxy = new DinamicProxyImpl();

        DinamicProxy newProxyInstance = (DinamicProxy) Proxy.newProxyInstance(DinamicProxy.class.getClassLoader(),
                new Class[]{DinamicProxy.class}, new Handlerr(new DinamicProxyImpl()));
//     newProxyInstance.dinamicProxy();
//     newProxyInstance.dinamicProxyNew();
        dinamicProxy.dinamicProxyNewNew();

    }
    static class Handlerr implements InvocationHandler{
        private DinamicProxy dinamicProxy;

        public Handlerr(DinamicProxy dinamicProxy) {
            this.dinamicProxy = dinamicProxy;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Transaction started");
            method.invoke(dinamicProxy,args);
            System.out.println("Transaction end");
            return null;
        }
    }
}
