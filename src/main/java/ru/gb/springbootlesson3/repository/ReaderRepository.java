package ru.gb.springbootlesson3.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson3.entity.Reader;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReaderRepository {
    private final List<Reader> list = new ArrayList<>();

    public ReaderRepository() {
        list.add(new Reader("Костя"));
        list.add(new Reader("Василий"));
        list.add(new Reader("Семен"));
    }

    public Reader findById(long id){
        return list.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public Reader findByName(String name){
        return list.stream().filter(e -> e.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
    public void save(Reader reader){
        list.add(reader);
    }
    public void deleteById(long id){
        list.removeIf(e -> e.getId() == id);
    }

    public List<Reader> getAll(){
        return List.copyOf(list);
    }
}
