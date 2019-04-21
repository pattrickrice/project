package github.paz.awardportal.controller;

import github.paz.awardportal.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController()
@RequestMapping(value = "/api/user")
@Api(value = "User Management System", description = "Operations pertaining to User in User Management System.")
public class UserController {

    // TODO - for demonstration purposes only. Real implementation
    //   will retrieve USERS from a service layer.
    private static final List<User> USERS = Arrays.asList(
            new User(1, "Matthew", "Anderson", "anderma8@oregonstate.edu", "", true),
            new User(2, "Patrick", "Rice", "ricep@oregonstate.edu", "", true),
            new User(3, "Zi", "Wu", "wuzi@oregonstate.edu", "", true)
    );

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiOperation(value = "View list of all available USERS", response = List.class)
    public ResponseEntity<List<User>> getAllUsers() {
        System.out.println("Get - All Users");
        return ResponseEntity.ok(USERS);
    }

    // Returns User with the given ID, or 404 NOT FOUND.
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "View an User with the given ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved User with given ID."),
            @ApiResponse(code = 404, message = "The User with the given ID could not be found.")
    })
    public ResponseEntity<User> getUser(@PathVariable int id) {

        return USERS.stream()
                .filter(u -> u.getId() == id)
                .map(ResponseEntity::ok)
                .findFirst()
                .orElse(ResponseEntity.notFound().build());
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = "Create an User with the given id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created user."),
            @ApiResponse(code = 500, message = "Failed to create the user. Try again later.")
    })
    public ResponseEntity<String> createUser(
            @RequestHeader("firstName") String firstName,
            @RequestHeader("lastName") String lastName,
            @RequestHeader("email") String email,
            @RequestHeader("password") String password) {

        System.out.println("Received Request to created user: " + firstName + " " + lastName + " " + email);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // TODO: This will be the password stored.
        // TODO: Create the user.
        String hashedPassword = passwordEncoder.encode(password);

        return ResponseEntity.ok("User Created!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "Update an User with the given id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated user."),
            @ApiResponse(code = 500, message = "Failed to update the user. Try again later.")
    })
    public ResponseEntity<String> updateUser(
            @RequestHeader("id") String id,
            @RequestHeader("firstName") String firstName,
            @RequestHeader("lastName") String lastName,
            @RequestHeader("email") String email,
            @RequestHeader("password") String password) {

        System.out.println("Received Request to update user: " + firstName + " " + lastName + " " + email);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // TODO: This will be the password stored.
        // TODO: Update the user
        String hashedPassword = passwordEncoder.encode(password);

        return ResponseEntity.ok("User Updated!");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Delete an User with the given id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted user."),
            @ApiResponse(code = 500, message = "Failed to deleted the user. Try again later.")
    })
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {

        System.out.println("Received Request to delete user: " + id);
        // TODO: Delete the user

        return ResponseEntity.ok("User Deleted!");
    }
}
