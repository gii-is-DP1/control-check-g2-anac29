package org.springframework.samples.petclinic.feeding;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.pet.PetType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="feeding_type")
public class FeedingType extends BaseEntity {
    
	@Column(name="name",unique=true)
	@Length(min=5,max=30)
	@NotEmpty
	String name;
	
	@NotEmpty
    String description;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "pet_type_id")
    private PetType petType;
}
