package com.nnk.springboot.unitServiceTest;

import com.nnk.springboot.configuration.Data;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDto;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RatingServiceTest {

@Autowired
    private RatingService ratingService;
    
@MockBean
    private RatingRepository ratingRepository;
    @MockBean
    private UserService userService;
    @MockBean
    private Data data;

private final Rating rating = new Rating(1, "moodysRating", "sandRating", "fitchRating", 1);

@Test
    void saveTest(){
    when(ratingRepository.save(rating)).thenReturn(rating);

    Rating result = ratingService.save(rating);
    
    assertEquals(rating.getId(), result.getId());
}

@Test
void saveWithRatingDtoTest(){
    RatingDto ratingDto = new RatingDto(1, "moodysRating", "sandRating", "fitchRating", 1);
    
    when(ratingRepository.save(any(Rating.class))).thenReturn(rating);
    
    Rating ratingToSave = ratingService.save(ratingDto);
    
    assertEquals(rating.getId(), ratingToSave.getId());
}

@Test
    void findByIdShouldReturnTheExpectedRatingTest(){
    when(ratingRepository.findById(rating.getId())).thenReturn(Optional.of(rating));
    
    Rating result = ratingService.getById(rating.getId());
    
    assertEquals(rating.getId(), result.getId());
}
    
    @Test
    void shouldReturnExceptionIfGetByIdNotFoundRatingTest(){
        Integer id = 1;
        when(ratingRepository.findById(id)).thenReturn(Optional.empty());
        
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () -> ratingService.getById(id));
        
        assertTrue("Rating id: 1 not found.".contains(runtimeException.getMessage()));
    }

@Test
    void findAllShouldReturnAListOfAllRatingTest(){
    List<Rating> ratingList = new ArrayList<>(List.of(rating));
    
    when(ratingRepository.findAll()).thenReturn(ratingList);
    
    List<Rating> result = ratingService.getAll();
    
    assertFalse(result.isEmpty());
}

@Test
void shouldUpdateRatingTest(){
    when(ratingRepository.findById(rating.getId())).thenReturn(Optional.of(rating));
    RatingDto update = new RatingDto(rating.getId(),"newMoodysRating", "newSandRating", "newFitchRating", 5);
    rating.setSand(update.getSand());
    rating.setMoodys(update.getMoodys());
    rating.setFitch(update.getFitch());
    rating.setOrder(update.getOrder());
    when(ratingRepository.save(rating)).thenReturn(rating);
    
    Rating ratingUpdated = ratingService.update(update);
    
    assertEquals(rating, ratingUpdated);
    
    
}

@Test
    void shouldDeleteByIdRatingTest(){
    doNothing().when(ratingRepository).deleteById(1);
    
    ratingService.deleteById(1);
    
    assertDoesNotThrow(() -> ratingService.deleteById(1));
}
}
