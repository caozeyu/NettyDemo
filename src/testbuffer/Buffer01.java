package testbuffer;

import java.nio.ByteBuffer;

public class Buffer01 {
    public static void main(String args[]){
        byte array[] = new byte[1024];
        //创建一个Byte缓冲数组
        ByteBuffer buffer = ByteBuffer.wrap(array);

        //ByteBuffer buffer1 = ByteBuffer.allocate(1024);
        buffer.put( (byte)'a' );
        buffer.put( (byte)'b' );
        buffer.put( (byte)'c' );

        System.out.println("position:-->"+ buffer.position());
        System.out.println("limit:-->"+ buffer.limit());
        System.out.println("capacity:-->"+ buffer.capacity());
        System.out.println("remaining:-->" + buffer.remaining()); // remaining 读取的是 position --> limit 值

        int i = 0;
       /* while (buffer.hasRemaining()){
            System.out.println("--"+ i +"buffer.get():-->"+ buffer.get());
            i++;
        }*/

        buffer.flip();

        System.out.println("------flip 之后-------");
        System.out.println("position:-->"+ buffer.position());
        System.out.println("limit:-->"+ buffer.limit());
        System.out.println("capacity:-->"+ buffer.capacity());
        System.out.println("remaining:-->" + buffer.remaining()); // remaining 读取的是 position --> limit 值

        while (buffer.hasRemaining()){
            System.out.println("--"+ i +"--buffer.get():-->"+ buffer.get());
            i++;
        }

        System.out.println("------get 之后-------");
        System.out.println("position:-->"+ buffer.position());
        System.out.println("limit:-->"+ buffer.limit());
        System.out.println("capacity:-->"+ buffer.capacity());
        System.out.println("remaining:-->" + buffer.remaining()); // remaining 读取的是 position --> limit 值

        buffer.clear();//位置初始化，并不清空原有值


        i = 0;
        System.out.println("------clear 之后-------");
        System.out.println("position:-->"+ buffer.position());
        System.out.println("limit:-->"+ buffer.limit());
        System.out.println("capacity:-->"+ buffer.capacity());
        System.out.println("remaining:-->" + buffer.remaining()); // remaining 读取的是 position --> limit 值

        buffer.put((byte)'d');

        buffer.flip();

        while (buffer.hasRemaining()){
            System.out.println("--"+ i +"--buffer.get():-->"+ buffer.get());
            i++;
        }


    }
}
