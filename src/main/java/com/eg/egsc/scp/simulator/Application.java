package com.eg.egsc.scp.simulator;

import com.eg.egsc.scp.simulator.client.BinaryClient;
import com.eg.egsc.scp.simulator.common.Constant;

public class Application {
	
	public static void main(String[] args) {
		try {
			new BinaryClient().connect(Constant.SERVER_PORT,Constant.SERVER_IP);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
