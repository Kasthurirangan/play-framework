package controllers;

import models.Book;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Set;

public class Bookscontroler extends Controller {

    @Inject
    MessagesApi messagesApi;
    @Inject FormFactory formFactory;

    Bookscontroler(){
        new Book();
    }

    public Result index1()
    {
        Set<Book> set = Book.all_books();
        return ok(views.html.index1.render(set));
    }

    public Result create(Http.Request request)
    {
        Form<Book> bookForm = formFactory.form(Book.class);
        return ok(views.html.create.render(bookForm, request, messagesApi.preferred(request)));
    }

    public Result save(Http.Request request)
    {
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest(request);
        Book book = bookForm.get();
        Book.add(book);
        return redirect(routes.Bookscontroler.index1());
    }

    public Result edit(Integer id, Http.Request request)
    {
        Book book = Book.find_by_id(id);
        System.out.println(book.title);
        if(book == null)
        {
            return notFound("Sry book not found");
        }
        Form<Book> bookForm1 = formFactory.form(Book.class).fill(book);
        if(bookForm1 == null)
        {
            System.out.println("bookform1 is null");
        }
        return ok(views.html.edit.render(bookForm1, request, messagesApi.preferred(request)));
    }

    public Result update(Http.Request request)
    {
        Book book = formFactory.form(Book.class).bindFromRequest(request).get();
        Book oldbook = Book.find_by_id(book.id);
        if(oldbook == null)
        {
            return  notFound("Unfortunetly somehow book got misplaced");
        }
        oldbook.id = book.id;
        oldbook.title = book.title;
        oldbook.author = book.author;
        return redirect(routes.Bookscontroler.index1());
    }

    public Result show(Integer id)
    {
        Book book = Book.find_by_id(id);
        if(book == null)
        {
            return  notFound("Sry desired book not found");
        }
       return ok(views.html.show.render(book));
    }

    public Result delete(Integer id)
    {
        Book book = Book.find_by_id(id);
        if(book == null)
        {
            return notFound("Sorry could'nt find that book so can't delete ");
        }
        Book.remove(book);
        return redirect(routes.Bookscontroler.index1());
    }
}