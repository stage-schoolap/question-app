package org.elongocrea.pratiquestage.utils.mappers;

import org.elongocrea.pratiquestage.models.Countries;
import org.elongocrea.pratiquestage.utils.dtos.CountriesDTO;
import org.springframework.stereotype.Component;

@Component
public class CountriesMapper {

    public CountriesDTO mapToDTO(Countries entity) {
        return CountriesDTO.builder()
                .id(entity.getId())
                .nameFr(entity.getNameFr())
                .nameEn(entity.getNameEn())
                .prefix(entity.getPrefix())
                .build();
    }

    public Countries mapToEntity(CountriesDTO dto) {
        return Countries.builder()
                .id(dto.getId())
                .nameFr(dto.getNameFr())
                .nameEn(dto.getNameEn())
                .prefix(dto.getPrefix())
                .build();
    }
}
