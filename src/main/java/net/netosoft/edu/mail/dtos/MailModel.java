package net.netosoft.edu.mail.dtos;

import java.io.Serializable;


/**
 * @author ernesto
 *
 */
public class MailModel implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = -805834825643073L;

    private String emailAddress;
    
    private String emailSubject;

	private String template;
	
	private String user;

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the emailSubject
     */
    public String getEmailSubject() {
        return emailSubject;
    }

    /**
     * @param emailSubject the emailSubject to set
     */
    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

	public String getTemplate(){
		return template;
	}

	public void setTemplate(String template){
		this.template = template;
	}

	public String getUser(){
		return user;
	}

	public void setUser(String user){
		this.user = user;
	}

	@Override
	public String toString(){
		return new StringBuilder()
				.append("emailAddress:").append(emailAddress).append(", ")
				.append("emailSubject:").append(emailSubject).append(", ")
				.append("template:").append(template).append(", ")
				.append("user:").append(user)
				.toString();
	}
	
	
}
