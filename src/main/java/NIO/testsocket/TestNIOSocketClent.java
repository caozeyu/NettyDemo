package NIO.testsocket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;



public class TestNIOSocketClent {
	public static void main(String[] args) throws Exception {
		String str="Hello你好";
		ByteBuffer encode = encode(str, "utf-8");
		System.out.println(encode);
		String decode = decode(encode, "utf-8");
		System.out.println(decode);
		String sendMessage = sendMessage(decode);
		System.out.println(sendMessage);
	}
	public static String sendMessage(String message) throws IOException{
		SocketChannel socketChannel=SocketChannel.open(new InetSocketAddress("localhost",8989));
		//写消息
		ByteBuffer buffer=encode(message,"utf-8");
		socketChannel.write(buffer);
		//关闭输出流 必须指定输出流 不然服务器那边的读操作处于阻塞状态
		socketChannel.shutdownOutput();

		ByteBuffer byteBuffer=ByteBuffer.allocate(1024);//指定缓冲区
		byteBuffer.clear();
		int num=0;
		//读取消息
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		while((num=socketChannel.read(byteBuffer))!=-1){
			byteBuffer.flip();
			byteArrayOutputStream.write(byteBuffer.array(), 0, byteBuffer.limit());
			byteBuffer.clear();
		}
		socketChannel.close();
		return new String(byteArrayOutputStream.toByteArray());
	}
	
	public static ByteBuffer encode(String message,String encoding) throws IOException{
		Charset charset=Charset.forName(encoding);
		
		CharsetEncoder newEncoder = charset.newEncoder();
		CharBuffer charBuffer=CharBuffer.allocate(message.length());
		charBuffer.put(message.toCharArray());
		charBuffer.flip();
		ByteBuffer buffer = newEncoder.encode(charBuffer);
		return buffer;
	}
	public static String decode(ByteBuffer byteBuffer,String encoding) throws IOException{
		Charset charset=Charset.forName(encoding);
		CharsetDecoder newDecoder = charset.newDecoder();
        //等价charset。decoder（）；
		newDecoder.onMalformedInput(CodingErrorAction.REPLACE)
	       .onUnmappableCharacter(CodingErrorAction.REPLACE);
		CharBuffer decodeBytes = newDecoder.decode(byteBuffer);
		return new String(decodeBytes.array(),0,decodeBytes.limit());
	}
}
