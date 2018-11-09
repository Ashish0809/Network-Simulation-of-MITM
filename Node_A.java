import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Node_A{
	public static void main(String args[]) throws Exception{

		Scanner sc=new Scanner(System.in);
		
		System.out.print("Host IP: ");
		String host_ip=sc.next();
		System.out.print("Port Number: ");
		String port_no=sc.next();
		
		//connecting to other node
		Socket s = new Socket(host_ip,Integer.parseInt(port_no));

		BufferedReader systemBuffer = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader socketBuffer = new BufferedReader(new InputStreamReader(s.getInputStream()));
		OutputStream out = s.getOutputStream();
		PrintWriter pw = new PrintWriter(out,true);
		String requestMessage, serverMessage;

		System.out.println("Connection established");

		while(true){
			System.out.println("Client : ");
			requestMessage = systemBuffer.readLine();
			pw.println(requestMessage);
			if((serverMessage = socketBuffer.readLine()) != null){
				System.out.println("Server : " + serverMessage);
			}
		}
	}
}
