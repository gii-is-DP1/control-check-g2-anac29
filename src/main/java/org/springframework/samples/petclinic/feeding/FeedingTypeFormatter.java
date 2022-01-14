package org.springframework.samples.petclinic.feeding;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Component;

@Component
public class FeedingTypeFormatter implements Formatter<FeedingType>{

	
	
	private final FeedingService feeService;

	@Autowired
	public FeedingTypeFormatter(FeedingService feeService) {
		this.feeService = feeService;
	}

    @Override
    public String print(FeedingType ft, Locale locale) {
        return ft.getName();
    }

    @Override
    public FeedingType parse(String text, Locale locale) throws ParseException {
    	FeedingType ft=feeService.getFeedingType(text);
    	if(ft==null) {
    		throw new ParseException("type not found: " + text, 0);
    	}else {
    		return ft;
    	}
    }
    
}
