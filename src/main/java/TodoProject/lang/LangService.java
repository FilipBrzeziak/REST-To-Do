package TodoProject.lang;

import java.util.List;

class LangService {
    private final LangRepository repository;

    LangService() {
        this(new LangRepository());
    }

    LangService(LangRepository repository) {
        this.repository = repository;
    }

    List<LangDTO> findAll() {
        return repository.findAll().stream().map(LangDTO::new).toList();
    }
}
