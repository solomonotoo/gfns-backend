//package com.gnfs.GNFS.mapper;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
//
//import com.gnfs.GNFS.dto.staffRegistration.DistrictReferenceDTO;
//import com.gnfs.GNFS.dto.staffRegistration.RegionReferenceDTO;
//import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationCreateRequestDTO;
//import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationResponseDTO;
//import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationUpdateRequestDTO;
//import com.gnfs.GNFS.entity.District;
//import com.gnfs.GNFS.entity.Region;
//import com.gnfs.GNFS.entity.StaffRegistration;
//
//@Mapper(componentModel = "spring")
//public interface StaffRegistrationMapper2 {
////	//StaffRegistrationMapper MAPPER = Mappers.getMapper(StaffRegistrationMapper.class);
////
////	//NB @Mapping(target = "id", ignore = true) is used here because of inherited id
////	//convert from dto to entity
////	@Mapping(target = "id", ignore = true)
////	StaffRegistration mapToStaffRegistrationEntityObject(StaffRegistrationCreateRequestDTO dto);
////	
////	
////	//NB @Mapping(target = "id", ignore = true) is used here because of inherited id
////	 // Update DTO → Entity (full copy)
////    @Mapping(target = "id", ignore = true) // Prevent overwriting ID
////	void updateStaffRegistrationEntityObjectFromDTO(StaffRegistrationUpdateRequestDTO dto, @MappingTarget StaffRegistration entity);
////	
////	
////    
//// // Entity → Response DTO
////    //@Mapping(target = "fullName", expression = "java(staffRegistration.getFullName())" ) is used to make getFullName method in the StaffRei
////    @Mapping(target = "fullName", expression = "java(staffRegistration.getFullName())" )
////    StaffRegistrationResponseDTO toResponseDTO(StaffRegistration entity);
////    
//   	
//	
//	
//	 // DTO → Entity (create)
//    @Mapping(target = "id", ignore = true)
//    StaffRegistration mapToStaffRegistrationEntityObject(StaffRegistrationCreateRequestDTO dto);
//
//    // DTO → Entity (update)
//    @Mapping(target = "id", ignore = true)
//    void updateStaffRegistrationEntityObjectFromDTO(StaffRegistrationUpdateRequestDTO dto, @MappingTarget StaffRegistration entity);
//
//    // Entity → DTO (response)
//    @Mapping(target = "fullName", expression = "java(entity.getFullName())")
//    StaffRegistrationResponseDTO toResponseDTO(StaffRegistration entity);
//
//    // Helper mappers
//    default RegionReferenceDTO regionToRegionReferenceDTO(Region region) {
//        if (region == null) return null;
//        return new RegionReferenceDTO(region.getId());
//    }
//
//    default DistrictReferenceDTO districtToDistrictReferenceDTO(District district) {
//        if (district == null) return null;
//        return new DistrictReferenceDTO(district.getId());
//    }
//
//    default Region regionReferenceDTOToRegion(RegionReferenceDTO dto) {
//        if (dto == null) return null;
//        Region region = new Region();
//        region.setId(dto.id());
//        return region;
//    }
//
//    default District districtReferenceDTOToDistrict(DistrictReferenceDTO dto) {
//        if (dto == null) return null;
//        District district = new District();
//        district.setId(dto.id());
//        return district;
//    }
//	
//    
//    
//    
//	
//}


