package com.tinellus.socialbooks.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tinellus.socialbooks.domain.Livro;
import com.tinellus.socialbooks.repository.LivrosRepository;

@RestController
@RequestMapping("/livros")
public class LivrosResources {
	
	@Autowired
	private LivrosRepository livrosRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Livro> listar() {
//		Livro li = new Livro("Rest Aplicado");
//		Livro li2 = new Livro("Git Passo a Passo");
//		List<Livro> livros = new ArrayList<>();
//		//Livro[] livros2 = {li, li2};
//		//livros.add(livros2)
//		livros.add(li);
//		livros.add(li2);				
		return livrosRepository.findAll();
		
		//other option		
		//Livro[] livros = {li, li2};
		//return Arrays.asList(livros);
		
	} //end method to list
	
	@RequestMapping(method = RequestMethod.POST)
	public void savar(@RequestBody Livro livro) {
		livrosRepository.save(livro);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Livro buscar(@PathVariable("id") Long id) {
		return livrosRepository.findById(id).orElse(null);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	public void deletar(@PathVariable("id") Long id) {
		livrosRepository.deleteById(id);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public void atualizar(@RequestBody Livro livro, @PathVariable("id")Long id){
		livro.setId(id);
		livrosRepository.save(livro);
	}

}
