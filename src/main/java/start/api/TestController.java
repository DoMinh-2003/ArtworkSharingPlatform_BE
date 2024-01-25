package start.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import start.utils.ResponseHandler;

@RestController
@SecurityRequirement(name ="api")

public class TestController {
    @Autowired
    ResponseHandler responseHandler;
    @GetMapping("admin-only")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity getAdmin(){
        return responseHandler.response(200, "Successfully get data!", null);
    }

    @GetMapping("all-user")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity get(){
        return responseHandler.response(200, "Successfully get data!", null);
    }

    @GetMapping("test")
    public ResponseEntity test() {
        return ResponseEntity.ok("test ok");
    }
    @GetMapping("test2")
 public ResponseEntity test2() {
        return ResponseEntity.ok("HT test");
    }
    @GetMapping("Minh-test2")
 public ResponseEntity test3() {
        return ResponseEntity.ok("Minh test");
    }
    @GetMapping("Thinh-3")
    public ResponseEntity test4() {
        return ResponseEntity.ok("Minh test");
    }
}
