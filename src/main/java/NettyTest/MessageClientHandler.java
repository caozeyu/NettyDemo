package NettyTest;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;


public class MessageClientHandler extends SimpleChannelHandler {

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		String message="我是客户端...发信息来了";
		ChannelBuffer channelBuffer=ChannelBuffers.dynamicBuffer();
		channelBuffer.writeBytes(message.getBytes());  
		e.getChannel().write(channelBuffer);
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("服务器有新的回复消息了...");
		ChannelBuffer buffer = (ChannelBuffer) e.getMessage(); 		
		System.out.println(buffer.toString(Charset.defaultCharset()));
		ChannelFuture future = e.getFuture();
		future.addListener(ChannelFutureListener.CLOSE);
	}
	
}
