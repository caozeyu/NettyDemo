package NIO.testbuffer;
// $Id$

import java.nio.ByteBuffer;
/**
 * 获取子缓冲区
 * @author ACER
 *
 */
public class Buffer03 {
  static public void main( String args[] ) throws Exception {
   
	ByteBuffer buffer = ByteBuffer.allocate( 10 );
    for (int i=0; i<buffer.capacity(); ++i) {
         buffer.put( (byte)i );
    }
    buffer.position( 3 );
    buffer.limit( 7 );



    //获取  子缓冲区
    ByteBuffer slice = buffer.slice();

    for (int i=0; i<slice.capacity(); ++i) {
      byte b = slice.get( i );
      b *= 11;
      slice.put( i, b );
    }
//    buffer.position( 0 );
//    buffer.limit( buffer.capacity() );
//    buffer.clear();
    while (buffer.remaining()>0) {
      System.out.println( buffer.get() );
    }
  }
}
