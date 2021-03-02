package br.com.brasilprev.repository;

import br.com.brasilprev.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByCpf(String cpf);

    Page<Customer> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
