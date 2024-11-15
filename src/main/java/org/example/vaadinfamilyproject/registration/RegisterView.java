package org.example.vaadinfamilyproject.registration;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.example.vaadinfamilyproject.domain.Registration;

@StyleSheet("frontend://src/styles.css")
@Route
public class RegisterView extends VerticalLayout {

    private transient RegistrationService service;

    private Binder<Registration> binder = new BeanValidationBinder<>(Registration.class);

    private TextField name = new TextField("Name");
    private EmailField email = new EmailField("Email");

    public RegisterView(RegistrationService service) {
        this.service = service;

        // Build the layout
        H1 heading = new H1("Register!");
        Button submit = new Button("Register!");
        setDefaultHorizontalComponentAlignment(FlexLayout.Alignment.CENTER);
        RouterLink listOrders = new RouterLink("View registrations", ListRegistrationsView.class);
        add(
                heading,
                name,
                email,
                submit,
                listOrders
        );

        // configure components

        submit.addClickListener(e -> {
            submitOrder();
            String msg = String.format(
                    "Thank you %s, your registration!",
                    binder.getBean().getName());
            Notification.show(msg, 3000, Notification.Position.MIDDLE);
            init();
        });


        // Add keyboard shortcut
        submit.addClickShortcut(Key.ENTER);

        // Bind fields from this UI class to domain object using naming convention
        binder.bindInstanceFields(this);
        // enable save button only if the bean is valid
        binder.addStatusChangeListener(e -> submit.setEnabled(binder.isValid()));

        init();
    }

    private void submitOrder() {
        service.register(binder.getBean());
    }

    private void init() {
        binder.setBean(new Registration());
    }

}