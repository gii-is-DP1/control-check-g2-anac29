package org.springframework.samples.petclinic.feeding;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FeedingService {
	
	private FeedingRepository feedRepo;	
	
	@Autowired
	public FeedingService(FeedingRepository feedRepo) {
		this.feedRepo = feedRepo;
	}	
	
	@Transactional
    public List<Feeding> getAll(){
        return feedRepo.findAll();
    }

    @Transactional
    public List<FeedingType> getAllFeedingTypes(){
        return feedRepo.findAllFeedingTypes();
    }

    @Transactional
    public FeedingType getFeedingType(String typeName) {
        return feedRepo.findFeedingByName(typeName);
    }

	@Transactional(rollbackFor = UnfeasibleFeedingException.class)
    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
    	
    	if(!(p.getPet().getType().equals(p.getFeedingType().getPetType()))) {
    		throw new UnfeasibleFeedingException();
    		
    	}else {
    		feedRepo.save(p);
    	}
    
	
	return null;

	}  
}
