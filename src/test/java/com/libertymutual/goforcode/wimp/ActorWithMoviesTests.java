package com.libertymutual.goforcode.wimp;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.libertymutual.goforcode.wimp.api.ActorApiController;
import com.libertymutual.goforcode.wimp.api.ActorNotFoundException;
import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.ActorWithMovies;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;

public class ActorWithMoviesTests extends Actor {

	ActorWithMovies actorW;
	
	@Before	
	public void setUp() { 
		actorW = new ActorWithMovies();  
	}
	
	
	@Test
	public void test_get_movies_method() {
		List<Movie> mv = new ArrayList<Movie>();
		actorW.setMovie(mv); 
		assertThat(mv).isSameAs(actorW.noReallyMovies());
	}	
}	

//@JsonProperty
//public List<Movie> noReallyMovies(){
//	return getMovies();
//}
