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
import com.libertymutual.goforcode.wimp.api.ActorApiController;
import com.libertymutual.goforcode.wimp.api.ActorNotFoundException;
import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;



public class ActorModelTests {
		
	Actor actor;
	
	@Before	
	public void setUp() { 
		actor = new Actor();   // NB
	}

	// Constructor
	@Test
	public void test_contructor_for_actor() {		
		actor = new Actor("Jack","Jaskson", 2000L);
		assertThat("Jack").isEqualTo(actor.getFirstName());
		assertThat("Jaskson").isEqualTo(actor.getLastName());
		assertThat(2000L).isEqualTo(actor.getActiveSinceYear());
	}
	
	
	@Test
	public void test_get_set_first_name_for_actor() {			
		actor.setFirstName("Jack");		
		assertEquals("Jack", actor.getFirstName());
	}	
	
	@Test
	public void test_get_set_id_for_actor() {			
		actor.setId(20L);
		assertThat(20L).isEqualTo(actor.getId());
	}

	@Test
	public void test_get_set_last_name_for_actor() {			
		actor.setLastName("Jackson");		
		assertEquals("Jackson", actor.getLastName());
	}
	

	@Test
	public void test_get_set_active_since_year_for_actor() {			
		actor.setActiveSinceYear(1990L); 
		assertThat(1990L).isEqualTo(actor.getActiveSinceYear());
	}	

	
	@Test
	public void test_get_set_birth_date_for_actor() {
		Date now = new Date();
		actor.setBirthDate(now);  
		assertThat(now).isEqualTo(actor.getBirthDate());
	}
	
	
	@Test
	public void test_get_set_movie_assosiated_with_actor() {
		List<Movie> mv = new ArrayList<Movie>();
		actor.setMovie(mv); 
		assertThat(mv).isSameAs(actor.getMovies());
	}	
}