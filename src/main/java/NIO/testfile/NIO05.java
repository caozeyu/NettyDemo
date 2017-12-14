package NIO.testfile;
// $Id$

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
//多次执行该main函数查看效果
public class NIO05 {
  static private final int start = 10;
  static private final int end = 20;

  static public void main( String args[] ) throws Exception {
    // Get file channel
    RandomAccessFile raf = new RandomAccessFile( "usefilelocks.txt", "rw" );
    FileChannel fc = raf.getChannel();

    // Get lock
    System.out.println( "trying to get lock" );
    FileLock lock = fc.lock( start, end, false );
    System.out.println( "got lock!" );

    // Pause
    System.out.println( "pausing" );
    try { Thread.sleep( 30000 ); } catch( InterruptedException ie ) {
    	ie.printStackTrace();
    }

    // Release lock
    System.out.println( "going to release lock" );
    lock.release();
    System.out.println( "released lock" );
    raf.close();
  }
}
