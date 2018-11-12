import java.util.Scanner;
import java.io.*;
import java.net.*;

public class Node_B {
	private int jump_value;
	public static void main(String as[]) throws Exception{
		Node_B ob=new Node_B();

		Scanner sc=new Scanner(System.in);
		System.out.print(" Enter the port: ");
		int port_number=sc.nextInt();

		ob.listenEncryptionKey(port_number);

	}

	public void listenEncryptionKey(int port_number) throws Exception {
		ServerSocket ss=new ServerSocket(port_number);
		Socket s=ss.accept();
		DataInputStream dis=new DataInputStream(s.getInputStream());
		jump_value=Integer.parseInt((String)dis.readUTF());
		ss.close();
		System.out.println(" Key Received.");
		
		acceptMessage(port_number);
	}

	public void acceptMessage(int port_number) throws Exception{
		ServerSocket ss=new ServerSocket(port_number);
		System.out.println(" ----Listening----");
		
		Socket s=ss.accept();
		System.out.println(" Connection Established!");

		DataInputStream dis=new DataInputStream(s.getInputStream());
		String msg_listened=(String)dis.readUTF();

		System.out.println(" ----------Data Read----------\n"+msg_listened);
		ss.close();
		System.out.println(" Decrypted Message:\n"+decryptMessage(msg_listened));
		sendMessage(msg_listened,port_number);
	}

	public String decryptMessage(String msg) {
		msg=msg.toUpperCase();
		msg=msg.trim();
		
		System.out.println(" Jump Value: "+jump_value);
		String cipher_text="";
		
		for(int i=0;i<msg.length();i++) {
			if(msg.charAt(i)==' ')
				cipher_text+="";
			else
				cipher_text+=(char)(msg.charAt(i)-jump_value);
		}
		
		return cipher_text;
	}//end of ceaserCipher....

	public void sendMessage(String msg,int port_number) throws Exception{
		Scanner sc=new Scanner(System.in);
		System.out.print(" Host IP: ");
		String host_ip=sc.nextLine();

		sendEncryptionKey(host_ip,String.valueOf(port_number));
		
		Socket s=new Socket(host_ip,port_number);

		//re-sending the itercepted msg to Node_C
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		dout.writeUTF(msg);
		dout.flush();
		dout.close();
		System.out.println(" Message Sent!");
	}

	public void sendEncryptionKey(String host_ip,String port_no) throws Exception{
		Socket s=new Socket(host_ip,Integer.parseInt(port_no));
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		dout.writeUTF(String.valueOf(jump_value));
		dout.flush();
		dout.close();
		System.out.println(" Encryption Key sent!");
	}//end of sendEncryptionKey....
}