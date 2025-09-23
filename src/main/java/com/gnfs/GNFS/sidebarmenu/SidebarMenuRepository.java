package com.gnfs.GNFS.sidebarmenu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SidebarMenuRepository  extends JpaRepository<SidebarMenu, Integer>{
//	@Query("SELECT m FROM SidebarMenu m LEFT JOIN FETCH m.children WHERE m.parent IS NULL")
//    List<SidebarMenu> findAllWithChildren();
	
	@Query("SELECT m FROM SidebarMenu m LEFT JOIN FETCH m.children")
    List<SidebarMenu> findAllWithChildren();
}
