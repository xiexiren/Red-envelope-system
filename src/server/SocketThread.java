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
 * Socket多线程处理类 用来处理服务端接收到的客户端请求（处理Socket对象）
 */
public class SocketThread extends Thread {
    private Socket socket;
    private DataInputStream dis;
    public Bao bao;
    private int flag = 0;
    private UserInfo user;//用户信息对象
    private boolean i = false;
    public  static Bao usebao ;
    public SocketThread(Socket socket,Bao bao,Bao usebao) {
        this.socket = socket;
        this.bao=bao;
//        this.usebao = usebao;
    }
    
    public void run() {
    	
        // 根据输入输出流和客户端连接
        try {
        
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();// 获取一个输出流，向服务端发送信息
            PrintWriter printWriter = new PrintWriter(outputStream);// 将输出流包装成打印流
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            // 得到一个输入流，接收客户端传递的信息
           // dis = new DataInputStream(inputStream);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);// 提高效率，将自己字节流转为字符流
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);// 加入缓冲区
	            DataInputStream dis = new DataInputStream(inputStream);
	            String datas = dis.readUTF();
	    		String[] dataArray = datas.split(" ");
	    		System.out.println("你输入的用户名为：" + dataArray[0]);
	    		System.out.println("你输入的用户名为：" + dataArray[1]);
	    		String userName = dataArray[0];
	    		String pwd = dataArray[1];
	    		
	           // String userName=bufferedReader.readLine();
	           
	           // String pwd=bufferedReader.readLine();
	            
	            user=new UserInfo();
	    		user.setName(userName);
	    		user.setPassword(pwd);
	    		boolean loginState=DaoTools.checkLogin(user);
    		if(!loginState) {
    			//如果不存在这个账号则关闭
    			String n = "登入失败";
    			printWriter.print(n);
    			printWriter.flush();
//    			outputStream.write(n);
//    			System.out.println("hhhhhhhhh");
    			socket.close();
    			return;
    		}else {
//    			System.out.println("hhkkkkkkh");
    			printWriter.print("登入成功\n");
    			printWriter.print("1.抢红包\n");
    			printWriter.print("2.发红包or抢用户红包\n");
    			printWriter.print("请输入：\n");
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
		                System.out.println("已接收到客户端连接");
		                System.out.println("服务端接收到客户端信息：" + info + ",当前客户端ip为："
		                        + socket.getInetAddress().getHostAddress());
		               
		                printWriter.print("你好你抢到了"+" "+i+" "+"元");
		                printWriter.flush();
		                flag++;
		            	}
		            	else {
		            		System.out.println("已接收到客户端连接");
		                    System.out.println("服务端接收到客户端信息：" + info + ",当前客户端ip为："
		                            + socket.getInetAddress().getHostAddress());
		                    printWriter.print("你抢过了");
		                    printWriter.flush();
		            	}
//		            }
            	}
            	else if(te==2) {
            		printWriter.print("1.create\n2.抢");
            		printWriter.flush();
            		int te1 = 0;
            		if((te1 =new Integer( bufferedReader.readLine()))==1) {
            			printWriter.print("输入红包大小：");
            		printWriter.flush();
            		double t = new Double(bufferedReader.readLine());
            		printWriter.print("输入红包个数：");
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
    		                System.out.println("已接收到客户端连接");
    		                System.out.println("服务端接收到客户端信息：" + info + ",当前客户端ip为："
    		                        + socket.getInetAddress().getHostAddress());
    		               
    		                printWriter.print("你好你抢到了"+" "+i+" "+"元");
    		                printWriter.flush();
    		                flag++;
    		            	}
    		            	else {
    		            		System.out.println("已接收到客户端连接");
    		                    System.out.println("服务端接收到客户端信息：" + info + ",当前客户端ip为："
    		                            + socket.getInetAddress().getHostAddress());
    		                    printWriter.print("你抢过了");
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
            		printWriter.print("输入错误指令\n请再次输入");
        			printWriter.flush();
            	}            	
            	printWriter.print("再次输入\n1.抢红包\n2.发红包or抢用户红包\n");
    			printWriter.flush();
            
            }

            
            
            //socket.shutdownOutput();// 关闭输出流

            // 关闭相对应的资源
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
    class toDo extends Thread  { //子线程
        
        private Socket socket;
        private Bao bao;
        public toDo(Socket socket,Bao bao) {
            this.socket = socket;
            this.bao = bao;
        }
        
                @Override
        public void run() {
         	try {
            System.out.println("线程" + Thread.currentThread().getId() + "工作---" );
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String temp = null;
            String info = "";
            int flags =0 ;
            printWriter.print("输入任意字符抢红包：");
            printWriter.flush();
            temp = bufferedReader.readLine();
            while(true){
            	if(temp!=null) {
	            		while ((temp = bufferedReader.readLine()) != null) {
		            	if(flags==0) {
		            	double i=bao.getRandomMoney();
		            	info="";
		                info += temp;
		                System.out.println("已接收到客户端连接");
		                System.out.println("服务端接收到客户端信息：" + info + ",当前客户端ip为："
		                        + socket.getInetAddress().getHostAddress());
		               
		                printWriter.print("你好你抢到了"+" "+i+" "+"元");
		                printWriter.flush();
		                flags++;
		            	}
		            	else {
		            		System.out.println("已接收到客户端连接");
		                    System.out.println("服务端接收到客户端信息：" + info + ",当前客户端ip为："
		                            + socket.getInetAddress().getHostAddress());
		                    printWriter.print("你抢过了");
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

