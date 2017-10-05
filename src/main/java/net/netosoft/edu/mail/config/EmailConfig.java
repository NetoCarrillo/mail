package net.netosoft.edu.mail.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig{
	
	@Bean
	@ConfigurationProperties("mail.template.validation")
	public MailContentTemplate validationMailTemplate(){
		return new MailContentTemplate();
	}

	@Bean
	@ConfigurationProperties("mail.template.user-registration")
	public MailContentTemplate userRegistrationMailTemplate(){
		return new MailContentTemplate();
	}
	
	@Bean
	@ConfigurationProperties("mail.template.ticket-registration")
	public MailContentTemplate ticketRegistrationMailTemplate(){
		return new MailContentTemplate();
	}
	
	@Bean
	@ConfigurationProperties("mail.template.ticket-cancellation")
	public MailContentTemplate ticketCancellationMailTemplate(){
		return new MailContentTemplate();
	}
	
	@Bean
	@ConfigurationProperties("mail.template.successful-charge")
	public MailContentTemplate successfulChargeMailTemplate(){
		return new MailContentTemplate();
	}
	
	@Bean
	@ConfigurationProperties("mail.template.unsuccessful-charge")
	public MailContentTemplate unsuccessfulChargeMailTemplate(){
		return new MailContentTemplate();
	}
	
}
