package org.phantastikos.structures.hopital;

import org.phantastikos.entite.creatures.Creature;
import org.phantastikos.entite.creatures.CreatureFactory;
import org.phantastikos.entite.etats.maladies.Maladie;
import org.phantastikos.entite.etats.maladies.TypeMaladie;
import org.phantastikos.structures.hopital.services.CentreQuarantaine;
import org.phantastikos.structures.hopital.services.Crypte;
import org.phantastikos.structures.hopital.services.ServiceMedical;
import org.phantastikos.entite.medecins.Medecin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hopital {
    private String nomHopital;
    private int maxServices;
    private List<ServiceMedical> services;
    private List<Medecin> medecins;
    private int temps;

    public String getNomHopital() {
        return nomHopital;
    }

    public void setNomHopital(String nomHopital) {
        this.nomHopital = nomHopital;
    }

    public int getMaxServices() {
        return maxServices;
    }

    public void setMaxServices(int maxServices) {
        this.maxServices = maxServices;
    }

    public List<ServiceMedical> getServices() {
        return services;
    }

    public void setServices(List<ServiceMedical> services) {
        this.services = services;
    }

    public List<Medecin> getMedecins() {
        return medecins;
    }

    public void setMedecins(List<Medecin> medecins) {
        this.medecins = medecins;
    }

    public Hopital(String nomHopital, int maxServices) {
        this.nomHopital = nomHopital;
        this.maxServices = maxServices;
        this.services = new ArrayList<>();
        this.medecins = new ArrayList<>();
        this.temps = 0;
    }

    public int nombreCreatures(){
        int cpt = 0;
        for (ServiceMedical service : services) {
            for (Creature creature : service.getCreatures()) {
                cpt++;
            }
        }
        return cpt;
    }

    public void ajouterService(ServiceMedical Service) {
        services.add(Service);
    }

    public void retirerService(ServiceMedical Service) {
        services.remove(Service);
    }


    public String modifierEtatCreatures() {
        StringBuilder rapport = new StringBuilder("Modification des états des créatures :\n");
        Random rand = new Random();

        for (ServiceMedical service : services) {
            for (Creature creature : service.getCreatures()) {
                int moralChange = rand.nextInt(21) - 10;
                creature.setMoral(creature.getMoral() + moralChange);
                rapport.append(creature.getNom())
                        .append(" : moral changé de ")
                        .append(moralChange)
                        .append(", nouveau moral : ")
                        .append(creature.getMoral())
                        .append("\n");

                if (!creature.getMaladies().isEmpty() && rand.nextBoolean()) {
                    Maladie maladie = creature.getMaladies().get(rand.nextInt(creature.getMaladies().size()));
                    maladie.diminuerNiveau(rand.nextInt(5));
                    rapport.append("  Maladie ")
                            .append(maladie.getNomAbrege())
                            .append(" : niveau réduit à ")
                            .append(maladie.getNiveauActuel())
                            .append("\n");
                }

                if (rand.nextInt(100) < 20) {
                    Maladie nouvelleMaladie = new Maladie(TypeMaladie.getAleatoire());
                    creature.ajouterMaladie(nouvelleMaladie);
                    rapport.append("  Nouvelle maladie : ")
                            .append(nouvelleMaladie.getNomAbrege())
                            .append("\n");
                }
            }
        }

        return rapport.toString();
    }

    public String modifierEtatServices() {
        StringBuilder rapport = new StringBuilder("Modification des états des services médicaux :\n");
        Random rand = new Random();

        for (ServiceMedical service : services) {
            int budgetChange = rand.nextInt(101) - 50;
            service.setBudget(service.getBudget() + budgetChange);
            rapport.append(service.getNom())
                    .append(" : budget changé de ")
                    .append(budgetChange)
                    .append(", nouveau budget : ")
                    .append(service.getBudget())
                    .append("\n");

            if (service instanceof CentreQuarantaine) {
                CentreQuarantaine centre = (CentreQuarantaine) service;
                if (rand.nextInt(100) < 30) {
                    centre.setIsolation(true);
                    rapport.append("  Isolation améliorée.\n");
                }
            }

            if (service instanceof Crypte) {
                Crypte crypte = (Crypte) service;
                int temperatureChange = rand.nextInt(3) - 1;
                crypte.setTemperature(crypte.getTemperature() + temperatureChange);
                rapport.append("  Température ajustée de ")
                        .append(temperatureChange)
                        .append(" degrés, nouvelle température : ")
                        .append(crypte.getTemperature())
                        .append("\n");

                if (rand.nextBoolean()) {
                    int ventilationChange = rand.nextInt(3) - 1; //
                    crypte.setNiveauVentilation(crypte.getNiveauVentilation() + ventilationChange);
                    rapport.append("  Niveau de ventilation ajusté de ")
                            .append(ventilationChange)
                            .append(", nouveau niveau : ")
                            .append(crypte.getNiveauVentilation())
                            .append("\n");
                }
            }
        }

        return rapport.toString();
    }
    public String ajouterCreaturesAleatoires() {
        StringBuilder rapport = new StringBuilder("Ajout de créatures dans les services de l'hôpital :\n");
        Random rand = new Random();

        for (int i = 0; i < rand.nextInt(20); i++) {
            Creature creature = CreatureFactory.creerCreatureAleatoire();
            ServiceMedical service = services.get(rand.nextInt(services.size()));

            if (service.accepterCreature(creature)) {
                service.ajouterCreature(creature);
                rapport.append("Créature ").append(creature.getNom())
                        .append(" ajoutée à ").append(service.getNom()).append("\n");
            } else {
                rapport.append("Créature ").append(creature.getNom())
                        .append(" rejetée par ").append(service.getNom()).append("\n");
            }
        }

        return rapport.toString();
    }

    public String verifMalade() {
        StringBuilder rapport = new StringBuilder("Vérification des créatures malades :\n");
        List<Creature> creaturesASupprimer = new ArrayList<>();

        for (ServiceMedical service : services) {
            List<Creature> creatures = service.getCreatures();

            for (Creature creature : creatures) {
                if (creature.getMaladies().isEmpty()) {
                    creaturesASupprimer.add(creature);
                    rapport.append("Créature ").append(creature.getNom())
                            .append(" retirée du service ").append(service.getNom())
                            .append(" car elle n'a plus de maladies.\n");
                }
            }

            for (Creature creature : creaturesASupprimer) {
                service.enleverCreature(creature);
            }
            creaturesASupprimer.clear();
        }

        if (rapport.length() == "Vérification des créatures malades :\n".length()) {
            rapport.append("Aucune créature sans maladies n'a été trouvée.\n");
        }

        return rapport.toString();
    }

    public String verifierMaladiesLetal() {
        StringBuilder rapport = new StringBuilder("Vérification des créatures avec maladies létales :\n");
        Random rand = new Random();

        for (ServiceMedical service : services) {
            for (Creature creature : service.getCreatures()) {
                for (Maladie maladie : creature.getMaladies()) {
                    if (maladie.estLetal()) {
                            if (creature.trepasser()){
                            rapport.append("Créature ").append(creature.getNom())
                                    .append(" est morte à cause de la maladie ")
                                    .append(maladie.getNomAbrege())
                                    .append(" avec un niveau de maladie de ")
                                    .append(maladie.getNiveauActuel())
                                    .append(".\n");
                        } else {
                            rapport.append("Créature ").append(creature.getNom())
                                    .append(" a survécu malgré un niveau létal de la maladie ")
                                    .append(maladie.getNomAbrege())
                                    .append(" : niveau actuel ")
                                    .append(maladie.getNiveauActuel())
                                    .append(".\n");
                        }
                    }
                }
            }
        }

        if (rapport.length() == "Vérification des créatures avec maladies létales :\n".length()) {
            rapport.append("Aucune créature n'a atteint un niveau létal de maladie.\n");
        }

        return rapport.toString();
    }

    public String verifierMaladiesNiveau() {
        StringBuilder rapport = new StringBuilder("Vérification des maladies avec un niveau inférieur ou égal à zéro :\n");

        for (ServiceMedical service : services) {
            for (Creature creature : service.getCreatures()) {
                List<Maladie> maladiesASupprimer = new ArrayList<>();

                for (Maladie maladie : creature.getMaladies()) {
                    if (maladie.getNiveauActuel() <= 0) {
                        maladiesASupprimer.add(maladie);
                        rapport.append("La maladie ")
                                .append(maladie.getNomAbrege())
                                .append(" de la créature ")
                                .append(creature.getNom())
                                .append(" a un niveau de ")
                                .append(maladie.getNiveauActuel())
                                .append(" et sera retirée.\n");
                    }
                }

                for (Maladie maladie : maladiesASupprimer) {
                    creature.enleverMaladie(maladie);
                }
            }
        }

        if (rapport.length() == "Vérification des maladies avec un niveau inférieur ou égal à zéro :\n".length()) {
            rapport.append("Aucune maladie n'a un niveau inférieur ou égal à zéro.\n");
        }

        return rapport.toString();
    }



    public String avancerTemps(int temps){
        StringBuilder rapports = new StringBuilder();
        for (int k = 0; k < temps; k++) {
            rapports.append(ajouterCreaturesAleatoires());
            rapports.append(modifierEtatCreatures());
            rapports.append("\n");
            rapports.append(modifierEtatServices());
            verifMalade();
            verifierMaladiesLetal();
            verifierMaladiesNiveau();
        }
        for (ServiceMedical service : services) {
            for (Creature creature : service.getCreatures()) {
                creature.attendre();
                creature.vieillir();
            }
        }



        this.temps += temps;
        return rapports.toString();
    }


}