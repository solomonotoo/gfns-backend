package com.gnfs.GNFS.service.region;

import java.util.List;
import java.util.Optional;

import com.gnfs.GNFS.dto.region.RegionRequestDTO;
import com.gnfs.GNFS.dto.region.RegionResponseDTO;
import com.gnfs.GNFS.entity.Region;

public interface RegionService {
	
	public List<RegionResponseDTO> listRegion();
	public Region createRegion(Region region);
	public Region updateRegion(Integer Id, RegionRequestDTO region);
	public Optional<Region> getRegionById(Integer id);
	public void deleteRegion(Integer id);
	

}
