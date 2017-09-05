package com.libertymutual.goforcode.wimp;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
//import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.libertymutual.goforcode.wimp.api.ActorApiController;
import com.libertymutual.goforcode.wimp.api.ActorNotFoundException;
import com.libertymutual.goforcode.wimp.api.MovieApiController;
import com.libertymutual.goforcode.wimp.api.MovieNotFoundException;
import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.repositories.ActorRepository;
import com.libertymutual.goforcode.wimp.repositories.MovieRepository;



public class MovieControllerApiTests {
	
	private ActorRepository actorRepo; 
	private ActorApiController controllerA;
	
	private MovieRepository mvRepo; 
	private MovieApiController controller; 
	
	
	
	@Before
	public void setUp() { 
		actorRepo    = mock(ActorRepository.class); 
		controllerA  = new ActorApiController(actorRepo);
		
		mvRepo       = mock(MovieRepository.class); 
		controller   = new MovieApiController(mvRepo, actorRepo);		
		}
	
	
	
	// GetAll
	@Test
	public void test_get_all_returns_all_records_from_database() {
		
		// Arrange
		ArrayList<Movie> mvs = new ArrayList<Movie>();
		mvs.add(new Movie());
		mvs.add(new Movie());
		mvs.add(new Movie());				
		when(mvRepo.findAll()).thenReturn(mvs); 	
				
		// Act
		List <Movie> actual = controller.getAll();
				
		// Assert
		assertThat(actual.size()).isEqualTo(3);
		assertThat(actual.get(0)).isSameAs(mvs.get(0));
		verify(mvRepo).findAll();   
	}	
	
	
	
	
	// GetOne
	@Test
	public void test_get_a_movie_by_id() throws MovieNotFoundException{
		
		// Arrange
		Movie mv = new Movie();				
		when(mvRepo.findOne(22L)).thenReturn(mv); 
		
		// Act
		Movie actual = controller.getOne(22L);
		
		// Assert
		assertThat(actual).isSameAs(mv);
		verify(mvRepo).findOne(22L);		
	}
	@Test
	public void test_get_one_thows_MovieNotFoundException_when_no_actor_is_returned() throws MovieNotFoundException{
		try {
			controller.getOne(1);
			fail("The Controller did not throw MovieNotFoundException");  
		}catch(MovieNotFoundException e) {}		
	}		
	

	
	
	// Add an Actor to a Movie
	@Test
	public void test_an_actor_is_associated_with_a_movie(){	
		
		// Arrange
		Movie mv    = new Movie();	
		when(mvRepo.findOne(22L)).thenReturn(mv);		
		Actor actor = new Actor();	
		when(actorRepo.findOne(33L)).thenReturn(actor);
		mv.addActor(actor);
			
		// Act
		Movie actual = controller.associateAnActor(22L, actor);
			
		// Assert
		assertThat(actual).isSameAs(mv);
		//assertThat(actual.getId()).isEqualTo(22);						
	}
		
	
	// Update
	@Test
	public void test_update_saves_modified_movie_record_from_database() throws ActorNotFoundException{
			
		// Arrange
		Movie mv = new Movie();				
		when(mvRepo.save(mv)).thenReturn(mv); 
			
		// Act
		Movie actual = controller.update(mv, 22);
			
		// Assert
		assertThat(actual).isSameAs(mv);
		assertThat(actual.getId()).isEqualTo(22);		
	}	
	
	

	

	// Create
	@Test
	public void test_a_movie_record_is_created(){
			
		// Arrange
		Movie mv = new Movie();				
		when(mvRepo.save(mv)).thenReturn(mv); 
			
		// Act
		Movie actual = controller.create(mv);
			
		// Assert
		assertThat(actual).isSameAs(mv);	
	}
	
		
	
	// Delete
	@Test
	public void test_delete_returns_movie_deleted_when_actor_is_found() throws MovieNotFoundException{
		// Arrange
		Movie mv = new Movie();
		when(mvRepo.findOne(33L)).thenReturn(mv);
		
		// Act	
		Movie actual = controller.delete(33L);
		
		// Assert
		assertThat(mv).isSameAs(actual);
		verify(mvRepo).delete(33L);    
		verify(mvRepo).findOne(33L);  	
	}
	
	@Test
	public void test_that_null_is_returned_when_findOne_throws_MovieNotFoundException() {
		// Arrange
		when(mvRepo.findOne(4L)).thenThrow(new EmptyResultDataAccessException(0));
		
		// Act
		Movie actual = controller.delete(4L);
		
		// Assert
		assertThat(actual).isNull(); 
		verify(mvRepo).findOne(4L); 
	}		
		
	
	
	
}