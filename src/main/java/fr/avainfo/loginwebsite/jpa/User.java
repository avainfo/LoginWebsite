package fr.avainfo.loginwebsite.jpa;


import jakarta.persistence.*;

/**
 * On définit la class User en entity
 * Grace au table on spécifie le nom de la table
 */
@Entity
@Table(name = "user")
public class User {

    /**
     * Fait réference a l'id de l'entitiy
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Fait réference au field name de la base de donnée
     */
    private String name;
    /**
     * Fait référence au field password de la base de donnée
     */
    private String password;
    /**
     * Fait référence au field age de la base de donnée grace a l'annotation @Column(name="age")
     */
    @Column(name = "age")
    private int ageActuel;

    /**
     * @return String - le nom du user
     * Sert a obtenir le nom du user
     */
    public String getName() {
        return name;
    }

    /**
     * @param name String - le nom du user
     * Sert a redéfinir le nom du user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String - le password du user
     * Sert a obtenir le password du user
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password String - le password du user
     * Sert a redéfinir le password du user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return int - l'age du user
     * Sert a obtenir l'age du user
     */
    public int getAgeActuel() {
        return ageActuel;
    }

    /**
     * @param ageActuel int - l'age du user
     * Sert a redéfinir l'age  du user
     */
    public void setAgeActuel(int ageActuel) {
        this.ageActuel = ageActuel;
    }
}
