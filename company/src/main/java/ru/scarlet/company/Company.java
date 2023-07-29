package ru.scarlet.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Table
@Entity
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonView()
public class Company {
	@Id
	@GeneratedValue(generator = "uuid")
			@JsonProperty("uuid")
	UUID uuid;

	@JsonProperty("companyName")
	String companyName;

	String description;

	String address;

	String city;

	String federalSubject;

	String country;

	String postalCode;

	String website;

}
