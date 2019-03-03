package usecases;

import entities.Stat;
import lombok.*;
import persistence.StatsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Stats {

    @Inject
    private StatsDAO statsDAO;

    @Getter
    @Setter
    private Stat statToCreate = new Stat();

    private List<Stat> allStats;
    @PostConstruct
    public void init(){
        loadStats();
    }

    public void loadStats() {
        this.allStats = statsDAO.loadAll();
    }

    public List<Stat> getAllStats(){
        return allStats;
    }

    @Transactional
    public String createStat(){
        this.statsDAO.persist(statToCreate);
        return "success";
    }
}