package com.gnfs.GNFS.service.drawings;

import java.util.List;

import com.gnfs.GNFS.dto.drawing.DrawingRequestDTO;
import com.gnfs.GNFS.dto.drawing.DrawingResponseDTO;

public interface DrawingService {


	public List<DrawingResponseDTO> listDrawings();
	public DrawingResponseDTO createDrawings(DrawingRequestDTO drawingRequestDTO);
	public DrawingResponseDTO getDrawings(Integer id);
	public DrawingResponseDTO updataDrawings(Integer id, DrawingRequestDTO drawingRequestDTO);
	public void deleteDrawings(Integer id);
}
