package com.tiy;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by DTG2 on 09/15/16.
 */
public interface GameRepository extends CrudRepository<Game, Integer> {

    //responsible for querying

    List<Game> findByGenre(String genre);
    List<Game> findByReleaseYear(int year);

    // allow to search by user
    List<Game> findByUser(User user);

    @Query("SELECT g FROM Game g WHERE g.name LIKE ?1%")
    List<Game> findByNameStartsWith(String name);

    //findBy is a required method, Genre is the column.
    // Doesn't have to be implemented. Spring will do it for us
}

