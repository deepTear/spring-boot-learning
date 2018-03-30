package com.deep.activemq;

import java.io.IOException;

import org.apache.activemq.transport.TransportListener;

public class ActiveMQListener implements TransportListener{
	
	@Override
	public void onCommand(Object arg0) {
		System.out.println("--------------onCommand");
	}

	@Override
	public void onException(IOException arg0) {
		System.out.println("--------------异常");
	}

	@Override
	public void transportInterupted() {
		System.out.println("--------------传输中断");
	}

	@Override
	public void transportResumed() {
		System.out.println("--------------回复");
	}


}
