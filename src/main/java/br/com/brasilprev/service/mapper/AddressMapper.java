package br.com.brasilprev.service.mapper;

import br.com.brasilprev.model.Address;
import br.com.brasilprev.service.dto.AddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends EntityMapper<AddressDto, Address> {

}
