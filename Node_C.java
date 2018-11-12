import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Node_C {
	private int jump_value;
	public static void main(String[] args)throws Exception {
		Node_C ob=new Node_C();

		Scanner sc=new Scanner(System.in);
		System.out.print(" Enter the port number: ");
		int port_no=sc.nextInt();

		ob.recieveEncryptionKey(port_no);

		ServerSocket ss=new ServerSocket(port_no);
		System.out.println(" --Node C online--");
		Socket s=ss.accept();
		System.out.println(" --Accepted Connection--");
		
		//BufferedReader buff_msg=new BufferedReader(new InputStreamReader(s.getInputStream()));
		DataInputStream dis=new DataInputStream(s.getInputStream());
		String msg_received=(String)dis.readUTF();
		ss.close();
		System.out.println(" -------Message Received-------\n"+msg_received);
		System.out.println(" Decrypted Message:\n"+ob.decryptMessage(msg_received));
		
	}
	public void recieveEncryptionKey(int port_number) throws Exception {
		ServerSocket ss=new ServerSocket(port_number);
		Socket s=ss.accept();
		DataInputStream dis=new DataInputStream(s.getInputStream());
		jump_value=Integer.parseInt((String)dis.readUTF());
		ss.close();
		System.out.println(" Key Received."+jump_value);
		
	}//end of receiveEncryptionKey....
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

}