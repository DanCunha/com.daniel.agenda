package com.daniel.agenda.modelo.persistencia.entidades;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.daniel.agenda.modelo.persistencia.enums.TipoUsuarioEnum;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"login"}))
public class Usuario extends ValueObject{
	
	private static final long serialVersionUID = 2151122536670387711L;
	
	private String nome;
	private String login;
	private String senha;
	private Calendar data_nascimento;
	
	@OneToOne
	private Departamento departamento;
	
	@OneToMany
	private Set<Fone> fone;
	
	@Enumerated
	private TipoUsuarioEnum tipoUsuarioEnum;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Set<Fone> getFone() {
		return fone;
	}
	public void setFone(Set<Fone> fone) {
		this.fone = fone;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Calendar getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(Calendar data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	public TipoUsuarioEnum getTipoUsuarioEnum() {
		return tipoUsuarioEnum;
	}
	public void setTipoUsuarioEnum(TipoUsuarioEnum tipoUsuarioEnum) {
		this.tipoUsuarioEnum = tipoUsuarioEnum;
	}
	
	

}
