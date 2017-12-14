package NIO.testbuffer;
/**
 * 字节缓冲区,将一个字节转换为一个字节缓冲区
 */

import java.nio.ByteBuffer;

public class Buffer01
{
  static public void main( String args[] ) throws Exception {
    byte array[] = new byte[1024];//字节数组  长度1024
    ByteBuffer buffer = ByteBuffer.wrap( array );//创建一个字节缓存数组

    buffer.put( (byte)'a' );
    buffer.put( (byte)'b' );
    buffer.put( (byte)'c' );

    System.out.println(buffer.capacity());
    System.out.println(buffer.position());
    System.out.println(buffer.limit());
    System.out.println(buffer.remaining());
    buffer.flip();
    System.out.println("-------------");
    System.out.println(buffer.capacity());
    System.out.println(buffer.position());
    System.out.println(buffer.limit());
    System.out.println(buffer.remaining());
    System.out.println("~~~~~~~~~~~~~~~");
    while(buffer.hasRemaining()){
    	System.out.println(buffer.get());
    }
    System.out.println("##############");
    System.out.println(buffer.capacity());
    System.out.println(buffer.position());
    System.out.println(buffer.limit());
    System.out.println(buffer.remaining());
    
   // buffer.put((byte)'d');
    
    
    System.out.println("&&&&&&&&&&&&&&&&&");
    buffer.clear();
    buffer.put((byte)'d');
    buffer.flip();
    System.out.println(buffer.capacity());
    System.out.println(buffer.position());
    System.out.println(buffer.limit());
    System.out.println(buffer.remaining());
    while(buffer.hasRemaining()){
    	System.out.println(buffer.get());
    }
  }
}
