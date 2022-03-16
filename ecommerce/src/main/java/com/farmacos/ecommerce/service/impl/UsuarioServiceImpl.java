package com.farmacos.ecommerce.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
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
import com.farmacos.ecommerce.model.Role;
import com.farmacos.ecommerce.model.Usuario;
import com.farmacos.ecommerce.model.dto.request.UsuarioRequest;
import com.farmacos.ecommerce.model.dto.response.UsuarioResponse;
import com.farmacos.ecommerce.repository.UsuarioRepository;
import com.farmacos.ecommerce.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	/*
	 * @Override public Usuario salvarUsuario(Usuario usuario) {
	 * usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha())); return
	 * this.usuarioRepository.save(usuario); }
	 */
	@Override
	public UsuarioResponse alterarUsuario(long id, UsuarioRequest usuario) {

		Usuario usu = this.usuarioRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Usuario não existente"));

		usu.setNome(usuario.getNome());
		usu.setTelefone(usuario.getTelefone());
		usu.setDtNascimento(usuario.getDtNascimento());
		usu.setCargo(usuario.getCargo());
		usu.setStatus(usuario.getStatus());
		usu.setSenha(passwordEncoder.encode(usuario.getSenha()));

		usuarioRepository.save(usu);

		UsuarioResponse usuarioResponse = new UsuarioResponse(usu);

		return usuarioResponse;
	}

	@Override
	public UsuarioResponse status(Long id, StatusUsuario status) {
		Usuario usu = this.usuarioRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Usuario não existente"));
		try {
			usu.setStatus(status);
			this.usuarioRepository.save(usu);

		} catch (Exception e) {
			new Exception("");
		}
		UsuarioResponse usuResponse = new UsuarioResponse(usu);
		return usuResponse;
	}

	@Override // lista todos usuarios
	public List<Usuario> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override // cadastrar novo usuário
	public void saveUsuario(Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		usuario.setRole(Arrays.asList(new Role("ADM")));
		this.usuarioRepository.save(usuario);
	}

	@Override // alterar usuário
	public Usuario getUsuarioID(long id) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		Usuario usuario = null;
		if (optional.isPresent()) {
			usuario = optional.get();

		} else {
			throw new RuntimeException("Usuário não encontrato no ID :: " + id);
		}
		return usuario;

	}

	@Override // paginação
	public Page<Usuario> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return this.usuarioRepository.findAll(pageable);
	}

	/*
	 * public void ativoInativo(long id){ this.usuarioRepository.getById(id); //???
	 * como programar isso? }
	 */

	@Override
	public List<Usuario> findUsuario(String keyword) {
		if (keyword != null) {
			return usuarioRepository.search(keyword);
		}
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario user = usuarioRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Usuario inválido");
		}
		return new User(user.getEmail(), user.getSenha(), mapRoleAuthorities(user.getRole()));
	}

	private Collection<? extends GrantedAuthority> mapRoleAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
