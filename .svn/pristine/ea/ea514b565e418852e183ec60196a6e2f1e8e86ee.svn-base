package cn.itheima.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileBianMa {
	private static File file1 = new File("f:/新建文本文档.txt");
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(file1),"utf8"));
		String string0=bufferedReader.readLine();
		char[] cs = string0.toCharArray();
		System.out.println(cs.length);
		for (char c : cs) {
			System.out.println(c);
		}
		System.out.println(string0);
		
		FileInputStream fileInputStream = new FileInputStream(file1);
		byte[] bs=new byte[10];
		System.out.println("----------------------------");
		fileInputStream.read(bs);
		for(int i=0;i<bs.length;i++) {
			System.out.println(bs[i]);
		}
		System.out.println(new String(bs, "utf8"));
		System.out.println("---------------------");
		resolveCode("f:/新建文本文档.txt");
		System.out.println("-------------------------");
		String string="谢念1";
		byte[] bs2=new byte[10];
		bs2 = string.getBytes();
		for(int i=0;i<bs2.length;i++) {
			System.out.println(bs2[i]);
		}
		System.out.println(new String(bs2, "utf8"));
	}

    public static String resolveCode(String path) throws Exception {  
        InputStream inputStream = new FileInputStream(path);    
        byte[] head = new byte[3];    
        inputStream.read(head);     
        System.out.println(new String(head, "utf8"));
        String code = "gb2312";  //或GBK  
        if (head[0] == -1 && head[1] == -2 )    
            code = "UTF-16";    
        else if (head[0] == -2 && head[1] == -1 )    
            code = "Unicode";    
        else if(head[0]==-17 && head[1]==-69 && head[2] ==-65)    
            code = "UTF-8";    
            
        inputStream.close();  
          
        System.out.println(code);   
        return code;  
    } 

}
