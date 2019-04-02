package usecases.cdi.jpa;

import javax.transaction.Transactional;

public interface ICreateStat {
    @Transactional
    String createStat();
}
