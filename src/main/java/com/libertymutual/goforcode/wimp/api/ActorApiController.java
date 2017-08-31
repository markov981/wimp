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
@RequestMapping("api/actors")
public class ActorApiController {
	
	private ActorRepository actorRepo; 
	private AwardRepository awardRepo; 
	

	public ActorApiController(ActorRepository actorRepo, AwardRepository awardRepo) {
		
		this.actorRepo = actorRepo;
		
		actorRepo.save(new Actor("James", "Shmond", 1970l));
		actorRepo.save(new Actor("Smbd", "Nickolson", 1980l));
		actorRepo.save(new Actor("Else", "Bellman", 1990l));	
	}
	
	
	
	@GetMapping("")
	public List<Actor> getAll(){
		return actorRepo.findAll();	
	}
	
	
	// Awards
	@PostMapping("{actorid}/awards")
	public Actor associateAnAward(@PathVariable long actorid, @RequestBody Award award) {
		
		Actor actor = actorRepo.findOne(actorid);
		award = awardRepo.findOne(award.getId());
		actor.addAward(award);
		actorRepo.save(actor);
		return actor;			
	}		
	
	
	
	@GetMapping("{id}")
	public Actor getOne(@PathVariable long id) throws ActorNotFoundException{
		Actor actor = actorRepo.findOne(id);		
		if(actor == null) {
			throw new ActorNotFoundException(); 
		}	
		// 1st approach 		
		ActorWithMovies newA = new ActorWithMovies();
		newA.setActiveSinceYear(actor.getActiveSinceYear());
		newA.setBirthDate(actor.getBirthDate());
		newA.setMovie(actor.getMovies());
		newA.setFirstName(actor.getFirstName());
		newA.setLastName(actor.getLastName());
			
		return newA;
				
		// 2nd approach 
		// return actor;
	}

	
	

	@DeleteMapping("{id}")
	public Actor delete(@PathVariable long id){
		try {
		Actor actor = actorRepo.findOne(id);
		actorRepo.delete(id);
		return actor;
	} catch(EmptyResultDataAccessException e){
		return null;	
	}
}

	
	@PutMapping("{id}")
	public Actor update(@RequestBody Actor actor, @PathVariable long id) {
		actor.setId(id);
		return actorRepo.save(actor);	
	}
}
