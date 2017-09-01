package com.libertymutual.goforcode.wimp.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.repositories.AwardRepository;




@RestController
@RequestMapping("api/awards")
public class AwardApiController {
	

	private AwardRepository awardRepo; 	

	public AwardApiController(AwardRepository awardRepo) {
		this.awardRepo = awardRepo; 
		
		awardRepo.save(new Award("EarthSea", "Locus", 1111));
		awardRepo.save(new Award("LeftHand", "Hugo", 2222));
		awardRepo.save(new Award("Dispossessed", "Nebula", 3333));		
	}
	
	
	@GetMapping("")
	public List<Award> getAll(){
		return awardRepo.findAll();	
	}	
}
