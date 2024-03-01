package start.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
class PageController {

    @GetMapping("/html")
    String index(Principal principal) {
        return "emailtemplate.html";
    }

}
