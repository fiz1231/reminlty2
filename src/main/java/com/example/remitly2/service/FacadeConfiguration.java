package com.example.remitly2.service;

import com.example.remitly2.repository.DataRepository;


public class FacadeConfiguration {
    public static Facade createCodeSwiftCrudFacade(final DataRepository repository){
        return new Facade
        (new DataAdder(repository),new DataDeleter(repository),new DataGetter(repository));
    }
}
