package kw.kng.app.response;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class Ticket 
{
	private Integer ticketid;
	private String fromPlace;
	private String toPlace;
	private String fromDate;
	private String toDate;
	private String trainNum;
	private String ticketCost;
	private String ticketStatus;

}
