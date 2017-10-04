package net.netosoft.edu.mail;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import net.netosoft.edu.mail.config.MailContentTemplate;
import net.netosoft.edu.mail.beans.MailModel;
import net.netosoft.edu.mail.beans.UnsuccessfulCharge;
import net.netosoft.edu.mail.service.EmailService;
import net.netosoft.edu.mail.beans.ValidationCode;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
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
	@Qualifier("userRegistryMailTemplate")
	private MailContentTemplate userRegistryMailTemplate;
	
	@Autowired
	@Qualifier("ticketRegistryMailTemplate")
	private MailContentTemplate ticketRegistryMailTemplate;
	
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
		
		
//		emailService.sendEmail(sendValidationCode());
		emailService.sendEmail(sendUnsuccessfulCharge());
	}
	
	private MailModel sendValidationCode(){
		LOGGER.debug("Subject: {}", validationMailTemplate.getSubject());
		LOGGER.debug("Template path: {}", validationMailTemplate.getPath());
		
		ValidationCode model = new ValidationCode();
		
		fillCommonData(model, validationMailTemplate);
		
		model.setCode("00000000");
		
		LOGGER.debug("{}", model);
		return model;
	}
	
	private MailModel sendUnsuccessfulCharge(){
		LOGGER.debug("Subject: {}", unsuccessfulChargeMailTemplate.getSubject());
		LOGGER.debug("Template path: {}", unsuccessfulChargeMailTemplate.getPath());
		
		UnsuccessfulCharge model = new UnsuccessfulCharge();
		
		fillCommonData(model, unsuccessfulChargeMailTemplate);
		
		Date in = new GregorianCalendar(2017, Calendar.NOVEMBER, 2, 8, 10, 25).getTime();
		Date out = new GregorianCalendar(2017, Calendar.NOVEMBER, 2, 13, 40, 12).getTime();
		Date ptime = new Date(out.getTime() - in.getTime());
		
		DateTime a = new DateTime(in.getTime());
		DateTime b = new DateTime(out.getTime());
		
		Interval interval = new Interval(in.getTime(), out.getTime());
		Period period = Period.fieldDifference(new LocalDate(in.getTime()), new LocalDate(out.getTime()));
//		period.toStandardHours().
//		interval.toString();
		
//		Interval interval = 
		model.setTicketNumber("1234");
		model.setParkingLot("Plaza Inn");
		model.setParkingAddress("Av Insurgentes Sur");
		model.setInDate(in);
		model.setExitDate(out);
		model.setTicketStatus("Cobrado");
		model.setParkingTime(period.toString());
		model.setHourCost(16f);
		model.setFee(29.50f);
		model.setTaxes(2.34f);
		model.setTotalCharge(model.getFee() + model.getTaxes());
		model.setLastDigits("76565");

		LOGGER.debug("{}", model);
		return model;
	}
	
	
	
	
	
	private void fillCommonData(MailModel model, MailContentTemplate contentTmpl){
		model.setEmailAddress("ernestocarrillo@anzen.com.mx");
		model.setEmailSubject(contentTmpl.getSubject());
		model.setTemplate(contentTmpl.getPath());
		model.setUser("Ernesto");
	}
}
