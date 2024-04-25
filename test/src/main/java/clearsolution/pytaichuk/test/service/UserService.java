package clearsolution.pytaichuk.test.service;

import clearsolution.pytaichuk.test.dto.UserDTO;
import clearsolution.pytaichuk.test.dto.request.UserBirthRange;
import clearsolution.pytaichuk.test.dto.request.UserEmail;
import clearsolution.pytaichuk.test.model.Address;
import clearsolution.pytaichuk.test.model.User;
import clearsolution.pytaichuk.test.repository.UserRepository;
import clearsolution.pytaichuk.test.util.builder.UserBuilder;
import clearsolution.pytaichuk.test.util.exception.FoundException;
import clearsolution.pytaichuk.test.util.exception.NotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserBuilder userBuilder;

    @Value("${years_old}")
    private int yearsOld;

    @Transactional
    public void createUser(UserDTO userDTO) {

        if(userRepository.findUserByEmail(userDTO.getEmail()).isPresent())
            throw new FoundException("email - User already exist;");

        if(userRepository.findUserByTelephoneNumber(userDTO.getTelephoneNumber()).isPresent())
            throw new FoundException("telephoneNumber - User already exist;");

        if(Period.between(userDTO.getBirthDate(), LocalDate.now()).getYears() < yearsOld){
            throw new ValidationException("birthDate - User must be " + yearsOld + " years of age or older.");
        }

        User user = userBuilder.mapUserToPojo(userDTO);

        user = userRepository.save(user);
        user.getAddress().setUser(user);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(Long id, UserDTO userDTO){

        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("id - User not found;"));

        if(!user.getEmail().equals(userDTO.getEmail()))
            if(userRepository.findUserByEmail(userDTO.getEmail()).isPresent())
                throw new FoundException("email - User already exist;");

        if(!user.getTelephoneNumber().equals(userDTO.getTelephoneNumber()))
            if(userRepository.findUserByTelephoneNumber(userDTO.getTelephoneNumber()).isPresent())
                throw new FoundException("telephoneNumber - User already exist;");

        if(Period.between(userDTO.getBirthDate(), LocalDate.now()).getYears() < yearsOld){
            throw new ValidationException("birthDate - User must be " + yearsOld + " years of age or older.");
        }

        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBirthDate(userDTO.getBirthDate());

        Address address = user.getAddress();

        address.setCountry(userDTO.getAddress().getCountry());
        address.setCity(userDTO.getAddress().getCity());
        address.setStreet(userDTO.getAddress().getStreet());
        address.setHouseNumber(userDTO.getAddress().getHouseNumber());

        user.setAddress(address);
        user.setBirthDate(userDTO.getBirthDate());
        user.setTelephoneNumber(userDTO.getTelephoneNumber());

        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(UserEmail userEmail){
        User user = findUserByEmail(userEmail.getEmail());

        userRepository.delete(user);
    }

    public List<UserDTO> searchUserByBirthRange(UserBirthRange userBirthRange){
        List<User> userList = userRepository.findUserByBirthDateBetween(userBirthRange.getFrom(), userBirthRange.getTo());

        return userList.stream()
                .map(userBuilder::mapUserToDTO)
                .toList();
    }

    private User findUserByEmail(String email){
        return userRepository.findUserByEmail(email)
                .orElseThrow(() ->new NotFoundException("email - User not found;"));
    }
}
