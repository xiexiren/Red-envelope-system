package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    /**
     * Socket�����= new Bao(0,0)
     */
	public static  Bao bao = new Bao(10,5);
//	public  static  Bao usebao = new Bao(5,3) ;
    public  static void main(String[] args) {
        try {
        	
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("��������������ȴ��ͻ�������..");

            while (true) {
                Socket socket = serverSocket.accept();// ���������ܵ����׽��ֵ�����,����һ��Socket����
                SocketThread socketThread = new SocketThread(socket,bao,bao);
                socketThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}