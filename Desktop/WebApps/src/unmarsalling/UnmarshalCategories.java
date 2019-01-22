package unmarsalling;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import ConnectionFactory.CategoriesDAO;
import ConnectionFactory.CategoriesDAOImpl;
import model.Categories;

public class UnmarshalCategories {

	private Part file;

	public void UnmarshalXml() throws Exception {
		JAXBContext context = JAXBContext.newInstance(Items.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Items items = (Items) unmarshaller.unmarshal(file.getInputStream());

		List<Item> itemList = items.getItems();
		List<String> listCategory = new ArrayList<String>();

		if (!itemList.isEmpty()) {
			for (Object object : itemList) {
				Item item = (Item) object;

				listCategory = item.getCategory();
				int i = 0;
				String nameParent = "";
				for (Object obj1 : listCategory) {
					String category = (String) obj1;
					if (i == 0) {
						
						CategoriesDAO categoriesDAO = new CategoriesDAOImpl();
						if (categoriesDAO.findCategories(category) == 1){
							Categories categories = new Categories();
							categories.setGrade(i + 1);
							categories.setName(category);
							nameParent = categories.getName();
							i++;
							categoriesDAO.createCategories(categories);
						}
						
					} else {
						CategoriesDAO categoriesDAO = new CategoriesDAOImpl();
						if (categoriesDAO.findCategories(category) == 1){
							Categories categories = new Categories();
							categories.setGrade(i + 1);
							categories.setName(category);
							categories.setParent(nameParent);
							nameParent = categories.getName();
							i++;
							categoriesDAO.createCategories(categories);
						}
						
					}
				}

			}
		}

	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

}
