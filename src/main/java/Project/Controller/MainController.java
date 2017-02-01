package Project.Controller;

import Project.Model.Message;
import Project.Model.User;
import Project.Service.MessageService;
import Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

@Controller
@SessionAttributes(value = "User", types = { User.class })
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String main(ModelMap modelMap, HttpSession httpSession){

        User userSession = (User) httpSession.getAttribute("User");
        if (userSession == null) {
            // Création de l'utilisateur
            long id = userService.add(new User("Mattieu", "Racine"));


            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<User>> constraintViolations = validator.validate( new User("M", "Racine") );
            Iterator<ConstraintViolation<User>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

            // Récupération de l'utilisateur
            userSession = userService.getById(id, false);

            // Création de 2 messages
            messageService.add(new Message("Message 1", userSession));
            messageService.add(new Message("Message 2", userSession));

            // Récupération de l'utilisateur
            userSession = userService.getById(id, true);

            // Sauvegarde de l'utilisateur dans la session
            httpSession.setAttribute("User", userSession);
        }

        // Ajouter les éléments à la vue
        modelMap.addAttribute("User", userSession);
        modelMap.addAttribute("Message", new Message());

        String messageEncode = userSession.getMessages().size() > 0 ? encodeSHA512(userSession.getMessages().get(userSession.getMessages().size()-1).getMessage()) : "Erreur, Aucun Message";
        modelMap.addAttribute("Encode", messageEncode);

        return "Main/index";
    }

    @RequestMapping( method = RequestMethod.POST , value = "/addMessage")
    public String addMessage(@ModelAttribute("Message") @Valid Message message, BindingResult result, ModelMap modelMap, HttpSession httpSession) {

        // Récupération de l'utilisateur courant
        User user = (User) httpSession.getAttribute("User");

        // Vérification des erreurs
        message.validate(message, result);
        if (result.hasErrors()) {
            return "Main/index";
        }

        // Ajout du Message
        message.setUser(user);
        messageService.add(message);

        user.getMessages().add(message);

        // Mise à jour de la session
        httpSession.setAttribute("User", user);

        // Ajouter les éléments à la vue
        modelMap.addAttribute("User", user);

        return "redirect:/";
    }

    @RequestMapping( method = RequestMethod.POST , value = "/deleteUser")
    public String deleteUser(HttpSession httpSession, SessionStatus status) {

        // Récupération de l'utilisateur courant
        User user = (User) httpSession.getAttribute("User");

        // Suppression de l'utilisateur
        userService.delete(user);

        // Suppression de la session
        status.setComplete();

        return "redirect:/";
    }

    @RequestMapping( method = RequestMethod.POST , value = "/deleteMessage")
    public String deleteMessage(HttpSession httpSession, SessionStatus status) {

        // Récupération de l'utilisateur courant
        User user = (User) httpSession.getAttribute("User");

        // Suppression de l'utilisateur
        messageService.deleteAll(user);
        user.setMessages(new ArrayList<Message>());

        // Mise à jour de la session
        httpSession.setAttribute("User", user);

        return "redirect:/";
    }

    public static String encodeSHA512(String plainPassword) {
        return new ShaPasswordEncoder(512).encodePassword(plainPassword, null);
    }
}
