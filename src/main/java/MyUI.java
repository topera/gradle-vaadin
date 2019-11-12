import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;

@Title("Hello")
@Theme("valo")
public class MyUI extends UI {

    private int clickCounter = 0;
    private Label clickCounterLabel;

    @WebServlet(value="/*", asyncSupported=true)
    @VaadinServletConfiguration(productionMode=false, ui=MyUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        layout.addComponent(new Label("Hello World!"));
        layout.addComponent(createButton());

        clickCounterLabel = new Label("Clicks: 0");
        layout.addComponent(clickCounterLabel);
    }

    private Button createButton() {
        Button button = new Button("Click Me");
        button.addClickListener((Button.ClickListener) event -> {
            clickCounter++;
            clickCounterLabel.setValue("Clicks: " + clickCounter);
            Notification.show("Thank you for clicking.");
        });
        return button;
    }

}