package server;

import java.io.*;
//import java.io.InputStreamReader;

public class Reader extends Thread{
	private InputStreamReader in;

    public Reader(InputStream in) {
        this.in = new InputStreamReader(in);
    }
    public void run() {
        try {
            // ������Telnet�������ķ�����Ϣ��ʾ�ڿ���̨��Ļ��
            while (true) {
                // ��Telnet��������ȡ����
                int b = in.read();
                if (b == -1)  
                    System.exit(0);
                // ��������ʾ�ڿ���̨��Ļ��
                System.out.print((char) b);
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

	public Reader() {
		// TODO Auto-generated constructor stub
	}

}
