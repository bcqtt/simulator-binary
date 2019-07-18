package com.eg.egsc.scp.simulator;

import com.eg.egsc.scp.simulator.client.BinaryClient;
import com.eg.egsc.scp.simulator.common.Constant;

public class Application {
	
    public static final int CLIENT_PORT = 38080;
	
	public static void main(String[] args) {
		try {
			new BinaryClient().connect(Constant.SERVER_PORT,Constant.SERVER_IP);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
