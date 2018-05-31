package rpc1.gui;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Link;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
public class HomePage extends UI {
	
	VerticalLayout layoutV;
	MenuBar menuHeader;
	Button buttonBrowse;
	Button buttonCategories;
	Button buttonSignUp;
	Button buttonLogIn;
	
	@Override
	protected void init(VaadinRequest request) {
		initMenuButtons();
		initMenuBar();
		initLayout();
		setContent(layoutV);
	}
	
	private void initMenuBar() {
		menuHeader = new MenuBar();
		menuHeader.addItem(buttonBrowse.getCaption(), new Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				buttonBrowse.click();
			}
		});
		menuHeader.addItem(buttonCategories.getCaption(), new Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				buttonCategories.click();
			}
		});
		menuHeader.addItem(buttonSignUp.getCaption(), new Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				buttonSignUp.click();
			}
		});
		menuHeader.addItem(buttonLogIn.getCaption(), new Command() {
			@Override
			public void menuSelected(MenuItem selectedItem) {
				buttonLogIn.click();
			}
		});	
	}
	
	private void initMenuButtons() {
		buttonBrowse = new Button("Browse", event -> {
            getPage().setLocation("http://localhost:8080/items");
            getSession().close();
        });
		buttonCategories = new Button("Categories", event -> {
            getPage().setLocation("http://localhost:8080/categories");
            getSession().close();
        });
		buttonSignUp = new Button("Sign Up", event -> {
            getPage().setLocation("http://localhost:8080/signup");
            getSession().close();
        });
		buttonLogIn = new Button("Log In", event -> {
            getPage().setLocation("http://localhost:8080/login");
            getSession().close();
        });
		buttonBrowse.addStyleName(ValoTheme.MENU_ITEM);
		buttonCategories.addStyleName(ValoTheme.MENU_ITEM);
		buttonSignUp.addStyleName(ValoTheme.MENU_ITEM);
		buttonLogIn.addStyleName(ValoTheme.MENU_ITEM);
	}
	
	private void initLayout() {
		layoutV = new VerticalLayout();
		layoutV.addComponent(menuHeader);
		layoutV.setComponentAlignment(menuHeader ,Alignment.TOP_CENTER);
	}

}
