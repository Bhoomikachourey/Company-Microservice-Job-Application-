package com.pack.companies.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.pack.companies.CompanyService;
import com.pack.companies.dto.ReviewMessage;

@Service
public class ReviewMessageConsumer {

	private final CompanyService companyService;

	public ReviewMessageConsumer(CompanyService companyService) {
		super();
		this.companyService = companyService;
	} 
	
	@RabbitListener(queues ="companyRatingQueue")
	public void consumeMessage(ReviewMessage reviewMessage) {
		companyService.updateCompanyRating(reviewMessage);
	}
}
