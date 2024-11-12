package org.example.vaadinfamilyproject.registration;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.example.vaadinfamilyproject.domain.Registration;
import org.example.vaadinfamilyproject.domain.RegistrationRepository;

@Route
public class ListRegistrationsView extends VerticalLayout {

    private final transient RegistrationRepository repo;
    private final Grid<Registration> registrations = new Grid<>(Registration.class);


    public ListRegistrationsView(RegistrationRepository repo) {
        this.repo = repo;
        // Build the layout
        H1 heading = new H1("List of submitted registrations");
        Button update = new Button(VaadinIcon.REFRESH.create());
        RouterLink orderView = new RouterLink("Submit new registration", RegisterView.class);
        add(heading, update, registrations, orderView);

        registrations.setColumns("name", "email");
        registrations.addComponentColumn(order -> {
            Button deleteBtn = new Button(VaadinIcon.TRASH.create());
            deleteBtn.addClickListener(e -> {
                repo.delete(order);
                listOrders();
            });
            return deleteBtn;
        });
        listOrders();

        update.addClickListener(e -> listOrders());

    }

    public void listOrders() {
        registrations.setItems(repo.findAll());
    }

}