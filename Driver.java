
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Processes p1 = new Processes("p1");
		Processes p2 = new Processes("p2");
		Processes p3 = new Processes("p3");
		
		
		InstanceMapper.mapper(p1, p2, p3);
			
		ExecutorService executorService = Executors.newCachedThreadPool();
		
				
		executorService.execute(p1);
		executorService.execute(p2);
		executorService.execute(p3);
		
		executorService.shutdown();
		
	}

}
