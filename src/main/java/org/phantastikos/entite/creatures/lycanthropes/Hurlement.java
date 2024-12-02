package org.phantastikos.entite.creatures.lycanthropes;

public class Hurlement {
    private TypeHurlement type;
    private Lycanthrope emetteur;

    public Hurlement(Lycanthrope emetteur, TypeHurlement type) {
        this.emetteur = emetteur;
        this.type = type;
    }

    public void emettre() {

        switch (type) {
            case APPARTENANCE:
                hurlementAppartenance();
                break;
            case DOMINATION:
                hurlementDomination();
                break;
            case SOUMISSION:
                hurlementSoumission();
                break;
            case AGRESSIVITE:
                hurlementAgressivite();
                break;
        }
    }

    private String hurlementAppartenance() {
        for (Lycanthrope membre : emetteur.getMeute().getMembres()) {
            if (!membre.equals(emetteur)) {
                membre.emettreHurlement(TypeHurlement.APPARTENANCE);
                return membre.getNom() + " répond au hurlement d'appartenance.";
            }
        }

        for (Meute autre : emetteur.getMeute().getColonie().getMeutes()) {
            if (!autre.equals(emetteur.getMeute())) {
                return "La meute"+ autre.getNom() +"répond avec son propre hurlement.";
            }
        }
        return "Silence...";
    }

    private void hurlementDomination() {
        for (Meute meute : emetteur.getMeute().getColonie().getMeutes()) {
            for (Lycanthrope membre : meute.getMembres()) {
                if (!membre.equals(emetteur)) {
                    if (emetteur.getRang() == 'ω' || emetteur.getRang() < membre.getRang()){
                        membre.emettreHurlement(TypeHurlement.AGRESSIVITE);
                    }
                    else {
                        membre.emettreHurlement(TypeHurlement.SOUMISSION);
                    }
                }
            }
        }
    }

    private String hurlementSoumission() {
        return emetteur.getNom() + " exprime sa soumission.";
    }

    private String hurlementAgressivite() {
        return emetteur.getNom() + " exprime son agressivité !";
    }

}

