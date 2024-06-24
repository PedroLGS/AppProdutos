package br.com.plgs.AppProdutos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.plgs.AppProdutos.model.Estoque;
import br.com.plgs.AppProdutos.model.Produto;
import br.com.plgs.AppProdutos.repository.EstoqueRepository;
import br.com.plgs.AppProdutos.repository.ProdutoRepository;
import br.com.plgs.AppProdutos.service.interfaces.EstoqueServiceInterface;

@Service
public class EstoqueService implements EstoqueServiceInterface {
	
	@Autowired
	private EstoqueRepository estoqueRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public Estoque save(Estoque estoque) {
		// verificar se o produto existe, caso não, avisar
		if(estoque.getProduto().getId() != null) {
			
			// buscar o produto no banco de dados
			Optional<Produto> findProduto = produtoRepository.findById(estoque.getProduto().getId());
			if(!findProduto.isEmpty()) {
				//ñ vazio
				estoque.setProduto(findProduto.get());
				return estoqueRepository.save(estoque);
			} else {
				//vazio
				System.out.println("Produto não encontrado: " + estoque.getProduto().getId());
				return null;
			}
			
		} else {
			System.out.println("Produto não encontrado!");
			return null;
		}

	}

	@Override
	public Optional<Estoque> findById(Long id) {
		return estoqueRepository.findById(id);
	}

	@Override
	public List<Estoque> findAll() {
		return estoqueRepository.findAll();
	}

	@Override
	public Estoque update(Estoque estoque) {
		// pesquisar se o estoque existe
		Optional<Estoque> findEstoque = estoqueRepository.findById(estoque.getId());
		
		// se o estoque existir, atualizo (persisto)
		if(findEstoque.isPresent()) {
			// criar um novo objeto do produto e lançar os dados do objeto de parâmetro neste novo objeto e gravar
			Estoque updEstoque = findEstoque.get();
			updEstoque.setQuantidade(estoque.getQuantidade());
			//retornar o objeto gravado
			return estoqueRepository.save(updEstoque); // UPDATE	
		} else {
			// INSERT
			return save(estoque);
		}
		
	}

	@Override
	public void delete(Long id) {
		estoqueRepository.deleteById(id);
		
	}

}
