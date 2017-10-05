package net.netosoft.edu.mail.beans;

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
	
	
}
