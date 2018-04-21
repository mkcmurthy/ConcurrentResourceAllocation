
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	
	
	private static int recvPermissionFalse = 0;
	private static int recvPermissionTrue = 0;
	
	
		
	public static synchronized boolean permRequest(Processes proc,String resource){
		System.out.println("Process "+Thread.currentThread().getId()+": now attempting to eneter "+resource);
		boolean perm;
		perm = evaluatePermission(proc, resource);
		
		setRecvPermissionFalse(0);
		setRecvPermissionTrue(0);
		
		return perm;
	}
	
	public static void setRecvPermissionFalse(int recvPermissionFalse) {
		Server.recvPermissionFalse = recvPermissionFalse;
	}

	public static void setRecvPermissionTrue(int recvPermissionTrue) {
		Server.recvPermissionTrue = recvPermissionTrue;
	}
	
	public static void resetPermGrantedS1(Processes proc){
		
		// reset permission granted by processes other than calling process for S1
		for (int i=0; i<3; i++){
			
			if(!proc.equals(InstanceMapper.proc[i])){
				
				InstanceMapper.proc[i].setPermissionGrantedS1("none");
				
			}
			
		}
		
	}
	
	// reset permission granted by processes other than calling process for S2
	public static void resetPermGrantedS2(Processes proc){
			
			for (int i=0; i<3; i++){
				
				if(!proc.equals(InstanceMapper.proc[i])){
					
					InstanceMapper.proc[i].setPermissionGrantedS2("none");
					
				}
				
			}
			
	}

	
	
	public static boolean evaluatePermission(Processes obj, String resc){
		
		boolean permission;
		
		for (int i=0; i<3; i++){
			
			if(!obj.equals(InstanceMapper.proc[i])){
				//System.out.println(InstanceMapper.proc[i]);
				permission = InstanceMapper.proc[i].grantPermission(resc);
				if(permission){
					recvPermissionTrue++;
					
				}
				else {
					recvPermissionFalse++;
				}
			}
			
		}
		
		if (recvPermissionTrue >= 1)
			return true;
		else 
			return false;
		
	}
	
	public static synchronized void updateS1(int rVal){
		
		System.out.println("  Random number is: "+rVal);
		System.out.println("  Quantity of hand before: S1="+Resources.getS1());
		
		Resources.setS1(rVal);
		System.out.println("                  after : S1="+Resources.getS1());
		
	}
	
	public static synchronized void updateS2(int rVal){
		
		
		System.out.println("  Random number is: "+rVal);
		System.out.println("  Quantity of hand before: S2="+Resources.getS1());
		
		Resources.setS2(rVal);
		System.out.println("                  after : S2="+Resources.getS1());
	}
		

}
