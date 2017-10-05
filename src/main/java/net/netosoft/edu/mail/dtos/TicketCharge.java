package net.netosoft.edu.mail.dtos;

import java.util.Date;

/**
 *
 * @author ernesto
 */
public class TicketCharge extends MailModel{
	
	private String ticketNumber;
	private String parkingLot;
	private String parkingAddress;
	private Date inDate;
	private Date exitDate;
	private String ticketStatus;
	private String parkingTime;
	private Float hourCost;
	private Float fee;
	private Float taxes;
	private Float totalCharge;
	private String lastDigits;

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

	public Date getExitDate(){
		return exitDate;
	}

	public void setExitDate(Date exitDate){
		this.exitDate = exitDate;
	}

	public String getTicketStatus(){
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus){
		this.ticketStatus = ticketStatus;
	}

	public String getParkingTime(){
		return parkingTime;
	}

	public void setParkingTime(String parkingTime){
		this.parkingTime = parkingTime;
	}

	public Float getHourCost(){
		return hourCost;
	}

	public void setHourCost(Float hourCost){
		this.hourCost = hourCost;
	}

	public Float getFee(){
		return fee;
	}

	public void setFee(Float fee){
		this.fee = fee;
	}

	public Float getTaxes(){
		return taxes;
	}

	public void setTaxes(Float taxes){
		this.taxes = taxes;
	}

	public Float getTotalCharge(){
		return totalCharge;
	}

	public void setTotalCharge(Float totalCharge){
		this.totalCharge = totalCharge;
	}

	public String getLastDigits(){
		return lastDigits;
	}

	public void setLastDigits(String lastDigits){
		this.lastDigits = lastDigits;
	}

	@Override
	public String toString(){
		return new StringBuilder().append('{')
				.append(super.toString()).append(", ")
				.append("ticketNumber:").append(ticketNumber).append(", ")
				.append("parkingLot:").append(parkingLot).append(", ")
				.append("parkingAddress:").append(parkingAddress).append(", ")
				.append("inDate:").append(inDate).append(", ")
				.append("exitDate:").append(exitDate).append(", ")
				.append("ticketStatus:").append(ticketStatus).append(", ")
				.append("parkingTime:").append(parkingTime).append(", ")
				.append("hourCost:").append(hourCost).append(", ")
				.append("fee:").append(fee).append(", ")
				.append("taxes:").append(taxes).append(", ")
				.append("totalCharge:").append(totalCharge).append(", ")
				.append("lastDigits:").append(lastDigits).append('}')
				.toString();
	}
	
	
}
