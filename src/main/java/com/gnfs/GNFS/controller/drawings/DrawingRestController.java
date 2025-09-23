package com.gnfs.GNFS.controller.drawings;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gnfs.GNFS.dto.CustomSuccessMessageResponse;
import com.gnfs.GNFS.dto.drawing.DrawingRequestDTO;
import com.gnfs.GNFS.dto.drawing.DrawingResponseDTO;
import com.gnfs.GNFS.service.drawings.DrawingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/drawings")
@RequiredArgsConstructor
public class DrawingRestController {

	private final DrawingService drawingService;

	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<List<?>>> listDrawings() {
		List<?> listDrawings = drawingService.listDrawings();
		CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<List<?>>(
				"Drawing List fetched successfully!", listDrawings);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getDrawings(@PathVariable Integer id) {
		DrawingResponseDTO drawingResponseDTO = drawingService.getDrawings(id);

		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>(
				"Drawing List fetched successfully!", drawingResponseDTO);

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<CustomSuccessMessageResponse<?>> createDrawings(@RequestBody DrawingRequestDTO requestDTO) {
		DrawingResponseDTO drawingResponseDTO = drawingService.createDrawings(requestDTO);

		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Drawing Created successfully!",
				drawingResponseDTO);

		return ResponseEntity.created(URI.create("/api/v1/drawings" + drawingResponseDTO.getId())).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> updateDrawings(@PathVariable Integer id,
			@RequestBody DrawingRequestDTO drawingRequestDTO) {
		DrawingResponseDTO drawingResponseDTO = drawingService.updataDrawings(id, drawingRequestDTO);

		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>(
				"Drawing List fetched successfully!", drawingResponseDTO);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<Void>> deleteDrawings(@PathVariable Integer id) {
		drawingService.deleteDrawings(id);

		CustomSuccessMessageResponse<Void> response = new CustomSuccessMessageResponse<>(
				"Drawings with ID: " + id + " deleted successfully!", null);

		return ResponseEntity.ok(response);
	}

}
