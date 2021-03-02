package br.com.brasilprev.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Address {
	@Column(name = "street", nullable = false, length = 255)
    private String street;

	@Column(name = "city", nullable = false, length = 255)
	private String city;

	@Column(name = "region", nullable = false, length = 255)
	private String region;

	@Column(name = "postal_code", nullable = false, length = 255)
	private String postalCode;

	@Column(name = "country", nullable = false, length = 255)
	private String country;
}