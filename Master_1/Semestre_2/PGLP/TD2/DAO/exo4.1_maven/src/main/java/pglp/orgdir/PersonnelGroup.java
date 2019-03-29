package pglp.orgdir;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonnelGroup extends OrganizationElement {
    private String name;
    private List<OrganizationElement> personnels;

    public PersonnelGroup(String name) {
        this.name = name;
        personnels = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public void add(OrganizationElement element) {
        personnels.add(element);
    }

    public boolean contains(OrganizationElement element) {
        return personnels.contains(element);
    }

    @Override
    public String toString() {
        return "PersonnelGroup{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    protected void addSubElementsDFS(List<OrganizationElement> list) {
        for(OrganizationElement element : personnels) {
            list.add(element);
            element.addSubElementsDFS(list);
        }
    }

    @Override
    protected void addSubElementsBFS(List<OrganizationElement> list) {
        for(OrganizationElement element : personnels) {
            list.add(element);
        }
        for(OrganizationElement element : personnels) {
            element.addSubElementsBFS(list);
        }
    }

    @Override
    public String getDescription() {
        return name + " ["
                + personnels.stream()
                    .map(OrganizationElement::getDescription)
                    .collect(Collectors.joining(", "))
                + "]";
    }
}
