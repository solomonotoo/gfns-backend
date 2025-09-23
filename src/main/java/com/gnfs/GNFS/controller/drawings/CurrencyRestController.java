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
import com.gnfs.GNFS.dto.drawing.CurrencyRequestDTO;
import com.gnfs.GNFS.dto.drawing.CurrencyResponseDTO;
import com.gnfs.GNFS.service.drawings.CurrencyService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/currencies")
@AllArgsConstructor
public class CurrencyRestController {

	private final CurrencyService currencyService;

	@GetMapping
	public ResponseEntity<CustomSuccessMessageResponse<?>> listCurrencies(){
		List<CurrencyResponseDTO> listCurrency = currencyService.listCurrency();
		
	    CustomSuccessMessageResponse<List<?>> response = new CustomSuccessMessageResponse<List<?>>("Currency list fetched successfully!", listCurrency);
		
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> getCurrencyById(@PathVariable Integer id) {
		CurrencyResponseDTO responseDTO = currencyService.getCurrency(id);

		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Currency exist in the database!",
				responseDTO);

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<?> createCurrency(@RequestBody CurrencyRequestDTO currencyRequestDTO) {
		CurrencyResponseDTO currencyResponseDTO = currencyService.createCurrency(currencyRequestDTO);

		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Currency exist in the database!",
				currencyResponseDTO);

		return ResponseEntity.created(URI.create("/api/v1/currencies")).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<?>> updateCurrency(@PathVariable Integer id,
			@RequestBody CurrencyRequestDTO currencyRequestDTO) {
		CurrencyResponseDTO responseDTO = currencyService.updataCurrency(id, currencyRequestDTO);

		CustomSuccessMessageResponse<?> response = new CustomSuccessMessageResponse<>("Currency updated successfully!",
				responseDTO);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CustomSuccessMessageResponse<Void>> deleteCurrency(@PathVariable Integer id) {
		currencyService.deleteCurrency(id);

		CustomSuccessMessageResponse<Void> response = new CustomSuccessMessageResponse<>(
				"Currency updated successfully!", null);

		return ResponseEntity.ok(response);
	}

}
