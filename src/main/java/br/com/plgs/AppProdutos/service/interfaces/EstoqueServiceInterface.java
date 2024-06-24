package br.com.plgs.AppProdutos.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.plgs.AppProdutos.model.Estoque;

public interface EstoqueServiceInterface {
	
	Estoque save(Estoque estoque);
	Optional<Estoque> findById(Long id);
	List<Estoque> findAll();
	Estoque update(Estoque estoque);
	void delete(Long id);

}
