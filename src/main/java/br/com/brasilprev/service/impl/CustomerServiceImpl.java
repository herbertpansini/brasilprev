package br.com.brasilprev.service.impl;

import br.com.brasilprev.model.Customer;
import br.com.brasilprev.repository.CustomerRepository;
import br.com.brasilprev.service.CustomerService;
import br.com.brasilprev.service.dto.CustomerDto;
import br.com.brasilprev.service.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDto> findByNameContainingIgnoreCase(String name, Pageable pageable) {
        return this.customerRepository.findByNameContainingIgnoreCase(name, pageable).map(this.customerMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto findById(Long id) {
        return this.customerMapper.toDto( this.customerRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NO_CONTENT, String.format("Customer %d not found", id))) );
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        this.validate(customerDto);
        return this.customerMapper.toDto( this.customerRepository.save( this.customerMapper.toEntity(customerDto) ) );
    }

    @Override
    public CustomerDto update(Long id, CustomerDto customerDto) {
        CustomerDto customerUpdate = this.findById(id);
        BeanUtils.copyProperties(customerDto, customerUpdate, "id");
        this.validate(customerUpdate);
        return this.customerMapper.toDto(this.customerRepository.save(this.customerMapper.toEntity(customerUpdate)));
    }

    @Override
    public void delete(Long id) {
        this.customerRepository.deleteById(id);
    }

    private void validate(CustomerDto customerDto) {
        if (this.validateCpf(customerDto)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("CPF %s Already", customerDto.getCpf()));
        }
    }

    private boolean validateCpf(CustomerDto customerDto) {
        Customer customerExist = this.customerRepository.findByCpf(customerDto.getCpf());
        return !(customerExist == null || customerExist.getId().equals(customerDto.getId()));
    }
}
