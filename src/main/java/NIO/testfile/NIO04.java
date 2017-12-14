package NIO.testfile;
// $Id$

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class NIO04
{
  static public void main( String args[] ) throws Exception {
   

	String infile = "1.wmv";
	String outfile = "3.wmv";

    FileInputStream fin = new FileInputStream( infile );
    FileOutputStream fout = new FileOutputStream( outfile );

    FileChannel fcin = fin.getChannel();
    FileChannel fcout = fout.getChannel();
    
    
    ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
    long current=System.currentTimeMillis();
    while (true) {
      buffer.clear();
      int r = fcin.read( buffer );
      if (r==-1) {
        break;
      }
      buffer.flip();
      fcout.write( buffer );
    }
    //ByteBuffer.allocate( 1024 );
    //726
    System.out.println(System.currentTimeMillis()-current);
  }
}
