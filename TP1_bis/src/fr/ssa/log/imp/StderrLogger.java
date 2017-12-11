package fr.ssa.log.imp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

import fr.ssa.log.ILog;

@Service
public class StderrLogger implements ILog {
	// format des dates
	private final DateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss | ");
	
	@PostConstruct
	public void init() {
		System.err.println("Init " + this);
	}
	
	@PreDestroy
	public void close() {
		System.err.println("Close " + this);
	}
	
	public void log(String message) {
		System.err.print(format.format(new Date()));
		System.err.println(message);
	}
	
	public void log(String message, String arg1) {
		log(message.replaceAll("\\$1", arg1));
	}
}