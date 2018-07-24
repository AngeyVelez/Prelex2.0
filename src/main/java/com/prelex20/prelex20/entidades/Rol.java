package com.prelex20.prelex20.entidades;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "role")
public class Rol {
	private Long id;
    private String name;
    private Set<Usuario> usuarios;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles")
    public Set<Usuario> getUsers() {
        return usuarios;
    }

    public void setUsers(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
