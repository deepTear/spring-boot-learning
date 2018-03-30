package com.deep.base;

import java.io.IOException;
import java.net.URI;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.transport.Transport;
import org.apache.activemq.transport.TransportFactory;
import org.apache.activemq.transport.TransportListener;
import org.apache.activemq.transport.failover.FailoverTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.EnableJms;

import com.deep.activemq.ActiveMQListener;


@EnableJms
@Configuration
public class ActivemqConfig {

	@Autowired
	Environment env;
	
	@Bean
	public ActiveMQConnectionFactory activeMQFactory(){
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(env.getProperty("spring.activemq.broker-url"));
		factory.setTransportListener(new ActiveMQListener());
		return factory;
	}
	
	/*@Bean
	protected Transport createTransport() throws Exception {
	   Transport transport = TransportFactory.connect(new URI("failover://(tcp://localhost:1234?transport.connectTimeout=10000)"));
	   transport.setTransportListener(new ActiveMQListener());
	   transport.start();
	   return transport;
	}*/

}
