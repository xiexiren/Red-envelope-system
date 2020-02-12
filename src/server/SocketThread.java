package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Socket���̴߳����� �����������˽��յ��Ŀͻ������󣨴���Socket����
 */
public class SocketThread extends Thread {
    private Socket socket;
    private DataInputStream dis;
    public Bao bao;
    private int flag = 0;
    private UserInfo user;//�û���Ϣ����
    private boolean i = false;
    public  static Bao usebao ;
    public SocketThread(Socket socket,Bao bao,Bao usebao) {
        this.socket = socket;
        this.bao=bao;
//        this.usebao = usebao;
    }
    
    public void run() {
    	
        // ��������������Ϳͻ�������
        try {
        
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();// ��ȡһ��������������˷�����Ϣ
            PrintWriter printWriter = new PrintWriter(outputStream);// ���������װ�ɴ�ӡ��
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            // �õ�һ�������������տͻ��˴��ݵ���Ϣ
           // dis = new DataInputStream(inputStream);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);// ���Ч�ʣ����Լ��ֽ���תΪ�ַ���
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);// ���뻺����
	            DataInputStream dis = new DataInputStream(inputStream);
	            String datas = dis.readUTF();
	    		String[] dataArray = datas.split(" ");
	    		System.out.println("��������û���Ϊ��" + dataArray[0]);
	    		System.out.println("��������û���Ϊ��" + dataArray[1]);
	    		String userName = dataArray[0];
	    		String pwd = dataArray[1];
	    		
	           // String userName=bufferedReader.readLine();
	           
	           // String pwd=bufferedReader.readLine();
	            
	            user=new UserInfo();
	    		user.setName(userName);
	    		user.setPassword(pwd);
	    		boolean loginState=DaoTools.checkLogin(user);
    		if(!loginState) {
    			//�������������˺���ر�
    			String n = "����ʧ��";
    			printWriter.print(n);
    			printWriter.flush();
//    			outputStream.write(n);
//    			System.out.println("hhhhhhhhh");
    			socket.close();
    			return;
    		}else {
//    			System.out.println("hhkkkkkkh");
    			printWriter.print("����ɹ�\n");
    			printWriter.print("1.�����\n");
    			printWriter.print("2.�����or���û����\n");
    			printWriter.print("�����룺\n");
    			printWriter.flush();
//    			socket.close();
    		}
            
            String temp = null;
            int te = 0;
            String info = "";
            while(true){
            	if((te =new Integer( bufferedReader.readLine()))==1) {
//	            		while (te != 0) {
		            	if(flag==0) {
		            	double i=bao.getRandomMoney();
		            	info="";
		                info += te+"";
		                System.out.println("�ѽ��յ��ͻ�������");
		                System.out.println("����˽��յ��ͻ�����Ϣ��" + info + ",��ǰ�ͻ���ipΪ��"
		                        + socket.getInetAddress().getHostAddress());
		               
		                printWriter.print("�����������"+" "+i+" "+"Ԫ");
		                printWriter.flush();
		                flag++;
		            	}
		            	else {
		            		System.out.println("�ѽ��յ��ͻ�������");
		                    System.out.println("����˽��յ��ͻ�����Ϣ��" + info + ",��ǰ�ͻ���ipΪ��"
		                            + socket.getInetAddress().getHostAddress());
		                    printWriter.print("��������");
		                    printWriter.flush();
		            	}
//		            }
            	}
            	else if(te==2) {
            		printWriter.print("1.create\n2.��");
            		printWriter.flush();
            		int te1 = 0;
            		if((te1 =new Integer( bufferedReader.readLine()))==1) {
            			printWriter.print("��������С��");
            		printWriter.flush();
            		double t = new Double(bufferedReader.readLine());
            		printWriter.print("������������");
            		printWriter.flush();
            		int c = new Integer(bufferedReader.readLine());
            		usebao = new Bao(t, c);
//            		System.out.println(usebao.getCount());
//            		System.out.println(usebao.getTotal());
//            		toDo sT = new toDo(socket,usebao);
//                    sT.start();
//                    sT.interrupt();
            		
            		}else if(te1 ==2){
            			while (te1 != 0) {
    		            	if(flag==0) {
    		            	double i=usebao.getRandomMoney();
    		            	info="";
    		                info += te+"";
    		                System.out.println("�ѽ��յ��ͻ�������");
    		                System.out.println("����˽��յ��ͻ�����Ϣ��" + info + ",��ǰ�ͻ���ipΪ��"
    		                        + socket.getInetAddress().getHostAddress());
    		               
    		                printWriter.print("�����������"+" "+i+" "+"Ԫ");
    		                printWriter.flush();
    		                flag++;
    		            	}
    		            	else {
    		            		System.out.println("�ѽ��յ��ͻ�������");
    		                    System.out.println("����˽��յ��ͻ�����Ϣ��" + info + ",��ǰ�ͻ���ipΪ��"
    		                            + socket.getInetAddress().getHostAddress());
    		                    printWriter.print("��������");
    		                    printWriter.flush();
    		            	}
    		            	
    		            	te1 =new Integer( bufferedReader.readLine());
    		            }
//                		toDo sT = new toDo(socket,usebao);
//                        sT.run();
//                        sT.interrupt();
            		}else {
            			printWriter.print("error");
                		printWriter.flush();
            		}
            		
////            		userbao(t,c);
            	}else {
            		printWriter.print("�������ָ��\n���ٴ�����");
        			printWriter.flush();
            	}            	
            	printWriter.print("�ٴ�����\n1.�����\n2.�����or���û����\n");
    			printWriter.flush();
            
            }

            
            
            //socket.shutdownOutput();// �ر������

            // �ر����Ӧ����Դ
            //bufferedReader.close();
            //inputStream.close();
            //printWriter.close();
            //outputStream.close();
            
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public static void userbao(double total, int count) {
//    	Bao usebao = new Bao(total, count);implements Runnable
//    	
//    }
    class toDo extends Thread  { //���߳�
        
        private Socket socket;
        private Bao bao;
        public toDo(Socket socket,Bao bao) {
            this.socket = socket;
            this.bao = bao;
        }
        
                @Override
        public void run() {
         	try {
            System.out.println("�߳�" + Thread.currentThread().getId() + "����---" );
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String temp = null;
            String info = "";
            int flags =0 ;
            printWriter.print("���������ַ��������");
            printWriter.flush();
            temp = bufferedReader.readLine();
            while(true){
            	if(temp!=null) {
	            		while ((temp = bufferedReader.readLine()) != null) {
		            	if(flags==0) {
		            	double i=bao.getRandomMoney();
		            	info="";
		                info += temp;
		                System.out.println("�ѽ��յ��ͻ�������");
		                System.out.println("����˽��յ��ͻ�����Ϣ��" + info + ",��ǰ�ͻ���ipΪ��"
		                        + socket.getInetAddress().getHostAddress());
		               
		                printWriter.print("�����������"+" "+i+" "+"Ԫ");
		                printWriter.flush();
		                flags++;
		            	}
		            	else {
		            		System.out.println("�ѽ��յ��ͻ�������");
		                    System.out.println("����˽��յ��ͻ�����Ϣ��" + info + ",��ǰ�ͻ���ipΪ��"
		                            + socket.getInetAddress().getHostAddress());
		                    printWriter.print("��������");
		                    printWriter.flush();
		            	}
		            }
            	}
//            	socket.close();
//            this.destroy();
            }
         	} catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

