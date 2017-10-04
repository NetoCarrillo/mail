package net.netosoft.edu.mail.config;

import java.util.Set;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 *
 * @author ernesto
 */
public class MailContentTemplate{
	@NestedConfigurationProperty
	private String path;
	
	@NestedConfigurationProperty
	private String subject;
	
	public String getPath(){
		return path;
	}

	public void setPath(String path){
		this.path = path;
	}

	public String getSubject(){
		return subject;
	}

	public void setSubject(String subject){
		this.subject = subject;
	}
}
