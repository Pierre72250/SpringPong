package Project.Controller;

import Project.Model.Competition;
import Project.Model.Elo;
import Project.Model.Participation;
import Project.Model.User;
import Project.Service.*;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    @Autowired
    private ResulatService resulatService;

    @RequestMapping(method = RequestMethod.GET, value = "profile/{id}")
    public String profile(@PathVariable("id") long id, ModelMap modelMap, HttpSession httpSession) {

        User userProfile = (User) userService.getById(id, true);
        userProfile.setPassword("");

        // Recuperation de l'id de l'utilisateur connecté
        long currentUserId;
        try{
            currentUserId = (Long) httpSession.getAttribute("currentUserId");
        }catch (Exception e){
            return "redirect:/";
        }

        // Récuperer currentUser
        User currentUser = userService.getById(currentUserId, true);

        modelMap.addAttribute("currentUser", currentUser);
        modelMap.addAttribute("currentUserParticipations", currentUser.getParticipations());

        modelMap.addAttribute("userProfile", userProfile);
        modelMap.addAttribute("participations", userProfile.getParticipations());

        modelMap.addAttribute("totalMatchs", resulatService.getTotalMatchs(userProfile));
        modelMap.addAttribute("totalVictories", resulatService.getTotalVictories(userProfile));
        modelMap.addAttribute("totalLooses", resulatService.getTotalLooses(userProfile));


        modelMap.addAttribute("UpdateUserForm", userProfile);
        modelMap.addAttribute("newCompetition", new Competition());

        return "Main/profile";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addprofilepicture")
    public String updateUser(@RequestParam("file") MultipartFile file, BindingResult result, ModelMap modelMap, HttpSession httpSession) {
        return "";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/settings")
    public String settings(ModelMap modelMap, HttpSession httpSession) {

        // Recuperation de l'utilisateur connecté
        long currentUserId;
        try{
            currentUserId = (Long) httpSession.getAttribute("currentUserId");
        }catch (Exception e){
            return "redirect:/";
        }
        User currentUser = userService.getById(currentUserId, true);
        currentUser.setPassword("");

        modelMap.addAttribute("currentUser", currentUser);
        modelMap.addAttribute("currentUserParticipations", currentUser.getParticipations());
        modelMap.addAttribute("newCompetition", new Competition());

        modelMap.addAttribute("userInformationForm", currentUser);
        modelMap.addAttribute("userLoginForm", currentUser);
        try{
            modelMap.addAttribute("dateOfBirth",  currentUser.getDateOfBirth().toString().substring(0, currentUser.getDateOfBirth().toString().length()-11));
        }catch (Exception e){
            e.printStackTrace();
        }


        return "Main/settings";
    }

    @RequestMapping(method = RequestMethod.POST, value = "updateUserInformations")
    public String updateUserInformations(@ModelAttribute("userInformationForm") User user, @RequestParam("dateString") String dateString, HttpSession httpSession) throws java.text.ParseException {

        // Recuperation de l'utilisateur connecté
        long currentUserId;
        try{
            currentUserId = (Long) httpSession.getAttribute("currentUserId");
        }catch (Exception e){
            return "redirect:/";
        }
        User currentUser = userService.getById(currentUserId, true);

        currentUser.setName(user.getName());
        currentUser.setSurname(user.getSurname());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {

            currentUser.setDateOfBirth(formatter.parse(dateString));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        userService.update(currentUser);

        return "redirect:/settings";
    }

    @RequestMapping("updateUserLogin")
    public String updateUserLogin(@ModelAttribute("userLoginForm") User user, @RequestParam("confirmPassword") String confirmPassword, @RequestParam("newPassword") String password, ModelMap modelMap, HttpSession httpSession) {

        // Recuperation de l'utilisateur connecté
        long currentUserId;
        try{
            currentUserId = (Long) httpSession.getAttribute("currentUserId");
        }catch (Exception e){
            return "redirect:/";
        }

        User currentUser = userService.getById(currentUserId, true);

        System.out.println(user.getPassword());
        System.out.println(currentUser.getPassword());
        System.out.println(password);
        System.out.println(confirmPassword);

        user.setPassword(encodeSHA512(user.getPassword()));
        System.out.println(user.getPassword());


        if(!user.getPassword().equals(currentUser.getPassword()) || !password.equals(confirmPassword)){
            return "redirect:/";
        }

        currentUser.setMail(user.getMail());
        currentUser.setPassword(encodeSHA512(password));

        userService.update(currentUser);

        return "redirect:/settings";
    }

    public static String encodeSHA512(String password) {
        return new ShaPasswordEncoder(512).encodePassword(password, null);
    }
}
