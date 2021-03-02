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
public class AddressDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String street;
    private String city;
    private String region;
    private String postalCode;
    private String country;
}
