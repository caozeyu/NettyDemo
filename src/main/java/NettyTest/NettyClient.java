package NettyTest;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
/**
 * 客户端应用
 * @author ACER
 *
 */
public class NettyClient {
    public static void main(String[] args) {
        NioClientSocketChannelFactory factory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool());
        ClientBootstrap bootstrap = new ClientBootstrap(factory);
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                // TODO Auto-generated method stub
                return Channels.pipeline(new MessageClientHandler());
            }
        });

        ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8080));
        future.awaitUninterruptibly();
        System.out.println("----------------");
        if(!future.isSuccess()){
            future.getCause().printStackTrace();
        }

        System.out.println("即将资源释放了");
        future.getChannel().getCloseFuture().awaitUninterruptibly();
        factory.releaseExternalResources();
    }
}
