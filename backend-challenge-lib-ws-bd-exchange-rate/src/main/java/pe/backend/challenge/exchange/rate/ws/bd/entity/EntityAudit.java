package pe.backend.challenge.exchange.rate.ws.bd.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class EntityAudit {
	
	@Column(name = "CREATE_USER", nullable = false, updatable = false)
	@CreatedBy
	private String createUser;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DATE_CREATE", nullable = false, updatable = false)
	@CreatedDate
	private LocalDate dateUser;

	@Column(name = "MODIFY_USER", nullable = false)
	@LastModifiedBy
	private String modifyUser;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DATE_MODIFY", nullable = false)
	@LastModifiedDate
	private LocalDate dateModify;

}
