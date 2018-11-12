import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Node_A {
	private int jump_value;
	
	Node_A() {
		jump_value=(int)(Math.random()*8);
	}

	public static void main(String args[]) throws Exception{
		Node_A ob=new Node_A();
		Scanner sc=new Scanner(System.in);
		
		System.out.print(" Host IP: ");
		String host_ip=sc.nextLine();
		System.out.print(" Port Number: ");
		String port_no=sc.nextLine();

		//sending EncrptionKey to host.
		ob.sendEncryptionKey(host_ip,port_no);
		
		//connecting to other node
		Socket s = new Socket(host_ip,Integer.parseInt(port_no));
		System.out.println(" Connection Established with node C");

		//for message reading and encrypting
		System.out.println(" Enter the message to be sent!");
		String msg_transmit=sc.nextLine();
		msg_transmit=ob.ceaserCipher(msg_transmit);
		
		//for sending msg
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		dout.writeUTF(msg_transmit);
		dout.flush();
		dout.close();
		System.out.println(" Message Sent!");

	}//end of main....

	public String ceaserCipher(String msg) {
		msg=msg.toUpperCase();
		msg=msg.trim();
		
		System.out.println(" Jump Value: "+jump_value);
		String cipher_text="";
		
		for(int i=0;i<msg.length();i++) {
			if(msg.charAt(i)==' ')
				cipher_text+="";
			else
				cipher_text+=(char)(msg.charAt(i)+jump_value);
		}
		
		return cipher_text;
	}//end of ceaserCipher....

	public void sendEncryptionKey(String host_ip,String port_no) throws Exception{
		Socket s=new Socket(host_ip,Integer.parseInt(port_no));
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		dout.writeUTF(String.valueOf(jump_value));
		dout.flush();
		dout.close();
		System.out.println(" Encryption Key sent!");
	}//end of sendEncryptionKey....
}
