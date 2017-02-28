import com.mvc.framework.annotations.controller.Controller;
import com.mvc.framework.annotations.parameters.ModelAttribute;
import com.mvc.framework.annotations.request.GetMapping;
import com.mvc.framework.annotations.request.PostMapping;
import entities.User;
import repository.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static javax.swing.text.StyleConstants.ModelAttribute;

@Stateless
@Controller
public class FormController {

    @Inject
    private UserRepository userRepository;

    @GetMapping("/form")
    public String getFormPage(HttpServletResponse response) {
        return "form";
    }

    @PostMapping("/form")
    public String inputFormData(@ModelAttribute User user, HttpSession session) {
        this.userRepository.create(user);
        session.setAttribute("user", user);
        return "form";
    }
}
