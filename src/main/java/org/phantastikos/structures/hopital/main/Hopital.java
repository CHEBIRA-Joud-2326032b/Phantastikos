package org.phantastikos.structures.hopital.main;

import org.phantastikos.entite.creatures.Creature;
import org.phantastikos.entite.etats.maladies.Maladie;
import org.phantastikos.entite.etats.maladies.TypeMaladie;
import org.phantastikos.structures.hopital.services.ServiceMedical;
import org.phantastikos.entite.medecins.Medecin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.phantastikos.structures.hopital.services.Budget.*;

public class Hopital {
    private String nomHopital;
    private int maxServices;
    private List<ServiceMedical> services;
    private List<Medecin> medecins;
    private int compteurTour;

    public Hopital(String nomHopital) {
        this.nomHopital = nomHopital;
        this.services = new ArrayList<>();
        this.compteurTour = 0;
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



}