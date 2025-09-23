package com.gnfs.GNFS.mapper;

import com.gnfs.GNFS.dto.region.RegionRequestDTO;
import com.gnfs.GNFS.entity.Region;

public class Mapper {

	public RegionRequestDTO ConvertRegionToRegionDTO(Region region) {
		RegionRequestDTO regionRequestDTO = new RegionRequestDTO();
		regionRequestDTO.setRegionName(region.getRegionName());
		
		
		return regionRequestDTO;
	}
	
	public Region ConvertRegionDTOToRegionEntiy(RegionRequestDTO regionRequestDTO) {
		Region region = new Region();
		region.setRegionName(regionRequestDTO.getRegionName());
		
		
		return region;
	}
}
