package com.gnfs.GNFS.service.region.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.region.RegionRequestDTO;
import com.gnfs.GNFS.dto.region.RegionResponseDTO;
import com.gnfs.GNFS.entity.Region;
import com.gnfs.GNFS.exceptions.RegionNotFoundException;
import com.gnfs.GNFS.mapper.Mapper;
import com.gnfs.GNFS.repository.RegionRepository;
import com.gnfs.GNFS.service.region.RegionService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService{
	
	private final RegionRepository regionRepo;

	@Override
	public List<RegionResponseDTO> listRegion() {
		List<Region> regions = regionRepo.findAll();
		
		return regions.stream().map(region -> new RegionResponseDTO(
			region.getId(),region.getRegionName()
		)).collect(Collectors.toList());
	}

	@Override
	public Region createRegion(Region region) {
		return regionRepo.save(region);
	}

	@Override
	public Region updateRegion(Integer id, RegionRequestDTO region) {
		return getRegionById(id).map(
				existingRegion -> {
					existingRegion.setRegionName(region.getRegionName());
					return regionRepo.save(existingRegion);
				})
				.orElseThrow(() -> new RegionNotFoundException("Could not find region with ID: " + id));
		
	}

	@Override
	public void deleteRegion(Integer id) {
		Long countById = regionRepo.countById(id);
		
		if(countById == null || countById == 0) {
			throw new RegionNotFoundException("Could not find District ID: " + id);
		}
		regionRepo.deleteById(id);
	}

//	@Override
//	public Region getRegionById(Integer id) {
//		// TODO Auto-generated method stub
//		return regionRepo.findById(id).get();
//	}
	
	@Override
	public Optional<Region> getRegionById(Integer id) {
		// TODO Auto-generated method stub
		return regionRepo.findById(id);
	}

}
