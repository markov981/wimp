package com.libertymutual.goforcode.wimp.api;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.ActorWithMovies;
import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.AwardRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;



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
	
	
	// Awards
//	@PostMapping("{actorid}/awards")
//	public Actor associateAnAward(@PathVariable long actorid, @RequestBody Award award) {
//		
//		Actor actor = actorRepo.findOne(actorid);
//		award = awardRepo.findOne(award.getId());
//		actor.addAward(award);
//		actorRepo.save(actor);
//		return actor;			
//	}		
	
	
}
