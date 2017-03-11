package Project.Controller;

import Project.Model.*;
import Project.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private ParticipationService participationService;
    @Autowired
    private EloService eloService;


    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String main(ModelMap modelMap, HttpSession httpSession) {

        // Envoie d'un objet User vide, pour qu'il soit rempli par le formulaire d'inscription
        modelMap.addAttribute("UserInscription", new User());
        // Envoie d'un objet User vide, pour qu'il soit rempli par le formulaire de connection
        modelMap.addAttribute("UserConnection", new User());

        // Retourne la page d'accueil du site
        return "Main/index";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public String addUser(@ModelAttribute("UserInscription") User user, @RequestParam("confirmPassword") String confirmPassword, BindingResult result, ModelMap modelMap, HttpSession httpSession) {

        if (!user.getPassword().equals(confirmPassword)){
            return "redirect:/";
        }

        user.setPassword(encodeSHA512(user.getPassword()));

        // On ajoute l'utilisateur dans la bdd
        long id = userService.add(user);

        // Récupération de l'utilisateur
        User currentUser = userService.getById(id, true);

        // Sauvegarde de l'utilisateur dans la session
        httpSession.setAttribute("currentUserId", currentUser.getId());

        return "redirect:/profile/" + user.getId() + "";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/connection")
    public String connection(@ModelAttribute("UserConnection") User user, BindingResult result, ModelMap modelMap, HttpSession httpSession) {

        // Récupération de l'utilisateur
        User currentUser = (User) userService.getByMail(user.getMail());

        if(currentUser == null){
            user.setPassword("");
            modelMap.addAttribute("UserInscription", new User());
            modelMap.addAttribute("UserConnection", user);
            return "Main/index";
        }

        user.setPassword(encodeSHA512(user.getPassword()));

        if(currentUser.getPassword().equals(user.getPassword())){
            httpSession.setAttribute("currentUserId", currentUser.getId());
            return "redirect:/profile/" + currentUser.getId() + "";
        }else{
            user.setPassword("");
            modelMap.addAttribute("UserInscription", new User());
            modelMap.addAttribute("UserConnection", user);
            return "Main/index";
        }

    }

    @RequestMapping( method = RequestMethod.GET , value = "/deconnection")
    public String deconnection(HttpSession httpSession, SessionStatus status) {
        // On vide le session et on renvoie l'utilisateur vers la page d'accueil du site
        httpSession.invalidate();
        return "redirect:/";
    }

    public static String encodeSHA512(String password) {
        return new ShaPasswordEncoder(512).encodePassword(password, null);
    }

}