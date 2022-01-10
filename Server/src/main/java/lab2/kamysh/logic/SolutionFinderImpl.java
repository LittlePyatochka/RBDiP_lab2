package lab2.kamysh.logic;

import lab2.kamysh.entity.SessionFactoryBuilder;
import lab2.kamysh.entity.Solution;
import lab2.kamysh.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.rmi.RemoteException;


public class SolutionFinderImpl implements SolutionFinder {

    @Override
    public String findSolution(Integer index, String login) throws RemoteException {
        User user = UserHandler.getUserByLogin(login);
        Session session = openSession();
        CriteriaBuilder cbCount = session.getCriteriaBuilder();
        CriteriaQuery<Solution> criteriaCount = cbCount.createQuery(Solution.class);
        Root<Solution> rootCount = criteriaCount.from(Solution.class);
        criteriaCount.select(rootCount);
        criteriaCount.where(cbCount.equal(rootCount.get("user"), user.getId()));
        Query queryCount = session.createQuery(criteriaCount);
        queryCount.setFirstResult(index);
        Solution result = (Solution) queryCount.getSingleResult();
        String resultString = result.getSolution();
        session.close();
//        System.out.println(result);
        return resultString;
    }

    @Override
    public Long getCountSolutions(String login) throws RemoteException {
        User user = UserHandler.getUserByLogin(login);
        Session session = openSession();
        CriteriaBuilder cbCount = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaCount = cbCount.createQuery(Long.class);
        Root<Solution> rootCount = criteriaCount.from(Solution.class);
        criteriaCount.select(cbCount.count(rootCount));
        criteriaCount.where(cbCount.equal(rootCount.get("user"), user.getId()));
        Query queryCount = session.createQuery(criteriaCount);
        Long result = (Long) queryCount.getSingleResult();
        session.close();
        return result;
    }

    private static Session openSession() {
        SessionFactory sessionFactory = SessionFactoryBuilder.getSessionFactory();
        return sessionFactory.openSession();
    }

}
