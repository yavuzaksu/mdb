package com.mdb.camel.main;

import org.apache.camel.main.Main;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 *  This is handy when testing camel integration features. No need of deplolying "dashboard-1.0-SNAPSHOT.war" to tomcat.
 *  For web application integration with camel, deploy the "dashboard-1.0-SNAPSHOT.war" to tomcat.
 *  
 */

public class CamelStandaloneMain {

	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.enableHangupSupport();

        new ClassPathXmlApplicationContext("camel-sonar.xml");

        System.out.println("Starting Camel... Use ctrl + c to terminate the JVM. Or press the STOP button in eclipse. \nStarted.");

        main.run();
		
	}

}
