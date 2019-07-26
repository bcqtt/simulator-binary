package com.eg.egsc.scp.simulator.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eg.egsc.scp.simulator.common.MsgBytes;
import com.eg.egsc.scp.simulator.util.ByteUtils;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class BinaryClientHandler extends ChannelHandlerAdapter {

	private static final Log log = LogFactory.getLog(BinaryClientHandler.class);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("客户端(" + ctx.channel().localAddress() + ")启动连接");
		
		while(true) {
//			ctx.writeAndFlush(MsgBytes.DEVICE_CODE_UPLOAD);
//			log.info("客户端发出[上报机器编号]：消息长度:" + MsgBytes.DEVICE_CODE_UPLOAD.length + ",内容：" + ByteUtils.toHexString(MsgBytes.DEVICE_CODE_UPLOAD));
//			Thread.sleep(3000);
			
//			ctx.writeAndFlush(MsgBytes.HEARTBEAT);
//			log.info("客户端发出[心跳]：消息长度:" + MsgBytes.HEARTBEAT.length + ",内容：" + ByteUtils.toHexString(MsgBytes.HEARTBEAT));
//			Thread.sleep(3000);
			
//			ctx.writeAndFlush(MsgBytes.DEVICE_STATUS_UPLOAD);
//			log.info("客户端发出[设备状态]：消息长度:" + MsgBytes.DEVICE_STATUS_UPLOAD.length + ",内容：" + ByteUtils.toHexString(MsgBytes.DEVICE_STATUS_UPLOAD));
//			Thread.sleep(3000);
			
//			ctx.writeAndFlush(MsgBytes.DEVICE_TEMP_CONFIG_RESULT);
//			log.info("客户端发出[响应温度设置结果]：消息长度:" + MsgBytes.DEVICE_TEMP_CONFIG_RESULT.length + ",内容：" + ByteUtils.toHexString(MsgBytes.DEVICE_TEMP_CONFIG_RESULT));
//			Thread.sleep(3000);
			
//			ctx.writeAndFlush(MsgBytes.DEVICE_LIGHT_CONFIG_RESULT);
//			log.info("客户端发出[响应温度设置结果]：消息长度:" + MsgBytes.DEVICE_LIGHT_CONFIG_RESULT.length + ",内容：" + ByteUtils.toHexString(MsgBytes.DEVICE_LIGHT_CONFIG_RESULT));
//			Thread.sleep(3000);

			ctx.writeAndFlush(MsgBytes.DEVICE_GOODS_OUT_RESULT);
			log.info("客户端发出[出货结果]：消息长度:" + MsgBytes.DEVICE_GOODS_OUT_RESULT.length + ",内容：" + ByteUtils.toHexString(MsgBytes.DEVICE_GOODS_OUT_RESULT));
			Thread.sleep(3000);
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
