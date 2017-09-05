package entity;

import dao.IdentifiedTable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "projects")
public class Projects implements IdentifiedTable<Integer> {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "cost")
    private int cost;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "project_developers",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id"))
    private Set<Developers> developers = new HashSet<Developers>();

    @ManyToMany(mappedBy = "projects",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Customers> customers = new HashSet<>();
    @ManyToMany(mappedBy = "projects",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Companies> companies = new HashSet<>();
    public Projects() {
    }

    public Projects(Integer id, String title, int cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public String getTitle() {
        return title;
    }

    public Set<Developers> getDevelopers() { return developers; }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDevelopers(Set<Developers> developers) {
        this.developers = developers;
    }

    public void addDevelopers( Developers developer){
        this.developers.add(developer);
        developer.getProjects().add(this);
    }

    public Set<Customers> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customers> customers) {
        this.customers = customers;
    }

    public Set<Companies> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Companies> companies) {
        this.companies = companies;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Developers)) return false;
        return (id != null) && id.equals(((Projects) obj).id);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + id^prime;
        return result;
    }

    @Override
    public String toString() {
        return "Projects:" +
                "\nid = " + this.id + "," +
                "\ntitle = " + this.title + "," +
                "\ncost = " + this.cost;
    }
}
