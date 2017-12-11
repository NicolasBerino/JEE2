package fr.ssa.log.imp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import fr.ssa.log.ILog;

public class FileLogger implements ILog {

	// format de sortie des dates
	private final DateFormat format = new SimpleDateFormat(	"yyyy/MM/dd hh:mm:ss | ");
	
	// fichier de sortie
	
	private final PrintWriter file;
	
	public FileLogger(String fileName) {
		try {
			this.file = new PrintWriter(new FileOutputStream(fileName, true));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("bad fileName");
		}
	}
	
	@PostConstruct
	public void init() {
		System.err.println("Init " + this);
	}
	
	@PreDestroy
	public void close() {
		file.close();
		System.err.println("Close " + this);
	}
	
	public void log(String message) {
		file.print(format.format(new Date()));
		file.println(message);
	}
	
	public void log(String message, String arg1) {
		log(message.replaceAll("\\$1", arg1));
	}
}