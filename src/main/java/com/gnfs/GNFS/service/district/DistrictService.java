package com.gnfs.GNFS.service.district;

import java.util.List;
import java.util.Optional;

import com.gnfs.GNFS.dto.district.DistrictRequestDTO;
import com.gnfs.GNFS.dto.district.DistrictResponseDTO;
import com.gnfs.GNFS.entity.District;
import com.gnfs.GNFS.exceptions.DistrictNotFoundException;

public interface DistrictService {
	
	public List<DistrictResponseDTO> listDistrict();
	public District createDistrict(District district);
	public District updateDistrict(Integer id, DistrictRequestDTO district);
	public Optional<District> getDistrictById(Integer id);
	public void deleteDistrict(Integer id) throws DistrictNotFoundException;
	List<DistrictResponseDTO> listDistricByRegion(Integer regionId);

}
