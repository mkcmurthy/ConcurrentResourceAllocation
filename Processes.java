
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

public class Processes implements Runnable {

	private final String processName;
	private String permissionGrantedS1 = "none";
	private String permissionGrantedS2 = "none";
	private String permissionReceived = "none";
	private int attempts = 0;
	private static final SecureRandom generator = new SecureRandom();
		
	
	public Processes(String processName) {
		
		this.processName = processName;
	}
	
	
	
	
	
	public void run(){
		
		while (attempts < 20){
			resourceAccess();
		}
	}
	
	public void resourceAccess(){
		
				
			// permission request for resource from here
			if (Server.permRequest(this, "S1")){
									
				permissionReceived = "S1";
				try {
					
					Thread.sleep(generator.nextInt(1000));
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				
				System.out.println("Process "+Thread.currentThread().getId()+": received majority votes - granted permission");
				// update S1 resource
				System.out.println("Process "+Thread.currentThread().getId()+": now in S1");
				Server.updateS1(randomGenerator());
				setPermissionReceived("none");
				Server.resetPermGrantedS1(this);
				attempts++;
						
													
			}
			
			else if (Server.permRequest(this, "S2")){
				
				permissionReceived = "S2";
				try {
					
					Thread.sleep(generator.nextInt(1000));
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				
				System.out.println("Process "+Thread.currentThread().getId()+": received majority votes - granted permission");
				// update S2 resource
				System.out.println("Process "+Thread.currentThread().getId()+": now in S2");
				Server.updateS1(randomGenerator());
				setPermissionReceived("none");
				Server.resetPermGrantedS2(this);
				attempts++;
				
				
			}
			
			else {
				
				System.out.println("Process "+Thread.currentThread().getId()+": did not receive majority votes");
				try {
					Thread.sleep(generator.nextInt(1000));
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				attempts++;
				
			}
	
			
	}
	
	public int randomGenerator(){
		
		int rNumber;
		Random rnd = new Random();
		rNumber = rnd.nextInt(100);
		rNumber = (rNumber+rNumber) - 100;
		return rNumber;
	}
	
		
	public void setPermissionReceived(String permissionReceived) {
		this.permissionReceived = permissionReceived;
	}
	
	public void setPermissionGrantedS1(String permissionGrantedS1) {
		this.permissionGrantedS1 = permissionGrantedS1;
	}

	public void setPermissionGrantedS2(String permissionGrantedS2) {
		this.permissionGrantedS2 = permissionGrantedS2;
	}

	
	// grant permission to requesting process
	public boolean grantPermission(String resource){
		
		if(resource == "S1"){
					
			if (permissionGrantedS1 == resource || permissionReceived == resource){
				return false;
			}
			
			else{
			
				permissionGrantedS1 = resource;
				return true;
			}
		}
		
		else {
			
					
			if (permissionGrantedS2 == resource || permissionReceived == resource){
				return false;
			}
				
			else{
			
				permissionGrantedS2 = resource;
				return true;
			}
				
		}
		
	}

}
