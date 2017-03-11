package Project.Controller;

import Project.Model.Competition;
import Project.Model.Elo;
import Project.Model.Participation;
import Project.Model.User;
import Project.Service.CompetitionService;
import Project.Service.EloService;
import Project.Service.ParticipationService;
import Project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Calendar;

/**
 * Created by Pierre on 03/03/2017.
 */
@Controller
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private ParticipationService participationService;
    @Autowired
    private EloService eloService;

    @RequestMapping("profile/{id}")
    public String profile(@PathVariable("id") long id, ModelMap modelMap, HttpSession httpSession) {

        User userProfile = (User) userService.getById(id, true);
        userProfile.setPassword("");

        // Recuperation de l'id de l'utilisateur connecté
        long currentUserId = (Long) httpSession.getAttribute("currentUserId");
        // Récuperer currentUser
        User currentUser = userService.getById(currentUserId, true);

        // Renvoie l'utilisateur sur la page de connection si il n'es pas connecté
        if(currentUser == null){
            return "redirect:/";
        }

        modelMap.addAttribute("currentUser", currentUser);
        modelMap.addAttribute("currentUserParticipations", currentUser.getParticipations());

        modelMap.addAttribute("userProfile", userProfile);
        modelMap.addAttribute("participations", userProfile.getParticipations());

        modelMap.addAttribute("UpdateUserForm", userProfile);
        modelMap.addAttribute("newCompetition", new Competition());

        return "Main/profile";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addprofilepicture")
    public String updateUser(@RequestParam("file") MultipartFile file, BindingResult result, ModelMap modelMap, HttpSession httpSession) {
        return "";
    }
}
