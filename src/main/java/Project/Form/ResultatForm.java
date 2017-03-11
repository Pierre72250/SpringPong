package Project.Form;

import Project.Model.Competition;
import Project.Model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by Pierre on 03/03/2017.
 */
public class ResultatForm {

    @Getter @Setter private int scoreUser_1;

    @Getter @Setter private int scoreUser_2;

    @Getter @Setter private String competition;

    @Getter @Setter private String user_1;

    @Getter @Setter private String user_2;

    public ResultatForm() {
    }

    @Override
    public String toString() {
        return "ResultatForm{" +
                "scoreUser_1=" + scoreUser_1 +
                ", scoreUser_2=" + scoreUser_2 +
                ", competition=" + competition +
                ", user_1=" + user_1 +
                ", user_2=" + user_2 +
                '}';
    }
}
