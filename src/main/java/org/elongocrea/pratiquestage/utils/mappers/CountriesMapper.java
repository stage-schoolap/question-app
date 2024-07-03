package org.elongocrea.pratiquestage.utils.mappers;

import org.elongocrea.pratiquestage.models.Countries;
import org.elongocrea.pratiquestage.utils.dtos.CountriesDTO;
import org.springframework.stereotype.Component;

@Component
public class CountriesMapper {

    public CountriesDTO mapToDTO(Countries entity) {
        return CountriesDTO.builder()
                .id(entity.getId())
                .name_fr(entity.getName_fr())
                .name_en(entity.getName_en())
                .prefix(entity.getPrefix())
                .build();
    }

    public Countries mapToEntity(CountriesDTO dto) {
        return Countries.builder()
                .id(dto.getId())
                .name_fr(dto.getName_fr())
                .name_en(dto.getName_en())
                .prefix(dto.getPrefix())
                .build();
    }
}
