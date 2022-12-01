package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team") //mappedBy는 뭐냐면 1대N 매핑에서 반대편 사이트의 필드를 적어주는 것이다. 즉, 읽기 전용
    private List<Member> members = new ArrayList<>(); //관례인데 이렇게 하면 add할때 null poionter가 안 뜬다.

    public void addMember(Member member) {
        member.setTeam(this);
        members.add(member);
    }
    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", members=" + members +
                '}';
    }
}
