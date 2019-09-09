package cl.com.ripley.ripleyshop.home.presenter;


import java.util.List;

import cl.com.ripley.ripleyshop.home.model.HomeProduct;

public interface Home {

    public interface  View{
        void addProducts(List<HomeProduct> products);
        void showErrorConsultingSKU();
        void showErrorConsultingPublications();
    }

    public interface Interactor{
        void getItemsInformation(String SKUs);
    }

    public  interface Presenter{
        void addSKUS(String listSKU);
        void addPublications(List<HomeProduct> products);
        void errorConnection(String tag);
    }
}
