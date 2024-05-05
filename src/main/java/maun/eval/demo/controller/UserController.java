package maun.eval.demo.controller;


import maun.eval.demo.dto.UserDto;
import maun.eval.demo.entity.User;
import maun.eval.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping( consumes= MediaType.APPLICATION_JSON_VALUE , produces=MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<UserDto> registerUsuario(@RequestBody User user){
        return ResponseEntity.ok(userService.registerUser(user));
    }


}
