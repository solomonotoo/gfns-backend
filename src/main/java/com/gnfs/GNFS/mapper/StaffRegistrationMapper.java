package com.gnfs.GNFS.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.gnfs.GNFS.dto.referenceDTO.StaffRegistrationReferenceDTO;
import com.gnfs.GNFS.dto.staffRegistration.DistrictReferenceDTO;
import com.gnfs.GNFS.dto.staffRegistration.RegionReferenceDTO;
import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationCreateRequestDTO;
import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationResponseDTO;
import com.gnfs.GNFS.dto.staffRegistration.StaffRegistrationUpdateRequestDTO;
import com.gnfs.GNFS.entity.District;
import com.gnfs.GNFS.entity.Region;
import com.gnfs.GNFS.entity.StaffRegistration;

@Mapper(componentModel = "spring")
public interface StaffRegistrationMapper {

    // ===============================
    // 1️  Full DTO ↔ Entity mappings
    // ===============================

    // Create
    @Mapping(target = "id", ignore = true)
    StaffRegistration mapToStaffRegistrationEntityObject(StaffRegistrationCreateRequestDTO dto);

    // Update (in-place)
    @Mapping(target = "id", ignore = true)
    void updateStaffRegistrationEntityObjectFromDTO(StaffRegistrationUpdateRequestDTO dto, @MappingTarget StaffRegistration entity);

    // Response
    @Mapping(target = "fullName", expression = "java(entity.getFullName())")
    StaffRegistrationResponseDTO toResponseDTO(StaffRegistration entity);


    // ==================================
    // 2️  Reference-only ID-based mapping
    // ==================================

    // Convert Entity → Reference DTO
    default StaffRegistrationReferenceDTO toReferenceDTO(StaffRegistration entity) {
        if (entity == null) return null;
        return new StaffRegistrationReferenceDTO(entity.getId());
    }

    // Convert Reference DTO → Entity (only sets ID)
    default StaffRegistration mapReferenceDTOToEntity(StaffRegistrationReferenceDTO dto) {
        if (dto == null) return null;
        StaffRegistration staff = new StaffRegistration();
        staff.setId(dto.id());
        return staff;
    }


    // ===============================
    // 3️  Region / District helpers
    // ===============================

    default RegionReferenceDTO regionToRegionReferenceDTO(Region region) {
        if (region == null) return null;
        return new RegionReferenceDTO(region.getId());
    }

    default DistrictReferenceDTO districtToDistrictReferenceDTO(District district) {
        if (district == null) return null;
        return new DistrictReferenceDTO(district.getId());
    }

    default Region regionReferenceDTOToRegion(RegionReferenceDTO dto) {
        if (dto == null) return null;
        Region region = new Region();
        region.setId(dto.id());
        return region;
    }

    default District districtReferenceDTOToDistrict(DistrictReferenceDTO dto) {
        if (dto == null) return null;
        District district = new District();
        district.setId(dto.id());
        return district;
    } 
	
}
