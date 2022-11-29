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
            //비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            //영속
            System.out.println("=== BEFORE ===");
            em.persist(member);
            em.detach(member);
            System.out.println("=== AFTER ===");

            tx.commit(); // 이 시점에 query가 실행된다.
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close(); // EntityManager가 내부적으로 트랜잭션을 물고 있기 때문에 꼭 닫아줘야 한다.
        }
        emf.close();
    }
}