package pl.filipzeglen.jdbc.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ReqListener implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("Request destroyed");
    }

    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("Request initialized");
    }
}