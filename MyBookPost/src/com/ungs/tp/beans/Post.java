package com.ungs.tp.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private long id;
	private String contenidoPost;
	private Date fechaCreacion;
	private String puntuacion;
	
	
	public Post(String text,Usuario usuario) {
		this.contenidoPost=text;
		this.fechaCreacion=new Date();
		this.user=usuario;		
	}

	public Post() {
		
	}

	@ManyToOne
	private Usuario user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContenidoPost() {
		return contenidoPost;
	}

	public void setContenidoPost(String contenidoPost) {
		this.contenidoPost = contenidoPost;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(String puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

}
