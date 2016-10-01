package at.neonartworks.neolib.log;

import java.util.ArrayList;
import java.util.List;

public enum LoggerLevel {
	DEBUG(0),
	INFO(1),
	ALERT(2),
	ERROR(3);

	private final int priority;

	private LoggerLevel(int weight) {
		this.priority = weight;
	}

	public int getPriority() {
		return priority;
	}
	
	public LoggerLevel[] getLevelsAbove(){
		List<LoggerLevel> levels = new ArrayList<LoggerLevel>();
		for(LoggerLevel l : LoggerLevel.values()){
			if(l.getPriority() >=this.getPriority()){
				levels.add(l);
			}
		}
		return levels.toArray(new LoggerLevel[levels.size()]);
	}
}
