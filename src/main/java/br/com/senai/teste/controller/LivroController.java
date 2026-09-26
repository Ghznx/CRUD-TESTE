package br.com.senai.teste.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;

import br.com.senai.teste.model.Livro;
import br.com.senai.teste.service.LivroService;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {
    
    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<Livro> cadastrarLivro(@Valid @RequestBody Livro livro) {

        Livro livroCadastrado = livroService.cadastrarLivro(livro);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(livroCadastrado);
    }

    @GetMapping 
    public ResponseEntity<List<Livro>> listarLivros() {
        
        List<Livro> livros = livroService.listarLivros();
        
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable Integer id) {
        
        Optional<Livro> livros = livroService.buscarLivroPorId(id);

        if(livros.isPresent()) {
            return ResponseEntity.ok(livros.get());
        } 

        return ResponseEntity.notFound().build();
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Integer id, @RequestBody Livro novosDados) {
        
        Optional<Livro> livroAtualizado = livroService.atualizarLivro(id, novosDados);

        if(livroAtualizado.isPresent()) {
            return ResponseEntity.ok(livroAtualizado.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Integer id) {
        
        boolean deletado = livroService.deletarLivro(id);

        if(deletado) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
