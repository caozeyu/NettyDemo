package NIO.testsocket;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;


public class TestNIOSocketServer {
	public static void main(String[] args) throws Exception {
		ServerSocketChannel socketChannel=ServerSocketChannel.open();
		socketChannel.configureBlocking(false);//设置非阻塞的通信
		socketChannel.bind(new InetSocketAddress("localhost", 8989));
		Selector selector=Selector.open();//创建选择器
		socketChannel.register(selector, SelectionKey.OP_ACCEPT);
		//轮询
		while(true){
			//查询是否有可用通道
			int n = selector.select();
			if (n == 0) { 
				continue;
			}
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			while(it.hasNext()){
				SelectionKey key = it.next();
				if (key.isAcceptable()){ 
					System.out.println("我在等待中。。。");
					ServerSocketChannel server = (ServerSocketChannel) key.channel(); 
					SocketChannel channel = server.accept(); 
					channel.configureBlocking(false); 
					channel.register(selector, SelectionKey.OP_READ);
				}
				if(key.isReadable()){//读取数据
					SocketChannel channel=(SocketChannel) key.channel();
					channel.configureBlocking(false);
					ByteBuffer buffer=ByteBuffer.allocate(5);
					int num=0;
					ByteArrayOutputStream dataReceive=new ByteArrayOutputStream();
					dataReceive.write("服务器响应:".getBytes("utf-8"));
					while((num= channel.read(buffer))!=-1){
						buffer.flip();
						if(num!=0){
							//byte[] bytes=buffer.array();
							for(int i=0;i<buffer.limit();i++)
							dataReceive.write(buffer.array()[i]);
						}
						buffer.clear();
					}
					channel.register(selector, SelectionKey.OP_WRITE,dataReceive);
				}
				if(key.isWritable()){
					SocketChannel channel=(SocketChannel) key.channel();
					ByteArrayOutputStream attachment = (ByteArrayOutputStream) key.attachment();
					byte[] bytes=attachment.toByteArray();
					ByteBuffer byteBuffer=ByteBuffer.allocate(bytes.length);
					byteBuffer.put(bytes);
					byteBuffer.flip();
					channel.write(byteBuffer);
					//取消注册时间类型
					key.cancel();
					channel.close();
				}
				it.remove();
			}
			
		}
	}
	
}
