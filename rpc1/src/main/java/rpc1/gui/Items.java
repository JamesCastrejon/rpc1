package rpc1.gui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

@SpringUI(path="/items")
public class Items extends UI {

	@Override
	protected void init(VaadinRequest request) {
		setContent(new Button("Click Me!!!"));
	}
}
