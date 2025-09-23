package com.gnfs.GNFS.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Table(name = "fire_system")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "registrationForm") // don't include collection in toString
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // only use ID
public class EquipFireFightingSystem {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	
	@ManyToMany(mappedBy="equipFireFightingSystems")
	private Set<RegistrationForm> registrationForm;
}
