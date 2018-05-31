package rpc1.gui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

@SpringUI(path="/categories")
public class Categories extends UI{

	@Override
	protected void init(VaadinRequest request) {
		setContent(new Button("Click Me!!!"));
	}

}
