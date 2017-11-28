package com.ungs.tp.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Usuario {
	@Id
	@GeneratedValue
	private long id;
	private String nombre;
	private String apellido;
	private String contraseña;
	private String nombreUsuario;
	private String nroCuitCuil;
	private String tipo;
	private String email;
	
	public Usuario(){
		
	}
	public Usuario(String nombre, String apellido, String nroCuitCuil,
			String tipo, String email, String nombreUsuario, String contraseña) {
		this.nombre=nombre;
		this.apellido=apellido;
		this.nroCuitCuil=nroCuitCuil;
		this.tipo=tipo;
		this.email=email;
		this.nombreUsuario=nombreUsuario;
		this.contraseña=contraseña;

	}

	public String getNroCuitCuil() {
		return nroCuitCuil;
	}

	public void setNroCuitCuil(String nroCuitCuil) {
		this.nroCuitCuil = nroCuitCuil;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private List<Post> postId = new ArrayList<Post>();


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public List<Post> getPostId() {
		return postId;
	}
	
	public void setPostId(List<Post> postId) {
		this.postId = postId;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void agregarPost(Post p){
		
		if (!postId.contains(p)){					
			this.postId.add(p);
			p.setUser(this);
		}
	}
	
	public void eliminarPost(Post p){
		
		if (postId.contains(p)){
			this.postId.remove(p);
			p.setUser(null);
		}
	
	}

}
