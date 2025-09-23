package com.gnfs.GNFS.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gnfs.GNFS.dto.CustomSuccessMessageResponse;
import com.gnfs.GNFS.dto.district.DistrictRequestDTO;
import com.gnfs.GNFS.dto.district.DistrictResponseDTO;
import com.gnfs.GNFS.entity.District;
import com.gnfs.GNFS.entity.Region;
import com.gnfs.GNFS.exceptions.DistrictNotFoundException;
import com.gnfs.GNFS.exceptions.RegionNotFoundException;
import com.gnfs.GNFS.service.district.DistrictService;
import com.gnfs.GNFS.service.region.RegionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/districts")
public class DistrictRestApiController {

	private final DistrictService districtService;
	private final RegionService regionService;
	


	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<DistrictResponseDTO>>>listDistricts(@RequestParam Integer regionId) {
		List<DistrictResponseDTO> district = districtService.listDistricByRegion(regionId);
		
		
		CustomSuccessMessageResponse<List<DistrictResponseDTO>> successMessageResponse = new CustomSuccessMessageResponse<>(
	            "Districts fetched successfully",district
	    );
		return ResponseEntity.ok(successMessageResponse);
	}
	
	@GetMapping("/all")
	public ResponseEntity<CustomSuccessMessageResponse<List<DistrictResponseDTO>>>listAllDistricts() {
		List<DistrictResponseDTO> district = districtService.listDistrict();
		
		
		CustomSuccessMessageResponse<List<DistrictResponseDTO>> successMessageResponse = new CustomSuccessMessageResponse<>(
	            "Districts fetched successfully",district
	    );
		return ResponseEntity.ok(successMessageResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<DistrictResponseDTO>>getDistrictsById(@PathVariable Integer id) {
		District district = districtService.getDistrictById(id).orElseThrow(() -> new DistrictNotFoundException("Could Not find District with ID:" + id));
		
		DistrictResponseDTO response = new DistrictResponseDTO(district.getId(), district.getDistrict() ,district.getRegion().getRegionName(), district.getRegion().getId());

		CustomSuccessMessageResponse<DistrictResponseDTO> successMessageResponse = new CustomSuccessMessageResponse<>(
				"District exist in the database", response
				);
		return ResponseEntity.ok(successMessageResponse);
	}
	
	
//	@PostMapping
//	public ResponseEntity<DistrictResponseDTO>  createDistrict(@RequestBody DistrictRequestDTO districtRequestDTO) {
//		 District district = new District();
//		    district.setDistrict(districtRequestDTO.getDistrictName());
//		    
//		    Region region = regionService.getRegionById(districtRequestDTO.getRegionId())
//		    		.orElseThrow(() -> new RegionNotFoundException("Region With ID " + districtRequestDTO.getRegionId() + " Not Found"));
//		    
//		    district.setRegion(region);
//		    
//		    District savedDistrict = districtService.createDistrict(district);
//		    
//		DistrictResponseDTO response = new DistrictResponseDTO(
//				savedDistrict.getId(),
//				savedDistrict.getDistrict(), 
//				savedDistrict.getRegion().getRegionName());
//		    
//		return new ResponseEntity<> (response, HttpStatus.CREATED);
//		
//	}
	
	
	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<DistrictResponseDTO>>  createDistrict(@RequestBody DistrictRequestDTO districtRequestDTO) {
		 District district = new District();
		    district.setDistrict(districtRequestDTO.getDistrictName());
		    
		    Region region = regionService.getRegionById(districtRequestDTO.getRegionId())
		    		.orElseThrow(() -> new RegionNotFoundException("Region With ID " + districtRequestDTO.getRegionId() + " Not Found"));
		    
		    district.setRegion(region);
		    
		    District savedDistrict = districtService.createDistrict(district);
		    
		DistrictResponseDTO response = new DistrictResponseDTO(
				savedDistrict.getId(),
				savedDistrict.getDistrict(), 
				savedDistrict.getRegion().getRegionName(),
				savedDistrict.getRegion().getId());
		    
		CustomSuccessMessageResponse<DistrictResponseDTO> successMessageResponse = new CustomSuccessMessageResponse<>(
				"A new district created successfully!", response
				);
		
		return new ResponseEntity<>(successMessageResponse, HttpStatus.CREATED);
		
	}
	
	
	
	@PutMapping("{id}")
	public ResponseEntity<CustomSuccessMessageResponse<DistrictResponseDTO>> updateDistrict(@PathVariable Integer id,
			@RequestBody DistrictRequestDTO districtRequestDTO) {

		District updateDistrict = districtService.updateDistrict(id, districtRequestDTO);

		DistrictResponseDTO response = new DistrictResponseDTO(updateDistrict.getId(), updateDistrict.getDistrict(),
				updateDistrict.getRegion().getRegionName(),updateDistrict.getRegion().getId());
		System.out.println("updated district" + response);
		CustomSuccessMessageResponse<DistrictResponseDTO> successMessageResponse = new CustomSuccessMessageResponse<>(
				"District with ID: " + id + "Updated successfully", response);
		return new ResponseEntity<>(successMessageResponse, HttpStatus.OK);
	}
	
	
	@DeleteMapping("{id}")
	public void deleteDistrict(@PathVariable Integer id) {
		try {
			districtService.deleteDistrict(id);
		} catch (DistrictNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
