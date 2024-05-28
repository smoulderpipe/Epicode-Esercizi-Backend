package it.epicode.u5w7d1teoria.service;
import it.epicode.u5w7d1teoria.dto.UserDto;
import it.epicode.u5w7d1teoria.entity.Role;
import it.epicode.u5w7d1teoria.entity.User;
import it.epicode.u5w7d1teoria.exception.NotFoundException;
import it.epicode.u5w7d1teoria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String saveUser(UserDto userDto){
        User userToSave = new User();
        userToSave.setName(userDto.getName());
        userToSave.setSurname(userDto.getSurname());
        userToSave.setEmail(userDto.getEmail());
        userToSave.setRole(Role.USER);
        userToSave.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userToSave);
        return "User with ID=" + userToSave.getId() + " correctly saved";
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }

    public User getUserByEmail(String email){

        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            return userOptional.get();
        } else {
            throw new NotFoundException("The user with email=" + email + " not found");
        }
    }

    public User updateUser(int id, UserDto userDto){
        Optional<User> userOptional = getUserById(id);
        if (userOptional.isPresent()){
            User userToUpdate = userOptional.get();
            userToUpdate.setName(userDto.getName());
            userToUpdate.setSurname(userDto.getSurname());
            userToUpdate.setEmail(userDto.getEmail());
            userToUpdate.setPassword(passwordEncoder.encode(userDto.getPassword()));
            return userRepository.save(userToUpdate);
        } else {
            throw new NotFoundException("User with id=" + id + " not found");
        }
    }

    public String deleteUser(int id){
        Optional<User> userOptional = getUserById(id);

        if (userOptional.isPresent()){
            userRepository.deleteById(id);
            return "User with id=" + id + " correctly deleted";
        } else {
            throw new NotFoundException("User with id=" + id + " not found");
        }
    }
}
