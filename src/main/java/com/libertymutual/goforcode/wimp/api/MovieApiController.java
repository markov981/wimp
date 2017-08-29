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
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;



@RestController
@RequestMapping("api/movies")
public class MovieApiController {
	
	private MovieRepository mvRepo; 
	

	public MovieApiController(MovieRepository mvRepo) {		
		this.mvRepo = mvRepo;		
		mvRepo.save(new Movie("One", "ABC", 1000000l));
		mvRepo.save(new Movie("Two", "MGM", 2_000_000l));
		mvRepo.save(new Movie("Google", "XXX", 3000l));				
	}
	
	
	
	@GetMapping("")
	public List<Movie> getAll(){
		return mvRepo.findAll();	
	}
		
	
	@GetMapping("{id}")
	public Movie getOne(@PathVariable long id) throws MovieNotFoundException{
		Movie mv = mvRepo.findOne(id);
		
		if(mv == null) {
			throw new MovieNotFoundException(); // sends back 404 status
		}		
		return mv;		
	}


	@DeleteMapping("{id}")
	public Movie delete(@PathVariable long id){
		try {
			Movie mv = mvRepo.findOne(id);
		mvRepo.delete(id);
		return mv;
	} catch(EmptyResultDataAccessException e){
			return null;	
	}
}
	

	@PostMapping("")
	public Movie create(@RequestBody Movie mv) {
		return mvRepo.save(mv);	
	}
	

	@PutMapping("{id}")
	public Movie update(@RequestBody Movie mv, @PathVariable long id) {
		mv.setId(id);
		return mvRepo.save(mv);	
	}
}