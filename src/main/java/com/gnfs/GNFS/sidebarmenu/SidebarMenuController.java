package com.gnfs.GNFS.sidebarmenu;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gnfs.GNFS.sidebarmenu.dto.SidebarMenuRequestDTO;
import com.gnfs.GNFS.sidebarmenu.dto.SidebarMenuResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/sidebarmenu")
@RequiredArgsConstructor
public class SidebarMenuController {
	
	private final SidebarMenuService menuService;
	
	@GetMapping
	public ResponseEntity<List<?>> listMenu(){
		List<?> menu = menuService.getAllMenuTree();
		return ResponseEntity.ok(menu);
	}
	
	@GetMapping("/table")
	public ResponseEntity<List<?>> listMenuTable(){
		List<?> menu = menuService.menuList();
		return ResponseEntity.ok(menu);
	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<SidebarMenuResponseDTO> getMenuById(@PathVariable Integer id){
//		SidebarMenu menu =menuService.getMenuById(id)
//				.orElseThrow(() -> new RuntimeException("Parent not found"));
//		SidebarMenuResponseDTO responseDTO = new SidebarMenuResponseDTO(
//				menu.getId(), menu.getMenuName(), menu.getIconName(), menu.getRoute(),menu.getParent().getId());
//				return ResponseEntity.ok(responseDTO);
//	}
	

	@GetMapping("/{id}")
	public ResponseEntity<?> getMenuById(@PathVariable Integer id){
		SidebarMenuResponseDTO menu =menuService.getMenuById(id);
		//Integer parentId = menu.getParent() != null ? menu.getParent().getId() : null;
//		SidebarMenuResponseDTO responseDTO = new SidebarMenuResponseDTO(
//				menu.getId(), menu.getMenuName(), menu.getIconName(), menu.getRoute(),parentId);
				return ResponseEntity.ok(menu);
	}
	
	@PostMapping
	public ResponseEntity<?> createMenu(@RequestBody SidebarMenuRequestDTO dto){
		return ResponseEntity.ok(menuService.createMenu(dto));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMenu(@PathVariable Integer id, @RequestBody SidebarMenuRequestDTO dto){
		return ResponseEntity.ok(menuService.updateMenu(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMenu(@PathVariable Integer id){
		menuService.deleteMenu(id);
		return ResponseEntity.ok().build();
		
	}

}
