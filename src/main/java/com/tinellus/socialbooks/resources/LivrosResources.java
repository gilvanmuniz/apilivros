package com.tinellus.socialbooks.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<Void> savar(@RequestBody Livro livro) {
		livro = livrosRepository.save(livro);
		 URI uri  = ServletUriComponentsBuilder.fromCurrentRequest()
				 .path("/{id}").buildAndExpand(livro.getId()).toUri();
		   return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		Livro livro =  livrosRepository.findById(id).orElse(null);
		if(livro == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(livro);
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
