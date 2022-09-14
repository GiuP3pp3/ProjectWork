package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Profile;

public interface ProfileDao extends CrudRepository<Profile, Integer> {
	
}
