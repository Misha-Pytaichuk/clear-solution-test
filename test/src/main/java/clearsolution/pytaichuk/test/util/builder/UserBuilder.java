package clearsolution.pytaichuk.test.util.builder;

import clearsolution.pytaichuk.test.dto.AddressDTO;
import clearsolution.pytaichuk.test.dto.UserDTO;
import clearsolution.pytaichuk.test.model.Address;
import clearsolution.pytaichuk.test.model.User;

public class UserBuilder {
    public User mapUserToPojo(UserDTO userDTO){
        return User.builder()
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .birthDate(userDTO.getBirthDate())
                .address(mapAddressToPojo(userDTO.getAddress()))
                .telephoneNumber(userDTO.getTelephoneNumber())
                .build();
    }

    public Address mapAddressToPojo(AddressDTO addressDTO){
        return Address.builder()
                .country(addressDTO.getCountry())
                .city(addressDTO.getCity())
                .street(addressDTO.getStreet())
                .houseNumber(addressDTO.getHouseNumber())
                .build();
    }

    public UserDTO mapUserToDTO(User user){
        return UserDTO.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthDate(user.getBirthDate())
                .address(mapAddressToDTO(user.getAddress()))
                .telephoneNumber(user.getTelephoneNumber())
                .build();
    }

    public AddressDTO mapAddressToDTO(Address address){
        return AddressDTO.builder()
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .build();
    }
}
