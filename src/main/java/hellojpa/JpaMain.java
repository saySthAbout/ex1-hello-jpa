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

            //저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team); // 영속 상태가 되려면 무조건 PK값이 세팅이 되고 나서 영속상태가 된다.

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team); //JPA가 알아서 team에서 PK를 꺼내서 FK로 사용한다.
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers(); //양방향 연관관계

            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}