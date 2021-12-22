package pe.backend.challenge.exchange.rate.ws.bd.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import lombok.Data;
import pe.backend.challenge.exchange.rate.ws.util.bd.AppConstants;

@Data
@Entity
@Table(name = AppConstants.ENTITY_TABLE_USER, schema=AppConstants.SCHEMA)
public class EntityUser {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(name="USERNAME")
	private String username;

    @Column(name="PASSWORD")
	private String password;

}
