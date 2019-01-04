package ank.hao.aio.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AIOServer {
    public static void main(String[] args) {

        new Thread(new AIOServerAcceptHandler()).start();
    }
}

class AIOServerAcceptHandler implements Runnable{

    CountDownLatch countDownLatch;
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    @Override
    public void run() {
        try {
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind(new InetSocketAddress(8812));
            countDownLatch = new CountDownLatch(1);

            asynchronousServerSocketChannel.accept(this, new CompletionHandler<AsynchronousSocketChannel, AIOServerAcceptHandler>() {
                @Override
                public void completed(AsynchronousSocketChannel result, AIOServerAcceptHandler attachment) {
                    attachment.asynchronousServerSocketChannel.accept(attachment, this);
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    result.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {

                        private AsynchronousSocketChannel channel;

                        {
                            channel = result;
                        }

                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {

                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {

                        }
                    });
                }

                @Override
                public void failed(Throwable exc, AIOServerAcceptHandler attachment) {
                    attachment.countDownLatch.countDown();
                }
            });

            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
