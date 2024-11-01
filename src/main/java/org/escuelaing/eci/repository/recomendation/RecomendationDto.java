package org.escuelaing.eci.repository.recomendation;

import java.util.List;

import org.escuelaing.eci.repository.place.Place;
import org.escuelaing.eci.repository.preference.Preference;
import org.escuelaing.eci.repository.user.User;

public class RecomendationDto {


    private List<Preference> preference;
    private List<Place> place;
    private User user;


    public RecomendationDto() {
    }

    public RecomendationDto(List<Preference> preference, List<Place> place, User user) {
        this.preference = preference;
        this.place = place;
        this.user = user;
    }
   


    public List<Preference> getPreference() {
        return this.preference;
    }

    public void setPreference(List<Preference> preference) {
        this.preference = preference;
    }

    public List<Place> getPlace() {
        return this.place;
    }

    public void setPlace(List<Place> place) {
        this.place = place;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    



}