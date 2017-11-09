package net.netosoft.edu.mail.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import net.netosoft.edu.mail.dtos.MailModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 *
 * @author ernesto
 */
@Service
public class EmailServiceImpl implements EmailService{
	private static final Logger LOGGER =
			LoggerFactory.getLogger(EmailServiceImpl.class);

	@Value("${spring.mail.username}")
	private String sender;
	
	@Value("${spring.mail.from}")
	private String from;
	
	@Value("${mail.template.main.path}")
	private String mainTemplate;
	
	@Value("${mail.template.main.header.name}")
	private String headerName;
	
	@Value("${mail.template.main.header.path}")
	private String headerPath;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Configuration fmConfig;
	
	@Override
	public void sendEmail(MailModel request){
		LOGGER.debug("from: {} \t to:{}", sender, request.getEmailAddress());
		LOGGER.debug("{}", request);

		try{
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setTo(request.getEmailAddress().split(","));
			helper.setFrom(sender, from);
			helper.setSubject(request.getEmailSubject());
			helper.setText(buildContent(request), true);
			helper.addInline(headerName, new File(headerPath));
			
			mailSender.send(message);
			
		}catch(MessagingException | IOException | TemplateException ex){
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private String buildContent(Object model)
						 throws IOException, TemplateException{
		
		Template template = fmConfig.getTemplate(mainTemplate);
		return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
	}
}
