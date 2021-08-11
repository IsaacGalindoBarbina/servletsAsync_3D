package mx.edu.utez.model.game;
import mx.edu.utez.model.category.BeanCategory;

public class BeanGame {

    private int idGame;
    private String nameGame;
    private String imgGame;
    private BeanCategory Category_idCategory;
    private String datePremiere;
    private int Status;

    public BeanGame() {
    }

    public BeanGame(int idGame, String nameGame, String imgGame, BeanCategory category_idCategory, String datePremiere, int status) {
        this.idGame = idGame;
        this.nameGame = nameGame;
        this.imgGame = imgGame;
        Category_idCategory = category_idCategory;
        this.datePremiere = datePremiere;
        Status = status;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public String getNameGame() {
        return nameGame;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }

    public String getImgGame() {
        return imgGame;
    }

    public void setImgGame(String imgGame) {
        this.imgGame = imgGame;
    }

    public BeanCategory getCategory_idCategory() {
        return Category_idCategory;
    }

    public void setCategory_idCategory(BeanCategory category_idCategory) {
        Category_idCategory = category_idCategory;
    }

    public String getDatePremiere() {
        return datePremiere;
    }

    public void setDatePremiere(String datePremiere) {
        this.datePremiere = datePremiere;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
