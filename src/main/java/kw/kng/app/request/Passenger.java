package kw.kng.app.request;

import lombok.Data;

@Data
public class Passenger 
{
	private String fname;
	private String lname;
	private String fromPlace;
	private String toPlace;
	private String fromDate;
	private String toDate;
	private String trainNum;
}
