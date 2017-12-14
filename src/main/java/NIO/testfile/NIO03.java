package NIO.testfile;
// $Id$

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class NIO03
{
  static public void main( String args[] ) throws Exception {
   
   //1846
    String infile = "1.wmv";
    String outfile = "2.wmv";

    FileInputStream fin = new FileInputStream( infile );
    FileOutputStream fout = new FileOutputStream( outfile );

    FileChannel fcin = fin.getChannel();
    FileChannel fcout = fout.getChannel();
    
    
    ByteBuffer buffer = ByteBuffer.allocate( 1024 );
   
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
    System.out.println(System.currentTimeMillis()-current);
  }
}
