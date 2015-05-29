package net.hb.day27;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class DataTransferClient {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("203.236.209.120", 8888);

		System.out.println(socket);
		System.out.println("select file name");
		FileDialog fd = new FileDialog(new Frame(), "Open file", FileDialog.LOAD);
		fd.setVisible(true);
		String dir = fd.getDirectory();
		System.out.println("dir : " + dir);
		String fileName = fd.getFile();
		System.out.println("FileName : " + fileName);

		if (dir == null || fileName == null) {
			return;
		}

		File selectedfilePath = new File(dir + fileName);
		System.out.println("file path : " + selectedfilePath);
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(selectedfilePath)); // send file name
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("C:\\Mtest\\xyz.gif")); // save
		
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    System.out.println("파일명 : "+fileName);
    bw.write(fileName+"\n"); bw.flush();
    
  /* 선택한 파일로 부터 스트림을 읽어들여서 얻어놓은 OutputStream에 연결 */
    DataInputStream dis=new DataInputStream(new FileInputStream(new File(dir+fileName)));
    DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
    
    int b=0;
    while( (b=dis.read()) != -1 ){
     dos.writeByte(b); dos.flush();
    }
    
   /* 자원정리 */
    dis.close(); dos.close(); socket.close(); 
    dis=null; dos=null; socket=null;
    System.exit(1);






		
		/*while (true) {
			int data = in.read();

			if (data == -1) {
				break;
			}// end if
			out.write(data);
		}// end while*/
	}// end main
}// end class
