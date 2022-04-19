package io.github.mat3e.lang;

import java.util.List;

class LangService {
    private LangRepository repository;

    LangService(){
        this(new LangRepository());
    }

    LangService(LangRepository repository){
        this.repository = repository;
    }
    List<LangDTO> findAll(){
        return repository.findAll().stream().map(LangDTO::new).toList();
    }
}
