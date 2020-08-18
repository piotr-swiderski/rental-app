package com.swiderski.carrental.crud.client;

import com.swiderski.carrental.crud.abstraction.AbstractDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

import static com.swiderski.carrental.crud.client.ClientMessageUtils.*;

@NoArgsConstructor
@Getter
@Setter
public class ClientDto extends AbstractDto {

    @NotBlank(message = NAME_VALID_MESSAGE)
    private String name;

    @NotBlank(message = SURNAME_VALID_MESSAGE)
    private String surname;

    @NotBlank(message = EMAIL_VALID_MESSAGE)
    @Email
    private String email;

    @NotBlank(message = CITY_VALID_MESSAGE)
    private String city;

    private String street;

    private String zipCode;

    @NotBlank(message = PHONE_VALID_MESSAGE)
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDto clientDto = (ClientDto) o;
        return id == clientDto.id &&
                Objects.equals(name, clientDto.name) &&
                Objects.equals(surname, clientDto.surname) &&
                Objects.equals(email, clientDto.email) &&
                Objects.equals(city, clientDto.city) &&
                Objects.equals(street, clientDto.street) &&
                Objects.equals(zipCode, clientDto.zipCode) &&
                Objects.equals(phone, clientDto.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, city, street, zipCode, phone);
    }

    public static final class ClientDtoBuilder {
        private long id;
        private String name;
        private String surname;
        private String email;
        private String city;
        private String street;
        private String zipCode;
        private String phone;

        private ClientDtoBuilder() {
        }

        public static ClientDtoBuilder aClientDto() {
            return new ClientDtoBuilder();
        }

        public ClientDtoBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public ClientDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ClientDtoBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public ClientDtoBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ClientDtoBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public ClientDtoBuilder withStreet(String street) {
            this.street = street;
            return this;
        }

        public ClientDtoBuilder withZipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public ClientDtoBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public ClientDto build() {
            ClientDto clientDto = new ClientDto();
            clientDto.setId(id);
            clientDto.setName(name);
            clientDto.setSurname(surname);
            clientDto.setEmail(email);
            clientDto.setCity(city);
            clientDto.setStreet(street);
            clientDto.setZipCode(zipCode);
            clientDto.setPhone(phone);
            return clientDto;
        }
    }

    @Override
    public String toString() {
        return   "id=" + id + " " + name +
                " " + surname;
    }
}
