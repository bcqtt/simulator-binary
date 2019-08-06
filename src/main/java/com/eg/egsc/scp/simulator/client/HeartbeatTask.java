package com.eg.egsc.scp.simulator.client;

import com.eg.egsc.scp.simulator.common.MsgBytes;
import com.eg.egsc.scp.simulator.util.ByteUtils;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeartbeatTask implements Runnable {
	
	private static final Logger log = LoggerFactory.getLogger(HeartbeatTask.class);

	private final ChannelHandlerContext ctx;
	
	public HeartbeatTask(final ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}
	
	@Override
	public void run() {
		ctx.writeAndFlush(MsgBytes.HEARTBEAT);
		log.info("客户端发出[心跳]：消息长度:" + MsgBytes.HEARTBEAT.length + ",内容：" + ByteUtils.toHexString(MsgBytes.HEARTBEAT));
	}

}
