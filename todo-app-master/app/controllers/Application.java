package controllers;

import models.Budzet;
import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
  static Form<Budzet> budzetForm = Form.form(Budzet.class);

    public static Result index() {
      //return ok(index.render("Your new application is ready."));
      return redirect(routes.Application.tasks());
    }
    
    public static Result tasks()
    {
      return ok(views.html.index.render(Budzet.all(), budzetForm));
    }

  public static Result dodajUBudzet()
  {

    Form<Budzet> filledForm = budzetForm.bindFromRequest();
    if(filledForm.hasErrors()) {
      return badRequest(
              views.html.dodajUBudzet.render(Budzet.all(), filledForm)
      );
    } else {
      Budzet.create(filledForm.get());
      return redirect(routes.Application.dodajUBudzet());
    }
  }

  public static Result prikaziTask() {
    Form<Budzet> filledForm = budzetForm.bindFromRequest();
    if(filledForm.hasErrors()) {
      return badRequest(
              views.html.prikazListe.render(Budzet.all(), filledForm)
      );
    } else {
      Budzet.create(filledForm.get());
      return redirect(routes.Application.tasks());
    }
  }
    public static Result newTask() {
      Form<Budzet> filledForm = budzetForm.bindFromRequest();
      if(filledForm.hasErrors()) {
        return badRequest(
          views.html.dodaj.render(Budzet.all(), filledForm)
        );
      } else {
          Budzet.create(filledForm.get());
        flash("success", String.format("Uspjesno ste dodali"));
          return redirect(routes.Application.newTask());}
    }
  public static Result newTaskEdit(Long id){

    Form<Budzet> filledForm = budzetForm.bindFromRequest();
    if(filledForm.hasErrors()) {
      return badRequest(
              views.html.dodajPromijeni.render(Budzet.all(), filledForm,id)
      );
    } else {
      Budzet.delete(id);
      Budzet.create(filledForm.get());
      flash("success", String.format("Uspjesno ste dodali"));
    return redirect(routes.Application.prikaziTask());}
  }
  public static Result dodajUBudzetPromijeni(Long id){
    Form<Budzet> filledForm = budzetForm.bindFromRequest();
    if(filledForm.hasErrors()) {
      return badRequest(
              views.html.dodajUBudzetPromijeni.render(Budzet.all(), filledForm,id)
      );
    } else {
      Budzet.delete(id);
      Budzet.create(filledForm.get());
     // flash("success", String.format("Uspjesno ste dodali"));
      return redirect(routes.Application.prikaziTask());}
  }
    public static Result deleteTask(Long id){
      Budzet.delete(id);
      return redirect(routes.Application.prikaziTask());
    }
  public static Result updateTask(Long id){
   Budzet b= Budzet.update(id);
    if(b.suma==0)
    {
      return ok(views.html.dodajPromijeni.render(Budzet.all(),budzetForm.fill(b),id));
    }
    else
    {
      return ok(views.html.dodajUBudzetPromijeni.render(Budzet.all(),budzetForm.fill(b),id));
    }
  }
}
