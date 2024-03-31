package com.fuctura.projeto.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fuctura.projeto.model.Veiculo;
import com.fuctura.projeto.repository.VeiculoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	private final VeiculoRepository repository;

	public VeiculoController(VeiculoRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Veiculo salvar(@RequestBody @Valid Veiculo veiculo) {
		return repository.save(veiculo);
	}

	@GetMapping
	public List<Veiculo> obterTodos() {
		return repository.findAll();
	}

	@GetMapping("{id}")
	public Veiculo acharPorId(@PathVariable Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não encontrado"));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		repository.findById(id).map(veiculo -> {
			repository.delete(veiculo);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não encontrado"));
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Long id, @RequestBody @Valid Veiculo veiculoAtualizado) {
		repository.findById(id).map(veiculo -> {
			veiculo.setPlaca(veiculoAtualizado.getPlaca());
			veiculo.setModelo(veiculoAtualizado.getModelo());
			veiculo.setAno(veiculoAtualizado.getAno());
			veiculo.setValor(veiculoAtualizado.getValor());
			return repository.save(veiculo);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não encontrado"));
	}

}
