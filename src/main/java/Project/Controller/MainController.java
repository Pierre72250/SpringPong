package Project.Controller;

import Project.Model.User;
import Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String main(ModelMap modelMap, HttpSession httpSession) {

        // Envoie d'un objet User vide, pour qu'il soit rempli par le formulaire
        modelMap.addAttribute("UserForm", new User());
        modelMap.addAttribute("UserConnection", new User());

        // Retourne la page d'accueil du site
        return "Main/index";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public String addUser(@ModelAttribute("UserForm") User user, @RequestParam("confirmPassword") String confirmPassword, BindingResult result, ModelMap modelMap, HttpSession httpSession) {

        if (!user.getPassword().equals(confirmPassword)){
            return "redirect:/";
        }

        user.setPassword(encodeSHA512(user.getPassword()));

        // Recuperation de l'utilisateur connecté
        User userSession = (User) httpSession.getAttribute("userSession");

        // On ajoute l'utilisateur dans la bdd
        long id = userService.add(user);

        // On connecte l'utilisateur seulement si aucun compte n'est connecté au moment de l'envoie du formulaire
        if (userSession == null) {

            // Récupération de l'utilisateur
            userSession = userService.getById(id, true);

            // Sauvegarde de l'utilisateur dans la session
            userSession.setPassword("");
            httpSession.setAttribute("userSession", userSession);

        }
        //return "Main/moncompte";

        return "redirect:/profile/" + user.getId() + "";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/connection")
    public String connection(@ModelAttribute("UserConnection") User user, BindingResult result, ModelMap modelMap, HttpSession httpSession) {

        // Récupération de l'utilisateur
        User userSession = (User) userService.getByMail(user.getMail());
        modelMap.addAttribute("UserForm", new User());

        if(userSession == null){
            return "Main/index";
        }

        user.setPassword(encodeSHA512(user.getPassword()));

        if(userSession.getPassword().equals(user.getPassword())){
            userSession.setPassword("");
            httpSession.setAttribute("userSession", userSession);
            return "redirect:/profile/" + userSession.getId() + "";
        }else{
            user.setPassword("");
            return "Main/index";
        }

    }

    @RequestMapping( method = RequestMethod.GET , value = "/deconnection")
    public String deconnection(HttpSession httpSession, SessionStatus status) {
        // On vide le session et on renvoie l'utilisateur vers la page d'accueil du site
        httpSession.invalidate();
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/compte")
    public String monCompte(ModelMap modelMap, HttpSession httpSession) {
        modelMap.addAttribute("UserConnection", new User());
        return "Main/moncompte";
    }

    @RequestMapping("profile/{id}")
    public String profile(@PathVariable("id") long id, ModelMap modelMap, HttpSession httpSession) {
        User userProfile = (User) userService.getById(id, false);
        modelMap.addAttribute("userProfile", userProfile);
        modelMap.addAttribute("UserConnection", new User());
        return "Main/profile";
    }

    public static String encodeSHA512(String password) {
        return new ShaPasswordEncoder(512).encodePassword(password, null);
    }

}