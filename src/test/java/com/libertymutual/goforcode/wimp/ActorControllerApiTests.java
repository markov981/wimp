package com.libertymutual.goforcode.wimp;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
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
import com.libertymutual.goforcode.wimp.models.ActorWithMovies;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;



public class ActorControllerApiTests {
	
	private ActorRepository actorRepo; 
	private ActorApiController controller;
	
	
	@Before
	public void setUp() { 
		actorRepo    = mock(ActorRepository.class); 
		controller = new ActorApiController(actorRepo);
		}
	
	
	
	// GetAll
	@Test
	public void test_get_all_returns_all_records_from_database() {
		
		// Arrange
		ArrayList<Actor> actors = new ArrayList<Actor>();
		actors.add(new Actor());
		actors.add(new Actor());
		actors.add(new Actor());				
		when(actorRepo.findAll()).thenReturn(actors); 	
				
		// Act
		List <Actor> actual = controller.getAll();
				
		// Assert
		assertThat(actual.size()).isEqualTo(3);
		assertThat(actual.get(0)).isSameAs(actors.get(0));
		verify(actorRepo).findAll();   
	}	
	
	

	
	
	// GetOne
	@Test
	public void test_get_one() throws ActorNotFoundException{
		
		Date now = new Date();
		
		// Arrange
		Actor actor = new Actor();	
		actor.setActiveSinceYear(1999L);
		actor.setBirthDate(now);
		actor.setFirstName("Bob");
		actor.setId(11L);
		actor.setLastName("Saget");
		actor.setMovie(new ArrayList<Movie>());		
		when(actorRepo.findOne(22L)).thenReturn(actor); 
		
		
		// Act
		Actor actual = controller.getOne(22L);
				
		// Assert
		assertThat(actual.getActiveSinceYear()).isEqualTo(1999L);
		assertThat(actual.getFirstName()).isEqualTo("Bob");
		assertThat(actual.getLastName()).isEqualTo("Saget");
		assertThat(actual.getId()).isSameAs(11L);
		assertThat(actual.getBirthDate()).isEqualTo(now);
		assertThat(actual.getMovies()).isEqualTo(new ArrayList<Movie>());		
		verify(actorRepo).findOne(22L);		
	}
	@Test
	public void test_get_one_thows_ActorNotFoundException_when_no_actor_is_returned() throws ActorNotFoundException{
		try {
			controller.getOne(1);
			fail("The Controller did not throw ActorNotFoundException");  
		}catch(ActorNotFoundException e) {}		
	}		
	
	
	
	
	
	// Update
	@Test
	public void test_update_saves_modified_record_from_database() throws ActorNotFoundException{
			
		// Arrange
		Actor actor = new Actor();				
		when(actorRepo.save(actor)).thenReturn(actor); 
			
		// Act
		Actor actual = controller.update(actor, 22);
			
		// Assert
		assertThat(actual).isSameAs(actor);
		assertThat(actual.getId()).isEqualTo(22);		
	}	
	
	
	
	// Delete
	@Test
	public void test_delete_returns_actor_deleted_when_actor_is_found() throws ActorNotFoundException{
		// Arrange
		Actor actor = new Actor();
		when(actorRepo.findOne(33L)).thenReturn(actor);
		
		// Act
		Actor actual = controller.delete(33L);
		
		// Assert
		assertThat(actor).isSameAs(actual);
		verify(actorRepo).delete(33L);    
		verify(actorRepo).findOne(33L);  	
	}
	
	@Test
	public void test_that_null_is_returned_when_findOne_thows_exc_ActorNotFoundException() {
		// Arrange
		when(actorRepo.findOne(4L)).thenThrow(new EmptyResultDataAccessException(0));
		
		// Act
		Actor actual = controller.delete(4L);
		
		// Assert
		assertThat(actual).isNull(); 
		verify(actorRepo).findOne(4L); 
	}		
		
	
	
	
}