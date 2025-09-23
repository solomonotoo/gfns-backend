package com.gnfs.GNFS.service.drawings.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gnfs.GNFS.dto.drawing.DrawingRequestDTO;
import com.gnfs.GNFS.dto.drawing.DrawingResponseDTO;
import com.gnfs.GNFS.entity.Drawing;
import com.gnfs.GNFS.exceptions.DrawingNotFoundException;
import com.gnfs.GNFS.repository.DrawingsRespository;
import com.gnfs.GNFS.service.drawings.DrawingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DrawingServiceImpl implements DrawingService {
	
	private final DrawingsRespository drawingsRepo;
	private final ModelMapper mapper;

	@Override
	public List<DrawingResponseDTO> listDrawings() {
		List<Drawing> listDrawings = drawingsRepo.findAll();
		return listDrawings.stream().map(drawings -> convertFromEntityToDTO(drawings))
				.collect(Collectors.toList());
	}

	@Override
	public DrawingResponseDTO createDrawings(DrawingRequestDTO drawingRequestDTO) {
		Drawing drawing = convertFromDTOToEntity(drawingRequestDTO);
		
		Drawing savedDrawings = drawingsRepo.save(drawing);
		return convertFromEntityToDTO(savedDrawings);
	}

	@Override
	public DrawingResponseDTO getDrawings(Integer id) {
		Drawing drawing = getDrawingId(id);
		return convertFromEntityToDTO(drawing);
	}

	@Override
	public DrawingResponseDTO updataDrawings(Integer id, DrawingRequestDTO drawingRequestDTO) {
		Drawing existingDrawing = getDrawingId(id);
		existingDrawing.setName(drawingRequestDTO.getName());
		existingDrawing.setDescription(drawingRequestDTO.getDescription());
		
		Drawing updatedDrawings = drawingsRepo.save(existingDrawing);
		return convertFromEntityToDTO(updatedDrawings);
	}

	@Override
	public void deleteDrawings(Integer id) {
		Integer countById = drawingsRepo.countById(id);
		
		if(countById == null || countById == 0) {
			throw new DrawingNotFoundException("Could not find Drawings with ID: " + id);
		}
		
		drawingsRepo.deleteById(id);
		
	}
	
	private Drawing getDrawingId(Integer id) {
		return drawingsRepo.findById(id)
				.orElseThrow(() -> new DrawingNotFoundException("Could not find Drawing with ID: " + id));
	}
	
	
	private DrawingResponseDTO convertFromEntityToDTO(Drawing drawing) {
		return mapper.map(drawing, DrawingResponseDTO.class);
	}
	
	private Drawing convertFromDTOToEntity(DrawingRequestDTO drawingRequestDTO) {
		return mapper.map(drawingRequestDTO, Drawing.class);
	}
	

}
