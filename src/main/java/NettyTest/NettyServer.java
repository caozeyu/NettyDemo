package NettyTest;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;


public class NettyServer {
	private static ChannelGroup channelGroup=new DefaultChannelGroup("mygroup");
	public static void main(String[] args) {
		ChannelFactory factory=
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool());
		ServerBootstrap bootstrap=new ServerBootstrap(factory);
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				// TODO Auto-generated method stub
				return Channels.pipeline(new NettyServerHandler());
			}
		});
		
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		Channel channel = bootstrap.bind(new InetSocketAddress(8080));
		
		
		//关闭资源
		/*ChannelFuture future = channel.close();
		future.awaitUninterruptibly();
        factory.releaseExternalResources();*/
		//关闭服务器
		/*
		channelGroup.add(channel);
		ChannelGroupFuture future = channelGroup.close();
		future.awaitUninterruptibly();
        factory.releaseExternalResources();
        */
	}
}
