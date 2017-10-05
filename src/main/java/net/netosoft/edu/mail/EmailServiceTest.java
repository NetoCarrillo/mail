package net.netosoft.edu.mail;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import net.netosoft.edu.mail.config.MailContentTemplate;
import net.netosoft.edu.mail.dtos.MailModel;
import net.netosoft.edu.mail.dtos.TicketCharge;
import net.netosoft.edu.mail.dtos.TicketReg;
import net.netosoft.edu.mail.service.EmailService;
import net.netosoft.edu.mail.dtos.ValidationCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author ernesto
 */
@Component
public class EmailServiceTest implements CommandLineRunner{
	private static final Logger LOGGER =
			LoggerFactory.getLogger(EmailServiceTest.class);
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	@Qualifier("validationMailTemplate")
	private MailContentTemplate validationMailTemplate;

	@Autowired
	@Qualifier("userRegistrationMailTemplate")
	private MailContentTemplate userRegistrationMailTemplate;
	
	@Autowired
	@Qualifier("ticketRegistrationMailTemplate")
	private MailContentTemplate ticketRegistrationMailTemplate;
	
	@Autowired
	@Qualifier("ticketCancellationMailTemplate")
	private MailContentTemplate ticketCancellationMailTemplate;
	
	@Autowired
	@Qualifier("successfulChargeMailTemplate")
	private MailContentTemplate successfulChargeMailTemplate;
	
	@Autowired
	@Qualifier("unsuccessfulChargeMailTemplate")
	private MailContentTemplate unsuccessfulChargeMailTemplate;

	@Override
	public void run(String... args) throws Exception{
		LOGGER.debug("Testing sendEmail");
		
		emailService.sendEmail(sendValidationCode());
		emailService.sendEmail(sendUserRegistration());
		emailService.sendEmail(sendTicketRegistration());
		emailService.sendEmail(sendSuccessfulCharge());
		emailService.sendEmail(sendTicketCancellation());
		emailService.sendEmail(sendUnsuccessfulCharge());
	}
	
	private MailModel sendValidationCode(){
		LOGGER.debug("Subject: {}", validationMailTemplate.getSubject());
		LOGGER.debug("Template path: {}", validationMailTemplate.getPath());
		
		ValidationCode model = new ValidationCode();
		
		fillCommonData(model, validationMailTemplate);
		
		model.setCode("00000000");
		
		return model;
	}
	
	private MailModel sendSuccessfulCharge(){
		LOGGER.debug("Subject: {}", successfulChargeMailTemplate.getSubject());
		LOGGER.debug("Template path: {}", successfulChargeMailTemplate.getPath());
		
		TicketCharge model = new TicketCharge();
		
		fillCommonData(model, successfulChargeMailTemplate);
		
		fillCharge(model);

		return model;
	}
	
	private MailModel sendUnsuccessfulCharge(){
		LOGGER.debug("Subject: {}", unsuccessfulChargeMailTemplate.getSubject());
		LOGGER.debug("Template path: {}", unsuccessfulChargeMailTemplate.getPath());
		
		TicketCharge model = new TicketCharge();
		
		fillCommonData(model, unsuccessfulChargeMailTemplate);
		
		fillCharge(model);

		return model;
	}
	
	private MailModel sendTicketRegistration(){
		LOGGER.debug("Subject: {}", ticketRegistrationMailTemplate.getSubject());
		LOGGER.debug("Template path: {}", ticketRegistrationMailTemplate.getPath());
		
		TicketReg model = new TicketReg();
		
		fillCommonData(model, ticketRegistrationMailTemplate);
		
		Date in = new GregorianCalendar(2017, Calendar.NOVEMBER, 2, 8, 10, 25).getTime();

		model.setTicketNumber("1234");
		model.setParkingLot("Plaza Inn");
		model.setParkingAddress("Av Insurgentes Sur");
		model.setInDate(in);

		return model;
	}
	
	private MailModel sendTicketCancellation(){
		LOGGER.debug("Subject: {}", ticketCancellationMailTemplate.getSubject());
		LOGGER.debug("Template path: {}", ticketCancellationMailTemplate.getPath());
		
		TicketReg model = new TicketReg();
		
		fillCommonData(model, ticketCancellationMailTemplate);
		
		Date in = new GregorianCalendar(2017, Calendar.NOVEMBER, 2, 8, 10, 25).getTime();

		model.setTicketNumber("1234");
		model.setParkingLot("Plaza Inn");
		model.setParkingAddress("Av Insurgentes Sur");
		model.setInDate(in);

		return model;
	}
	
	private MailModel sendUserRegistration(){
		LOGGER.debug("Subject: {}", userRegistrationMailTemplate.getSubject());
		LOGGER.debug("Template path: {}", userRegistrationMailTemplate.getPath());
		
		MailModel model = new MailModel();
		
		fillCommonData(model, userRegistrationMailTemplate);

		return model;
	}
	
	private void fillCommonData(MailModel model, MailContentTemplate contentTmpl){
		model.setEmailAddress("ernestocarrillo@anzen.com.mx");
//		model.setEmailAddress("ernestocarrillo@anzen.com.mx,rhernandez@anzen.com.mx,joseluna@anzen.com.mx");
		model.setEmailSubject(contentTmpl.getSubject());
		model.setTemplate(contentTmpl.getPath());
		model.setUser("Ernesto");
	}
	
	private void fillCharge(TicketCharge model){
		Date in = new GregorianCalendar(2017, Calendar.NOVEMBER, 2, 8, 10, 25).getTime();
		Date out = new GregorianCalendar(2017, Calendar.NOVEMBER, 2, 13, 40, 12).getTime();
		DateDiff diff = new DateDiff(in, out);

		String parkTime = String.format(
				"%d:%02d:%02d",
				diff.hours,
				diff.minutes,
				diff.seconds);
		
		model.setTicketNumber("1234");
		model.setParkingLot("Plaza Inn");
		model.setParkingAddress("Av Insurgentes Sur");
		model.setInDate(in);
		model.setExitDate(out);
		model.setTicketStatus("Cobrado");
		model.setParkingTime(parkTime);
		model.setHourCost(16f);
		model.setFee(29.50f);
		model.setTaxes(2.34f);
		model.setTotalCharge(model.getFee() + model.getTaxes());
		model.setLastDigits("76565");
	}

	private class DateDiff{
		private final int seconds;
		private final int minutes;
		private final int hours;

		public DateDiff(Date a, Date b){
			long rawdiff = Math.abs(b.getTime() - a.getTime());

			//throw milliseconds
			rawdiff /= 1000;
			//Seconds
			this.seconds = (int)(rawdiff % 60);
			//Minutes
			rawdiff /= 60;
			this.minutes = (int)(rawdiff % 60);
			//Hours
			rawdiff /= 60;
			if(rawdiff > Integer.MAX_VALUE){
				throw new RuntimeException("Period between dates is to long");
			}
			this.hours = (int)(rawdiff);
		}
	}
	
}
