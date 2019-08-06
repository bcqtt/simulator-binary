package com.eg.egsc.scp.simulator.handler;

import com.eg.egsc.scp.simulator.client.HeartbeatTask;
import com.eg.egsc.scp.simulator.codec.ProtocolBody;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eg.egsc.scp.simulator.common.MsgBytes;
import com.eg.egsc.scp.simulator.util.ByteUtils;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BinaryClientHandler extends ChannelHandlerAdapter {

	private static final Log log = LogFactory.getLog(BinaryClientHandler.class);

	private volatile ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("客户端(" + ctx.channel().localAddress() + ")启动连接");
		ctx.writeAndFlush(MsgBytes.DEVICE_CODE_UPLOAD);
		log.info("客户端发出[上报机器编号]：消息长度:" + MsgBytes.DEVICE_CODE_UPLOAD.length + ",内容：" + ByteUtils.toHexString(MsgBytes.DEVICE_CODE_UPLOAD));


		executor.scheduleAtFixedRate(new HeartbeatTask(ctx), 0, 15, TimeUnit.SECONDS);


//			ctx.writeAndFlush(MsgBytes.DEVICE_STATUS_UPLOAD);
//			log.info("客户端发出[设备状态]：消息长度:" + MsgBytes.DEVICE_STATUS_UPLOAD.length + ",内容：" + ByteUtils.toHexString(MsgBytes.DEVICE_STATUS_UPLOAD));
//			Thread.sleep(3000);

//			ctx.writeAndFlush(MsgBytes.DEVICE_TEMP_CONFIG_RESULT);
//			log.info("客户端发出[响应温度设置结果]：消息长度:" + MsgBytes.DEVICE_TEMP_CONFIG_RESULT.length + ",内容：" + ByteUtils.toHexString(MsgBytes.DEVICE_TEMP_CONFIG_RESULT));
//			Thread.sleep(3000);

//			ctx.writeAndFlush(MsgBytes.DEVICE_LIGHT_CONFIG_RESULT);
//			log.info("客户端发出[响应温度设置结果]：消息长度:" + MsgBytes.DEVICE_LIGHT_CONFIG_RESULT.length + ",内容：" + ByteUtils.toHexString(MsgBytes.DEVICE_LIGHT_CONFIG_RESULT));
//			Thread.sleep(3000);

//			ctx.writeAndFlush(MsgBytes.DEVICE_GOODS_OUT_RESULT);
//			log.info("客户端发出[出货结果]：消息长度:" + MsgBytes.DEVICE_GOODS_OUT_RESULT.length + ",内容：" + ByteUtils.toHexString(MsgBytes.DEVICE_GOODS_OUT_RESULT));
//			Thread.sleep(3000);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ProtocolBody body = (ProtocolBody)msg;
		log.info("收到响应贞：" + body.toString());

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
