package br.com.brasilprev.service;

import br.com.brasilprev.service.dto.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Page<CustomerDto> findByNameContainingIgnoreCase(String name, Pageable pageable);

    CustomerDto findById(Long id);

    CustomerDto save(CustomerDto customerDto);

    CustomerDto update(Long id, CustomerDto customerDto);

    void delete(Long id);
}
