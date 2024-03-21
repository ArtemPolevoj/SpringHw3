package ru.gb.springbootlesson3.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson3.entity.Issue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IssueRepository {
    private final List<Issue> list = new ArrayList<>();

    public void createIssue(Issue issue) {
        list.add(issue);
    }

    public Issue findById(long id) {
        return list.stream().filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public int getCountBookReader(long readerId) {
        int count = 0;
        for (Issue issue:list){
            if (issue.getIdReader() == readerId && issue.getReturnedAt() == null){
                count++;
            }
        }

        return count;
    }
    public List<Issue> findIssueByReaderId(long readerId){
        List<Issue> temp = new ArrayList<>();
        for (Issue issue:list){
            if (issue.getIdReader() == readerId){
                temp.add(issue);
            }
        }
        return temp;
    }
    public void setReturnedAt(long id){
        for (Issue issue:list){
            if (issue.getId() == id && issue.getReturnedAt() == null){
                issue.setReturnedAt(LocalDateTime.now());
            }
        }
    }
    public List<Issue> getAll(){
        return List.copyOf(list);
    }
}
