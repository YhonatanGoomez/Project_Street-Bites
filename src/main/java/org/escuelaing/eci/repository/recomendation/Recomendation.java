package org.escuelaing.eci.repository.recomendation;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.escuelaing.eci.repository.place.Place;
import org.escuelaing.eci.repository.preference.Preference;
import org.escuelaing.eci.repository.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Recomendation_collection")
public class Recomendation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private List<Preference> preference;
    private List<Place> place;
    private User user;


    public Recomendation() {
    }

    public Recomendation(String id, List<Preference> preference, List<Place> place, User user) {
        this.id = id;
        this.preference = preference;
        this.place = place;
        this.user = user;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", preference='" + getPreference() + "'" +
            ", place='" + getPlace() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }




    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Recomendation)) {
            return false;
        }
        Recomendation recomendation = (Recomendation) o;
        return Objects.equals(id, recomendation.id) && Objects.equals(preference, recomendation.preference) && Objects.equals(place, recomendation.place) && Objects.equals(user, recomendation.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, preference, place, user);
    }




}