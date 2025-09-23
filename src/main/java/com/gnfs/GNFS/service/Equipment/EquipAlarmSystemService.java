package com.gnfs.GNFS.service.Equipment;

import java.util.List;

import com.gnfs.GNFS.dto.equipment.EquipAlarmSystemRequestDTO;
import com.gnfs.GNFS.dto.equipment.EquipAlarmSystemResponseDTO;



public interface EquipAlarmSystemService {
	
	public List<EquipAlarmSystemResponseDTO> listAlarmSystem();
	public EquipAlarmSystemResponseDTO getAlarmSystemId(Integer id);
	public EquipAlarmSystemResponseDTO createAlarmSystem(EquipAlarmSystemRequestDTO alarmSystemRequestDTO);
	public EquipAlarmSystemResponseDTO updateAlarmSystem(Integer id, EquipAlarmSystemRequestDTO alarmSystemRequestDTO);
	public void deleteAlarmSystem(Integer id);

}
