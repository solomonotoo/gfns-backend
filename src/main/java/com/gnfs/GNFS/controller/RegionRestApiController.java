package com.gnfs.GNFS.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gnfs.GNFS.dto.CustomSuccessMessageResponse;
import com.gnfs.GNFS.dto.region.RegionRequestDTO;
import com.gnfs.GNFS.dto.region.RegionResponseDTO;
import com.gnfs.GNFS.entity.Region;
import com.gnfs.GNFS.exceptions.RegionNotFoundException;
import com.gnfs.GNFS.service.region.RegionService;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/regions")
@RequiredArgsConstructor
public class RegionRestApiController {

	private final RegionService regionService;

	@GetMapping
	public ResponseEntity<List<RegionResponseDTO>> listAllRegions() {
		List<RegionResponseDTO> regions = regionService.listRegion();
		return ResponseEntity.ok(regions);
	}
	


	@PostMapping()
	public ResponseEntity<CustomSuccessMessageResponse<RegionResponseDTO>> postMethodName(@RequestBody RegionRequestDTO regionRequestDTO) {

		Region region = new Region();
		region.setRegionName(regionRequestDTO.getRegionName());
		
		Region savedRegion = regionService.createRegion(region);
		
		RegionResponseDTO response = new RegionResponseDTO(savedRegion.getId(), savedRegion.getRegionName());

		CustomSuccessMessageResponse<RegionResponseDTO> successMessageResponse = new CustomSuccessMessageResponse<>(
				"Region created successfully", response);

		return new ResponseEntity<CustomSuccessMessageResponse<RegionResponseDTO>>(successMessageResponse,
				HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<RegionResponseDTO>> getRegionById(@PathVariable Integer id){
		Region region = regionService.getRegionById(id).orElseThrow(() -> new RegionNotFoundException("Region with ID " + id + " not found"));
	
		
		RegionResponseDTO regionResponseDTO = new RegionResponseDTO(region.getId(), region.getRegionName());
		
		System.out.println(regionResponseDTO);
		CustomSuccessMessageResponse<RegionResponseDTO> successMessageResponse = new CustomSuccessMessageResponse<>(
				"Region with id " +id+ " found" , regionResponseDTO);
		
	
		return ResponseEntity.ok(successMessageResponse);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<RegionResponseDTO>> updateRegion(@PathVariable Integer id,
			@RequestBody RegionRequestDTO regionRequestDTO) {
		
		Region updateRegion = regionService.updateRegion(id, regionRequestDTO);
		
		//response dto
		RegionResponseDTO response = new RegionResponseDTO(updateRegion.getId(), updateRegion.getRegionName());
		
		//custom success message
		CustomSuccessMessageResponse<RegionResponseDTO> successMessageResponse = new CustomSuccessMessageResponse<RegionResponseDTO>("Region with ID: " + id + " updated successfully", response);

		return ResponseEntity.ok(successMessageResponse);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Map<String, Object>> deleteRegion(@PathVariable Integer id){
		regionService.deleteRegion(id);
		
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Region with ID: " + id + " deleted");
		
		return ResponseEntity.ok(response);
		
	}

}
