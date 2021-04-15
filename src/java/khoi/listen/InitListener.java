/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoi.listen;

import java.io.BufferedReader;
import java.io.FileReader;
import static java.rmi.server.LogStream.log;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Nguyen Khoi
 */
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        try {
            FileReader fr = new FileReader(sce.getServletContext().getRealPath("/") + "/WEB-INF/data.txt");
            BufferedReader br = new BufferedReader(fr);
            String str;
            while ((str = br.readLine()) != null) {
                String[] data = str.split("=");
                ctx.setAttribute(data[0], data[1]);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
           log(e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
