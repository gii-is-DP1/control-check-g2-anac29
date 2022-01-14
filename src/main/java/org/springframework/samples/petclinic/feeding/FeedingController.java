package org.springframework.samples.petclinic.feeding;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerService;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feeding")
public class FeedingController {
	
	
	private static final String VIEWS_FEEDING_CREATE_OR_UPDATE_FORM = "feedings/createOrUpdateFeedingForm";

	
	private final FeedingService feService;
	private final PetService petService;


	
	@Autowired
	public FeedingController(FeedingService feService,PetService petService) {
		this.feService=feService;
		this.petService = petService;
	}

	@ModelAttribute("feedingTypes")
	public Collection<FeedingType> populateFeedingTypes() {
		return this.feService.getAllFeedingTypes();
	}
	

	@ModelAttribute("pets")
	public Collection<Pet> populatePetsTypes() {
		return this.petService.getAllPets();
	}
	
	@InitBinder("feeding")
	public void initFeedingBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	
	@GetMapping(value = "/create")
	public String initCreationForm(ModelMap model) {
		Feeding feeding = new Feeding();
		model.put("feeding", feeding);
		return VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
	}
	
	
	@PostMapping(value = "/create")
	public String processCreationForm(@Valid Feeding feeding, BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			model.put("feeding", feeding);
			return VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
		}
		else {
                    try{
                    	
                    	this.feService.save(feeding);
                    }catch(UnfeasibleFeedingException ex){
                        return VIEWS_FEEDING_CREATE_OR_UPDATE_FORM;
                    }

                    return "redirect:/welcome";
		}
	}

	
	
    
}
