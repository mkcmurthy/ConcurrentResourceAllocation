

public class InstanceMapper {
	
	static Processes[] proc = new Processes[3];
	
	
	public static void mapper(Processes proc1, Processes proc2, Processes proc3){
		
		InstanceMapper.proc[0] = proc1;
		InstanceMapper.proc[1] = proc2;
		InstanceMapper.proc[2] = proc3;
		
	}

}
