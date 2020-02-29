package com.test.demo;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

/**
 * Hello world!
 *
 */
public class TomcatZhujieStarter {
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
		String webappDirLocation = "src/resources";
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);

		StandardContext ctx = (StandardContext) tomcat.addWebapp("/embeddedTomcat",
				new File(webappDirLocation).getAbsolutePath());

//declare an alternate location for your "WEB-INF/classes" dir:
		File additionWebInfClasses = new File("target/classes");
		WebResourceRoot resources = new StandardRoot(ctx);
		resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
		ctx.setResources(resources);

		tomcat.start();
		tomcat.getServer().await();
    	
    }
}