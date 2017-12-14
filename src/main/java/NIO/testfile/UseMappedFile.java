package NIO.testfile;
// $Id$

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class UseMappedFile
{
  static private final int start = 0;
  static private final int size = 1024;

  static public void main( String args[] ) throws Exception {
    RandomAccessFile raf = new RandomAccessFile( "usemappedfile.txt", "rw" );
    FileChannel fc = raf.getChannel();

    MappedByteBuffer mbb = fc.map( FileChannel.MapMode.READ_WRITE,
      start, size );
    mbb.put( 0, (byte)97 );
    mbb.put( 1, (byte)98 );
    mbb.put( 2, (byte)99 );
    raf.close();
    System.out.println("运行结束");
  }
}
