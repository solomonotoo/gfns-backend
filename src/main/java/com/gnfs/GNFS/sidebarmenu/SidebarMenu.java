package com.gnfs.GNFS.sidebarmenu;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
public class SidebarMenu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "menu_name")
	private String menuName;
	
	@Column(name = "icon_name")
	private String iconName;
	
	// Optional: Add route for top-level and submenus
	private String route;
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	@JsonIgnore
	private SidebarMenu parent;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SidebarMenu> children;

	
	
	
	
}
