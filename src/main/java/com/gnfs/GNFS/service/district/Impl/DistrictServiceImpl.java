package com.gnfs.GNFS.service.district.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.district.DistrictRequestDTO;
import com.gnfs.GNFS.dto.district.DistrictResponseDTO;
import com.gnfs.GNFS.entity.District;
import com.gnfs.GNFS.entity.Region;
import com.gnfs.GNFS.exceptions.DistrictNotFoundException;
import com.gnfs.GNFS.exceptions.RegionNotFoundException;
import com.gnfs.GNFS.repository.DistrictRepository;
import com.gnfs.GNFS.service.district.DistrictService;
import com.gnfs.GNFS.service.region.RegionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService{
	
	private final DistrictRepository distRepo;
	private final RegionService regionService;
	
	@Override
	public List<DistrictResponseDTO> listDistrict() {
		List<District> districts = distRepo.findAll();
		return districts.stream().map(
				d -> new DistrictResponseDTO(
						d.getId(), d.getDistrict(),d.getRegion().getRegionName(),
					    d.getRegion().getId())
				).collect(Collectors.toList());
	}
	
	@Override
	public List<DistrictResponseDTO> listDistricByRegion(Integer regionId) {
		List<District> districts = distRepo.findByRegionId(regionId);
		return districts.stream().map(
				d -> new DistrictResponseDTO(
						d.getId(), d.getDistrict(),d.getRegion().getRegionName(),
					    d.getRegion().getId())
				).collect(Collectors.toList());
	}

	@Override
	public District createDistrict(District district) {
		return distRepo.save(district);
	}

	@Override
	public District updateDistrict(Integer id, DistrictRequestDTO district) {
		return getDistrictById(id).map(
				existingDistrict -> {
					existingDistrict.setDistrict(district.getDistrictName());
					
					 // Fetch Region by ID
					Region region = regionService.getRegionById(district.getRegionId())
							.orElseThrow(() -> new RegionNotFoundException("District with ID " + id + " not found"));
					existingDistrict.setRegion(region);
					
					return distRepo.save(existingDistrict);
				}
				)
		.orElseThrow(() -> new DistrictNotFoundException("District with ID " + id + " not found"));
	}

	@Override
	public void deleteDistrict(Integer id) throws DistrictNotFoundException{
		Long countById = distRepo.countById(id);
		
		if(countById == null || countById == 0) {
			throw new DistrictNotFoundException("Could not find District ID: " + id);
		}
		
		distRepo.deleteById(id);
		
	}

	@Override
	public Optional<District> getDistrictById(Integer id) {
		// TODO Auto-generated method stub
		return distRepo.findById(id);
	}

}
