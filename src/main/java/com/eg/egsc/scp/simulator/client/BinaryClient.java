package com.eg.egsc.scp.simulator.client;

import com.eg.egsc.scp.simulator.codec.ProtocolDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.eg.egsc.scp.simulator.codec.BinaryEncoder;
import com.eg.egsc.scp.simulator.common.Constant;
import com.eg.egsc.scp.simulator.handler.BinaryClientHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BinaryClient {

	private static final Log log = LogFactory.getLog(BinaryClient.class);

	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

	EventLoopGroup group = new NioEventLoopGroup();

	public void connect(int port, String host) throws Exception {

		// 配置客户端NIO线程组

		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast("decoder", new ProtocolDecoder(512, 2, 2, -4, 0));
							ch.pipeline().addLast("message104Encoder",new BinaryEncoder());
							ch.pipeline().addLast("message104ClientHandler",new BinaryClientHandler());
						}
					});
			// 发起异步连接操作
			ChannelFuture future = b.connect(new InetSocketAddress(host, port),
					new InetSocketAddress(Constant.CLIENT_IP, Constant.CLIENT_PORT)).sync();
			
			if (future.channel().isActive()) {
				log.info(String.format("TCP连接成功 :%s:%d --> %s:%d ", Constant.CLIENT_IP, Constant.CLIENT_PORT, host, port));
			} else {
				log.info(String.format("TCP连接失败 :%s:%d --> %s:%d ", Constant.CLIENT_IP, Constant.CLIENT_PORT, host, port));
			}
			
			// 当对应的channel关闭的时候，就会返回对应的channel。
			future.channel().closeFuture().sync(); //等待服务端链路关闭之后，main方法退出
			
		} finally {
			// 所有资源释放完成之后，清空资源，再次发起重连操作
			executor.execute(new Runnable() {
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(1);
						try {
							connect(Constant.SERVER_PORT, Constant.SERVER_IP);// 发起重连操作
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	public static void main(String[] args) throws Exception {
		new BinaryClient().connect(Constant.SERVER_PORT, Constant.SERVER_IP);
	}

}
