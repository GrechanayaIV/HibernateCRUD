package entity;

import dao.IdentifiedTable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "developers")
public class Developers implements IdentifiedTable<Integer> {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "salary")
    private int salary;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "developers_skills",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skills> skills = new HashSet<>();

    @ManyToMany(mappedBy = "developers", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Projects> projects = new HashSet<>();

    public Developers() {
    }

    public Developers(Integer id, String fname, String lname, int salary) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.salary = salary;
    }

    public void setSkills(Set<Skills> skills) {
        this.skills = skills;
    }

    public void addSkills(Skills skill) {
        this.skills.add(skill);
        skill.getDevelopers().add(this);
    }

    /**
     * public void removeSkills(Skills skill){
     * this.skills.remove(skill);
     * skill.getDevelopers().remove(this);
     * }
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Set<Skills> getSkills() {
        return skills;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Set<Projects> getProjects() {
        return projects;
    }

    public void setProjects(Set<Projects> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Developers)) return false;
        return id != null && id.equals(((Developers) obj).id);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (id ^ (id >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Developers:" +
                "\nid = " + id +
                ",\nfname = " + fname +
                ",\nlname = " + lname +
                ",\nsalary = " + salary;
    }
}

