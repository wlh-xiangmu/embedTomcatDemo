package com.test.demo;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

/**
 * Hello world!
 *
 */
public class TomcatStarter {
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
    	Tomcat tomcat = new Tomcat();//创建tomcat实例，用来启动tomcat


		tomcat.setPort(8080);//设置端口

    	Connector connector = new Connector();//设置协议，默认就是这个协议connector.setURIEncoding(“UTF-8”);//设置编码
    	connector.setURIEncoding("UTF-8");
    	connector.setPort(8080);
    	tomcat.getService().addConnector(connector);
    	Context context = tomcat.addContext("myapp", null);
    	tomcat.addServlet(context, "myServlet", new MyServlet());
    	context.addServletMappingDecoded("/myServlet", "myServlet");

    	String basePath = System.getProperty("user.dir")+File.separator;


    	tomcat.getHost().setAppBase(basePath+"");
    	tomcat.addWebapp("", "src/resources");

    	tomcat.start();//启动tomcat

    	tomcat.getServer().await();//维持tomcat服务，否则tomcat一启动就会关闭
    	
    }
}