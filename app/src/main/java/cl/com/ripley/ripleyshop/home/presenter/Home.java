package cl.com.ripley.ripleyshop.home.presenter;


public interface Home {

    public interface  View{

    }

    public interface Interactor{
        void getItems();
    }

    public  interface Presenter{

        void getItems();

    }
}
