package unmarsalling;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Items")
public class Items {
	
	private List<Item> item;

	@XmlElement(name="Item")
	public List<Item> getItems() {
		if (null == item) {
			item = new ArrayList<Item>();
		}
		return item;
	}

	
	public void setItems(List<Item> item) {
		this.item= item;
	}



	@Override
	public String toString() {
		return "Items [\n"
				+ (item != null ?   item + "," :"");
	}
	
	
	
}
