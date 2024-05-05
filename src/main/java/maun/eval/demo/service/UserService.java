package maun.eval.demo.service;

import maun.eval.demo.dto.UserDto;
import maun.eval.demo.entity.User;
import maun.eval.demo.repository.PhoneRepository;
import maun.eval.demo.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private JwtService jwtService;

    public UserDto registerUser(User user) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        if (optionalUser.isPresent()) {
            throw new RuntimeException("El correo ya está registrado");

        } else {
            user.setCreated(LocalDateTime.now());
            user.setModified(user.getCreated());
            user.setIsactive(true);
            String jwt = jwtService.generateToken(user,generateExtraClaims(user));
            user.setUsertoken(jwt);
            User user1=userRepository.saveAndFlush(user);
            if(!user.getPhones().isEmpty()) {
                user.getPhones().forEach(phone -> phone.getUser().setId(user1.getId()));
                phoneRepository.saveAllAndFlush(user.getPhones());
            }
            return new UserDto(user.getId(),user.getCreated(),user.getModified(),user1.getModified(),jwt,user1.getIsactive());
        }
    }

    private Map<String, Object> generateExtraClaims(User user) {

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("email", user.getEmail());
        extraClaims.put("password", user.getPassword());

        return extraClaims;
    }

    public Optional<User> buscarPorId(UUID id) {
        return userRepository.findById(id);
    }
}