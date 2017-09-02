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



public class MovieModelTests {
	
	
	Movie mv;
	
	@Before	
	public void setUp() { 
		mv = new Movie();   
	}

	
	// Constructor
	@Test
	public void test_contructor_for_movie() {		
		mv = new Movie("Wink","MGM", 1000L);
		assertThat("Wink").isEqualTo(mv.getTitle());
		assertThat("MGM").isEqualTo(mv.getDistributor());
		assertThat(1000L).isEqualTo(mv.getBudget());
	}
	

	@Test
	public void test_that_an_actor_is_added_to_a_movie() {
		Actor act = new Actor();
		mv.addActor(act);
		assertThat(mv.getActors()).contains(act);
	}
		
	@Test
	public void test_get_set_id_for_movie() {			
		mv.setId(20L);
		assertThat(20L).isEqualTo(mv.getId());
	}
		
	@Test
	public void test_get_set_title_for_movie() {			
		mv.setTitle("Wink");		
		assertEquals("Wink", mv.getTitle());
	}
	
	@Test
	public void test_get_set_distributor_for_movie() {			
		mv.setDistributor("MGM");		
		assertEquals("MGM", mv.getDistributor());
	}

	@Test
	public void test_get_set_movie_budget() {			
		mv.setBudget(1990L); 
		assertThat(1990L).isEqualTo(mv.getBudget());
	}	

	
	@Test
	public void test_get_set_release_date_for_a_movie() {
		Date now = new Date();
		mv.setReleaseDate(now);  
		assertThat(now).isEqualTo(mv.getReleaseDate());
	}

	
	@Test
	public void test_get_set_actor_in_a_movie() {
		List<Actor> act = new ArrayList<Actor>();
		mv.setActors(act); 
		assertThat(act).isSameAs(mv.getActors());
	}	
}