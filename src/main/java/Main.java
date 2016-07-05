import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.forsfortis.service.dbservice.CredentialService;
import com.forsfortis.util.DeviceType;
import com.forsfortis.util.credentials.Credentials;

public class Main {
public static void main(String ar[]){

	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	CredentialService credentialService = ctx.getBean("credentialService", CredentialService.class);
	
	/*List<Credentials> listCredentialsByDeviceType = credentialService.listAllCredentials(DeviceType.WMI);
	System.out.println(listCredentialsByDeviceType);*/
    /*Credentials linuxCredentials=new Credentials();
	linuxCredentials.setUserName("test");
	linuxCredentials.setPassword("test");
	linuxCredentials.setDeviceType(DeviceType.WMI);
	credentialService.addCredentials(linuxCredentials);*/
	
    ctx.close();

}


}
