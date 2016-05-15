package cn.rx.qrcode;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
* jetty服务启动
*/
public class WarStarter {
    public static final String BASE_URL = "http://localhost:8099";

    public static void main(String[] args) {
        Server server = new Server(8099);
        WebAppContext context = new WebAppContext("src/main/webapp", "");
//        context.setWar("/Users/xule/IdeaProjects/qr-service/src/main/webapp");
//        context.setContextPath("/src/main/webapp");
//        context.setParentLoaderPriority(true);
        server.setHandler(context);

        try {
            server.start();
            System.out.println("Server running at " + BASE_URL);
            //server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
