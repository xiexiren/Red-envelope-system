package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class TcpClient {
	
	public static void main(String[] args) throws IOException{
//		Socket sc= new Socket("127.0.0.1",8980);
		PrintStream out = null;
		InputStreamReader in = null;
            System.out.println("�ͻ�������...............");
            Socket sock = new Socket("127.0.0.1",8888);
            PrintStream ps = null;
    		BufferedReader br = null;
    		ps= new PrintStream(sock.getOutputStream());//�����׽���֮�佨����ϵ
    		br = new BufferedReader(new InputStreamReader(System.in));
    		
    		System.out.println("�������û�����");
    		String uname = br.readLine();
    		System.out.println("���������룺");
    		String upwd = br.readLine();
            
    		DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
    		dos.writeUTF(uname + " " + upwd);
    		dos.flush();
            // ����һ��д�߳�
            new Write(sock.getOutputStream()).start();
//            out = new PrintStream(sock.getOutputStream());
            // ����һ�����߳�
            new Reader(sock.getInputStream()).start();
//            in = new InputStreamReader(sock.getInputStream());
//            try {
//                // ������Telnet�������ķ�����Ϣ��ʾ�ڿ���̨��Ļ��
//                while (true) {
//                    // ��Telnet��������ȡ����
//                    int b = in.read();
//                    if (b == -1)  
//                        System.exit(0);
//                    // ��������ʾ�ڿ���̨��Ļ��
//                    System.out.print((char) b);
//                }
//            } catch (IOException exc) {
//                exc.printStackTrace();
//            }
            

//		OutputStream os = sc.getOutputStream();
//		
//		os.write("hello 6".getBytes());
//		InputStream ip = sc.getInputStream();
//		 
//		byte[] bytes = new byte[1024];
//		int len = ip.read(bytes);
//		System.out.println(new String(bytes,0,len));
//		sock.close();
	}
	public TcpClient() {
		// TODO Auto-generated constructor stub
	}
}
