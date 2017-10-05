package net.netosoft.edu.mail.dtos;

import java.util.Date;

/**
 *
 * @author ernesto
 */
public class TicketReg extends MailModel{
	private String ticketNumber;
	private String parkingLot;
	private String parkingAddress;
	private Date inDate;

	public String getTicketNumber(){
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber){
		this.ticketNumber = ticketNumber;
	}

	public String getParkingLot(){
		return parkingLot;
	}

	public void setParkingLot(String parkingLot){
		this.parkingLot = parkingLot;
	}

	public String getParkingAddress(){
		return parkingAddress;
	}

	public void setParkingAddress(String parkingAddress){
		this.parkingAddress = parkingAddress;
	}

	public Date getInDate(){
		return inDate;
	}

	public void setInDate(Date inDate){
		this.inDate = inDate;
	}
	
	@Override
	public String toString(){
		return new StringBuilder().append('{')
				.append(super.toString()).append(", ")
				.append("ticketNumber:").append(ticketNumber).append(", ")
				.append("parkingLot:").append(parkingLot).append(", ")
				.append("parkingAddress:").append(parkingAddress).append(", ")
				.append("inDate:").append(inDate)
				.append('}')
				.toString();
	}
}
