package cl.com.ripley.ripleyshop.home.presenter;


import java.util.List;

import cl.com.ripley.ripleyshop.home.model.HomeProduct;

public interface Home {

    public interface  View{

    }

    public interface Interactor{
        void getItemsInformation(String SKUs);
    }

    public  interface Presenter{

        void addSKUS(String listSKU);

        void addPublications(List<HomeProduct> products);

    }
}
