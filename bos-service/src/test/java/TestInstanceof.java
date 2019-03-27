import javax.mail.MessagingException;

import org.junit.Test;

import com.itheima.bos.utils.MailUtils;

public class TestInstanceof {
	
	public static void main(String[] args) {
		/**
		 * 学习instanceof用法
		 */
		boolean flag = false;
		A a = new B();
		if(a instanceof Object) {
			flag = true;
		}
		System.out.println("继承的A类的B类的一个对象是否是A类型呢？"+flag);
		

	}
	@Test
	public void send() {
		try {
			MailUtils.sendMail("1033430806@qq.com", "积分卡上课啦发送");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class A{}

class B extends A{}