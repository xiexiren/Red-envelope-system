package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    /**
     * Socket服务端= new Bao(0,0)
     */
	public static  Bao bao = new Bao(10,5);
//	public  static  Bao usebao = new Bao(5,3) ;
    public  static void main(String[] args) {
        try {
        	
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("服务端已启动，等待客户端连接..");

            while (true) {
                Socket socket = serverSocket.accept();// 侦听并接受到此套接字的连接,返回一个Socket对象
                SocketThread socketThread = new SocketThread(socket,bao,bao);
                socketThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}