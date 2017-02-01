package Project.Model;

import lombok.Data;

import java.util.Date;

/**
 * Created by Pierre on 12/01/2017.
 */

@Data
public class Article {
    private int id;
    private String titre;
    private int prix;
    private Date date;
    private String urlImage;
    private String categorie;

    public Article() {}

    public Article(int id, String titre, int prix, Date date, String urlImage, String categorie) {
        this.id = id;
        this.titre = titre;
        this.prix = prix;
        this.date = date;
        this.urlImage = urlImage;
        this.categorie = categorie;
    }
}
