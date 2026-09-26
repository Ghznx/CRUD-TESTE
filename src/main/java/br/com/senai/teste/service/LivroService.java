package br.com.senai.teste.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import br.com.senai.teste.model.Livro;
import br.com.senai.teste.repository.LivroRepository;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro cadastrarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarLivroPorId(Integer id) {
        return livroRepository.findById(id);
    }

    public Optional<Livro> atualizarLivro(Integer id, Livro novosDados) {

        Optional<Livro> livroEncontrado = livroRepository.findById(id);
        
        if(livroEncontrado.isEmpty()) {
            return Optional.empty();
        }
        
        Livro livro = livroEncontrado.get();

        livro.setTitulo(novosDados.getTitulo());
        livro.setAutor(novosDados.getAutor());

        return Optional.of(livroRepository.save(livro));
    }

    public boolean deletarLivro(Integer id) {
        Optional<Livro> livroEncontrado = livroRepository.findById(id);
        
        if(livroEncontrado.isEmpty()) {
            return false;
        }
        
        livroRepository.deleteById(id);
        return true;
    }
}
