package net.netosoft.edu.mail.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import net.netosoft.edu.mail.beans.MailModel;
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
	private String from;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Configuration fmConfig;
	
	@Override
	public void sendEmail(MailModel request){
		LOGGER.debug("from: {} \t to:{}", from, request.getEmailAddress());

		try{
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
			helper.setTo(request.getEmailAddress());
			helper.setFrom(from);
			helper.setText(
					buildContent(request.getTemplate(), request),
					true);
			mailSender.send(message);
		}catch(MessagingException | IOException | TemplateException ex){
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private String buildContent(String tmplName, Object model)
						 throws IOException, TemplateException{
		
		Template template = fmConfig.getTemplate(tmplName);
		return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
	}
}
