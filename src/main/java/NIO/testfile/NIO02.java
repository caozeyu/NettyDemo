package NIO.testfile;
// $Id$

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class NIO02
{
  static private final byte message[] ="write somebytes".getBytes();

  static public void main( String args[] ) throws Exception {
   
	FileOutputStream fout = new FileOutputStream( "writesomebytes.txt" );
    //获取一个写通道
    FileChannel fc = fout.getChannel();
    //创建一个换缓冲区
    ByteBuffer buffer = ByteBuffer.allocate( 1024 );

    for (int i=0; i<message.length; ++i) {
      buffer.put( message[i] );
    }

    buffer.flip();

    fc.write( buffer );

    fout.close();
  }
}
