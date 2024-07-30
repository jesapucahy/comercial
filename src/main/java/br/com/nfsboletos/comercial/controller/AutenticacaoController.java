package br.com.nfsboletos.comercial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nfsboletos.comercial.model.DadosAutenticacao;
import br.com.nfsboletos.comercial.model.Usuario;
import br.com.nfsboletos.comercial.repository.NfRepository;
import br.com.nfsboletos.comercial.repository.UsuarioRepository;
import br.com.nfsboletos.comercial.security.DadosTokenJWT;
import br.com.nfsboletos.comercial.security.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Validated DadosAutenticacao dados) {
    	String login = dados.login();
    	String senha = dados.senha();
    	//User abaixo e if criados apenas para facilitar testes por parte de vocês da Alpe. Obviamente usuários já estariam em banco e não criaria esse usuário dessa forma.    	
    	UserDetails usuario = usuarioRepository.findByLogin("eduardo.lins@alpe.com.br");
    	if(usuario==null) {
    		Usuario user = new Usuario();
    		user.setLogin("eduardo.lins@alpe.com.br");
    		user.setSenha("$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.");
    		user.setUsuarioMaster(true);
    		usuarioRepository.save(user);
    		login = user.getLogin();
    		senha = user.getSenha();
    	}
        var authenticationToken = new UsernamePasswordAuthenticationToken(login, senha);
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT =tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}