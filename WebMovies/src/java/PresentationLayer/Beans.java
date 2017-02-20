package PresentationLayer;

import ApplicationVariables.AppVariables;
import BusinessLayer.MovieBusinessLayer;
import ClassLayer.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mqul
 */
@ManagedBean(name="bean")
@ViewScoped
public class Beans extends BaseBean implements Serializable{
    
    private MovieBusinessLayer mbl = new MovieBusinessLayer();
    private String selectedFilm, selectedDirector, selectedActor, selectedYear, selectedRating;
    List<Director> directors;
    List<Actor> actors;
    List<SimplisticFilm> sFilms;
    List<String> filmYears, filmRatings;
    private boolean isSubmitted = false, isAllSelected = false;
    
    @PostConstruct
    protected void load(){
        if (this.isPostback()){
            try{
                String filmID = (selectedFilm == null ? null : selectedFilm);
                String directorID = (selectedDirector == null ? null : selectedDirector);
                String actorID = (selectedActor == null ? null : selectedActor);
                String filmYear = (selectedYear == null ? null : selectedYear);
                String filmRating = (selectedRating == null ? null : selectedRating);

                populateDropDownsWithFilteredData(filmID, directorID, actorID, filmYear, filmRating);//, imdbID);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            populateDropDownsWithOriginalData();
        }
    }
    
    private void populateDropDownsWithOriginalData(){
        try{
            Films films = mbl.getFilms();
            
            directors = mbl.getDistinctDirectorsFromFilms(films);
            actors = mbl.getDistinctActorsFromFilms(films);
            sFilms = mbl.getDistinctSimplisticFilmsFromFilms(films);
            filmYears = mbl.getDistinctYearsFromFilms(films);
            filmRatings = mbl.getDistinctRatingsFromFilms(films);
        }catch(Exception e){
           e.printStackTrace();
        }
    }
    
    private void populateDropDownsWithFilteredData(String filmID, String directorID, String actorID, String filmYear, String filmRating){
        try{
            Films films = mbl.getFilms();
            
            Films tmp = mbl.getFilmsSubset(filmID, directorID, actorID, filmYear, filmRating, films);

            actors = (actorID == null) ? mbl.getDistinctActorsFromFilms(tmp) : mbl.getDistinctActor(tmp, actorID);
            directors = (directorID == null) ? mbl.getDistinctDirectorsFromFilms(tmp) : mbl.getDistinctDirector(tmp, directorID);
            sFilms = (filmID == null) ? mbl.getDistinctSimplisticFilmsFromFilms(tmp) : tmp.getDistinctSimplisticFilm(filmID);
            filmYears = (filmYear == null) ? mbl.getDistinctYearsFromFilms(tmp) : mbl.getDistinctYear(tmp, filmYear);
            filmRatings = (filmRating == null) ? mbl.getDistinctRatingsFromFilms(tmp) : tmp.getDistinctFilmRating(filmID); 
            
            if(sFilms.size() == 1 && directors.size() == 1 && actors.size() == 1 && filmYears.size() == 1){
                isAllSelected = true;
                this.populateFields(sFilms.get(0).filmID, directors.get(0).personID, actors.get(0).personID);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public List getFilms(){ return populateDropDownList(this.sFilms);}//populateFilms(this.sFilms); }
    
    //populate and return a list of directors based on what the films list currently holds
    public List getDirectors(){ return populateDropDownList(this.directors); }
    
    //populate and return a list of actors based on what the films list currently holds
    public List getActors(){ return populateDropDownList(this.actors); }
    
    //populate and return a list of years based on what the films list currently holds
    public List getFilmYears(){ return populateDropDownList(this.filmYears); }
    
    //populate and return a list of years based on what the films list currently holds
    public List getFilmRatings(){ return populateDropDownList(this.filmRatings); }
    
    
    //http://ruleoftech.com/2012/jsf-1-2-and-getting-selected-value-from-dropdown
    public void filmValueChanged(ValueChangeEvent e){
        if(isPostback()){
            selectedFilm = e.getNewValue().toString();
            this.load();
        }
    }
    
    public void directorValueChanged(ValueChangeEvent e){
        if(isPostback()){
            selectedDirector = e.getNewValue().toString();
            this.load();
        }
    }
    
    public void actorValueChanged(ValueChangeEvent e){
        if(isPostback()){
            selectedActor = e.getNewValue().toString();
            this.load();
        }
    }
    
    public void yearValueChanged(ValueChangeEvent e){
        if(isPostback()){
            selectedYear = e.getNewValue().toString();
            this.load();
        }
    }
    
    public void ratingValueChanged(ValueChangeEvent e){
        if(isPostback()){
            selectedRating = e.getNewValue().toString();
            this.load();
        }
    }
            
    //completely refresh the page to initial state
    public void reset() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
    public void submitForm(){
        setIsSubmitted(true);
        this.load();
    }
    
    //check if status of page is postback
    public static boolean isPostback() {
       return FacesContext.getCurrentInstance().isPostback();
    }
    
    //+getters, +setters
    public String getSelectedFilm(){return this.selectedFilm;}
    public void setSelectedFilm(String si){this.selectedFilm = si;}
    public String getSelectedDirector(){return this.selectedDirector;}
    public void setSelectedDirector(String si){this.selectedDirector = si;}
    public String getSelectedActor(){return this.selectedActor;}
    public void setSelectedActor(String si){this.selectedActor = si;}
    public String getSelectedYear(){return this.selectedYear;}
    public void setSelectedYear(String si){this.selectedYear = si;}
    public String getSelectedRating(){return this.selectedRating;}
    public void setSelectedRating(String si){this.selectedRating = si;}
    public boolean getIsSubmitted(){return this.isSubmitted;}
    public void setIsSubmitted(Boolean isSubmitted){this.isSubmitted = isSubmitted;}
    public boolean getIsAllSelected(){return this.isAllSelected;}
    public void setIsAllSelected(Boolean isAllSelected){this.isAllSelected = isAllSelected;}
    
    
    //-------------------------------------------------
    //   Populating strings with selected film data
    //-------------------------------------------------
    private Film film;
    private Director director;
    private Actor actor;
    
    public void populateFields(String filmID, String directorID, String actorID){
        this.film = mbl.getFilmFromSimplisticFilm(filmID);
        this.director = mbl.getDirectorFromSimplisticFilm(film, directorID);
        this.actor = mbl.getActorFromSimplisticFilm(film, actorID);
    }
    
    //JSF read access to fields
    public Film getFilm(){return film;}
    public Director getDirector(){return director;}
    public Actor getActor(){return actor;}
    public String getFilmImdbLink() {return String.format(AppVariables.WebProperties.imdbFilmURL, film.filmID);}
    public String getDirectorImdbLink() {return String.format(AppVariables.WebProperties.imdbProfileURL, director.personID);}
    public String getActorImdbLink() {return String.format(AppVariables.WebProperties.imdbProfileURL, actor.personID);}
}