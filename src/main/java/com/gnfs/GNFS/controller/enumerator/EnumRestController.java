package com.gnfs.GNFS.controller.enumerator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gnfs.GNFS.dto.enums.EnumResponse;
import com.gnfs.GNFS.dto.enums.PaymentModeEnumDTO;
import com.gnfs.GNFS.dto.finance.BillTypeEnumDTO;
import com.gnfs.GNFS.entity.BillTypeEnum;
import com.gnfs.GNFS.entity.CertificationTypeEnum;
import com.gnfs.GNFS.entity.IncidentPriorityEnum;
import com.gnfs.GNFS.entity.PaymentModeEnum;
import com.gnfs.GNFS.entity.StatusEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/enums")
public class EnumRestController {

	@GetMapping("/bill-types")
	public List<BillTypeEnumDTO> getBillTypes() {
        return Arrays.stream(BillTypeEnum.values())
                .map(BillTypeEnumDTO::fromEnum)
                .collect(Collectors.toList());
    }
	
	@GetMapping("/payment-mode")
	public List<PaymentModeEnumDTO> getPaymentModes(){
		return Arrays.stream(PaymentModeEnum.values())
				.map(PaymentModeEnumDTO :: fromEnum)
				.toList();
	}
	
	@GetMapping("/status")
	public List<StatusEnum> listStatus(){
		return Arrays.stream(StatusEnum.values()).toList();
	}
	
	@GetMapping("/incident-priority")
	public List<EnumResponse> listPriorities(){
		return Arrays.stream(IncidentPriorityEnum.values())
				.map(priority -> new EnumResponse(priority.name(), priority.getDisplayName()))
				.toList();
	}
	
	
	@GetMapping("/certificate-type")
	public List<CertificationTypeEnum> listCertification(){
		return Arrays.stream(CertificationTypeEnum.values()).toList();
	}
	
	//endpoint that can be used for all enum api
//		@GetMapping("/{enumName}")
//		public ResponseEntity<List<EnumResponse>> getEnumValues(@PathVariable String enumName) {
//	        try {
//	            String packageName = "com.gnfs.GNFS.entity"; // adjust if your enums are in another package
//	            Class<?> enumClass = Class.forName(packageName + "." + enumName);
//
//	            if (!enumClass.isEnum()) {
//	                return ResponseEntity.badRequest().build();
//	            }
//
//	            @SuppressWarnings("unchecked")
//	            List<EnumResponse> values = EnumUtils.toEnumResponseList((Class<? extends Enum>) enumClass);
//	            return ResponseEntity.ok(values);
//
//	        } catch (ClassNotFoundException e) {
//	            return ResponseEntity.notFound().build();
//	        } catch (Exception e) {
//	            return ResponseEntity.internalServerError().build();
//	        }
//	    }




//1. BillTypeEnum.values()
//		Purpose: Returns an array of all enum constants defined in BillTypeEnum.
//
//		Example:
//		If BillTypeEnum is:
//		
//		###code Example##
//		public enum BillTypeEnum {
//    NEW("New"), RENEW("Renew");
//    // ... rest of the code
//		}
//		### end of code##
//
//		Then BillTypeEnum.values() returns:
//		[BillTypeEnum.NEW, BillTypeEnum.RENEW]
//
//		2. Arrays.stream()
//				Purpose: Converts the array (BillTypeEnum[]) into a Stream of enum values.
//
//				Why Stream?
//				Streams allow functional-style operations (like map, filter, collect) for processing data in a declarative way.
//
//		3. .map(BillTypeEnumDTO::fromEnum)
//				Purpose: Transforms each BillTypeEnum into a BillTypeEnumDTO using the fromEnum static method.
//
//				How it works:
//
//				BillTypeEnumDTO::fromEnum is a method reference equivalent to:
//				billType -> BillTypeEnumDTO.fromEnum(billType)
//
//				Internally, fromEnum (defined in BillTypeEnumDTO) does:
//
//				#### code ####
//				public static BillTypeEnumDTO fromEnum(BillTypeEnum billType) {
//						return new BillTypeEnumDTO(billType.name(), billType.getDisplayName());
//				}
//				#### end of code#####
//
//				Example:
//					BillTypeEnum.NEW → new BillTypeEnumDTO("NEW", "New")
//					BillTypeEnum.RENEW → new BillTypeEnumDTO("RENEW", "Renew")
//
//		4. .collect(Collectors.toList())
//			Purpose: Converts the Stream of BillTypeEnumDTO objects into a List<BillTypeEnumDTO>.
//
//			Result:
//			A list like:
//
//			#### code ###
//			[
//					BillTypeEnumDTO(value="NEW", displayName="New"),
//					BillTypeEnumDTO(value="RENEW", displayName="Renew")
//			]
//			####end of code ###
//
//			Full Example Flow
//		Input: BillTypeEnum.values() → [NEW, RENEW] (enum constants)
//		Stream: Convert array to Stream → Stream.of(NEW, RENEW)
//		Map: Apply fromEnum to each element →
//
//				NEW → BillTypeEnumDTO("NEW", "New")
//				RENEW → BillTypeEnumDTO("RENEW", "Renew")
//
//		Collect: Gather results into a List →
//			[BillTypeEnumDTO("NEW", "New"), BillTypeEnumDTO("RENEW", "Renew")]
//	
//	

}
