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
            System.out.println("客户端启动...............");
            Socket sock = new Socket("127.0.0.1",8888);
            PrintStream ps = null;
    		BufferedReader br = null;
    		ps= new PrintStream(sock.getOutputStream());//流与套接字之间建立联系
    		br = new BufferedReader(new InputStreamReader(System.in));
    		
    		System.out.println("请输入用户名：");
    		String uname = br.readLine();
    		System.out.println("请输入密码：");
    		String upwd = br.readLine();
            
    		DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
    		dos.writeUTF(uname + " " + upwd);
    		dos.flush();
            // 创建一个写线程
            new Write(sock.getOutputStream()).start();
//            out = new PrintStream(sock.getOutputStream());
            // 创建一个读线程
            new Reader(sock.getInputStream()).start();
//            in = new InputStreamReader(sock.getInputStream());
//            try {
//                // 反复将Telnet服务程序的反馈信息显示在控制台屏幕上
//                while (true) {
//                    // 从Telnet服务程序读取数据
//                    int b = in.read();
//                    if (b == -1)  
//                        System.exit(0);
//                    // 将数据显示在控制台屏幕上
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
