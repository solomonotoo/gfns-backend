package com.gnfs.GNFS.service.Equipment;

import java.util.List;

import com.gnfs.GNFS.dto.equipment.EquipFireSystemRequestDTO;
import com.gnfs.GNFS.dto.equipment.EquipFireSystemResponseDTO;



public interface EquipFireFightingSystemService {
	
	public List<EquipFireSystemResponseDTO> listFireSystem();
	public EquipFireSystemResponseDTO getFireSystemId(Integer id);
	public EquipFireSystemResponseDTO createFireSystem(EquipFireSystemRequestDTO fireSystemRequestDTO);
	public EquipFireSystemResponseDTO updateFireSystem(Integer id, EquipFireSystemRequestDTO fireSystemRequestDTO);
	public void deleteFireSystem(Integer id);

}
