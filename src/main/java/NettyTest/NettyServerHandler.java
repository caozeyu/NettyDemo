package NettyTest;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;
public class NettyServerHandler extends SimpleChannelHandler {
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		ChannelBuffer buffer = (ChannelBuffer) e.getMessage(); 		
		ChannelBuffer channelBuffer=ChannelBuffers.dynamicBuffer();
		channelBuffer.writeBytes("服务器说：".getBytes());
		channelBuffer.writeBytes(buffer);
		ChannelFuture future = e.getChannel().write(channelBuffer);
		future.addListener(ChannelFutureListener.CLOSE);
	}
	
}
