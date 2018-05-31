package rpc1.gui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI(path="/signup")
public class SignUp extends UI {
	
	private VerticalLayout layoutV;
	private HorizontalLayout layoutH1;
	private HorizontalLayout layoutH2;
	private HorizontalLayout layoutH3;
	private HorizontalLayout layoutH4;
	private Label header;
	private Label userLabel;
	private Label passLabel;
	private TextField userField;
	private TextField passField;
	private Button confirm;
	
	@Override
	protected void init(VaadinRequest request) {
		initHeader();
		initUsernameField();
		initPasswordField();
		initConfirmButton();
		initLayout();
		setContent(layoutV);
	}

	private void initHeader() {
		header = new Label("Sign Up");
		layoutH1 = new HorizontalLayout();
		header.addStyleName(ValoTheme.LABEL_H1);
		layoutH1.addComponent(header);
	}
	
	private void initUsernameField() {
		userLabel = new Label("Username: ", ContentMode.TEXT);
		userField = new TextField();
		layoutH2 = new HorizontalLayout();
		layoutH2.addComponent(userLabel);
		layoutH2.addComponent(userField);
	}
	
	private void initPasswordField() {
		passLabel = new Label("Password: ", ContentMode.TEXT);
		passField = new TextField();
		layoutH3 = new HorizontalLayout();
		layoutH3.addComponent(passLabel);
		layoutH3.addComponent(passField);
	}
	
	private void initConfirmButton() {
		layoutH4 = new HorizontalLayout();
		confirm = new Button("Confirm"); // TODO: Create new user here
		layoutH4.addComponent(confirm);
	}
	
	private void initLayout() {
		layoutV = new VerticalLayout();
		layoutV.addComponent(layoutH1);
		layoutV.addComponent(layoutH2);
		layoutV.addComponent(layoutH3);
		layoutV.addComponent(layoutH4);
		layoutV.setComponentAlignment(layoutH1, Alignment.TOP_CENTER);
		layoutV.setComponentAlignment(layoutH2, Alignment.TOP_CENTER);
		layoutV.setComponentAlignment(layoutH3, Alignment.TOP_CENTER);
		layoutV.setComponentAlignment(layoutH4, Alignment.TOP_CENTER);
	}
	
}
