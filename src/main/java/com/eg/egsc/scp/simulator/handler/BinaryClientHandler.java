package com.eg.egsc.scp.simulator.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eg.egsc.scp.simulator.util.ByteUtils;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class BinaryClientHandler extends ChannelHandlerAdapter {

	private static final Log log = LogFactory.getLog(BinaryClientHandler.class);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("客户端(" + ctx.channel().localAddress() + ")启动连接");
		byte[] data = {(byte)0x02,(byte)0x20,(byte)0x00,(byte)0x38,(byte)0x01,(byte)0x43,(byte)0x48,(byte)0x4A,(byte)0x2D,(byte)0x42,(byte)0x44,(byte)0x36,(byte)0x39,(byte)0x2D,(byte)0x32,(byte)0x31,(byte)0x35,(byte)0x2D,(byte)0x33,(byte)0x33,(byte)0x36,(byte)0x38,(byte)0x2D,(byte)0x32,(byte)0x30,(byte)0x31,(byte)0x39,(byte)0x30,(byte)0x33,(byte)0x30,(byte)0x36,(byte)0x2D,(byte)0x30,(byte)0x30,(byte)0x31,(byte)0x38,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x0D,(byte)0x0A};
		while(true) {
			ctx.writeAndFlush(data);
			log.info("客户端发出[启动连接消息]：消息长度:" + data.length + ",内容：" + ByteUtils.toHexString(data));
			Thread.sleep(5000);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		Protocol104Body msgbody = Protocol104Util.msg2Body(msg);
//		log.info("客户端收到服务端消息：" + msgbody.toString());
//		
//		if (Protocol104Util.isStartConnectConfirm(msgbody)) {
//			log.info("收到服务器启动连接确认。发出总召唤数据命令...");
//			Protocol104Body body = Protocol104Util.buildAllSummon();
//			ctx.writeAndFlush(body);
//			log.info("客户端发出[总召唤命令]：" + body.toString());
//		} else if(Protocol104Util.isAllSummonConfirm(msgbody)){
//			log.info("收到服务器总召唤确认...");
//		} else if(Protocol104Util.isTestConnect(msgbody)){
//			log.info("收到服务器测试连接...");
//			Protocol104Body body = Protocol104Util.buildTestConnectConfirm();
//			ctx.writeAndFlush(body);
//			log.info("客户端发出[测试连接确认]：" + body.toString());
//		}else {
//			ctx.fireChannelRead(msg);   //传给下一个Handler
//		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.fireExceptionCaught(cause);
	}
	
	@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		log.info("客户端关闭 ..." + ctx.channel().localAddress()); 
		super.close(ctx, promise);
	}

}
