package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            //영속
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

            em.clear();

            Member member2 = em.find(Member.class, 150L);

            System.out.println("====================");
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close(); // EntityManager가 내부적으로 트랜잭션을 물고 있기 때문에 꼭 닫아줘야 한다.
        }
        emf.close();
    }
}