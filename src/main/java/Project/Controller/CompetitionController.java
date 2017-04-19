package Project.Controller;

import Project.Form.ResultatForm;
import Project.Model.*;
import Project.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Pierre on 03/03/2017.
 */
@Controller
public class CompetitionController {

    @Autowired
    private UserService userService;
    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private ParticipationService participationService;
    @Autowired
    private ResulatService resulatService;
    @Autowired
    private EloService eloService;

    @RequestMapping("competition/{id}")
    public String competition(@PathVariable("id") long id, ModelMap modelMap, HttpSession httpSession) {

        Competition competition = (Competition) competitionService.getById(id, true);

        // Recuperation de l'id de l'utilisateur connecté
        long currentUserId;
        try{
            currentUserId = (Long) httpSession.getAttribute("currentUserId");
        }catch (Exception e){
            return "redirect:/";
        }

        // Récuperer currentUser
        User currentUser = userService.getById(currentUserId, true);

        // Envoie du currentUser à la JSP
        modelMap.addAttribute("currentUser", currentUser);

        Boolean participe = false;

        for (Participation p : currentUser.getParticipations()){
            if (p.getCompetition().equals(competition)){
                participe = true;
                break;
            }
        }

        for (Participation p : competition.getParticipations()){
            modelMap.addAttribute("eloJ"+p.getUser().getId(), eloService.getLastElo(p).getElo());
        }

        modelMap.addAttribute("participe", participe);

        if(participe){
            modelMap.addAttribute("newResultat", new ResultatForm());
            modelMap.addAttribute("currentUserEloHistory", participationService.get(competition, currentUser, true).getElos());
        }

        modelMap.addAttribute("currentUserParticipations", currentUser.getParticipations());

        modelMap.addAttribute("currentCompetition", competition);
        modelMap.addAttribute("currentResultats", competition.getResultats());
        modelMap.addAttribute("nbResultats", competition.getResultats().size());

        DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(
                DateFormat.MEDIUM,
                DateFormat.MEDIUM);
        String date = mediumDateFormat.format(competition.getCreation());
        date = date.substring(0, date.length() -9);

        modelMap.addAttribute("creationDate", date );

        modelMap.addAttribute("newCompetition", new Competition());

        modelMap.addAttribute("participations", competition.getParticipations());
        modelMap.addAttribute("nbParticipants", competition.getParticipations().size());

        Elo currentUserElo = eloService.getLastElo(competition.getParticipations().get(0));
        modelMap.addAttribute("currentUserElo", currentUserElo);

        return "Main/competition";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addCompetition")
    public String addCompetition(@ModelAttribute("newCompetition") Competition competition, BindingResult result, ModelMap modelMap, HttpSession httpSession) {

        // On ajoute la compétition dans la bdd
        competition.setState(true);
        long idCompetition = competitionService.add(competition);

        // Recuperation de l'id de l'utilisateur connecté
        long currentUserId;
        try{
            currentUserId = (Long) httpSession.getAttribute("currentUserId");
        }catch (Exception e){
            return "redirect:/";
        }

        // Récuperer currentUser
        User currentUser = userService.getById(currentUserId, true);

        Participation participation = new Participation(currentUser, competition, true);

        participation.getElos().add(new Elo(1500, participation));

        long idParticipation = participationService.add(participation);

        return "redirect:/competition/" + idCompetition + "";
    }

    @RequestMapping("join/{id}")
    public String join(@PathVariable("id") long idCompetition, ModelMap modelMap, HttpSession httpSession) {

        Competition competition = (Competition) competitionService.getById(idCompetition, true);

        // Recuperation de l'id de l'utilisateur connecté
        long currentUserId;
        try{
            currentUserId = (Long) httpSession.getAttribute("currentUserId");
        }catch (Exception e){
            return "redirect:/";
        }

        // Récuperer currentUser
        User currentUser = userService.getById(currentUserId, true);

        Boolean participe = false;

        for (Participation p : currentUser.getParticipations()){
            if (p.getCompetition().equals(competition)){
                participe = true;
                break;
            }
        }

        if ( !participe ) {
            Participation participation = new Participation(currentUser, competition, false);
            participation.getElos().add(new Elo(1500, participation ));

            long idParticipation = participationService.add(participation);
        }

        return "redirect:/competition/" + idCompetition + "";

    }

    @RequestMapping(method = RequestMethod.POST, value = "/addResult")
    public String addMatch(@ModelAttribute("newResultat") ResultatForm newResultat, BindingResult result, ModelMap modelMap, HttpSession httpSession) {

        User user1 = userService.getById(Long.parseLong(newResultat.getUser_1()), false);
        User user2 = userService.getById(Long.parseLong(newResultat.getUser_2()), false);
        Competition competition = competitionService.getById(Long.parseLong(newResultat.getCompetition()), true);

        Participation participationUser1 = null;
        Participation participationUser2 = null;

        for ( Participation p : competition.getParticipations()){
            if (p.getCompetition().equals(competition) && p.getUser().equals(user1)){
                participationUser1 = p;
                break;
            }
        }
        for ( Participation p : competition.getParticipations()){
            if (p.getCompetition().equals(competition) && p.getUser().equals(user2)){
                participationUser2 = p;
                break;
            }
        }

        int eloUser1 = eloService.getLastElo(participationUser1).getElo();
        int eloUser2 = eloService.getLastElo(participationUser2).getElo();

        float luckUser1 = (float) (1 / (1 + Math.pow(10, ((eloUser2 - eloUser1)/400))) );
        float luckUser2 = (float) (1 / (1 + Math.pow(10, ((eloUser1 - eloUser2)/400))) );

        int newEloUser1;
        int newEloUser2;

        if (newResultat.getScoreUser_1() > newResultat.getScoreUser_2()){
            newEloUser1 = (int) (eloUser1 - getConstant(eloUser1) * ( 0-luckUser1 ));
            newEloUser2 = (int) (eloUser2 - getConstant(eloUser2) * ( 1-luckUser2 ));
        }else{
            newEloUser1 = (int) (eloUser1 - getConstant(eloUser1) * ( 1-luckUser1 ));
            newEloUser2 = (int) (eloUser2 - getConstant(eloUser2) * ( 0-luckUser2 ));
        }

        eloService.add(new Elo(newEloUser1, participationUser1 ));
        eloService.add(new Elo(newEloUser2, participationUser2 ));

        long idMatch = resulatService.add(new Resultat(newResultat.getScoreUser_1(), newResultat.getScoreUser_2(), competition, user1, user2));

        return "redirect:/competition/" + newResultat.getCompetition() + "";
    }

    public static int getConstant(int elo) {
        if (elo < 2000){
            return 32;
        }else if (elo < 2401){
            return 24;
        }else{
            return 16;
        }
    }
}
