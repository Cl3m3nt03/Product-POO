package Controller;

public abstract class Pharmacy {
    private String nom;
    private String adresse;

    public Pharmacy(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    public void afficherDetails() {
        System.out.println("Pharmacie: " + nom);
        System.out.println("Adresse: " + adresse);
    }


}
