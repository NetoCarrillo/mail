package net.netosoft.edu.mail.service;

import net.netosoft.edu.mail.beans.MailModel;

/**
 * This Type is about sending emails not about email address Entity.
 * 
 * @author ernesto
 */
public interface EmailService{
	
	void sendEmail(MailModel request);
	
}
