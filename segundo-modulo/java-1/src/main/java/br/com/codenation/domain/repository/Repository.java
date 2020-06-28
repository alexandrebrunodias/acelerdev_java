package br.com.codenation.domain.repository;

import br.com.codenation.domain.entity.Entity;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Repository<T extends Entity> {

    private final List<T> collection = new ArrayList<>();

    public void cadastrar(T t) {
        if(collection.stream().anyMatch(e -> e.getId().equals(t.getId())))
            throw new IdentificadorUtilizadoException();
        collection.add(t);
    }

    public Stream<T> buscar() {
        return collection.stream();
    }

    public Stream<T> buscar(Predicate<T> t) {
        return collection.stream().filter(t).collect(Collectors.toList()).stream();
    }

    public Optional<T> buscarPorId(Long id) {
        return collection.stream().filter(e -> e.getId().equals(id)).findFirst();
    }
}