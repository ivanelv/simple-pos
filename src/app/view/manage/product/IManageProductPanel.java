package app.view.manage.product;

import java.util.ArrayList;

import javax.swing.JPanel;

import app.view.custom_component.MyImageButton;

public interface IManageProductPanel {
	MyImageButton getAddButton();
	
	JPanel getMainPanel();
	
	void refreshData();
}
