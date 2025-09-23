package com.gnfs.GNFS.sidebarmenu;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.sidebarmenu.dto.SidebarMenuRequestDTO;
import com.gnfs.GNFS.sidebarmenu.dto.SidebarMenuResponseDTO;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SidebarMenuService {

	private final SidebarMenuRepository menuRepo;
	private final ModelMapper mapper;
	
//	public List<SidebarMenu> menuList() {
//	 return	menuRepo.findAll();
//	}
	
	public List<SidebarMenuResponseDTO> menuList() {
		List<SidebarMenu> listMenu = menuRepo.findAll();
		 return	listMenu.stream().map(menu -> convertFromEntityToDTO(menu))
				 .collect(Collectors.toList());
		}
	
//	public List<SidebarMenu> getAllMenuTree() {
//        return menuRepo.findAllWithChildren();
//    }
	
//	public List<SidebarMenuResponseDTO> getAllMenuTree() {
//		List<SidebarMenu> listMenu = menuRepo.findAllWithChildren();
//		return	listMenu.stream().map(menu -> convertFromEntityToDTO(menu))
//				 .collect(Collectors.toList());
//    }
	public List<SidebarMenuResponseDTO> getAllMenuTree() {
		List<SidebarMenu> allMenus = menuRepo.findAllWithChildren();

		// Filter for top-level only
		return allMenus.stream()
			.filter(menu -> menu.getParent() == null)
			.map(this::convertFromEntityToDTO)
			.collect(Collectors.toList());
	}
	
//	public Optional<SidebarMenu> getMenuById(Integer id) {
//        return menuRepo.findById(id);
//    }
	
//	public SidebarMenu getMenuById(Integer id) {
//        return menuRepo.findById(id)
//        		.orElseThrow(() -> new RuntimeException("Parent not found"));
//    }
	
	public SidebarMenuResponseDTO getMenuById(Integer id) {
        SidebarMenu sidebarMenu = menuRepo.findById(id)
        		.orElseThrow(() -> new RuntimeException("Parent not found"));
        
      return convertFromEntityToDTO(sidebarMenu);
    }
	
//	public SidebarMenu createMenu(SidebarMenuRequestDTO dto) {
//		SidebarMenu menu = new SidebarMenu();
//		menu.setMenuName(dto.getMenuName());
//		menu.setIconName(dto.getIconName());
//		menu.setRoute(dto.getRoute());
//		
//		if(dto.getParentId() != null) {
//			SidebarMenu parent = menuRepo.findById(dto.getParentId())
//					.orElseThrow(() -> new RuntimeException("Parent not found"));
//			menu.setParent(parent);
//		}
//		
//		return menuRepo.save(menu);
//	}
	
	public SidebarMenuResponseDTO createMenu(SidebarMenuRequestDTO dto) {
		SidebarMenu sidebarMenu = convertFromDTOToEntity(dto);
		//SidebarMenu
//		SidebarMenu menu = new SidebarMenu();
//		menu.setMenuName(sidebarMenu.getMenuName());
//		menu.setIconName(sidebarMenu.getIconName());
//		menu.setRoute(sidebarMenu.getRoute());
		
		if(dto.getParentId() != null) {
			SidebarMenu parent = menuRepo.findById(dto.getParentId())
					.orElseThrow(() -> new RuntimeException("Parent not found"));
			sidebarMenu.setParent(parent);
		}
		
		SidebarMenu savedSidebarMenu = menuRepo.save(sidebarMenu);
		
		return convertFromEntityToDTO(savedSidebarMenu);
	}
	
	
//	public SidebarMenu updateMenu(Integer id, SidebarMenuRequestDTO dto) {
//		return menuRepo.findById(id)
//				.map(existingMenu -> {
//					existingMenu.setMenuName(dto.getMenuName());
//					existingMenu.setIconName(dto.getIconName());
//					existingMenu.setRoute(dto.getRoute());
//					
//					if(dto.getParentId() != null) {
//						SidebarMenu parent = menuRepo.findById(dto.getParentId())
//								.orElseThrow(() -> new RuntimeException("Parent not found"));
//						existingMenu.setParent(parent);
//					}else {
//						existingMenu.setParent(null);
//					}
//					
//					return menuRepo.save(existingMenu);
//				})
//				.orElseThrow(() -> new RuntimeException("Parent not found"));
//	}
	
	
	public SidebarMenuResponseDTO updateMenu(Integer id, SidebarMenuRequestDTO dto) {
		//SidebarMenu sidebarMenu = convertFromDTOToEntity(dto);
		return menuRepo.findById(id)
				.map(existingMenu -> {
					existingMenu.setMenuName(dto.getMenuName());
					existingMenu.setIconName(dto.getIconName());
					existingMenu.setRoute(dto.getRoute());
					
					if(dto.getParentId() != null) {
						SidebarMenu parent = menuRepo.findById(dto.getParentId())
								.orElseThrow(() -> new RuntimeException("Parent not found"));
						existingMenu.setParent(parent);
					}else {
						existingMenu.setParent(null);
					}
					
					SidebarMenu updatedMenu = menuRepo.save(existingMenu);
					SidebarMenuResponseDTO responseDTO = convertFromEntityToDTO(updatedMenu);
					
					 // Manually set parentId if necessary (ModelMapper may not handle it automatically)
				    if (updatedMenu.getParent() != null) {
				        responseDTO.setParentId(updatedMenu.getParent().getId());
				    }

				    return responseDTO;
				})
				.orElseThrow(() -> new RuntimeException("Parent not found"));
		
		
		
	}
	
	
	public void deleteMenu(Integer id) {
		SidebarMenu menu = menuRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Parent not found"));
		menuRepo.delete(menu);
	}
	
	private SidebarMenuResponseDTO convertFromEntityToDTO(SidebarMenu sidebarMenu) {
		SidebarMenuResponseDTO dto = mapper.map(sidebarMenu, SidebarMenuResponseDTO.class);
		
		// Manually set route (just in case ModelMapper skipped it)
	    dto.setRoute(sidebarMenu.getRoute());

		if (sidebarMenu.getParent() != null) {
			dto.setParentId(sidebarMenu.getParent().getId());
		}

		if (sidebarMenu.getChildren() != null && !sidebarMenu.getChildren().isEmpty()) {
			List<SidebarMenuResponseDTO> childDTOs = sidebarMenu.getChildren().stream()
				.map(this::convertFromEntityToDTO) // ✅ Recursive mapping
				.collect(Collectors.toList());
			dto.setChildren(childDTOs);
		}

		return dto;
	}

	
	private SidebarMenu convertFromDTOToEntity(SidebarMenuRequestDTO sidebarMenuRequestDTO) {
		return mapper.map(sidebarMenuRequestDTO, SidebarMenu.class);
	}
	
	
}
