package com.gnfs.GNFS.service.Equipment;

import java.util.List;

import com.gnfs.GNFS.dto.equipment.EquipEscapeMeansRequestDTO;
import com.gnfs.GNFS.dto.equipment.EquipEscapeMeansResponseDTO;


public interface EquipEscapeMeansService {
	
	public List<EquipEscapeMeansResponseDTO> listEscapeMeans();
	public EquipEscapeMeansResponseDTO getEscapeMeansId(Integer id);
	public EquipEscapeMeansResponseDTO createEscapeMeans(EquipEscapeMeansRequestDTO escapeMeansRequestDTO);
	public EquipEscapeMeansResponseDTO updateEscapeMeans(Integer id, EquipEscapeMeansRequestDTO escapeMeansRequestDTO);
	public void deleteEscapeMeans(Integer id);

}
