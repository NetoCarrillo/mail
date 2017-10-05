package net.netosoft.edu.mail.dtos;

/**
 *
 * @author ernesto
 */
public class ValidationCode extends MailModel{

	private String code;

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code = code;
	}

	@Override
	public String toString(){
		return new StringBuilder().append('{')
				.append(super.toString()).append(", ")
				.append("code:").append(code).append('}')
				.toString();
	}
}
