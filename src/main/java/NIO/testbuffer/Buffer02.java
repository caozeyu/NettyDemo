package NIO.testbuffer;
/**
 *直接分配内存缓冲区 
 */

import java.nio.ByteBuffer;

public class Buffer02
{
  static public void main( String args[] ) throws Exception {
    ByteBuffer buffer = ByteBuffer.allocate( 1024 );

    buffer.put( (byte)'a' );
    buffer.put( (byte)'b' );
    buffer.put( (byte)'c' );

    //buffer.flip();//使用之前必须flip

    System.out.println( (char)buffer.get() );
    System.out.println( (char)buffer.get() );
    System.out.println( (char)buffer.get() );
  }
}
