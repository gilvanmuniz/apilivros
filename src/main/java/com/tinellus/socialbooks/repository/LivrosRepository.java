package com.tinellus.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tinellus.socialbooks.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{
	
}
