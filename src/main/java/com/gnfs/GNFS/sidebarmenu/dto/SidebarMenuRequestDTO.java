package com.gnfs.GNFS.sidebarmenu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SidebarMenuRequestDTO {

	@JsonProperty("menu_name")
    private String menuName;
	
	@JsonProperty("icon_name")
    private String iconName;
    private String route;
    
    @JsonProperty("parent_id")
    private Integer parentId; // optional
}
