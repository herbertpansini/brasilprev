package br.com.brasilprev.service.mapper;

import br.com.brasilprev.model.Customer;
import br.com.brasilprev.service.dto.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface CustomerMapper extends EntityMapper<CustomerDto, Customer> {

}
