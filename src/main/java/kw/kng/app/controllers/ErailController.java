package kw.kng.app.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.app.request.Passenger;
import kw.kng.app.response.Ticket;

@RestController
public class ErailController 
{
	
	private Map<Integer, Ticket> tickets=new HashMap();
	
	@PostMapping(
			value="/ticket",
			consumes= {"application/xml","application/json"},
			produces= {"application/xml","application/json"}
	)
	public Ticket bookTicket(@RequestBody Passenger passenger) 
	{
		//Logic to book ticket
		
		Ticket t=new Ticket();
		Random r= new Random();
		
		int ticketId=r.nextInt();
		
		t.setTicketid(ticketId);
		t.setFromPlace(passenger.getFromPlace());
		t.setToPlace(passenger.getToPlace());
		t.setFromDate(passenger.getFromDate());
		t.setToDate(passenger.getToDate());
		t.setTicketStatus("CONFIRMED");
		t.setTrainNum(passenger.getTrainNum());
		t.setTicketCost("1500.00 INR");
		
		tickets.put(ticketId, t);
		
		return t;
	}
	
	@GetMapping(
			value="/ticket/{ticketId}",
			produces= {"application/xml","application/json"}
	)
	public Ticket getTicket(@PathVariable Integer ticketId) 
	{
		if(tickets.containsKey(ticketId)) 
		{
			System.out.println(tickets.get(ticketId));
			return tickets.get(ticketId);
		}
		return null;
	}

}

/* 

 In POSTMAN do the following:
 
 1. POST , http://localhost:8080/ticket
 1.1. Header Data:
 		Key:Content-Type
 		Value:application/json
 		
 		Key:Accept
 		Value:application/json
 
 Body: Pass this passenger data in the body
 {
    "fname":"rajan",
    "lname":"Mathew",
    "fromPlace":"New Delhi",
    "toPlace":"Kerala",
    "fromDate": "24-03-2023",
    "toDate": "25-03-2023",
    "trainNum": "123"
}
 
2. GET , http://localhost:8080/ticket/{}
2.1. Header Data:
 		Key:Accept
 		Value:application/json
 		
or
2.2. Header Data:
 		Key:Accept
 		Value:application/xml



================================================
Steps to add Swagger Documentation for REST API
================================================

3. Add swagger & swagger-ui dependencies in project pom.xml

	<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.6.1</version>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.6.1</version>
	</dependency>


4. Create SwaggerConfig class to generate documentation

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket apiDoc() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				   .select()
				   .apis(RequestHandlerSelectors.basePackage("in.ashokit.rest"))
				   .paths(PathSelectors.any())
				   .build();
	}
}

5. In application.properties file add this line of code

spring.mvc.pathmatch.matching-strategy=ANT_PATH_MATCHER


6. Run the application and access Swagger documentation in browser.


Json Doc URL : http://localhost:8080/v2/api-docs

UI Doc URL : http://localhost:8080/swagger-ui.html#/

7. Refer docs.json file that contains the documentation details present in the following path

Path: src/main/resources
*/