package com.farmacos.ecommerce.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.farmacos.ecommerce.enums.StatusUsuario;
import com.farmacos.ecommerce.exception.ObjectNotFoundException;
import com.farmacos.ecommerce.model.Cliente;
import com.farmacos.ecommerce.model.Role;
import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.model.dto.request.ClienteRequest;
import com.farmacos.ecommerce.model.dto.response.ClienteResponse;
import com.farmacos.ecommerce.repository.ClienteRepository;
import com.farmacos.ecommerce.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public ClienteResponse alterarUsuario(long id, ClienteRequest client) {
		
		Cliente cli = this.clienteRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Usuario não existente"));

		cli.setDataNasc(null);
		cli.setGenero(client.getGenero());
		cli.setNome(client.getNome());
		cli.setSenha(passwordEncoder.encode(client.getSenha()));
		
		clienteRepository.save(cli);

		ClienteResponse clienteResponse = new ClienteResponse(cli);

		return clienteResponse;
	}

	@Override
	public void status(Long id) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Cliente> getAllClientes() {
		return clienteRepository.findAll();
	}

	@Override
	public void saveCliente(Cliente cliente) {
		cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));

		this.clienteRepository.save(cliente);
	}

	@Override
	public Usuario getClienteID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Cliente> findPaginated(int pageNo, int pageSize, String keyword) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		if (keyword == null) {
			return this.clienteRepository.findAll(pageable);
		}
		return this.clienteRepository.search(keyword, pageable);
	}

	@Override
	public List<Usuario> findCliente(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Cliente user = clienteRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Usuario inválido");
		} 
		return new User(user.getEmail(), user.getSenha(), null);
	}

}
