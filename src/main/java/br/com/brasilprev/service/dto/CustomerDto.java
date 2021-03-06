package br.com.brasilprev.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String cpf;
    private AddressDto address;
}
