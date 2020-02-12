package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Write extends Thread{
	private PrintStream out ; 
	public Write() {
		// TODO Auto-generated constructor stub
		
	}
	public Write(OutputStream out) {
		this.out = new PrintStream(out);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
            // 包装控制台输入流
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            // 反复将控制台输入写到Telnet服务程序
            while (true)  
                out.println(in.readLine());
        } catch (IOException exc) {
            exc.printStackTrace();
        }
		
	}

}
