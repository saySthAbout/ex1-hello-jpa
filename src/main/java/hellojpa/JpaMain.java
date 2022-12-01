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
//            team.getMembers().add(member);
            em.persist(team); // 영속 상태가 되려면 무조건 PK값이 세팅이 되고 나서 영속상태가 된다.

            Member member = new Member();
            member.setUsername("member1");

            em.persist(member);

            team.addMember(member);

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); //1차 캐시에 있다.
            List<Member> members = findTeam.getMembers();

            System.out.println("============");
            System.out.println("members = " + findTeam);
            System.out.println("============");

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}