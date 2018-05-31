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

@SpringUI(path="/login")
public class LoginPage extends UI {

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
	private Button submit;
	
	public LoginPage(){
		layoutV = new VerticalLayout();
		layoutH1 = new HorizontalLayout();
		layoutH2 = new HorizontalLayout();
		layoutH3 = new HorizontalLayout();
		layoutH4 = new HorizontalLayout();
		header = new Label("Login");
		header.addStyleName(ValoTheme.LABEL_H1);
		userLabel = new Label("Username: ", ContentMode.TEXT);
		passLabel = new Label("Password: ", ContentMode.TEXT);
		userField = new TextField();
		passField = new TextField();
		submit = new Button("Submit"); // TODO: Create new user here
		layoutH1.addComponent(header);
		layoutH2.addComponent(userLabel);
		layoutH2.addComponent(userField);
		layoutH3.addComponent(passLabel);
		layoutH3.addComponent(passField);
		layoutH4.addComponent(submit);
		layoutV.addComponent(layoutH1);
		layoutV.addComponent(layoutH2);
		layoutV.addComponent(layoutH3);
		layoutV.addComponent(layoutH4);
		layoutV.setComponentAlignment(layoutH1, Alignment.TOP_CENTER);
		layoutV.setComponentAlignment(layoutH2, Alignment.TOP_CENTER);
		layoutV.setComponentAlignment(layoutH3, Alignment.TOP_CENTER);
		layoutV.setComponentAlignment(layoutH4, Alignment.TOP_CENTER);
	}
	
	@Override
	protected void init(VaadinRequest request) {
		setContent(layoutV);
	}
}
